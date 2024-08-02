package com.example.connect3;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.connect3.databinding.ActivityEndGameBinding;

public class EndGameActivity extends AppCompatActivity {

    ActivityEndGameBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEndGameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String winnerName = getIntent().getStringExtra("WINNER_NAME");
        updateWinnerMessage(winnerName);

        binding.playAgainButton.setOnClickListener(v -> navigateToWelcome());

        binding.quitButton.setOnClickListener(v -> navigateToWelcome());
    }

    private void updateWinnerMessage(String winnerName) {
        if ("Draw".equals(winnerName)) {
            binding.winnerMessage.setText("The game is a draw");
        } else {
            binding.winnerMessage.setText(String.format(winnerName+ ", Congrats! You win!"));
        }
    }

    private void navigateToWelcome() {
        Intent intent = new Intent(EndGameActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}