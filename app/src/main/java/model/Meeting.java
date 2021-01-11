package model;

import android.provider.ContactsContract;
import android.text.format.Time;

import java.util.Date;

public class Meeting {



    private String mTime = new String();
    private String mPlace = new String();
    private String mEmail = new String();
    private String mSubject = new String();
    private String mUrl = new String();
    private static long mId = 0;

    public Meeting(String mPlace, String mEmail, String mSubject) {
        this.mPlace = mPlace;
        this.mEmail = mEmail;
        this.mSubject = mSubject;
    }

    public String getTime() { return mTime; }

    public void setTime(String time) {
        this.mTime = time;
    }

    public String getPlace() {
        return mPlace;
    }

    public void setPlace(String place) {
        this.mPlace = place;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        this.mEmail = email;
    }

    public static long getId() { return mId; }

    public static void setId(long mId) { Meeting.mId = mId; }

    public String getSubject() {
        return mSubject;
    }

    public void setSubject(String mSubject) { this.mSubject = mSubject; }

    public String getUrl() { return mUrl; }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }
}
