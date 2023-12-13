package com.example.anujin_biydaalt1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HomeActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        databaseReference = FirebaseDatabase.getInstance().getReference("users");

        displayWelcomeMessage();

        // Find the "about" button and add an OnClickListener
        Button aboutButton = findViewById(R.id.about);
        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Show a toast message when the button is pressed
                Toast.makeText(HomeActivity.this, "eme bol tanii", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void displayWelcomeMessage() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            String userId = currentUser.getUid();
            databaseReference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        User user = snapshot.getValue(User.class);
                        if (user != null) {
                            String userName = user.getName();
                            String userDateOfBirth = user.getDateOfBirth();
                            updateWelcomeMessage(userName);
                            updateAstroMessage(userDateOfBirth);
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }
    }

    private void updateWelcomeMessage(String userName) {
        TextView textViewWelcome = findViewById(R.id.textViewWelcome);
        // Display the welcome message along with the astrological sign
        textViewWelcome.setText("Тавтай морил " + userName);
    }

    private void updateAstroMessage(String userDateOfBirth) {
        TextView textViewWelcome = findViewById(R.id.text_view_bottom);
        // Get the astrological sign based on the user's date of birth
        String astrologicalSign = getAstrologicalSign(userDateOfBirth);
        // Display the welcome message along with the astrological sign
        textViewWelcome.setText("Таны орд: " + astrologicalSign);
    }

    private String getAstrologicalSign(String dateOfBirth) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy");
            Date dob = sdf.parse(dateOfBirth);

            Calendar cal = Calendar.getInstance();
            cal.setTime(dob);
            int month = cal.get(Calendar.MONTH) + 1; // Months are 0-based
            int day = cal.get(Calendar.DAY_OF_MONTH);

            String astrologicalSign = "";

            if ((month == 3 && day >= 21) || (month == 4 && day <= 19)) {
                astrologicalSign = "Хонь";
            } else if ((month == 4 && day >= 20) || (month == 5 && day <= 20)) {
                astrologicalSign = "Үхэр";
            } else if ((month == 5 && day >= 21) || (month == 6 && day <= 20)) {
                astrologicalSign = "Ихэр";
            } else if ((month == 6 && day >= 21) || (month == 7 && day <= 22)) {
                astrologicalSign = "Мэлхий";
            } else if ((month == 7 && day >= 23) || (month == 8 && day <= 22)) {
                astrologicalSign = "Арслан";
            } else if ((month == 8 && day >= 23) || (month == 9 && day <= 22)) {
                astrologicalSign = "Охин";
            } else if ((month == 9 && day >= 23) || (month == 10 && day <= 22)) {
                astrologicalSign = "Жинлүүр";
            } else if ((month == 10 && day >= 23) || (month == 11 && day <= 21)) {
                astrologicalSign = "Хилэнц";
            } else if ((month == 11 && day >= 22) || (month == 12 && day <= 21)) {
                astrologicalSign = "Нум";
            } else if ((month == 12 && day >= 22) || (month == 1 && day <= 19)) {
                astrologicalSign = "Матар";
            } else if ((month == 1 && day >= 20) || (month == 2 && day <= 18)) {
                astrologicalSign = "Бумба";
            } else if ((month == 2 && day >= 19) || (month == 3 && day <= 20)) {
                astrologicalSign = "Загас";
            }

            return astrologicalSign;

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "Unknown";
    }
}
