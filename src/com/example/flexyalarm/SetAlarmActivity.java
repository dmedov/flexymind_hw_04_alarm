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
    ToggleButton toogleButton;
    SimpleAlarm alarm;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setalarm);

        timePicker = (TimePicker)findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);
        toogleButton = (ToggleButton)findViewById(R.id.toggleButton);

        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        alarm = SimpleAlarm.getInstance();
        alarm.init(SetAlarmActivity.this, alarmManager);

        //Calendar calendar = Calendar.getInstance();
        //calendar.setTimeInMillis(System.currentTimeMillis());
        //calendar.add(Calendar.SECOND, 5);

        //alarm.set(calendar.getTimeInMillis());
    }

    public void quitBtnOnClick(View v) {
        finish();
    }

    public void toggleBtnHandler(View v) {
        Log.e("BOOOOOOOOOM", "bom");
        if (toogleButton.isChecked()) {
            Log.e("BOOOM", "ok");
            long time = getNextAlarmTime();
            alarm.set(time);
        } else {
            Log.e("BOOOM", "off");
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

        Log.e("h:", String.valueOf(pickerHour));
        Log.e("m:", String.valueOf(pickerMin));

        // check if alarm time must be set to next day
        if (calNext.getTimeInMillis() < calNow.getTimeInMillis()) {
            calNext.add(Calendar.DATE, 1);
            Log.e("hour", "added");
        }

        return calNext.getTimeInMillis();
    }


}

