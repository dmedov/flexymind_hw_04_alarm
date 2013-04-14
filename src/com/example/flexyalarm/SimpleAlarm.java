package com.example.flexyalarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;

public class SimpleAlarm {
    MediaPlayer mediaPlayer;
    Context context;
    AlarmManager alarmManager;
    private static SimpleAlarm instance;

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
    }

    void set(long time) {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(context, R.raw.alarmsound);
        }
        mediaPlayer.setLooping(true);
        Intent myIntent = new Intent(context, AlarmActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, myIntent, 0);
        alarmManager.set(AlarmManager.RTC_WAKEUP, time, pendingIntent);
    }

    void run() {
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }
    }

    void off() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
