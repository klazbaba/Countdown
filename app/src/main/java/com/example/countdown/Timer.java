package com.example.countdown;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class Timer extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_layout, null);

        dialogBuilder.setView(view)
                .setPositiveButton("OK", (dialog, id) -> {
                    handleOKPress(view);
                }).setNegativeButton("Cancel", (dialog, id) -> System.out.println("pressed Cancel"));

        return dialogBuilder.create();
    }

    private void handleOKPress(View view) {
        EditText secondsEditText = view.findViewById(R.id.seconds);
        EditText minutesEditText = view.findViewById(R.id.minutes);
        EditText hoursEditText = view.findViewById(R.id.hours);

        int seconds = Integer.parseInt(secondsEditText.getText().toString());
        int minutes = Integer.parseInt(minutesEditText.getText().toString());
        int hours = Integer.parseInt(hoursEditText.getText().toString());

        if (seconds > 60) {
            secondsEditText.setError("Seconds cannot be above 60");
            return;
        }

        if (minutes > 60) {
            secondsEditText.setError("Minutes cannot be above 60");
            return;
        }

        ((MainActivity) getActivity()).startTimer(seconds, minutes, hours);
    }
}
