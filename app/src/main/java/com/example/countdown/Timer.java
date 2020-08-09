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
        EditText secondsTextView = view.findViewById(R.id.seconds);
        EditText minutesTextView = view.findViewById(R.id.minutes);
        EditText hoursTextView = view.findViewById(R.id.hours);

        String seconds = secondsTextView.getText().toString();
        String minutes = minutesTextView.getText().toString();
        String hours = hoursTextView.getText().toString();

        ((MainActivity) getActivity()).startTimer(seconds, minutes, hours);
    }
}
