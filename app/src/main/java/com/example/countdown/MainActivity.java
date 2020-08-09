package com.example.countdown;

import android.os.Bundle;
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

    public void startTimer(String seconds, String minutes, String hours) {
        TextView time = findViewById(R.id.time);
        time.setText(hours + minutes + seconds);
    }
}