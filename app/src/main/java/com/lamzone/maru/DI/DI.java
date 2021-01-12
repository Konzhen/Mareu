package com.lamzone.maru.DI;

import com.lamzone.maru.model.DummyMeetingApiService;
import com.lamzone.maru.model.MeetingApiService;

public class DI {

    private static MeetingApiService service = new DummyMeetingApiService();

    public static MeetingApiService getMeetingApiService() {
        return service;
    }

    public static MeetingApiService getNewInstanceApiService() {
        return new DummyMeetingApiService();
    }
}
