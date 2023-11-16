package com.example.biydaalt1;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class FemaleHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_female_home);

        displayWelcomeMessage("Female");
    }

    private void displayWelcomeMessage(String gender) {
        TextView textViewWelcome = findViewById(R.id.textViewWelcome);
        textViewWelcome.setText("Welcome, " + gender + "!");
    }
}
