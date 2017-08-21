package com.thejoeunit.www.beautysalon.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by user on 2017-08-18.
 */

public class ContextUtil {
    private final static String prefName = "salonPref";
    private final static String loginUser = "LOGIN_USER";
    private final static String loginUserId = "LOGIN_USER_ID";
    private final static String loginUserPw = "LOGIN_USER_PW";

    public static void setLoginUser(Context context, String id, String pw) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        pref.edit().putBoolean(loginUser, true).commit();
        pref.edit().putString(loginUserId, id).commit();
        pref.edit().putString(loginUserPw, pw).commit();
    }

    public static String getUserData(Context context, String key, String dftValue) {
        SharedPreferences pref = context.getSharedPreferences(prefName,
                Activity.MODE_PRIVATE);
        try {
            return pref.getString(key, dftValue);
        } catch (Exception e) {
            return dftValue;
        }
    }

    public static boolean getUserData(Context context, String key, boolean dftValue) {
        SharedPreferences pref = context.getSharedPreferences(prefName,
                Activity.MODE_PRIVATE);
        try {
            return pref.getBoolean(key, dftValue);
        } catch (Exception e) {
            return dftValue;
        }
    }

    public static void logoutProcess(Context context) {
        // 메모장에 기록된 사용자 정보를 다 초기화

        // 1. 메모장 파일 Open
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        pref.edit().putBoolean(loginUser, false).commit();
        pref.edit().putString(loginUserId, "").commit();
        pref.edit().putString(loginUserPw, "").commit();
    }
}
