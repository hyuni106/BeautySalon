package com.thejoeunit.www.beautysalon.activities.user_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.thejoeunit.www.beautysalon.R;
import com.thejoeunit.www.beautysalon.activities.BaseActivity;

public class MakeReviewActivity extends BaseActivity {

    private android.widget.EditText inputReviewEdt;
    private android.widget.Button reviewBtn;
    private android.widget.RadioButton radioBtn01;
    private android.widget.RadioButton radioBtn02;
    private android.widget.RadioButton radioBtn03;
    private android.widget.RadioButton radioBtn04;
    private android.widget.RadioButton radioBtn05;
    private android.widget.RadioGroup ratingRadioGroup;

    int selectRadio = 0;

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

//        ratingRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
//                switch(checkedId){
//                    case R.id.radioBtn01:
//                        selectRadio = 1;
//                        break;
//                    case R.id.radioBtn02:
//                        selectRadio = 2;
//                        break;
//                    case R.id.radioBtn03:
//                        selectRadio = 3;
//                        break;
//                    case R.id.radioBtn04:
//                        selectRadio = 4;
//                        break;
//                    case R.id.radioBtn05:
//                        selectRadio = 5;
//                        break;
//                }
//            }
//        });

        reviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ratingRadioGroup.getCheckedRadioButtonId() != -1) {
                    int checkedRadioBtnId = ratingRadioGroup.getCheckedRadioButtonId();
                    selectRadio = Integer.parseInt(findViewById(checkedRadioBtnId).getTag().toString());
//                    String checedRadioTag = findViewById(checkedRadioBtnId).getTag().toString();
//                    selectRadio = Integer.parseInt(checedRadioTag);

                    Intent intent = new Intent();
                    intent.putExtra("review", inputReviewEdt.getText().toString());
                    intent.putExtra("rating", selectRadio);
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    Toast.makeText(mContext, "점수를 선택해주세요.", Toast.LENGTH_SHORT).show();
                }
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
        this.ratingRadioGroup = (RadioGroup) findViewById(R.id.ratingRadioGroup);
        this.radioBtn05 = (RadioButton) findViewById(R.id.radioBtn05);
        this.radioBtn04 = (RadioButton) findViewById(R.id.radioBtn04);
        this.radioBtn03 = (RadioButton) findViewById(R.id.radioBtn03);
        this.radioBtn02 = (RadioButton) findViewById(R.id.radioBtn02);
        this.radioBtn01 = (RadioButton) findViewById(R.id.radioBtn01);
    }
}
