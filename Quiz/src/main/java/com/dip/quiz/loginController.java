package com.dip.quiz;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.dip.quiz.Main.showAlert;

public class loginController {

    protected static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/quiz_game";
    protected static final String JDBC_USER = "root";
    protected static final String JDBC_PASSWORD = "Leibniz@143";

    @FXML
    private TextField playerName;

    @FXML
    private PasswordField playerId;
    mainController controller = new mainController();



    @FXML
    private void handleLoginButton() throws Exception {
        String name = playerName.getText();
        int id;
        try {
            id = Integer.parseInt(playerId.getText());
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid player ID.");
            return;
        }
        Main.setPlayerInfo(id, name);

        if (checkLogin(name, id)) {
            System.out.println("playerId: " + id);
            Main.showCategorySelectionWindow((Stage) playerName.getScene().getWindow());
        } else {
            showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid player name or ID.");
        }
    }

    @FXML
    private void handleRegisterButton() {
        String name = playerName.getText();
        int id;
        try {
            id = Integer.parseInt(playerId.getText());
        } catch (NumberFormatException e) {
            Main.showAlert(Alert.AlertType.ERROR, "Registration Failed", "Invalid player ID.");
            return;
        }

        if (registerPlayer(name, id)) {
            Main.showAlert(Alert.AlertType.INFORMATION, "Registration Complete", "You are registered successfully.");
        } else {
            Main.showAlert(Alert.AlertType.ERROR, "Registration Failed", "Player ID already exists.");
        }
    }

    /**
     * Helps to register the new player.ww
     * @param playerName
     * @param playerId
     * @return
     */
    public static boolean registerPlayer(String playerName, int playerId) {
        String sql = "INSERT INTO players (player_id, player_name, score) VALUES (?, ?, 0)";

        try (Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, playerId);
            pstmt.setString(2, playerName);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * It checks if the players is already in the database, if yes allows to login.
     * @param playerName The name of the player
     * @param playerId The id of the player
     * @return
     */

    private boolean checkLogin(String playerName, int playerId) {
        String sql = "SELECT player_name FROM players WHERE player_id = ?";

        try (Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, playerId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String storedPlayerName = rs.getString("player_name");
                return storedPlayerName.equals(playerName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


}
