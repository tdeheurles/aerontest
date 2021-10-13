package com.tdeheurles.aerontest.cluster;

import java.util.Arrays;
import java.util.List;

public class Demo4GameLogic {
    private String winner = "";
    private final String[] squares = {
            "","","",
            "","","",
            "","",""
    };
    private String calculateWinner(String[] squares) {
        int[][] lines = {
                {0, 1, 2},
                {3, 4, 5},
                {6, 7, 8},
                {0, 3, 6},
                {1, 4, 7},
                {2, 5, 8},
                {0, 4, 8},
                {2, 4, 6}
        };

        for (int[] line : lines) {
            var a = line[0];
            var b = line[1];
            var c = line[2];
            if (!squares[a].equals("") && squares[a].equals(squares[b]) && squares[a].equals(squares[c])) {
                return squares[a];
            }
        }
        return "";
    }
    private boolean xIsNext = true;
    private String xOrO () {
        return xIsNext ? "X" : "O";
    }

    public void resetSquares() {
        for (var i = 0; i < 9 ; i++) {
            squares[i] = "";
        }
        winner = "";
    }
    public void calculateMove(int move) {
        if (!winner.equals("")) {
            ConsoleLog.main_3("Game is over, winner was " + winner + ", no move are available");
            return;
        }
        if (!squares[move].equals("")) {
            ConsoleLog.main_3("Move have already be executed by " + squares[move]);
            return;
        }
        squares[move] = xOrO();
        winner = calculateWinner(squares);
        xIsNext = !xIsNext;
    }
    public List<String> getFullState() {
        return Arrays.asList(squares.clone());
    }

    public boolean getXIsNext() {
        return xIsNext;
    }

    public String getWinner() {
        return winner;
    }
}
