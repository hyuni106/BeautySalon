package com.thejoeunit.www.beautysalon.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.thejoeunit.www.beautysalon.R;
import com.thejoeunit.www.beautysalon.activities.user_activity.MainActivity;
import com.thejoeunit.www.beautysalon.activities.worker_activity.WorkerMainActivity;
import com.thejoeunit.www.beautysalon.datas.Designer;
import com.thejoeunit.www.beautysalon.utils.ContextUtil;
import com.thejoeunit.www.beautysalon.utils.GlobalData;

import java.util.ArrayList;
import java.util.Calendar;

public class LoginActivity extends BaseActivity {
    boolean isWorkerMode = false;   // 직원모드를 판별해주는 변수
    private TextView workerTxt;
    private EditText idEdtTxt;
    private Button loginBtn;
    private android.widget.CheckBox autoCheckBox;
    private EditText pwEdtTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        isWorkerMode = getIntent().getBooleanExtra("mode", false);
        bindViews();
        setUpEvents();
        setValues();
    }

    @Override
    public void setValues() {
        if (isWorkerMode) {
            workerTxt.setVisibility(View.VISIBLE);
        } else {
            workerTxt.setVisibility(View.GONE);
        }

        if (ContextUtil.getUserData(mContext, "LOGIN_USER", false)) {
            autoCheckBox.setChecked(true);
            idEdtTxt.setText(ContextUtil.getUserData(mContext, "LOGIN_USER_ID", ""));
            pwEdtTxt.setText(ContextUtil.getUserData(mContext, "LOGIN_USER_PW", ""));
        } else {
            ContextUtil.logoutProcess(mContext);
        }
    }

    @Override
    public void setUpEvents() {
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (autoCheckBox.isChecked()) {
                    ContextUtil.setLoginUser(mContext, idEdtTxt.getText().toString(), pwEdtTxt.getText().toString());
                }
                Intent intent;
                if (isWorkerMode) {
                    intent = new Intent(mContext, WorkerMainActivity.class);
                } else {
                    intent = new Intent(mContext, MainActivity.class);
                }
                GlobalData.loginUser.setName(idEdtTxt.getText().toString());
                GlobalData.loginUser.setGender(1);
                startActivity(intent);
            }
        });
    }

    @Override
    public void bindViews() {
        this.loginBtn = (Button) findViewById(R.id.loginBtn);
        this.autoCheckBox = (CheckBox) findViewById(R.id.autoCheckBox);
        this.pwEdtTxt = (EditText) findViewById(R.id.pwEdtTxt);
        this.idEdtTxt = (EditText) findViewById(R.id.idEdtTxt);
        this.workerTxt = (TextView) findViewById(R.id.workerTxt);
    }
}
