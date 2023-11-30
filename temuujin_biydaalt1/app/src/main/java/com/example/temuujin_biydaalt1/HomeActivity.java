package com.example.temuujin_biydaalt1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int SIZE = 3;
    private Button[][] buttons;
    private boolean playerXTurn = true;
    private int movesCount = 0;
    private boolean gameEnded = false;
    private TextView resultTextView;
    private EditText playerXEditText, playerOEditText;
    private int playerXWins = 0;
    private int playerOWins = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        buttons = new Button[SIZE][SIZE];
        resultTextView = findViewById(R.id.resultTextView);
        playerXEditText = findViewById(R.id.playerXEditText);
        playerOEditText = findViewById(R.id.playerOEditText);

        GridLayout gridLayout = findViewById(R.id.gridLayout);

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                buttons[i][j] = new Button(this);
                buttons[i][j].setTextSize(24);
                buttons[i][j].setOnClickListener(this);
                gridLayout.addView(buttons[i][j]);
            }
        }

        Button resetButton = findViewById(R.id.resetButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });

        Button showResultsButton = findViewById(R.id.showResultsButton);
        showResultsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showResults();
            }
        });

        resetGame();
    }

    @Override
    public void onClick(View v) {
        Button clickedButton = (Button) v;
        if (!clickedButton.getText().toString().equals("") || gameEnded) {
            return;
        }

        if (playerXTurn) {
            clickedButton.setText("X");
        } else {
            clickedButton.setText("O");
        }

        movesCount++;

        if (checkForWin()) {
            if (playerXTurn) {
                updateResult(playerXEditText.getText().toString() + " тоглогч ялаалаа");
                playerXWins++;
            } else {
                updateResult(playerOEditText.getText().toString() + " тоглогч яллаа");
                playerOWins++;
            }
            gameEnded = true;
        } else if (movesCount == SIZE * SIZE) {
            updateResult("Тэнцлээ!");
            gameEnded = true;
        } else {
            playerXTurn = !playerXTurn;
            updateTurnTextView();
        }
    }

    private void resetGame() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                buttons[i][j].setText("");
            }
        }

        resultTextView.setText("");
        playerXTurn = true;
        movesCount = 0;
        gameEnded = false;

        // Set default names if not provided
        if (playerXEditText.getText().toString().isEmpty()) {
            playerXEditText.setText("Player X");
        }
        if (playerOEditText.getText().toString().isEmpty()) {
            playerOEditText.setText("Player O");
        }

        updateTurnTextView();
    }

    private void updateResult(String result) {
        resultTextView.setText(result);
    }

    private boolean checkForWin() {
        // Check rows, columns, and diagonals for a win
        for (int i = 0; i < SIZE; i++) {
            if (checkRowCol(buttons[i][0], buttons[i][1], buttons[i][2]) ||
                    checkRowCol(buttons[0][i], buttons[1][i], buttons[2][i])) {
                return true;
            }
        }

        // Check diagonals
        return checkRowCol(buttons[0][0], buttons[1][1], buttons[2][2]) ||
                checkRowCol(buttons[0][2], buttons[1][1], buttons[2][0]);
    }

    private boolean checkRowCol(Button b1, Button b2, Button b3) {
        return b1.getText().equals(b2.getText()) && b2.getText().equals(b3.getText()) && !b1.getText().equals("");
    }

    private void updateTurnTextView() {
        if (playerXTurn) {
            resultTextView.setText(playerXEditText.getText().toString() + "'s turn");
        } else {
            resultTextView.setText(playerOEditText.getText().toString() + "'s turn");
        }
    }

    private void showResults() {
        // Show total wins for each player
        Toast.makeText(this, "Player X Wins: " + playerXWins + "\nPlayer O Wins: " + playerOWins, Toast.LENGTH_SHORT).show();
    }
}
