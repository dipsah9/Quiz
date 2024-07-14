package com.dip.quiz;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Main extends Application {

    private static String category;
    private static int playerId;
    private static String playerName;
    private static mainController mController = new mainController();

    public static void setCategory(String cat){
        category = cat;
    }

    public static   String getCategory(){
        return  category;
    }


    /**
        This is the function which helps to set the information of player and sends the information to the quiz window.
        @param id  Its the id of the current player
        @param name Name of the player.
     */

    public static void setPlayerInfo(int id, String name) {
        playerId = id;
        playerName = name;
    }

    /**
     * @return id of the player
     */
    public static int getPlayerId() {
        return playerId;
    }

    /**
     * @return name of the player
     */
    public static String getPlayerName() {
        return playerName;
    }
    public static void main(String[] args) {
        launch(args);
    }

    /**
     *
     * @param stage the default stage of the quiz app
     * @throws IOException if any problem in the given stage or its fxml file.
     */

    @Override
    public void start(Stage stage) throws IOException {
        //checkDatabaseConnection();
        showLoginWindow(stage);
    }


    /**
     * Login Window
     * @param stage
     */
    private void showLoginWindow(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dip/quiz/loginView.fxml"));
            Parent root = loader.load();

            String cssPath = "/com/dip/quiz/loginStyle.css";
            String css = Main.class.getResource(cssPath).toExternalForm();
            System.out.println("Loading CSS from: " + css); // Debugging: Verify the path

            Scene scene = new Scene(root);
           // String css = getClass().getResource("/com/dip/quiz/loginStyle.css").toExternalForm();
            scene.getStylesheets().add(css);

            stage.setTitle("Login");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Helps to show the main quiz Window
     */
    public static void showMainQuizWindow(Stage stage, String selectedCategory) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/dip/quiz/mainView.fxml"));
            Parent root = fxmlLoader.load();

            mainController controller = fxmlLoader.getController();
            controller.initData(selectedCategory); // Pass the selected category to the controller

            String cssPath = "/com/dip/quiz/style.css";
            String css = Main.class.getResource(cssPath).toExternalForm();
            System.out.println("Loading CSS from: " + css); // Debugging: Verify the path
            Scene scene = new Scene(root, 771, 500);
            scene.getStylesheets().add(css);

            stage.setTitle("Quiz");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /**
     * Helps to show the category selection Window
     */
    public static void showCategorySelectionWindow(Stage currentStage) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/com/dip/quiz/category.fxml"));
            Parent root = loader.load();

            String cssPath = "/com/dip/quiz/decor/catStyle.css";
            String css = Main.class.getResource(cssPath).toExternalForm();
            System.out.println("Loading CSS from: " + css); // Debugging: Verify the path

            Scene scene = new Scene(root,255,300);
            scene.getStylesheets().add(css);

            CategorySelector controller = loader.getController();
            Stage stage = new Stage();
            stage.setTitle("Category Selection");
            stage.setScene(scene);
            stage.show();

            currentStage.close();

            controller.setOnCategorySelectedListener(category -> {
                showMainQuizWindow(stage, category);
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /**
     * Checks if the database connection is established or not.
     */
    private void checkDatabaseConnection() {
        try {
            Connection connection = Database.getConnection();
            if (connection != null) {
                showAlert(AlertType.INFORMATION, "Database Connection", "Connection established successfully!");
                connection.close(); // Close the connection after checking
            } else {
                showAlert(AlertType.ERROR, "Database Connection", "Failed to establish connection!");
            }
        } catch (Exception e) {
            showAlert(AlertType.ERROR, "Database Connection", "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }


    /**
     * This is to give the alert message.
     * @param alertType type of alert
     * @param title title of the alert window
     * @param message messages that need to be displayed on the alert screen.
     */
    public static void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}