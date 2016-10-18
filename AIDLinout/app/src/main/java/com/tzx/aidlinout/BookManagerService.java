package com.tzx.aidlinout;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.tzx.aidlinout.aidl.Book;
import com.tzx.aidlinout.aidl.IBookManager;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by tanzhenxing
 * Date: 2016/10/18.
 * Description:
 */
public class BookManagerService extends Service {
    private CopyOnWriteArrayList list = new CopyOnWriteArrayList();
    private IBinder mBinder = new IBookManager.Stub(){

        @Override
        public Book addInBook(Book book) throws RemoteException {
            book.bookId = -1;
            book.bookName = book.bookName + "-in";
            list.add(book);
            return book;
        }

        @Override
        public Book addOutBook(Book book) throws RemoteException {
            book.bookId = -1;
            book.bookName = book.bookName + "-out";
            list.add(book);
            return book;
        }

        @Override
        public Book addInoutBook(Book book) throws RemoteException {
            book.bookId = -1;
            book.bookName = book.bookName + "-inout";
            list.add(book);
            return book;
        }

        @Override
        public List<Book> getBookList() throws RemoteException {
            return list;
        }
    };
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
