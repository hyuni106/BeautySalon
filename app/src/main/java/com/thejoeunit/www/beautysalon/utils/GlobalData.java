package com.thejoeunit.www.beautysalon.utils;

import com.thejoeunit.www.beautysalon.datas.DesignCase;
import com.thejoeunit.www.beautysalon.datas.Designer;
import com.thejoeunit.www.beautysalon.datas.User;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by the on 2017-07-27.
 */

public class GlobalData {
    // 앱에서 공통적으로 사용되는 데이터를 임시 저장하는 클래스
    // 대부분의 변수/메소드는 static으로
    // GlobalData.메소드(), GlobalData.변수

    public static List<DesignCase> globalDesignCase = new ArrayList<>();
    public static List<Designer> designers = new ArrayList<>();
    public static List<User> users = new ArrayList<>();

    public static User loginUser = new User();
}
