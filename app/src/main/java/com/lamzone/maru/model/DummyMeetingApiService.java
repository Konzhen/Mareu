package com.lamzone.maru.model;

import androidx.annotation.VisibleForTesting;

import java.util.ArrayList;
import java.util.List;

public class DummyMeetingApiService implements MeetingApiService {

    private List<Meeting> meetings;

    public DummyMeetingApiService(){
        meetings = MeetingList.generateEmptyMeetingList();
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
    public List<Meeting> getMeetings(String date, int room) {
        ArrayList<Meeting> filter = new ArrayList();
        for (Meeting meeting : meetings) {
            if (date.equals("") && room == -1)
                return meetings;
            if (meeting.getDate().equals(date) && room == -1)
                filter.add(meeting);
            if (date.equals("") && meeting.getRoom() == room)
                filter.add(meeting);
            if (meeting.getDate().equals(date) && meeting.getRoom() == room)
                filter.add(meeting);
        }
        return filter;
    }

    @Override
    public List<Meeting> getMeetings(){
        return getMeetings("", -1);
    }

    @Override
    public void clearMeetings(){
        meetings.clear();
    }

    @VisibleForTesting
    public void createTestList () {
        meetings = MeetingList.generateMeetingList();
    }

}
