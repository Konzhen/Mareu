package com.lamzone.maru.model;

import java.util.Arrays;
import java.util.List;

public abstract class MeetingList {

    public static List<Meeting> MeetingList = Arrays.asList(
            new Meeting("room A", "bernard@yahoo.fr, jean-paul@hotmail.com, maxence@tropcool.com, ertgerftgsefyzertgbedrfgysdfhertfhbsrefhsredtfhbrfhedrfgrtgzs", "la petite vendeuse"),
            new Meeting("room B", "gerard@hotmail.fr", "le boulanger du coin"),
            new Meeting("room C", "yvette@gmail.com", "le gentil poissonier"),
            new Meeting("room D", "philippe@hotmail.com", "le dernier film au ciné"),
            new Meeting("room E", "vladimir@putin.ru", "priviet")
    );

    public static List<Meeting> generateMeetingList(){
        return MeetingList;
    }

}
