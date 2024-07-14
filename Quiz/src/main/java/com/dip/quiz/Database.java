package com.dip.quiz;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/quiz_game";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Leibniz@143";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Error closing the connection: " + e.getMessage());
            }
        }
    }

    /**
     * Returns the list of the questions.
     * @param category The category user wants to play.
     * @return returns the selected questions by the user
     * @throws SQLException
     */
   public static List<Question> questionFromCategory( String category) throws SQLException{
       String sql = "SELECT question, true_answer, fake_answer_1, fake_answer_2, fake_answer_3 FROM questions WHERE category = ?";
       Connection connection = getConnection();
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

    /**
     * Gives the first three players who has scored a lot.
     * @return
     */

    public List<player> getTopThreePlayers() {
        List<player> topPlayers = new ArrayList<>();
        String sql = "SELECT player_name, score FROM players ORDER BY score DESC LIMIT 3";

        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String playerName = rs.getString("player_name");
                int score = rs.getInt("score");
                topPlayers.add(new player(playerName, score));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return topPlayers;
    }

}
