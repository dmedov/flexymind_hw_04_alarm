package com.example.flexyalarm;

import android.app.Activity;
import android.app.AlarmManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import java.util.Calendar;

public class SetAlarmActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    TimePicker timePicker;
    ToggleButton toggleButton;
    SimpleAlarm alarm;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setalarm);

        timePicker = (TimePicker)findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);
        toggleButton = (ToggleButton)findViewById(R.id.toggleButton);

        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        alarm = SimpleAlarm.getInstance();
        alarm.init(SetAlarmActivity.this, alarmManager);
    }

    @Override
    public void onResume() {
        super.onResume();
        toggleButton.setChecked(alarm.isSet());
    }

    public void quitBtnOnClick(View v) {
        finish();
    }

    public void toggleBtnHandler(View v) {
        if (toggleButton.isChecked()) {
            long time = getNextAlarmTime();
            alarm.set(time);
        } else {
            alarm.off();
        }
    }

    public long getNextAlarmTime() {
        Calendar calNow = Calendar.getInstance();
        Calendar calNext = Calendar.getInstance();
        int pickerHour = timePicker.getCurrentHour();
        int pickerMin = timePicker.getCurrentMinute();

        calNext.set(Calendar.HOUR, pickerHour);
        calNext.set(Calendar.MINUTE, pickerMin);

        // check if alarm time must be set to next day
        if (calNext.getTimeInMillis() < calNow.getTimeInMillis()) {
            calNext.add(Calendar.DATE, 1);
            Log.e("hour", "added");
        }
        return calNext.getTimeInMillis();
    }
}

