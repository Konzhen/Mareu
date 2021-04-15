package com.lamzone.maru.model;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DummyMeetingApiService implements MeetingApiService {

    private List<Meeting> meetings;

    public DummyMeetingApiService(){
        meetings = MeetingList.generateMeetingList();
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        meetings.remove(meeting);
    }

    @Override
    public void createMeeting(Meeting meeting) {
        meetings.add(meeting);
    }

    @Override
    public List<Meeting> getMeetings(@Nullable String date, int room) {
        ArrayList<Meeting> filter = new ArrayList();
        for (Meeting meeting : meetings) {
            if (date == null && room == -1)
                return meetings;
            if (meeting.getDate().equals(date) && room == -1)
                filter.add(meeting);
            if (date == null && meeting.getRoom() == room)
                filter.add(meeting);
            if (meeting.getDate().equals(date) && meeting.getRoom() == room)
                filter.add(meeting);
        }
        return filter;
    }

}
