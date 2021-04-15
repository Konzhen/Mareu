package com.lamzone.maru.model;

import androidx.annotation.Nullable;

import java.util.List;

public interface MeetingApiService {

    List<Meeting> getMeetings(@Nullable String date, int room);

    void deleteMeeting(Meeting meeting);

    void createMeeting(Meeting meeting);

}
