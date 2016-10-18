package com.tzx.aidldemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.tzx.aidldemo.Utils.LogUtils;
import com.tzx.aidldemo.aidl.Book;
import com.tzx.aidldemo.aidl.IBookManager;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by tanzhenxing
 * Date: 2016/10/17.
 * Description:远程服务
 */
public class BookManagerService extends Service {
    //支持并发读写
    private CopyOnWriteArrayList<Book> mBookList = new CopyOnWriteArrayList<>();
    private Binder mBinder = new IBookManager.Stub() {

        @Override
        public List<Book> getBookList() throws RemoteException {
            LogUtils.d("calling getBookList~!");
            return mBookList;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            LogUtils.d("calling addBook~!");
            mBookList.add(book);
        }
    };
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        LogUtils.d("onBind");
        return mBinder;
    }
}
