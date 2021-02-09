package com.android.lesson6;

import android.os.Parcel;
import android.os.Parcelable;

public class Note implements Parcelable {

    public String title;
    String message;

    public Note(String title, String message){
        this.message = message;
        this.title = title;
    }

    protected Note(Parcel in) {
        title = in.readString();
        message = in.readString();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(message);
    }
}
