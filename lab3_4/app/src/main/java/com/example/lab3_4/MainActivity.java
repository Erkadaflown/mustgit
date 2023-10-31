package com.example.lab3_4;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private LinearLayout lay;
    private Button bt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lay = findViewById(R.id.baseLayout);
        bt1 = findViewById(R.id.button1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_red) {
            lay.setBackgroundColor(Color.RED);
            return true;
        } else if (id == R.id.menu_green) {
            lay.setBackgroundColor(Color.GREEN);
            return true;
        } else if (id == R.id.menu_blue) {
            lay.setBackgroundColor(Color.BLUE);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
