package com.dip.quiz;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.dip.quiz.Main.showAlert;

public class mainController {

    @FXML
    private Button submit;
    private Timeline timeline;
    @FXML
    private RadioButton A;
    @FXML
    private RadioButton B;
    @FXML
    private RadioButton C;
    @FXML
    private RadioButton D;
    @FXML
    private  Label timer;
    @FXML
    private Label questionLabel;
    @FXML
    private Label secondScorer;
    @FXML
    private Label thirdScorer;
    @FXML
    private Label topScorer;
    private  String playerName;
    private int  playerId;
    private String selectedCategory;
    private  int timeRemaining = 30;
    private  int score;

    public int  getScore(){
        return  score;
    }

    public void setPlayerDetails(String name, int id){
        this.playerName = name;
        this.playerId = id;
    }

    public void resetTimer(){
        timeRemaining = 30;
    }

    private void startTimer() {
        if (timeline != null) {
            timeline.stop();
        }
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            timeRemaining--;
             score = 30  - timeRemaining;
            timer.setText(String.valueOf(timeRemaining));
            if (timeRemaining <= 0) {
                resetTimer();
                currentQuestionIndex++;
                if (currentQuestionIndex < questions.size()) {
                    displayQuestion(questions.get(currentQuestionIndex));
                }
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }



    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private ToggleGroup toggleGroup;




    public void initData(String category) {
        this.selectedCategory = category;
    }
    @FXML
    public void initialize() throws SQLException {
        Database db = new Database();
        resetScore();
        try {
            questions = db.questionFromCategory(Main.getCategory());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        toggleGroup = new ToggleGroup();
        A.setToggleGroup(toggleGroup);
        B.setToggleGroup(toggleGroup);
        C.setToggleGroup(toggleGroup);
        D.setToggleGroup(toggleGroup);

        try {
            if (questions != null && !questions.isEmpty()) {
                startTimer();
                displayQuestion(questions.get(currentQuestionIndex));
            } else {
                showAlert(Alert.AlertType.INFORMATION, "GAME OVER", "Well done!!");
                setOptionsVisibility(false);
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.INFORMATION, "Initialization Error", "An error occurred during initialization: " + e.getMessage());
            e.printStackTrace();
        }

        displayTopScorers();
    }

    /**
     * Helps to display the questions from the database.
     * @param question Question from the database.
     */
    private void displayQuestion(Question question) {
        List<String> shuffledAnswers = shuffleAnswers(question);
        questionLabel.setText(question.getQuestion());
        A.setText(shuffledAnswers.get(0));
        B.setText(shuffledAnswers.get(1));
        C.setText(shuffledAnswers.get(2));
        D.setText(shuffledAnswers.get(3));
    }


    private void setOptionsVisibility(boolean visible) {
        A.setVisible(visible);
        B.setVisible(visible);
        C.setVisible(visible);
        D.setVisible(visible);
        submit.setVisible(visible);
        timer.setVisible(visible);

    }

    @FXML
    private void handleNextQuestion() {
        if (currentQuestionIndex + 1 < questions.size()) {
            currentQuestionIndex++;
            resetTimer();
            displayQuestion(questions.get(currentQuestionIndex));
        } else {
            questionLabel.setText("GAME OVER");
            setOptionsVisibility(false);

        }
    }

    @FXML
    private void handleSubmitAnswer() {
        Question currentQuestion = questions.get(currentQuestionIndex);
        RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();
        if (selectedRadioButton == null) {
            showAlert(Alert.AlertType.INFORMATION, "No selection", "Please select an option before submitting.");
            return;
        }
        String selectedAnswer = selectedRadioButton.getText();

        if (selectedAnswer.equals(currentQuestion.getTrueAnswer())) {
            showAlert(Alert.AlertType.INFORMATION, "Correct", "Your answer is correct!");
            updatePlayerScore();
            displayTopScorers();
        } else {
            showAlert(Alert.AlertType.INFORMATION, "Incorrect", "Your answer is incorrect. The correct answer is: " + currentQuestion.getTrueAnswer());
        }
        handleNextQuestion();
    }


    /**
     * The function to update the score in the Database after every correct user have given.
     */
    protected void updatePlayerScore() {
        int score = getScore();
        String sql = "UPDATE players SET score = score + ? WHERE player_id = ?";

        try(Connection con = DriverManager.getConnection(loginController.JDBC_URL, loginController.JDBC_USER, loginController.JDBC_PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, score);        // Set the score increment
            pstmt.setInt(2, Main.getPlayerId());     // Set the player_id


            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Score updated successfully for player ID: "+ Main.getPlayerId() ); // Debug statement
            } else{
                System.out.println("Failed to update score for player ID: " ); // Debug statement
            }
        }catch(SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to update the score: " + e.getMessage());
        }
    }

    protected void resetScore(){
        String sql = "UPDATE players SET score = 0 WHERE player_id = ?";

        try (Connection con = DriverManager.getConnection(loginController.JDBC_URL, loginController.JDBC_USER, loginController.JDBC_PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1,Main.getPlayerId());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Score updated successfully for player ID: "+ Main.getPlayerId() ); // Debug statement
            } else {
                System.out.println("Failed to update score for player ID: " ); // Debug statement
            }
        } catch(SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to update the score: " + e.getMessage());
        }
    }

    /**
     *
     * @param question Question from the Database is the parameter here.
     * @return returns the shuffled answered answer from the database.
     */
    private static List<String> shuffleAnswers(Question question) {
        List<String> answers = new ArrayList<>();
        answers.add(question.getTrueAnswer());
        answers.add(question.getFakeAnswer1());
        answers.add(question.getFakeAnswer2());
        answers.add(question.getFakeAnswer3());

        Collections.shuffle(answers);
        return answers;
    }

    /**
     * Display the top three Scorers on the quiz display.
     */
    private void displayTopScorers() {
        Database db = new Database();
        List<player> topPlayers = db.getTopThreePlayers();

        if (topPlayers.size() > 0) {
            topScorer.setText( topPlayers.get(0).getPlayerName() + " - " + topPlayers.get(0).getScore() + " points");
        } else {
            topScorer.setText("1. No player");
        }

        if (topPlayers.size() > 1) {
            secondScorer.setText(topPlayers.get(1).getPlayerName() + " - " + topPlayers.get(1).getScore() + " points");
        } else {
            secondScorer.setText("2. No player");
        }

        if (topPlayers.size() > 2) {
            thirdScorer.setText(topPlayers.get(2).getPlayerName() + " - " + topPlayers.get(2).getScore() + " points");
        } else {
            thirdScorer.setText("3. No player");
        }
    }
}
