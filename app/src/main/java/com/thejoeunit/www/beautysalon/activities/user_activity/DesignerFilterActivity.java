package com.thejoeunit.www.beautysalon.activities.user_activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.thejoeunit.www.beautysalon.R;
import com.thejoeunit.www.beautysalon.activities.BaseActivity;

public class DesignerFilterActivity extends BaseActivity {

    private android.widget.TextView okBtnTxt;
    private android.widget.ToggleButton manSelectToggleBtn;
    private android.widget.ToggleButton womanSelectToggleBtn;
    private android.widget.SeekBar ratingSeekBar;
    private TextView minRatingTxt;
    private android.widget.EditText nickSearchEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_designer_filter);
        bindViews();
        setUpEvents();
        setValues();
    }

    @Override
    public void setUpEvents() {
        super.setUpEvents();
        okBtnTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent finishIntent = new Intent();
                finishIntent.putExtra("manSelect", manSelectToggleBtn.isChecked());
                finishIntent.putExtra("womanSelect", womanSelectToggleBtn.isChecked());
                finishIntent.putExtra("ratingSelect", ratingSeekBar.getProgress());
                finishIntent.putExtra("searchNick", nickSearchEdt.getText().toString());
                setResult(RESULT_OK, finishIntent);
                finish();
            }
        });

        ratingSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                minRatingTxt.setText(progress + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    @Override
    public void setValues() {
        super.setValues();
    }

    @Override
    public void bindViews() {
        this.nickSearchEdt = (EditText) findViewById(R.id.nickSearchEdt);
        this.minRatingTxt = (TextView) findViewById(R.id.minRatingTxt);
        this.ratingSeekBar = (SeekBar) findViewById(R.id.ratingSeekBar);
        this.womanSelectToggleBtn = (ToggleButton) findViewById(R.id.womanSelectToggleBtn);
        this.manSelectToggleBtn = (ToggleButton) findViewById(R.id.manSelectToggleBtn);
        this.okBtnTxt = (TextView) findViewById(R.id.okBtnTxt);
    }
}
