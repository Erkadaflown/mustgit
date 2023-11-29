package com.example.biydaalt1;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextName, editTextPhone, editTextPassword;
    private Button buttonRegister, buttonLogin;
    private RadioGroup radioGroupGender;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("users");

        buttonLogin = findViewById(R.id.buttonLoginFromRegister);
        editTextName = findViewById(R.id.editTextName);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonRegister = findViewById(R.id.buttonRegister);
        radioGroupGender = findViewById(R.id.radioGroupGender);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }

    private void registerUser() {
        final String name = editTextName.getText().toString().trim();
        final String phone = editTextPhone.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter all details", Toast.LENGTH_SHORT).show();
        } else {
            // Get selected gender from radio group
            int selectedRadioButtonId = radioGroupGender.getCheckedRadioButtonId();
            String gender = getGenderFromRadioButtonId(selectedRadioButtonId);

            // Generate a unique email address for each user
            final String email = generateUniqueEmail(phone);

            // Check if the email is already in use
            firebaseAuth.fetchSignInMethodsForEmail(email)
                    .addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                        @Override
                        public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                            if (task.isSuccessful()) {
                                SignInMethodQueryResult result = task.getResult();
                                if (result != null && result.getSignInMethods() != null && !result.getSignInMethods().isEmpty()) {
                                    // Generic error message to avoid exposing too much information
                                    Toast.makeText(RegisterActivity.this, "Registration failed. Please try again.", Toast.LENGTH_SHORT).show();
                                } else {
                                    // Email is not in use, proceed with registration
                                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                @Override
                                                public void onComplete(@NonNull Task<AuthResult> task) {
                                                    if (task.isSuccessful()) {
                                                        String userId = firebaseAuth.getCurrentUser().getUid();
                                                        User user = new User(name, phone);

                                                        // Save user data to Firebase Database
                                                        databaseReference.child(userId).setValue(user)
                                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                    @Override
                                                                    public void onComplete(@NonNull Task<Void> task) {
                                                                        if (task.isSuccessful()) {
                                                                            Toast.makeText(RegisterActivity.this, "Registration successful!", Toast.LENGTH_SHORT).show();
                                                                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                                                            finish();
                                                                        } else {
                                                                            Toast.makeText(RegisterActivity.this, "Failed to save user data", Toast.LENGTH_SHORT).show();
                                                                        }
                                                                    }
                                                                });
                                                    } else {
                                                        // Handle different registration failures
                                                        if (task.getException() != null) {
                                                            Log.e("RegisterActivity", "Registration failed: " + task.getException().getMessage());
                                                            Toast.makeText(RegisterActivity.this, "Registration failed. Please try again.", Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                }
                                            });
                                }
                            } else {
                                Toast.makeText(RegisterActivity.this, "Error checking email availability", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    private String getGenderFromRadioButtonId(int radioButtonId) {
        if (radioButtonId == R.id.radioButtonMale) {
            return "Male";
        } else if (radioButtonId == R.id.radioButtonFemale) {
            return "Female";
        } else {
            return "Other";
        }
    }

    private String generateUniqueEmail(String phone) {
        // Generate a unique email address based on the phone number
        return phone + "@example.com";
    }
}