package com.lamzone.maru.view;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputEditText;
import com.lamzone.maru.DI.DI;
import com.lamzone.maru.R;
import com.lamzone.maru.model.Meeting;
import com.lamzone.maru.model.MeetingApiService;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateMeetingActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    @BindView(R.id.placeSpinner)
    Spinner placeSpinner;
    @BindView(R.id.timePicker)
    TextInputEditText timePicker;
    @BindView(R.id.datePicker)
    TextInputEditText datePicker;
    @BindView(R.id.sujet)
    TextInputEditText sujet;
    @BindView(R.id.email)
    TextInputEditText email;
    @BindView(R.id.create)
    MaterialButton create;
    @BindView(R.id.chipGroup)
    ChipGroup chipGroup;
    @BindView(R.id.addEmail)
    MaterialButton addEmail;

    private final Calendar localCalendar = Calendar.getInstance();
    private Calendar designedCalendar = Calendar.getInstance();
    private SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private MeetingApiService mApiservice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_meeting);
        ButterKnife.bind(this);
        mApiservice = DI.getMeetingApiService();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.roomListCreate, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        placeSpinner.setAdapter(adapter);
        placeSpinner.setOnItemSelectedListener(this);
        init();
        create.setEnabled(true);
    }

    private void init() {
        addEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createChip(email.getText().toString());
            }
        });
        timePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createTimePickerDialog();
            }
        });
        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDatePickerDialog();
            }
        });
        sujet.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        datePicker.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        timePicker.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @OnClick(R.id.create)
    void createMeeting() {
        boolean save = isSaveAvailable();
        if (save) {
            Meeting meeting = new Meeting(
                    placeSpinner.getSelectedItemPosition(),
                    returnEmailList(),
                    sujet.getText().toString(),
                    timePicker.getText().toString(),
                    datePicker.getText().toString()
            );
            mApiservice.createMeeting(meeting);
            finish();
            }
        else {
            Toast toast = Toast.makeText(getBaseContext(), "Champs non valides", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private void createChip(String email) {
        LayoutInflater inflater = LayoutInflater.from(this);
        Chip chip = (Chip)inflater.inflate(R.layout.chip_model, null, false);
        chip.setText(email);
        chip.setOnCloseIconClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chipGroup.removeView(v);
                checkChipgroupChild();
            }
        });
        if (isValidEmail(email)) {
            chipGroup.addView(chip);
            checkChipgroupChild();
            this.email.getText().clear();
        }
        else {
            Toast toast = Toast.makeText(getBaseContext(), "Email non valide", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private void checkChipgroupChild(){

    }

    public static void navigate(FragmentActivity activity) {
        Intent intent = new Intent(activity, CreateMeetingActivity.class);
        ActivityCompat.startActivity(activity, intent, null);
    }

    private void createTimePickerDialog(){
        int hour = localCalendar.get(Calendar.HOUR_OF_DAY);
        int minute = localCalendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(CreateMeetingActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                designedCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                designedCalendar.set(Calendar.MINUTE, minute);
                timePicker.setText(timeFormat.format(designedCalendar.getTime()));
            }
        }, hour, minute, true);
        timePickerDialog.show();
    }

    private void createDatePickerDialog(){
        int year = localCalendar.get(Calendar.YEAR);
        int month = localCalendar.get(Calendar.MONTH);
        int day = localCalendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(CreateMeetingActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                designedCalendar.set(Calendar.YEAR, year);
                designedCalendar.set(Calendar.MONTH, month);
                designedCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                datePicker.setText(dateFormat.format(designedCalendar.getTime()));
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    public static boolean isValidEmail(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private boolean isSaveAvailable() {
        return datePicker.getEditableText().toString().length() > 0 && timePicker.getEditableText().toString().length() > 0 && chipGroup.getChildCount() > 0 && sujet.getEditableText().toString().length() > 0;
    }

    private String returnEmailList(){
        String emails = "";
        for (int i=0; i<chipGroup.getChildCount(); i++){
            Chip chip = (Chip)chipGroup.getChildAt(i);
            emails += chip.getText() + " ";
        }
        return emails;
    }
}