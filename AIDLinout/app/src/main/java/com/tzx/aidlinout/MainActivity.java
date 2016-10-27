package com.tzx.aidlinout;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tzx.aidlinout.Utils.LogUtils;
import com.tzx.aidlinout.aidl.Book;
import com.tzx.aidlinout.aidl.IBookManager;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText bookIdET;
    private EditText bookNameET;
    private Button inBtn;
    private Button outBtn;
    private Button inoutBtn;
    private TextView bookinfoTV;
    private IBookManager bookManager;
    private boolean mBound = false;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            bookManager = IBookManager.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        if (!mBound) {
            Intent intent = new Intent(this, BookManagerService.class);
            bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
            mBound = true;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mBound) {
            unbindService(serviceConnection);
            mBound = false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    private void initView() {
        bookinfoTV = (TextView) findViewById(R.id.book_info);
        bookIdET = (EditText) findViewById(R.id.book_id_et);
        bookNameET = (EditText) findViewById(R.id.book_name_et);
        inBtn = (Button) findViewById(R.id.book_in);
        outBtn = (Button) findViewById(R.id.book_out);
        inoutBtn = (Button) findViewById(R.id.book_inout);
    }

    private void initListener() {
        inBtn.setOnClickListener(this);
        outBtn.setOnClickListener(this);
        inoutBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.book_in:
                try {
                    int bookId = Integer.parseInt(bookIdET.getText().toString());
                    String bookName = bookNameET.getText().toString();
                    if (bookId <= 0 || TextUtils.isEmpty(bookName)) return;
                    StringBuilder builder = new StringBuilder();
                    LogUtils.d("-----------book_in-----------------");
                    Book book0 = new Book(bookId, bookName);
                    String source = "source:" + book0.toString();
                    LogUtils.d(source);
                    builder.append(source);
                    builder.append('\n');
                    String result = "result:" + bookManager.addInBook(book0).toString();
                    LogUtils.d(result);
                    builder.append(result);
                    builder.append('\n');
                    source = "source" + book0.toString();
                    LogUtils.d(source);
                    builder.append(source);
                    LogUtils.d("**************book_in****************");
                    bookinfoTV.setText(builder.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.book_out:
                try {
                    int bookId = Integer.parseInt(bookIdET.getText().toString());
                    String bookName = bookNameET.getText().toString();
                    if (bookId <= 0 || TextUtils.isEmpty(bookName)) return;
                    StringBuilder builder = new StringBuilder();
                    LogUtils.d("-----------book_out-----------------");
                    Book book0 = new Book(bookId, bookName);
                    String source = "source:" + book0.toString();
                    LogUtils.d(source);
                    builder.append(source);
                    builder.append('\n');
                    String result = "result:" + bookManager.addOutBook(book0).toString();
                    LogUtils.d(result);
                    builder.append(result);
                    builder.append('\n');
                    source = "source" + book0.toString();
                    LogUtils.d(source);
                    builder.append(source);
                    LogUtils.d("**************book_out****************");
                    bookinfoTV.setText(builder.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.book_inout:
                try {
                    int bookId = Integer.parseInt(bookIdET.getText().toString());
                    String bookName = bookNameET.getText().toString();
                    if (bookId <= 0 || TextUtils.isEmpty(bookName)) return;
                    StringBuilder builder = new StringBuilder();
                    LogUtils.d("-----------book_inout-----------------");
                    Book book0 = new Book(bookId, bookName);
                    String source = "source:" + book0.toString();
                    LogUtils.d(source);
                    builder.append(source);
                    builder.append('\n');
                    String result = "result:" + bookManager.addInoutBook(book0).toString();
                    LogUtils.d(result);
                    builder.append(result);
                    builder.append('\n');
                    source = "source" + book0.toString();
                    LogUtils.d(source);
                    builder.append(source);
                    LogUtils.d("**************book_inout****************");
                    bookinfoTV.setText(builder.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
