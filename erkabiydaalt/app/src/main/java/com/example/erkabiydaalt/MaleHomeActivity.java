package com.example.erkabiydaalt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MaleHomeActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_male_home);

        databaseReference = FirebaseDatabase.getInstance().getReference("users");

        displayWelcomeMessage();

        Button startWorkoutButton = findViewById(R.id.workout);
        startWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Add the logic to jump to the desired layout/activity here
                // For example, if you have another activity called WorkoutActivity:
                Intent intent = new Intent(MaleHomeActivity.this, MaleHomeActivity.class);
                startActivity(intent);
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
                            updateWelcomeMessage(userName);
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
        textViewWelcome.setText("Тавтай морил " + userName);
    }
}
