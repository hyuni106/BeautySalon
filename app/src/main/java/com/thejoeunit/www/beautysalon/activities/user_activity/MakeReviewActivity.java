package com.thejoeunit.www.beautysalon.activities.user_activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.thejoeunit.www.beautysalon.R;
import com.thejoeunit.www.beautysalon.activities.BaseActivity;
import com.thejoeunit.www.beautysalon.datas.DesignCase;
import com.thejoeunit.www.beautysalon.datas.Designer;
import com.thejoeunit.www.beautysalon.utils.GlobalData;

import java.util.Calendar;

public class MakeReviewActivity extends BaseActivity {

    private android.widget.EditText inputReviewEdt;
    private android.widget.Button reviewBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_review);
        bindViews();
        setUpEvents();
        setValues();
    }

    @Override
    public void setUpEvents() {
        super.setUpEvents();
        reviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("review", inputReviewEdt.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    @Override
    public void setValues() {
        super.setValues();
    }

    @Override
    public void bindViews() {
        super.bindViews();
        this.reviewBtn = (Button) findViewById(R.id.reviewBtn);
        this.inputReviewEdt = (EditText) findViewById(R.id.inputReviewEdt);
    }
}
