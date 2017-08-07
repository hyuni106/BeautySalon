package com.thejoeunit.www.beautysalon.activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by the on 2017-07-26.
 */

public class BaseActivity extends AppCompatActivity {

    public Context mContext = this;

    public void bindViews() {
        // findViewById 선언 모음 메소드

    }

    public void setUpEvents() {
        // onClickListener 등의 이벤트 리스너 모음 메소드

    }

    public void setValues() {
        // TextView 등 xml 객체에 적절한 data 설정 모음 메소드

    }
}
