package com.example.flexyalarm;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import java.util.Calendar;

public class SetAlarmActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    //MediaPlayer mediaPlayer;
    SimpleAlarm alarm;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setalarm);

        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        alarm = SimpleAlarm.getInstance();
        alarm.init(SetAlarmActivity.this, alarmManager);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.SECOND, 5);

        alarm.set(calendar.getTimeInMillis());
    }

    public void quitBtnOnClick(View v) {
        finish();
    }
}

