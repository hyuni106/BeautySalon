package com.thejoeunit.www.beautysalon.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.thejoeunit.www.beautysalon.R;

public class SelectLoginModeActivity extends BaseActivity {
    private Button userLoginBtn;
    private Button workerLoginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_login_mode);
        bindViews();
        setUpEvents();
        setValues();
    }

    @Override
    public void setValues() {

    }

    @Override
    public void setUpEvents() {
        userLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, LoginActivity.class);
                intent.putExtra("mode", false);
                startActivity(intent);
            }
        });

        workerLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, LoginActivity.class);
                intent.putExtra("mode", true);
                startActivity(intent);
            }
        });
    }

    @Override
    public void bindViews() {
        this.workerLoginBtn = (Button) findViewById(R.id.workerLoginBtn);
        this.userLoginBtn = (Button) findViewById(R.id.userLoginBtn);
    }
}
