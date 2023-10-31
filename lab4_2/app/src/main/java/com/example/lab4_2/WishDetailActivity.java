package com.example.lab4_2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class WishDetailActivity extends AppCompatActivity {
    private TextView title, date, content;
    private Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_detail);

        title = findViewById(R.id.detailsTitle);
        date = findViewById(R.id.detailsDateText);
        content = findViewById(R.id.detailsTextView);
        deleteButton = findViewById(R.id.deleteButton);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            title.setText(extras.getString("title"));
            date.setText("Created: " + extras.getString("date"));
            content.setText(" \" " + extras.getString("content") + " \" ");
            final int id = extras.getInt("id");

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    com.example.lab4_2.data.DatabaseHandler dba = new com.example.lab4_2.data.DatabaseHandler(getApplicationContext());
                    dba.deleteWish(id);
                    Toast.makeText(getApplicationContext(), "Wish Deleted!", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(WishDetailActivity.this, DisplayWishesActivity.class));
                    WishDetailActivity.this.finish();
                }
            });
        }
    }
}
