package com.lamzone.maru.controller;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.lamzone.maru.DI.DI;
import com.lamzone.maru.R;
import com.lamzone.maru.model.MeetingApiService;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DialogFragmentFilter#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DialogFragmentFilter extends DialogFragment {

    @BindView(R.id.dialogQuestion)
    TextView dialogQuestion;
    @BindView(R.id.dialogDate)
    TextInputEditText dialogDate;
    @BindView(R.id.dialogSpinner)
    Spinner dialogSpinner;

    Calendar localCalendar = Calendar.getInstance();
    Calendar designedCalendar = Calendar.getInstance();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    MeetingApiService meetingApiService;
    DialogFilterListener listener;

    public static DialogFragmentFilter newInstance() {
        return new DialogFragmentFilter(); }

    public int getDialogSpinner() {
        return dialogSpinner.getSelectedItemPosition() - 1;
    }

    public String  getDialogDate() {
        String text = dialogDate.getText().toString();
        return text;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        meetingApiService = DI.getMeetingApiService();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.fragment_dialog_filter, null);
        builder.setView(v);
        ButterKnife.bind(this, v);
        builder.setPositiveButton(R.string.positiveButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.onDialogPositiveClick(DialogFragmentFilter.this);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(R.string.negativeButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.onDialogNegativeClick(DialogFragmentFilter.this);
                dialog.dismiss();
            }
        });
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.roomListFilter, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dialogSpinner.setAdapter(adapter);
        init();
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (DialogFilterListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException();
        }
    }

    private void init(){
        dialogDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int year = localCalendar.get(Calendar.YEAR);
                int month = localCalendar.get(Calendar.MONTH);
                int day = localCalendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        designedCalendar.set(Calendar.YEAR, year);
                        designedCalendar.set(Calendar.MONTH, month);
                        designedCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        dialogDate.setText(dateFormat.format(designedCalendar.getTime()));
                            }
                        }  , year, month, day);
                    datePickerDialog.show();
                    }
                });
        };

    public interface DialogFilterListener {
        public void onDialogPositiveClick(DialogFragmentFilter dialog);
        public void onDialogNegativeClick(DialogFragmentFilter dialog);
        }

    };
