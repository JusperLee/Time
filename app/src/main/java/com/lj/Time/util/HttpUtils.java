package com.lj.Time.util;

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * HTTP 工具类
 */

public class HttpUtils {

    private static final String TAG = "HttpUtils";

    public static String encode(String text){
        try{
            text = URLEncoder.encode(text, "utf-8");
        } catch(UnsupportedEncodingException e){
            Log.e(TAG, e.toString());
        }
        return text;
    }
}
