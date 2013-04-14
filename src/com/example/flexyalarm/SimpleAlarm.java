package com.example.flexyalarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;

import java.util.Calendar;

public class SimpleAlarm {
    MediaPlayer mediaPlayer;
    Context context;
    PendingIntent pendingIntent;
    AlarmManager alarmManager;
    Boolean isSet;
    private static SimpleAlarm instance;

    private final static int SNOOZE_TIME_MIN = 5;

    public static SimpleAlarm getInstance() {
        if (instance == null) {
            instance = new SimpleAlarm();
        }
        return instance;
    }

    private SimpleAlarm(){}

    void init(Context context, AlarmManager alarmManager) {
        this.context = context;
        this.alarmManager = alarmManager;
        isSet = false;
    }

    void set(long time) {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(context, R.raw.alarmsound);
        }
        mediaPlayer.setLooping(true);
        Intent myIntent = new Intent(context, AlarmActivity.class);
        pendingIntent = PendingIntent.getActivity(context, 0, myIntent, 0);
        alarmManager.set(AlarmManager.RTC_WAKEUP, time, pendingIntent);
        isSet = true;
    }

    void run() {
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }
    }

    void snooze() {
        off();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, SNOOZE_TIME_MIN);
        set(calendar.getTimeInMillis());
    }

    void off() {
        if (!isSet) {
            return;
        }
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        if (alarmManager != null) {
            alarmManager.cancel(pendingIntent);
        }
        isSet = false;
    }
}
