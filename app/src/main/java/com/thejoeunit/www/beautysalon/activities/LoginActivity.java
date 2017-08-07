package com.thejoeunit.www.beautysalon.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.thejoeunit.www.beautysalon.R;

public class LoginActivity extends BaseActivity {
    boolean isWorkerMode = false;
    private android.widget.TextView workerTxt;
    private android.widget.Button loginBtn;
    private EditText idEdtTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bindViews();

        isWorkerMode = getIntent().getBooleanExtra("wokerMode", false);

        setUpEvents();
        setValues();

    }

    @Override
    public void setUpEvents() {
        super.setUpEvents();
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                String inputId = idEdtTxt.getText().toString();
                if (isWorkerMode) {
                    if(inputId.equals("admin")) {
                        intent = new Intent(mContext, AdminMainActivity.class);
                    } else {
                        intent = new Intent(mContext, WorkerMainActivity.class);
                    }
                } else {
                    intent = new Intent(mContext, MainActivity.class);
                }
                startActivity(intent);
                finish();
                SelectLoginMode.activity.finish();
                // 로그인 모드 선택화면 종료
            }
        });
    }

    @Override
    public void setValues() {
        super.setValues();
        if(isWorkerMode) {
            // 직원 로그인 버튼을 눌러서 들어왔다면
            workerTxt.setVisibility(View.VISIBLE);
            // 직원모드 txt 표시
        } else {
            // 회원 로그인 버튼일때
            workerTxt.setVisibility(View.GONE);
        }
    }

    @Override
    public void bindViews() {
        super.bindViews();
        this.loginBtn = (Button) findViewById(R.id.loginBtn);
        this.workerTxt = (TextView) findViewById(R.id.workerTxt);
        idEdtTxt = (EditText)findViewById(R.id.idEdtTxt);
    }
}
