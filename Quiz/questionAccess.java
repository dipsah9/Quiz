
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


class Question{
    private String question;
    private String trueAnswer;
    private String fakeAnswer1;
    private String fakeAnswer2;
    private String fakeAnswer3;
    private int answerTime;  // answer time（sec）
    private double score;    // scoe

    public Question(String question, String trueAnswer, String fakeAnswer1,String fakeAnswer2,String fakeAnswer3){
        this.question = question;
        this.trueAnswer = trueAnswer;
        this.fakeAnswer1 = fakeAnswer1;
        this.fakeAnswer2 = fakeAnswer2;
        this.fakeAnswer3 = fakeAnswer3;
    }
    public String getQuestion(){
        return question;
    }
    public String getTrueAnswer(){
        return trueAnswer;
    }
    public String getFakeAnswer1(){
        return fakeAnswer1;
    }
    public String getFakeAnswer2(){
        return fakeAnswer2;
    }
    public String getFakeAnswer3(){
        return fakeAnswer3;
    }
    //get and set for answer time and score
    public int getAnswerTime() { 
        return answerTime; 
    }
    public void setAnswerTime(int answerTime) { 
        this.answerTime = answerTime; 
    }
    public double getScore() { 
        return score;
    }
    // Scoring based on answering time
    public void calculateScore() {
        this.score = 10 * 1.0 / (1 + this.answerTime); 
    }

}

public class questionAccess {

    //Change with own access data
    //(mysql) driver must be used
    private static final String DB_URL = "jdbc:mysql://localhost:3306/quizdb";
    private static final String USER = "root";
    private static final String PASS = "password";

    private static List<Question> questionFromCategory(Connection connection, String category) throws SQLException{
        String sql = "SELECT question, true_answer, fake_answer_1, fake_answer_2, fake_answer_3 FROM questions WHERE category = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, category);

        ResultSet rs = stmt.executeQuery();
        List<Question> questions = new ArrayList<>();

        while(rs.next()){
            String question = rs.getString("question");
            String trueAnswer = rs.getString("true_answer");
            String fakeAnswer1 = rs.getString("fake_answer_1");
            String fakeAnswer2 = rs.getString("fake_answer_2");
            String fakeAnswer3 = rs.getString("fake_answer_3");

            questions.add(new Question(question,trueAnswer,fakeAnswer1,fakeAnswer2,fakeAnswer3));
        }
        rs.close();
        stmt.close();

        return questions;
    }

    private static List<String> shuffleAnswers(Question question){
        List<String> answers = new ArrayList<>();
        answers.add(question.getTrueAnswer());
        answers.add(question.getFakeAnswer1());
        answers.add(question.getFakeAnswer2());
        answers.add(question.getFakeAnswer3());

        Collections.shuffle(answers);
        return answers;
    }

    public static boolean correctAnswer(Question question, String givenAnswer){
        return question.getTrueAnswer().equals(givenAnswer);
    }
    //Scanner inputs and consol outputs must be changed for gui actions
    public static void runQuiz(String category){
        try{
            Connection connection = DriverManager.getConnection(DB_URL,USER,PASS);
            List<Question> questions = questionFromCategory(connection, category);

            if(questions.isEmpty()){
                System.out.println("Category does not exist");
            } else{
                Scanner scanner = new Scanner(System.in);
                Collections.shuffle(questions);
                Integer count = 0;
                double totalScore = 0;  // New: Initialize the total score to 0
                for(Question question : questions){
                    List<String> shuffledAnswers = shuffleAnswers(question);

                    String answerA = shuffledAnswers.get(0);
                    String answerB = shuffledAnswers.get(1);
                    String answerC = shuffledAnswers.get(2);
                    String answerD = shuffledAnswers.get(3);

                    //Only for testing Purposes
                    System.out.println(question.getQuestion());
                    System.out.println("a:" + answerA);
                    System.out.println("b:" + answerB);
                    System.out.println("c:" + answerC);
                    System.out.println("d:" + answerD);
                    System.out.println("a/b/c/d?");
                    long startTime = System.currentTimeMillis(); // Record start time
                    String givenAnswer = scanner.nextLine();
                    long endTime = System.currentTimeMillis(); // Record end time

                    long duration = (endTime - startTime) / 1000; // Calculate answer time in seconds
                    question.setAnswerTime((int)duration); // Set the answer time in the Question object
                    question.calculateScore(); // Calculate the score based on answer time
                    String selectedAnswer = null;
                    switch(givenAnswer){
                        case"a":
                            selectedAnswer = answerA;
                            break;
                        case"b":
                            selectedAnswer = answerB;
                            break;
                        case"c":
                            selectedAnswer = answerC;
                            break;
                        case"d":
                            selectedAnswer = answerD;
                            break;
                        default:
                            selectedAnswer = ":(";
                    }
                    if(correctAnswer(question, selectedAnswer)){
                        System.out.println("correct answer");
                        count++;
                    }else{
                        System.out.println("wrong!");
                        System.out.println("Correct answer was: " + question.getTrueAnswer());
                        break;
                    }
                    totalScore += question.getScore(); // Add to totalScore
                }
                System.out.println("You answer/*  */d " + count + " Questions!");
                System.out.println("Total score: " + totalScore); // print total score
                scanner.close();
            }

            connection.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }


    //Only for testing Purposes
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("welche Kategorie?");
        String category = scanner.nextLine();
        runQuiz(category);
        scanner.close();
    }
}
