package com.example.erkabiydaalt;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.view.MotionEvent;


public class LoginActivity extends AppCompatActivity {

    private EditText editTextPhoneLogin, editTextPasswordLogin;
    private Button buttonLogin, buttonGoToRegister;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("users");

        editTextPhoneLogin = findViewById(R.id.editTextPhoneLogin);
        editTextPasswordLogin = findViewById(R.id.editTextPasswordLogin);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonGoToRegister = findViewById(R.id.buttonRegister);

        ImageView imageViewBackground = findViewById(R.id.imageViewBackground);


        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });

        imageViewBackground.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                hideKeyboard();
                return false;
            }
        });

        EditText editTextPhoneLogin = findViewById(R.id.editTextPhoneLogin);


        buttonGoToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

    private void loginUser() {
        String phone = editTextPhoneLogin.getText().toString().trim();
        String password = editTextPasswordLogin.getText().toString().trim();

        if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Утасны дугаар болон нууц үгээ оруулна уу!", Toast.LENGTH_SHORT).show();
        } else {
            if (isNetworkAvailable()) {
                firebaseAuth.signInWithEmailAndPassword(phone + "@example.com", password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Login successful, get user's gender from database
                                    String userId = firebaseAuth.getCurrentUser().getUid();
                                    databaseReference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            if (snapshot.exists()) {
                                                User user = snapshot.getValue(User.class);
                                                if (user != null) {
                                                    String gender = user.getGender();
                                                    navigateToHome(gender);
                                                } else {
                                                    showErrorMessage("Дата-г хүлээн авхад алдаа гарлаа");
                                                }
                                            } else {
                                                showErrorMessage("Хэрэглэгчийн мэдээлэл олдсонгүй");
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {
                                            showErrorMessage("Өгөгдөлийн сангийн алдаа: " + error.getMessage());
                                        }
                                    });
                                } else {
                                    showErrorMessage("Нэвтэрхэд алдаа гарлаа. Нууц үг дугаараа шалгана уу");
                                }
                            }
                        });
            } else {
                showErrorMessage("Интернет холболт тасарсан байна. Сүлжээгээ шалгана уу!");
            }
        }
    }

    private void navigateToHome(String gender) {
        Class<?> homeActivityClass;
        if ("Male".equals(gender)) {
            homeActivityClass = MaleHomeActivity.class;
        } else if ("Female".equals(gender)) {
            homeActivityClass = FemaleHomeActivity.class;
        } else {
            showErrorMessage("Хүйс тодорхойлход алдаа гарлаа: " + gender);
            return;
        }

        Intent intent = new Intent(LoginActivity.this, homeActivityClass);
        intent.putExtra("GENDER", gender);
        startActivity(intent);
        finish();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
        return false;
    }

    private void showErrorMessage(String message) {
        Log.e("LoginActivity", message);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}