package com.example.countdown;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

public class MainActivity extends AppCompatActivity {
    int totalTimeInSeconds = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showDialog(View view) {
        DialogFragment newFragment = new Timer();
        newFragment.show(getSupportFragmentManager(), "getDuration");
    }

    public void startTimer(String seconds, String minutes, String hours) {
        TextView time = findViewById(R.id.time);
        totalTimeInSeconds = convertTimeToSeconds(seconds, minutes, hours);
        String timeToDisplay = String.format("%02d:%02d:%02d", getHours(totalTimeInSeconds), getMinutes(totalTimeInSeconds), totalTimeInSeconds);
        time.setText(timeToDisplay);
    }

    private int getHours(int time) {
        totalTimeInSeconds = time % 3600;
        return time / 3600;
    }

    private int getMinutes(int time) {
        totalTimeInSeconds = time % 60;
        return time / 60;
    }

    private int convertTimeToSeconds(String seconds, String minutes, String hours) {
        int hoursInteger = Integer.parseInt(hours);
        int minutesInteger = Integer.parseInt(minutes);
        int secondsInteger = Integer.parseInt(seconds);

        return hoursInteger * 3600 + minutesInteger * 60 + secondsInteger;

    }
}