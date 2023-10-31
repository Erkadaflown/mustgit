package com.example.lab8_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar; // Import for ActionBar
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView txtDetails;
    private EditText inputName, inputEmail;
    private Button btnSave;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the action bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setIcon(R.mipmap.ic_launcher);
        }

        txtDetails = (TextView) findViewById(R.id.txt_user);
        inputName = (EditText) findViewById(R.id.name);
        inputEmail = (EditText) findViewById(R.id.email);
        btnSave = (Button) findViewById(R.id.btn_save);

        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("users");
        mFirebaseInstance.getReference("app_title").setValue("Realtime Database");

        mFirebaseInstance.getReference("app_title").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e(TAG, "App title updated");
                String appTitle = dataSnapshot.getValue(String.class);

                // Check if the action bar exists and is not null
                ActionBar actionBar = getSupportActionBar();
                if (actionBar != null) {
                    actionBar.setTitle(appTitle);
                } else {
                    Log.e(TAG, "Action bar is null");
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.e(TAG, "Failed to read app title value.", error.toException());
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = inputName.getText().toString();
                String email = inputEmail.getText().toString();
                if (TextUtils.isEmpty(userId)) {
                    createUser(name, email);
                } else {
                    updateUser(name, email);
                }
            }
        });
    }

    private void createUser(String name, String email) {
        if (TextUtils.isEmpty(userId)) {
            userId = mFirebaseDatabase.push().getKey();
        }
        User user = new User(name, email);
        mFirebaseDatabase.child(userId).setValue(user);
        addUserChangeListener();
    }

    private void addUserChangeListener() {
        mFirebaseDatabase.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                if (user == null) {
                    Log.e(TAG, "User data is null!");
                    return;
                }
                Log.e(TAG, "User data is changed!" + user.getName() + ", " + user.getEmail());
                txtDetails.setText(user.getName() + ", " + user.getEmail());
                inputEmail.setText("");
                inputName.setText("");
                toggleButton();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.e(TAG, "Failed to read user", error.toException());
            }
        });
        toggleButton();
    }

    private void toggleButton() {
        if (TextUtils.isEmpty(userId)) {
            btnSave.setText("Хадгалах");
        } else {
            btnSave.setText("Засах");
        }
    }

    private void updateUser(String name, String email) {
        if (!TextUtils.isEmpty(name))
            mFirebaseDatabase.child(userId).child("name").setValue(name);
        if (!TextUtils.isEmpty(email))
            mFirebaseDatabase.child(userId).child("email").setValue(email);
    }
}
