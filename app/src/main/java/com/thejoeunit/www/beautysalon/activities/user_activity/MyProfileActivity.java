package com.thejoeunit.www.beautysalon.activities.user_activity;

import android.os.Bundle;
import android.widget.TextView;

import com.thejoeunit.www.beautysalon.R;
import com.thejoeunit.www.beautysalon.activities.BaseActivity;
import com.thejoeunit.www.beautysalon.utils.ContextUtil;

public class MyProfileActivity extends BaseActivity {

    private android.widget.TextView userIdTxt;
    private TextView userNameTxt;
    private TextView userGenderTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        bindViews();
        setUpEvents();
        setValues();
    }

    @Override
    public void setUpEvents() {
        super.setUpEvents();
    }

    @Override
    public void setValues() {
        super.setValues();
        userIdTxt.setText(ContextUtil.getLoginUserId(mContext));
//        userNameTxt.setText(ContextUtil.getLoginUser(mContext).getName());
//        if (ContextUtil.getLoginUser(mContext).getGender() == 1) {
//            userGenderTxt.setText("여자");
//        } else {
//            userGenderTxt.setText("남자");
//        }
    }

    @Override
    public void bindViews() {
            super.bindViews();
        this.userGenderTxt = (TextView) findViewById(R.id.userGenderTxt);
        this.userNameTxt = (TextView) findViewById(R.id.userNameTxt);
        this.userIdTxt = (TextView) findViewById(R.id.userIdTxt);
    }
}
