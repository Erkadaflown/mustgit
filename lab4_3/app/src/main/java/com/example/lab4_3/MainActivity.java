package com.example.lab4_3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    public TextView songName, duration;
    private double timeElapsed = 0, finalTime = 0;
    private
    int forwardTime = 2000, backwardTime = 2000;
    private
    Handler durationHandler = new Handler();
    private
    SeekBar seekbar;

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_song1){
            songName = (TextView) findViewById(R.id.songName);
            mediaPlayer = MediaPlayer.create(this, R.raw.song);
            finalTime = mediaPlayer.getDuration();
            duration = (TextView) findViewById(R.id.songDuration);
            seekbar = (SeekBar) findViewById(R.id.seekBar);
            songName.setText("song.mp3");
            seekbar.setMax((int) finalTime);
            seekbar.setClickable(false);
            return true;
        } else if (id == R.id.menu_song2){
            songName = (TextView) findViewById(R.id.songName);
            mediaPlayer = MediaPlayer.create(this, R.raw.song2);
            finalTime = mediaPlayer.getDuration();
            duration = (TextView) findViewById(R.id.songDuration);
            seekbar = (SeekBar) findViewById(R.id.seekBar);
            songName.setText("song2.mp3");
            seekbar.setMax((int) finalTime);
            seekbar.setClickable(false);
            return true;
        } else if (id == R.id.menu_song3){
            songName = (TextView) findViewById(R.id.songName);
            mediaPlayer = MediaPlayer.create(this, R.raw.song3);
            finalTime = mediaPlayer.getDuration();
            duration = (TextView) findViewById(R.id.songDuration);
            seekbar = (SeekBar) findViewById(R.id.seekBar);
            songName.setText("song3.mp3");
            seekbar.setMax((int) finalTime);
            seekbar.setClickable(false);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews ();
    }

    public void initializeViews() {
        songName = (TextView) findViewById(R.id.songName);
        mediaPlayer = MediaPlayer.create(this, R.raw.song);
        finalTime = mediaPlayer.getDuration();
        duration = (TextView) findViewById(R.id.songDuration);
        seekbar = (SeekBar) findViewById(R.id.seekBar);
        songName.setText("Sample_Song.mp3");
        seekbar.setMax((int) finalTime);
        seekbar.setClickable(false);
    }

    public void play(View view) {
        mediaPlayer.start();
        timeElapsed = mediaPlayer.getCurrentPosition();
        seekbar.setProgress((int) timeElapsed);
        durationHandler.postDelayed(updateSeekBarTime, 100);
    }

    private Runnable updateSeekBarTime = new Runnable() {
        public void run() {
//ажиллаж байгаа хугацааны агшинг авах
            timeElapsed = mediaPlayer.getCurrentPosition();
// seekbar -тай холбох
            seekbar.setProgress((int) timeElapsed);
//дуусахаас өмнө үлдсэн хугацааг авах
            double timeRemaining = finalTime - timeElapsed;
            duration.setText(String.format("%d min, %d sec", TimeUnit.MILLISECONDS.toMinutes((long) timeRemaining), TimeUnit.MILLISECONDS.toSeconds((long) timeRemaining) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) timeRemaining))));
            durationHandler.postDelayed(this, 100);
        }
    };

    // түр зогсоох
    public void pause(View view) {
        mediaPlayer.pause();
    }

    // дууг гүйлгэх
    public void forward(View view) {
//гүйлгэхээс өмнө дуусах хугацааг шалгах
        if ((timeElapsed + forwardTime) <= finalTime) {
            timeElapsed = timeElapsed + backwardTime;
            mediaPlayer.seekTo((int) timeElapsed);
        }
    }
    public void rewind(View view) {
//гүйлгэхээс өмнө дуусах хугацааг шалгах
        if ((timeElapsed + forwardTime) <= finalTime) {
            timeElapsed = timeElapsed - backwardTime;
            mediaPlayer.seekTo((int) timeElapsed);
        }
    }
}