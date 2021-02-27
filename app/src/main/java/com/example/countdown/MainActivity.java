package com.example.countdown;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

public class MainActivity extends AppCompatActivity {
    CountDownTimer timer;
    long remainingTime;
    boolean isPause;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showDialog(View view) {
        DialogFragment newFragment = new TimeInputDialog();
        newFragment.show(getSupportFragmentManager(), "getDuration");
    }

    public void startTimer(int seconds, int minutes, int hours) {
        TextView time = findViewById(R.id.time);
        int totalTimeInSeconds = convertTimeToSeconds(seconds, minutes, hours);
        Button startButton = findViewById(R.id.start_button);
        startButton.setEnabled(false);
        Button stopButton = findViewById(R.id.stop_button);
        stopButton.setEnabled(true);
        timer = new CountDownTimer(totalTimeInSeconds * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long millisUntilFinishedInSeconds = millisUntilFinished / 1000;
                String timeToDisplay = String.format("%02d:%02d:%02d", getHours(millisUntilFinishedInSeconds), getMinutes(millisUntilFinishedInSeconds), millisUntilFinished / 1000 % 60);
                time.setText(timeToDisplay);
                remainingTime = millisUntilFinished;
            }

            @Override
            public void onFinish() {
                stopButton.setEnabled(false);
            }
        }.start();
    }

    public void pauseTimer(View view) throws InterruptedException {
        Button pauseButton = findViewById(R.id.pause_button);
        String buttonLabel = (String) pauseButton.getText();
        Button stopButton = findViewById(R.id.stop_button);
        if (buttonLabel.equals("pause")) {
            pauseButton.setText("continue");
            stopTimer(view);
            isPause = true;
        } else if (buttonLabel.equals("continue")) {
            pauseButton.setText("pause");
            TextView time = findViewById(R.id.time);
            timer = new CountDownTimer(remainingTime, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    long millisUntilFinishedInSeconds = millisUntilFinished / 1000;
                    String timeToDisplay = String.format("%02d:%02d:%02d", getHours(millisUntilFinishedInSeconds), getMinutes(millisUntilFinishedInSeconds), millisUntilFinished / 1000 % 60);
                    time.setText(timeToDisplay);
                    remainingTime = millisUntilFinished;
                    Log.d("TIME", timeToDisplay);
                }

                @Override
                public void onFinish() {
                    stopButton.setEnabled(false);
                }
            }.start();
        }
    }

    public void stopTimer(View view) {
        timer.cancel();
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