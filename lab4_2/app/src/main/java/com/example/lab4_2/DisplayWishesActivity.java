package com.example.lab4_2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class DisplayWishesActivity extends Activity {
    private com.example.lab4_2.data.DatabaseHandler dba;
    private ArrayList<com.example.lab4_2.model.MyWish> dbWishes = new ArrayList<>();
    private WishAdapter wishAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_wishes);
        listView = (ListView) findViewById(R.id.list);
        refreshData();
    }

    private void refreshData() {
        dbWishes.clear();
        dba = new com.example.lab4_2.data.DatabaseHandler(getApplicationContext());
        ArrayList<com.example.lab4_2.model.MyWish> wishesFromDB = dba.getWishes();
        for (int i = 0; i < wishesFromDB.size(); i++) {
            String title = wishesFromDB.get(i).getTitle();
            String dateText = wishesFromDB.get(i).getRecordDate();
            String content = wishesFromDB.get(i).getContent();
            int mid = wishesFromDB.get(i).getItemId();
            com.example.lab4_2.model.MyWish myWish = new com.example.lab4_2.model.MyWish();
            myWish.setTitle(title);
            myWish.setContent(content);
            myWish.setRecordDate(dateText);
            myWish.setItemId(mid);
            dbWishes.add(myWish);
        }
        dba.close();

        wishAdapter = new WishAdapter(DisplayWishesActivity.this, R.layout.wish_row, dbWishes);
        listView.setAdapter(wishAdapter);
        wishAdapter.notifyDataSetChanged();
    }

    public class WishAdapter extends ArrayAdapter<com.example.lab4_2.model.MyWish> {
        Activity activity;
        int layoutResource;
        ArrayList<com.example.lab4_2.model.MyWish> mData;

        public WishAdapter(Activity act, int resource, ArrayList<com.example.lab4_2.model.MyWish> data) {
            super(act, resource, data);
            activity = act;
            layoutResource = resource;
            mData = data;
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public com.example.lab4_2.model.MyWish getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            ViewHolder holder;

            if (row == null) {
                LayoutInflater inflater = LayoutInflater.from(activity);
                row = inflater.inflate(layoutResource, null);
                holder = new ViewHolder();
                holder.mTitle = row.findViewById(R.id.name);
                holder.mDate = row.findViewById(R.id.dateText);
                row.setTag(holder);
            } else {
                holder = (ViewHolder) row.getTag();
            }

            holder.myWish = getItem(position);
            holder.mTitle.setText(holder.myWish.getTitle());
            holder.mDate.setText(holder.myWish.getRecordDate());

            final int positionClicked = position;
            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String text = mData.get(positionClicked).getContent();
                    String dateText = mData.get(positionClicked).getRecordDate();
                    String title = mData.get(positionClicked).getTitle();
                    int id = mData.get(positionClicked).getItemId();
                    Log.v("MyId: ", String.valueOf(id));

                    Intent intent = new Intent(DisplayWishesActivity.this, WishDetailActivity.class);
                    intent.putExtra("content", text);
                    intent.putExtra("date", dateText);
                    intent.putExtra("title", title);
                    intent.putExtra("id", id);
                    startActivity(intent);
                }
            });

            return row;
        }

        class ViewHolder {
            com.example.lab4_2.model.MyWish myWish;
            TextView mTitle;
            TextView mDate;
        }
    }
}
