package com.lamzone.maru.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class MeetingList {

    public static List<Meeting> MeetingList = Arrays.asList(
            new Meeting(0, "Bertrand@hotmail.fr", "Les chaussures", "18:03", "29/04/2021"),
            new Meeting(4, "Isabelle@hotmail.fr", "Les poissons", "23:07", "29/04/2021"),
            new Meeting(2, "Bernard@hotmail.fr", "Les tableaux", "12:15", "30/04/2021"),
            new Meeting(6, "Rémond@hotmail.fr", "Les carottes", "06:24", "01/05/2021"),
            new Meeting(0, "Didier@hotmail.fr", "Les militaires", "09:35", "02/05/2021"),
            new Meeting(8, "Gérard@hotmail.fr", "Le fouet", "21:30", "30/04/2021"),
            new Meeting(6, "Bernadette@hotmail.fr", "Les paysans", "17:58", "30/04/2021"),
            new Meeting(6, "Henri@hotmail.fr", "Les renards", "13:01", "30/04/2021"),
            new Meeting(3, "Louis@hotmail.fr", "Les chevaux", "12:40", "29/04/2021")
    );

    public static List<Meeting> generateMeetingList(){
        List<Meeting> meetings = new ArrayList<>();
        meetings.addAll(MeetingList);
        return meetings;
    }

    public static List<Meeting> generateEmptyMeetingList(){
        List<Meeting> meetings = new ArrayList<>();
        return meetings;
    }

}
