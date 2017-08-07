package com.thejoeunit.www.beautysalon.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.thejoeunit.www.beautysalon.R;

public class SelectLoginMode extends BaseActivity {
    public  static SelectLoginMode activity;
    // 어느 클래스나 접근 가능
    // static => 객체화 하지 않아도 접근 가능하게
    // => 자료형 자체한테 접근 허가 하려고
    // 자료형 : SelectLoginMode
    // 변수명 : activity

    private android.widget.Button userLoginBtn;
    private android.widget.Button workerLoginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_login_mode);
        activity = this;

        bindViews();
        setUpEvents();
    }

    @Override
    public void bindViews() {
        super.bindViews();
        this.workerLoginBtn = (Button) findViewById(R.id.workerLoginBtn);
        this.userLoginBtn = (Button) findViewById(R.id.userLoginBtn);
    }

    @Override
    public void setUpEvents() {
        super.setUpEvents();
        workerLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // userLoginBtn이 눌리면 실행될 일
                Intent intent = new Intent(mContext, LoginActivity.class);
                intent.putExtra("wokerMode", true);
                startActivity(intent);
            }
        });

        userLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, LoginActivity.class);
                intent.putExtra("wokerMode", false);
                startActivity(intent);
            }
        });
    }
}
