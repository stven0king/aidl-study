package com.tzx.aidldemo.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tanzhenxing
 * Date: 2016/10/17.
 * Description:书籍
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
    //从序列化后的对象中创建原始对象
    protected Book(Parcel in) {
        bookId = in.readInt();
        bookName = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        //从序列化后的对象中创建原始对象
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }
        //指定长度的原始对象数组
        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
    //返回当前对象的内容描述。如果含有文件描述符，返回1，否则返回0，几乎所有情况都返回0
    @Override
    public int describeContents() {
        return 0;
    }
    //将当前对象写入序列化结构中，其flags标识有两种（1|0）。
    //为1时标识当前对象需要作为返回值返回，不能立即释放资源，几乎所有情况下都为0.
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(bookId);
        dest.writeString(bookName);
    }

    @Override
    public String toString() {
        return "[bookId=" + bookId + ",bookName='" + bookName + "']";
    }
}
