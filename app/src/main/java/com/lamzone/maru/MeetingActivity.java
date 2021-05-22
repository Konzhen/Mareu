package com.lamzone.maru;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lamzone.maru.DI.DI;
import com.lamzone.maru.controller.DialogFragmentFilter;
import com.lamzone.maru.events.DeleteMeetingEvent;
import com.lamzone.maru.model.MeetingApiService;
import com.lamzone.maru.view.CreateMeetingActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MeetingActivity extends AppCompatActivity implements DialogFragmentFilter.DialogFilterListener{

    @BindView(R.id.createMeetingButton)
    FloatingActionButton createMeetingButton;
    @BindView(R.id.meetingRecycler)
    RecyclerView meetingRecycler;

    private MeetingApiService mApiService = DI.getMeetingApiService();
    private MeetingAdapter mAdapter;

    @Subscribe
    public void onDeleteMeeting(DeleteMeetingEvent event) {
        mApiService.deleteMeeting(event.meeting);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting);
        ButterKnife.bind(this);
        mAdapter = new MeetingAdapter(mApiService.getMeetings());
        meetingRecycler.setAdapter(mAdapter);
        meetingRecycler.setLayoutManager(new LinearLayoutManager(this));
        meetingRecycler.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sort_button, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.sort_button) {
            showDialogFilter(); }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        mAdapter.setMeetings(mApiService.getMeetings());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mApiService.clearMeetings();
        mAdapter.notifyDataSetChanged();
    }

    public void showDialogFilter() {
        DialogFragment dialog = new DialogFragmentFilter();
        dialog.show(getSupportFragmentManager(), "DialogFragmentFilter");
    }

    @OnClick(R.id.createMeetingButton)
    void setCreateMeetingButton(){
        CreateMeetingActivity.navigate(this);
    }


    @Override
    public void onDialogPositiveClick(DialogFragmentFilter dialog) {
        if (dialog.getDialogDate() == "")
            Log.v("ok", "parfait");
        mAdapter.setMeetings(mApiService.getMeetings(dialog.getDialogDate(), dialog.getDialogSpinner()));
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDialogNegativeClick(DialogFragmentFilter dialog) {

    }
}