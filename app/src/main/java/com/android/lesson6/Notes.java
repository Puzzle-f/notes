package com.android.lesson6;

import android.os.Parcel;
import android.os.Parcelable;

public class Notes implements Parcelable {

    String title;
    String message;

    public Notes(String title, String message){
        this.message = message;
        this.title = title;
    }

    protected Notes(Parcel in) {
        title = in.readString();
        message = in.readString();
    }

    public static final Creator<Notes> CREATOR = new Creator<Notes>() {
        @Override
        public Notes createFromParcel(Parcel in) {
            return new Notes(in);
        }

        @Override
        public Notes[] newArray(int size) {
            return new Notes[size];
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
