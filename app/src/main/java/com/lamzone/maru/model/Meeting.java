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

    public int getUrl (int mRoomPosition) {
        switch (mRoomPosition) {
            case 0:
                return R.drawable.letter_a;
            case 1:
                return R.drawable.letter_b;
            case 2:
                return R.drawable.letter_c;
            case 3:
                return R.drawable.letter_d;
            case 4:
                return R.drawable.letter_e;
            case 5:
                return R.drawable.letter_f;
            case 6:
                return R.drawable.letter_g;
            case 7:
                return R.drawable.letter_h;
            case 8:
                return R.drawable.letter_i;
            case 9:
                return R.drawable.letter_j;
        }
        return mRoomPosition;
    }

        public String getStringRoom(int mRoomPosition){
            switch (mRoomPosition) {
                case 0 :
                    return "Réunion A";
                case 1 :
                    return "Réunion B";
                case 2 :
                    return "Réunion C";
                case 3 :
                    return "Réunion D";
                case 4 :
                    return "Réunion E";
                case 5 :
                    return "Réunion F";
                case 6 :
                    return "Réunion G";
                case 7 :
                    return "Réunion H";
                case 8 :
                    return "Réunion I";
                case 9 :
                    return "Réunion J";
            }
            return null;
    }
}
