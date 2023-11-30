package com.example.erkabiydaalt;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Female_Workout_Activity extends AppCompatActivity {
    private ImageView playButton, rightButton, leftButton;
    private TextView abovePlayText;
    private SeekBar musicSeekBar;
    private MediaPlayer mediaPlayer;
    private int currentTrackIndex = 0;
    private int[] musicResources = {
            R.raw.taylor_blank_swift,
            R.raw.taylor_with_me,
    };

    private String[] musicNames = {
            "Blank Space",
            "You belong with me",
    };

    private Handler handler = new Handler();
    private boolean isSeekbarTracking = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_female_workout);

        playButton = findViewById(R.id.play);
        rightButton = findViewById(R.id.right);
        leftButton = findViewById(R.id.left);
        abovePlayText = findViewById(R.id.abovePlayText);
        musicSeekBar = findViewById(R.id.musicSeekBar);

        setupButtons();
        setupMediaPlayer();
        setupSeekBar();
    }

    private void setupSeekBar() {
        musicSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress * 1000);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                isSeekbarTracking = true;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                isSeekbarTracking = false;
            }
        });

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null && !isSeekbarTracking) {
                    int currentPosition = mediaPlayer.getCurrentPosition() / 1000;
                    musicSeekBar.setProgress(currentPosition);
                }
                handler.postDelayed(this, 1000);
            }
        };
        handler.postDelayed(runnable, 1000);
    }

    private void setupButtons() {
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleMediaPlayer();
            }
        });

        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeToNextTrack();
            }
        });

        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeToPreviousTrack();
            }
        });
    }

    private void setupMediaPlayer() {
        mediaPlayer = MediaPlayer.create(this, musicResources[currentTrackIndex]);
        musicSeekBar.setMax(mediaPlayer.getDuration() / 1000);
    }

    private void toggleMediaPlayer() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            playButton.setImageResource(R.drawable.play);
        } else {
            mediaPlayer.start();
            playButton.setImageResource(R.drawable.pause);
        }
    }

    private void updateAbovePlayText() {

        String currentSongName = musicNames[currentTrackIndex];
        abovePlayText.setText(currentSongName);
    }

    private void changeToNextTrack() {
        currentTrackIndex = (currentTrackIndex + 1) % musicResources.length;
        mediaPlayer.stop();
        mediaPlayer = MediaPlayer.create(this, musicResources[currentTrackIndex]);
        musicSeekBar.setMax(mediaPlayer.getDuration() / 1000);
        toggleMediaPlayer();
        updateAbovePlayText();
    }

    private void changeToPreviousTrack() {
        currentTrackIndex = (currentTrackIndex - 1 + musicResources.length) % musicResources.length;
        mediaPlayer.stop();
        mediaPlayer = MediaPlayer.create(this, musicResources[currentTrackIndex]);
        musicSeekBar.setMax(mediaPlayer.getDuration() / 1000);
        toggleMediaPlayer();
        updateAbovePlayText();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
    }
}
