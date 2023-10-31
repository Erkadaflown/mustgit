package com.example.lab7_2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    ListView simpleList;
    String countryList[] = {"India", "China", "Australia", "Portugal",
            "America", "New Zealand"};
    int flags[] = {R.drawable.india, R.drawable.china, R.drawable.australia,
            R.drawable.portugal, R.drawable.america, R.drawable.newzeland};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        simpleList = findViewById(R.id.simpleListView);
        CustomAdapter customAdapter = new CustomAdapter(this, countryList, flags);
        simpleList.setAdapter(customAdapter);
    }
}
