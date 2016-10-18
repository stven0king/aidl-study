package com.tzx.aidldemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tzx.aidldemo.Utils.LogUtils;
import com.tzx.aidldemo.aidl.Book;
import com.tzx.aidldemo.aidl.IBookManager;

import java.util.List;

/**
 * Created by tanzhenxing
 * Date: 2016/10/17.
 * Description:主界面
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText bookNameTV;
    private Button bookAddTV;
    private Button bookCountTV;
    private TextView bookInfoTV;
    private Intent bookManagerIntent;
    private boolean mBound = false;
    private IBookManager bookManager;
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LogUtils.d("onServiceConnected");
            bookManager = IBookManager.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        bookManagerIntent = new Intent(this, BookManagerService.class);
        bindService(bookManagerIntent, mConnection, Context.BIND_AUTO_CREATE);
        mBound = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();

    }


    private void initView() {
        bookNameTV = (EditText) findViewById(R.id.book_name);
        bookAddTV = (Button) findViewById(R.id.book_add);
        bookCountTV = (Button) findViewById(R.id.book_count);
        bookInfoTV = (TextView) findViewById(R.id.book_info);
    }

    private void initListener() {
        bookAddTV.setOnClickListener(this);
        bookCountTV.setOnClickListener(this);
    }


    @Override
    protected void onStop() {
        super.onStop();
        if (mBound) {
            mBound = false;
            unbindService(mConnection);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.book_add:
                addBook();
                break;
            case R.id.book_count:
                getBookList();
                break;
        }
    }

    private void addBook() {
        if (bookManager != null && !TextUtils.isEmpty(bookNameTV.getText().toString())) {
            Book book = new Book((int) System.currentTimeMillis(), bookNameTV.getText().toString());
            try {
                bookManager.addBook(book);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void getBookList() {
        try {
            if (bookManager != null) {
                List<Book> list = bookManager.getBookList();
                if (list != null && list.size() > 0) {
                    StringBuilder builder = new StringBuilder();
                    for (Book book : list) {
                        builder.append(book.toString());
                        builder.append('\n');
                    }
                    bookInfoTV.setText(builder.toString());
                } else {
                    bookInfoTV.setText("Empty~!");
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
