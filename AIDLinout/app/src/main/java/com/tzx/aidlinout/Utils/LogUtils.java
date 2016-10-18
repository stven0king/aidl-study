package com.tzx.aidlinout.Utils;

import android.util.Log;

/**
 * Created by tanzhenxing
 * Date: 2016/10/17.
 * Description:
 */
public class LogUtils {
    static final String TAG = "tanzhenxing";
    public static void d(String s) {
        Log.d(TAG, s);
    }

    public static void d(long l) {
        Log.d(TAG, String.valueOf(l));
    }

    public static void d(float s) {
        Log.d(TAG, String.valueOf(s));
    }

    public static void d(int s) {
        Log.d(TAG, String.valueOf(s));
    }
}
