package com.example.lab5_4;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView im1;
    private TextView et1;

    private BroadcastReceiver br = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action != null && action.equals(Intent.ACTION_BATTERY_CHANGED)) {
                int remain = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
                et1.setText("Current charge: " + remain + "%\n");

                // Battery image logic
                if (remain >= 90)
                    im1.setImageResource(R.drawable.bat100);
                else if (remain >= 70)
                    im1.setImageResource(R.drawable.bat80);
                else if (remain >= 50)
                    im1.setImageResource(R.drawable.bat60);
                else if (remain >= 30)
                    im1.setImageResource(R.drawable.bat40);
                else if (remain >= 10)
                    im1.setImageResource(R.drawable.bat20);

                int plug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0);
                String connectionStatus;
                switch (plug) {
                    case 0:
                        connectionStatus = "Connection: Not Plugged";
                        break;
                    case BatteryManager.BATTERY_PLUGGED_AC:
                        connectionStatus = "Connection: Adapter Plugged";
                        break;
                    case BatteryManager.BATTERY_PLUGGED_USB:
                        connectionStatus = "Connection: USB Plugged";
                        break;
                    default:
                        connectionStatus = "Connection: Unknown";
                        break;
                }
                et1.append(connectionStatus + "\n");

                int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
                String statusString;
                switch (status) {
                    case BatteryManager.BATTERY_STATUS_CHARGING:
                        statusString = "Battery Status: Charging";
                        break;
                    case BatteryManager.BATTERY_STATUS_DISCHARGING:
                        statusString = "Battery Status: Discharging";
                        break;
                    case BatteryManager.BATTERY_STATUS_FULL:
                        statusString = "Battery Status: Full Charging";
                        break;
                    case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                        statusString = "Battery Status: Not Charging";
                        break;
                    default:
                        statusString = "Battery Status: Unknown";
                        break;
                }
                Toast.makeText(getApplicationContext(), statusString, Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        im1 = findViewById(R.id.ivBattery);
        et1 = findViewById(R.id.edtBattery);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(br);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter iFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(br, iFilter);
    }
}
