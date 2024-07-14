package com.dip.quiz;

public class player {

        private String playerName;
        private int score;

        public player(String playerName, int score) {
            this.playerName = playerName;
            this.score = score;
        }

        public String getPlayerName() {
            return playerName;
        }

        public int getScore() {
            return score;
        }

}
