package com.example.biydaalt1;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MaleHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_male_home);

        displayWelcomeMessage("Male");
    }

    private void displayWelcomeMessage(String gender) {
        TextView textViewWelcome = findViewById(R.id.textViewWelcome);
        textViewWelcome.setText("Welcome, " + gender + "!");
    }
}
