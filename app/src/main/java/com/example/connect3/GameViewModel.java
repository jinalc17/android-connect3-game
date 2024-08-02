package com.example.connect3;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GameViewModel extends ViewModel {

    private static final int SIZE = 3;
    private static final String X = "X";
    private static final String O = "O";

    private final MutableLiveData<String[][]> gameBoard;
    private final MutableLiveData<String> currentPlayer;
    private final MutableLiveData<String> winner;
    private final MutableLiveData<Boolean> isGameOver;

    private String player1Name;
    private String player2Name;

    public GameViewModel() {
        gameBoard = new MutableLiveData<>(new String[SIZE][SIZE]);
        currentPlayer = new MutableLiveData<>(X);
        winner = new MutableLiveData<>(null);
        isGameOver = new MutableLiveData<>(false);
    }

    public void setPlayerNames(String player1, String player2) {
        this.player1Name = player1;
        this.player2Name = player2;
        currentPlayer.setValue(player1);
    }

    public boolean isMoveValid(int row, int col) {
        return !Boolean.TRUE.equals(isGameOver.getValue()) && gameBoard.getValue()[row][col] == null;
    }

    public String getCurrentPlayerName() {
        return currentPlayer.getValue();
    }

    public boolean checkWin() {
        return isWinningMove();
    }

    public boolean isPlayerOneTurn() {
        return X.equals(currentPlayer.getValue());
    }

    public void updateBoard(int row, int col) {
        String[][] board = gameBoard.getValue();
        board[row][col] = currentPlayer.getValue();
        gameBoard.setValue(board);
    }

    public void switchTurn() {
        togglePlayer();
    }

    private void togglePlayer() {
        currentPlayer.setValue(currentPlayer.getValue().equals(X) ? O : X);
    }

    private boolean isWinningMove() {
        String[][] board = gameBoard.getValue();
        String player = currentPlayer.getValue();

        // Check rows and columns
        for (int i = 0; i < SIZE; i++) {
            if ((player.equals(board[i][0]) && player.equals(board[i][1]) && player.equals(board[i][2])) ||
                    (player.equals(board[0][i]) && player.equals(board[1][i]) && player.equals(board[2][i]))) {
                return true;
            }
        }

        // Check diagonals
        return (player.equals(board[0][0]) && player.equals(board[1][1]) && player.equals(board[2][2])) ||
                (player.equals(board[0][2]) && player.equals(board[1][1]) && player.equals(board[2][0]));
    }

    public boolean isBoardFull() {
        for (String[] row : gameBoard.getValue()) {
            for (String cell : row) {
                if (cell == null) {
                    return false;
                }
            }
        }
        return true;
    }

}