package com.example.lab9_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    DatabaseReference databaseReference =
            FirebaseDatabase.getInstance().getReferenceFromUrl("https://loginregistercc882-default-rtdb.firebaseio.com/");

    EditText mobile;  // Change variable name
    EditText password; // Change variable name

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mobile = findViewById(R.id.mobile); // Update variable assignments
        password = findViewById(R.id.password);

        final Button loginBtn = findViewById(R.id.loginBtn);
        final TextView registerNowBtn = findViewById(R.id.registerNowBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String phoneTxt = mobile.getText().toString();
                final String passwordTxt = password.getText().toString();
                if (phoneTxt.isEmpty() || passwordTxt.isEmpty()) {
                    Toast.makeText(Login.this, "Please enter your mobile and password", Toast.LENGTH_SHORT).show();
                } else {
                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(phoneTxt)) {
                                final String getPassword = snapshot.child(phoneTxt).child("password").getValue(String.class);
                                if (getPassword != null && getPassword.equals(passwordTxt)) {
                                    Toast.makeText(Login.this, "Successfully logged in", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(Login.this, MainActivity.class);
                                    finish();
                                } else {
                                    Toast.makeText(Login.this, "Wrong password", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(Login.this, "User not found", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(Login.this, "Database Error", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        registerNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Register.class));
                finish();
            }
        });
    }
}
