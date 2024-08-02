package com.example.connect3;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.connect3.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.startGameButton.setOnClickListener(v -> {
            String player1Name = binding.player1Name.getText().toString();
            String player2Name = binding.player2Name.getText().toString();

            Intent intent = new Intent(MainActivity.this, GameActivity.class);
            intent.putExtra("PLAYER1_NAME", player1Name);
            intent.putExtra("PLAYER2_NAME", player2Name);
            startActivity(intent);
        });
    }
}