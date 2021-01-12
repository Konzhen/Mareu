package com.lamzone.maru;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lamzone.maru.DI.DI;
import com.lamzone.maru.events.DeleteMeetingEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import model.MeetingApiService;

public class MeetingActivity extends AppCompatActivity {

    @BindView(R.id.meetingRecycler)
    RecyclerView meetingRecycler;

    private MeetingApiService mApiService = DI.getMeetingApiService();
    private MeetingAdapter mAdapter;

    @Subscribe
    public void onDeleteMeeting(DeleteMeetingEvent event) {
        mApiService.deleteMeeting(event.meeting);
        String log = "log";
        Log.v(log, "ok");
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
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

}