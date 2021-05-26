package com.lamzone.maru.model;

import com.lamzone.maru.R;

public class Meeting {

    private String mTime;
    private String mDate;
    private int mRoomPosition;
    private String mEmail;
    private String mSubject;
    private static long mId = 0;

    public Meeting(int mRoomPosition, String mEmail, String mSubject, String mTime, String mDate) {
        this.mRoomPosition = mRoomPosition;
        this.mEmail = mEmail;
        this.mSubject = mSubject;
        this.mTime = mTime;
        this.mDate = mDate;
    }

    public int getRoom() {
        return mRoomPosition;
    }

    public String getEmail() {
        return mEmail;
    }

    public static long getId() { return mId; }

    public static void setId(long mId) { Meeting.mId = mId; }

    public String getSubject() {
        return mSubject;
    }

    public String getTime() { return mTime; }

    public String getDate() {
        return mDate;
    }

    public int getUrl (int mRoomPosition){
        switch (mRoomPosition) {
            case 0 :
                return R.drawable.letter_a;
            case 1 :
                return R.drawable.letter_b;
            case 2 :
                return R.drawable.letter_c;
            case 3 :
                return R.drawable.letter_d;
            case 4 :
                return R.drawable.letter_e;
            case 5 :
                return R.drawable.letter_f;
            case 6 :
                return R.drawable.letter_g;
            case 7 :
                return R.drawable.letter_h;
            case 8 :
                return R.drawable.letter_i;
            case 9 :
                return R.drawable.letter_j;
        }

        return mRoomPosition;
    }


}
