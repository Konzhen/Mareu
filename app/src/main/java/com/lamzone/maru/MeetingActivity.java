package com.lamzone.maru;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.lamzone.maru.events.DeleteMeetingEvent;

import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import model.MeetingContainer;

public class MeetingActivity extends AppCompatActivity {

    @BindView(R.id.meetingRecycler)
    RecyclerView meetingRecycler;

    MeetingAdapter adapter = new MeetingAdapter();

    @Subscribe
    public void onDeleteMeeting(DeleteMeetingEvent event) {
        adapter.mMeetings.deleteMeeting(event.meeting);
        meetingRecycler.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting);
        ButterKnife.bind(this);
        meetingRecycler.setAdapter(adapter);
        meetingRecycler.setLayoutManager(new LinearLayoutManager(this));


    }

}