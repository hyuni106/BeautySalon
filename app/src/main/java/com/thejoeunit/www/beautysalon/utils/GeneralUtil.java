package com.thejoeunit.www.beautysalon.utils;

import com.thejoeunit.www.beautysalon.datas.DesignCase;
import com.thejoeunit.www.beautysalon.datas.Designer;
import com.thejoeunit.www.beautysalon.datas.User;

import java.util.ArrayList;

/**
 * Created by the on 2017-07-27.
 */

public class GeneralUtil {
    // 앱에서 공통적으로 사용되는 데이터를 임시 저장하는 클래스
    // 대부분의 변수/메소드는 static으로
    // GeneralUtil.메소드(), GeneralUtil.변수

    public static ArrayList<DesignCase> globalDesignCase = new ArrayList<DesignCase>();
    public static ArrayList<Designer> designers = new ArrayList<Designer>();
    public static ArrayList<User> users = new ArrayList<User>();
}
