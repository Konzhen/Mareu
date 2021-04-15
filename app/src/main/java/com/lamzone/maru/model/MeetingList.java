package com.lamzone.maru.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class MeetingList {

    public static List<Meeting> MeetingList = Arrays.asList(
    );

    public static List<Meeting> generateMeetingList(){
        List<Meeting> meetings = new ArrayList<>();
        meetings.addAll(MeetingList);
        return meetings;
    }

}
