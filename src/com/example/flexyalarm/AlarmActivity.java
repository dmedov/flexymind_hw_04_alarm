package com.example.flexyalarm;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;


public class AlarmActivity extends Activity {
    SimpleAlarm alarm;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm);
        alarm = SimpleAlarm.getInstance();
        alarm.run();
    }

    public void stopAlarm(View v) {
        alarm.off();
        finish();
    }

    public void snoozeAlarm(View v) {
        alarm.snooze();
    }
}