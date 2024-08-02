package com.example.connect3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.connect3.databinding.ActivityGameBinding;

public class GameActivity extends AppCompatActivity {

    private ActivityGameBinding binding;
    private GameViewModel viewModel;

    private int[][] gameState = new int[3][3];
    String player1Name;
    String player2Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // Initialize ViewModel
        viewModel = new ViewModelProvider(this).get(GameViewModel.class);

        // Get player names from intent
        player1Name = getIntent().getStringExtra("PLAYER1_NAME");
        player2Name = getIntent().getStringExtra("PLAYER2_NAME");

        // Set player names in ViewModel
        binding.player1Label.setText("Player 1: " + player1Name);
        binding.player2Label.setText("Player 2: " + player2Name);

        // Initialize game state
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gameState[i][j] = 0;
            }
        }
    }

    // Handle cell click
    public void onCellClick(View view) {
        ImageView selectedCell = (ImageView) view;
        String[] position = selectedCell.getTag().toString().split(",");
        int row = Integer.parseInt(position[0]);
        int col = Integer.parseInt(position[1]);

        if (viewModel.isMoveValid(row, col)) {
            applyMove(selectedCell, row, col);
            if (viewModel.checkWin()) {
                endGame(viewModel.getCurrentPlayerName());
            } else if (viewModel.isBoardFull()) {
                endGame("Draw");
            } else {
                viewModel.switchTurn();
            }
        }
    }

    // Apply move and animation
    private void applyMove(ImageView cell, int row, int col) {
        Animation complexAnimation = AnimationUtils.loadAnimation(this, R.anim.animation);
        int drawableId = viewModel.isPlayerOneTurn() ? R.drawable.x_icon : R.drawable.o_icon;
        cell.setImageResource(drawableId);
        cell.startAnimation(complexAnimation);
        viewModel.updateBoard(row, col);
    }

    // End game and navigate to EndGameActivity
    private void endGame(String winnerName) {
        Intent intent = new Intent(GameActivity.this, EndGameActivity.class);
        if(winnerName.equalsIgnoreCase("x")) {
            intent.putExtra("WINNER_NAME", player1Name );
        } else if (winnerName.equalsIgnoreCase("o")){
            intent.putExtra("WINNER_NAME", player2Name );
        } else {
            intent.putExtra("WINNER_NAME", winnerName );
        }
        startActivity(intent);
        finish();
    }
}
