package com.example.lab4_2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText title;
    private EditText content;
    private Button saveButton;
    private com.example.lab4_2.data.DatabaseHandler dba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dba = new com.example.lab4_2.data.DatabaseHandler(MainActivity.this);
        title = (EditText) findViewById(R.id.titleEditText);
        content = (EditText) findViewById(R.id.wishEditText);
        saveButton = (Button) findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToDB();
            }
        });
    }

    private void saveToDB() {
        com.example.lab4_2.model.MyWish wish = new com.example.lab4_2.model.MyWish();
        wish.setTitle(title.getText().toString().trim());
        wish.setContent(content.getText().toString().trim());
        dba.addWish(wish);
        dba.close();
        title.setText("");
        content.setText(""); // Pass an empty string to clear the content of the EditText
        Intent i = new Intent(MainActivity.this, DisplayWishesActivity.class);
        startActivity(i);
    }
}
