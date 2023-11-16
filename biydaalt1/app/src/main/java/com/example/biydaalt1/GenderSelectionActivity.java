package com.example.biydaalt1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class GenderSelectionActivity extends AppCompatActivity {

    private Button buttonMale, buttonFemale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender_selection);

        buttonMale = findViewById(R.id.buttonMale);
        buttonFemale = findViewById(R.id.buttonFemale);

        buttonMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToHome("Male");
            }
        });

        buttonFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToHome("Female");
            }
        });
    }

    private void navigateToHome(String selectedGender) {
        Class<?> homeActivityClass;
        if ("Male".equals(selectedGender)) {
            homeActivityClass = MaleHomeActivity.class;
        } else if ("Female".equals(selectedGender)) {
            homeActivityClass = FemaleHomeActivity.class;
        } else {
            // Handle the case when the gender is not recognized
            return;
        }

        Intent intent = new Intent(GenderSelectionActivity.this, homeActivityClass);
        intent.putExtra("GENDER", selectedGender);
        startActivity(intent);
        finish();
    }
}
