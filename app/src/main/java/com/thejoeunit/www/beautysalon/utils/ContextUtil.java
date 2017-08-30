package com.thejoeunit.www.beautysalon.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.thejoeunit.www.beautysalon.datas.User;

/**
 * Created by user on 2017-08-18.
 */

public class ContextUtil {

    //    로그인한 사용자의 정보를 담는데 이용할 변수
//    만약, loginUser가 null 이라면 로그인한 사람이 없다 > 비로그인 상태
//    만약, loginUser에 어떤 객체가 들어있다면 로그인상태
    private static User loginUser = null;

    private final static String prefName = "salonPref";
    private final static String LOGIN_USER = "LOGIN_USER";
    private final static String LOGIN_USER_ID = "LOGIN_USER_ID";
    private final static String LOGIN_USER_NAME = "LOGIN_USER_NAME";
    private final static String LOGIN_USER_NICKNAME = "LOGIN_USER_NICKNAME";
    private final static String LOGIN_USER_GENDER = "LOGIN_USER_GENDER";
    private final static String LOGIN_USER_PW = "LOGIN_USER_PW";

    public static void setLoginUser(Context context, String id, String pw) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        pref.edit().putBoolean(LOGIN_USER, true).commit();
        pref.edit().putString(LOGIN_USER_ID, id).commit();
        pref.edit().putString(LOGIN_USER_PW, pw).commit();
    }

    public static boolean getAutoLogin(Context context) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Activity.MODE_PRIVATE);
        boolean autoLogin = pref.getBoolean(LOGIN_USER, false);
        return autoLogin;
    }

    public static void setLoginUserId(Context context, String loginIdStr) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        pref.edit().putString(LOGIN_USER_ID, loginIdStr).commit();
    }

    public static String getLoginUserId(Context context) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        String id = pref.getString(LOGIN_USER_ID, null);
        return id;
    }

    public static String getLoginUserPw(Context context) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        String id = pref.getString(LOGIN_USER_PW, null);
        return id;
    }

    public static void logoutProcess(Context context) {
        // 메모장에 기록된 사용자 정보를 다 초기화

        // 1. 메모장 파일 Open
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        pref.edit().putBoolean(LOGIN_USER, false).commit();
        pref.edit().putString(LOGIN_USER_ID, "").commit();
        pref.edit().putString(LOGIN_USER_PW, "").commit();
    }

    public static void setLoginUser(Context context, String name, int gender) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        pref.edit().putString(LOGIN_USER_NAME, name).commit();
        pref.edit().putInt(LOGIN_USER_GENDER, gender).commit();

        loginUser = new User();
    }

    public static User getLoginUser(Context context) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        if (loginUser != null) {
            loginUser.setName(pref.getString(LOGIN_USER_NAME, ""));
            loginUser.setGender(pref.getInt(LOGIN_USER_GENDER, -1));
        }
        return loginUser;
    }

    public static void logout(Context context) {
        loginUser = null;
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        pref.edit().putString(LOGIN_USER_NAME, "").commit();
        pref.edit().putInt(LOGIN_USER_GENDER, -1).commit();
    }
}
