package com.lamzone.maru;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.lamzone.maru.DI.DI;
import com.lamzone.maru.model.Meeting;
import com.lamzone.maru.model.MeetingApiService;
import com.lamzone.maru.model.MeetingList;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
public class MareuUnitTests {

    private MeetingApiService service;

    @Before
    public void setup() {
        service = DI.getMeetingApiService();
    }

    @Test
    public void getMeetingWithSuccess() {
        List<Meeting> meetings = service.getMeetings();
        List<Meeting> expectedNeighbours = MeetingList.generateMeetingList();
        assertThat(meetings, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteMeetingWithSuccess() {
        Meeting neighbourToDelete = service.getMeetings().get(3);
        service.deleteMeeting(neighbourToDelete);
        assertFalse(service.getMeetings().contains(neighbourToDelete));
    }

    @Test
    public void CreateMeetingWithSuccess() {
        int listSize = service.getMeetings().size();
        service.createMeeting(new Meeting(7, "Jean-Test@leTest.fr", "Ceci est un test", "22:05", "03/05/2021"));
        assertEquals(service.getMeetings().size(), listSize + 1);
    }

    @Test
    public void RoomFilterWithSuccess() {
        int listSize1 = service.getMeetings("", 0).size();
        assertEquals(listSize1, 2);
        int listSize2 = service.getMeetings("", 6).size();
        assertEquals(listSize2, 3);
    }

    @Test
    public void DateFilterWithSuccess() {
        int listSize1 = service.getMeetings("29/04/2021", -1).size();
        assertEquals(listSize1, 3);
        int listSize2 = service.getMeetings("02/05/2021", -1).size();
        assertEquals(listSize2, 1);
    }

    @Test
    public void BothFilterWithSuccess() {
        int listSize1 = service.getMeetings("29/04/2021", 0).size();
        assertEquals(listSize1, 1);
        int listSize2 = service.getMeetings("30/04/2021", 6).size();
        assertEquals(listSize2, 2);
    }

}