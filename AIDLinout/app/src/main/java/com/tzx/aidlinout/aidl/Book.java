package com.tzx.aidlinout.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tanzhenxing
 * Date: 2016/10/18.
 * Description:
 */
public class Book implements Parcelable {

    public int bookId;
    public String bookName;

    public Book() {
    }

    public Book(int bookId, String bookName) {
        this.bookId = bookId;
        this.bookName = bookName;
    }

    protected Book(Parcel in) {
        bookId = in.readInt();
        bookName = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(bookId);
        dest.writeString(bookName);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[bookId=")
                .append(bookId)
                .append(",bookName=")
                .append(bookName)
                .append(']');
        return builder.toString();
    }

    public void readFromParcel(Parcel dest) {
        this.bookId = dest.readInt();
        this.bookName = dest.readString();
    }
}
