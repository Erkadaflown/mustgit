package com.example.lab3_5;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.graphics.Color;

public class MainActivity extends AppCompatActivity {

    Button bt1, bt2;
    LinearLayout lay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt1 = findViewById(R.id.button1);
        bt2 = findViewById(R.id.button2);
        lay = findViewById(R.id.baseLayout);

        registerForContextMenu(bt1);
        registerForContextMenu(bt2);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater mInflater = getMenuInflater();
        if (v == bt1) {
            menu.setHeaderTitle("Changing the background color");
            mInflater.inflate(R.menu.menu1, menu);
        }
        if (v == bt2) {
            mInflater.inflate(R.menu.menu2, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_red) {
            lay.setBackgroundColor(Color.RED);
            return true;
        } else if (item.getItemId() == R.id.menu_green) {
            lay.setBackgroundColor(Color.GREEN);
            return true;
        } else if (item.getItemId() == R.id.menu_blue) {
            lay.setBackgroundColor(Color.BLUE);
            return true;
        } else if (item.getItemId() == R.id.menu_gray) {
            lay.setBackgroundColor(Color.GRAY);
            return true;
        } else if (item.getItemId() == R.id.menu_yellow) {
            lay.setBackgroundColor(Color.YELLOW);
            return true;
        } else {
            return super.onContextItemSelected(item);
        }
    }

}
