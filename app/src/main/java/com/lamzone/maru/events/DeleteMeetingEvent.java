package com.lamzone.maru.events;

import com.lamzone.maru.model.Meeting;

public class DeleteMeetingEvent {

    public Meeting meeting;

    public DeleteMeetingEvent(Meeting meeting) {
        this.meeting = meeting;
    }

}
