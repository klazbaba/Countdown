package com.example.countdown;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showDialog(View view) {
        DialogFragment newFragment = new Timer();
        newFragment.show(getSupportFragmentManager(), "getDuration");
    }

    public void startTimer(int seconds, int minutes, int hours) {
        TextView time = findViewById(R.id.time);
        int totalTimeInSeconds = convertTimeToSeconds(seconds, minutes, hours);
        new CountDownTimer(totalTimeInSeconds * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long millisUntilFinishedInSeconds = millisUntilFinished / 1000;
                String timeToDisplay = String.format("%02d:%02d:%02d", getHours(millisUntilFinishedInSeconds), getMinutes(millisUntilFinishedInSeconds), millisUntilFinished / 1000 % 60);
                time.setText(timeToDisplay);
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }

    private long getHours(long time) {
        return time / 3600;
    }

    private long getMinutes(long time) {
        return time / 60;
    }

    private int convertTimeToSeconds(int seconds, int minutes, int hours) {
        return hours * 3600 + minutes * 60 + seconds;
    }
}