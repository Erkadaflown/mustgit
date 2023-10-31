package com.example.lab3_3;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final GridView gv = findViewById(R.id.gridView1);
        MyGridAdapter gAdapter = new MyGridAdapter(this);
        gv.setAdapter(gAdapter);
    }

    public class MyGridAdapter extends BaseAdapter {
        Context context;
        Integer[] posterID = {
                R.drawable.bird1, R.drawable.bird2,
                R.drawable.bird3, R.drawable.bird4,
                R.drawable.bird5, R.drawable.bird6,
                R.drawable.bird7, R.drawable.bird8,
                R.drawable.bird9, R.drawable.bird10,
                R.drawable.bird11, R.drawable.bird12
        };

        public MyGridAdapter(Context c) {
            context = c;
        }

        @Override
        public int getCount() {
            return posterID.length;
        }

        @Override
        public Object getItem(int position) {
            return posterID[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ImageView imView;
            if (convertView == null) {
                imView = new ImageView(context);
                imView.setLayoutParams(new GridView.LayoutParams(100, 150));
                imView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imView.setPadding(5, 5, 5, 5);
            } else {
                imView = (ImageView) convertView;
            }

            imView.setImageResource(posterID[position]);

            imView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    View dialogView = View.inflate(MainActivity.this, R.layout.dialog, null);
                    AlertDialog.Builder dbl = new AlertDialog.Builder(MainActivity.this);
                    ImageView ivPoster = dialogView.findViewById(R.id.ivPoster);
                    ivPoster.setImageResource(posterID[position]);
                    dbl.setTitle("Томруулсан зураг");
                    dbl.setIcon(R.drawable.bird2);
                    dbl.setView(dialogView);
                    dbl.setNegativeButton("Хаах", null);
                    dbl.show();
                }
            });

            return imView;
        }
    }
}
