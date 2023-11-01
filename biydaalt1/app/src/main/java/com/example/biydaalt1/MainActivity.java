package com.example.biydaalt1;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Button restart_btn;
    private ImageView scissors_btn;
    private ImageView paper_btn;
    private ImageView rock_btn;
    private ImageView user_move_img;
    private ImageView computer_move_img;
    private TextView winner_tv;
    private TextView player_score;
    private TextView computer_score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        restart_btn = findViewById(R.id.restart_btn);
        scissors_btn = findViewById(R.id.scissors_btn);
        paper_btn = findViewById(R.id.paper_btn);
        rock_btn = findViewById(R.id.rock_btn);
        user_move_img = findViewById(R.id.user_move_img);
        computer_move_img = findViewById(R.id.computer_move_img);
        winner_tv = findViewById(R.id.winner_tv);
        player_score = findViewById(R.id.player_score);
        computer_score = findViewById(R.id.computer_score);

        restart_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clear_score();
            }
        });

        scissors_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // set the image of the user move to scissors
                user_move_img.setImageResource(R.drawable.scissors_icons);

                // choose a random number between 1 to 3.
                int computer_move = new Random().nextInt(3) + 1;

                if (computer_move == 1) {
                    // set the image of the computer move to rock
                    computer_move_img.setImageResource(R.drawable.stone_icons);
                    winner_tv.setText("Компьютер яллаа");
                    int cscore = Integer.parseInt(computer_score.getText().toString()) + 1;
                    computer_score.setText(String.valueOf(cscore));
                } else if (computer_move == 2) {
                    // set the image of the computer move to paper
                    computer_move_img.setImageResource(R.drawable.paper_icon);
                    winner_tv.setText("Тоглолгч яллаа");
                    int pscore = Integer.parseInt(player_score.getText().toString()) + 1;
                    player_score.setText(String.valueOf(pscore));
                } else {
                    computer_move_img.setImageResource(R.drawable.scissors_icons);
                    winner_tv.setText("Тэнцсэн");
                }
            }
        });

        paper_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user_move_img.setImageResource(R.drawable.paper_icon);
                int computer_move = new Random().nextInt(3) + 1;

                if (computer_move == 1) {
                    computer_move_img.setImageResource(R.drawable.stone_icons);
                    winner_tv.setText("Тоглолгч яллаа");
                    int pscore = Integer.parseInt(player_score.getText().toString()) + 1;
                    player_score.setText(String.valueOf(pscore));
                } else if (computer_move == 2) {
                    computer_move_img.setImageResource(R.drawable.paper_icon);
                    winner_tv.setText("Тэнцсэн");
                } else {
                    computer_move_img.setImageResource(R.drawable.scissors_icons);
                    winner_tv.setText("Компьютер яллаа");
                    int cscore = Integer.parseInt(computer_score.getText().toString()) + 1;
                    computer_score.setText(String.valueOf(cscore));
                }
            }
        });

        rock_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user_move_img.setImageResource(R.drawable.stone_icons);
                int computer_move = new Random().nextInt(3) + 1;

                if (computer_move == 1) {
                    computer_move_img.setImageResource(R.drawable.stone_icons);
                    winner_tv.setText("Тэнцсэн");
                } else if (computer_move == 2) {
                    computer_move_img.setImageResource(R.drawable.paper_icon);
                    winner_tv.setText("Компьютер яллаа");
                    int cscore = Integer.parseInt(computer_score.getText().toString()) + 1;
                    computer_score.setText(String.valueOf(cscore));
                } else {
                    computer_move_img.setImageResource(R.drawable.scissors_icons);
                    winner_tv.setText("Тоглолгч яллаа");
                    int pscore = Integer.parseInt(player_score.getText().toString()) + 1;
                    player_score.setText(String.valueOf(pscore));
                }
            }
        });
    }

    private void clear_score() {
        computer_score.setText("0");
        player_score.setText("0");
        winner_tv.setText("");
        user_move_img.setImageResource(R.drawable.question_mark);
        computer_move_img.setImageResource(R.drawable.question_mark);
    }
}
