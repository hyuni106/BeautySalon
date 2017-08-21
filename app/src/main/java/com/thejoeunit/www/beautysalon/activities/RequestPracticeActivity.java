package com.thejoeunit.www.beautysalon.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import com.thejoeunit.www.beautysalon.R;

public class RequestPracticeActivity extends BaseActivity {

    private android.widget.EditText inputEdtTxt;
    private android.widget.Button okBtn;
    private android.widget.SeekBar avgSeekBar;
    private Button okBtn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_practice);
        bindViews();
        setUpEvents();
        setValues();
    }

    @Override
    public void setUpEvents() {
        super.setUpEvents();
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent finishIntent = new Intent();
                finishIntent.putExtra("practice", inputEdtTxt.getText().toString());
                setResult(RESULT_OK, finishIntent);
                finish();
            }
        });



        okBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value = avgSeekBar.getProgress();
                Intent finishIntent = new Intent();
                finishIntent.putExtra("practice", value);
                setResult(RESULT_OK, finishIntent);
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
        this.okBtn2 = (Button) findViewById(R.id.okBtn2);
        this.avgSeekBar = (SeekBar) findViewById(R.id.avgSeekBar);
        this.okBtn = (Button) findViewById(R.id.okBtn);
        this.inputEdtTxt = (EditText) findViewById(R.id.inputEdtTxt);
    }
}
