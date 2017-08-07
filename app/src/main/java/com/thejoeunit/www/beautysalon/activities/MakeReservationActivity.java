package com.thejoeunit.www.beautysalon.activities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.thejoeunit.www.beautysalon.R;
import com.thejoeunit.www.beautysalon.datas.DesignCase;
import com.thejoeunit.www.beautysalon.datas.Designer;
import com.thejoeunit.www.beautysalon.datas.User;
import com.thejoeunit.www.beautysalon.utils.DateTimeUtil;
import com.thejoeunit.www.beautysalon.utils.GeneralUtil;

import java.util.ArrayList;
import java.util.Calendar;

public class MakeReservationActivity extends BaseActivity {

    private android.widget.TextView dateTxt;
    private android.widget.Button selectDateBtn;
    private TextView timeTxt;
    private Button selectTimeBtn;

    Calendar mReservDate = Calendar.getInstance();
    private Button confirmBtn;
    private android.widget.Spinner styleHairSpinner;

    ArrayList<String> hairStyles = new ArrayList<String>();
    private Spinner categorySpinner;

    ArrayAdapter<String> StyleAdapter;

    private Designer mDesigner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_reservation);

        bindViews();
        setUpEvents();
        setValues();
        addHairStyles(0);
    }

    private void addHairStyles(int categoryIndex) {
        hairStyles.clear();
        if(categoryIndex == 0) {
            hairStyles.add("반삭");
            hairStyles.add("샤기컷");
            hairStyles.add("댄디컷");
            hairStyles.add("투블럭");
        } else if(categoryIndex == 1) {
            hairStyles.add("다운펌");
            hairStyles.add("가르마");
            hairStyles.add("매직");
        } else if(categoryIndex == 2) {
            hairStyles.add("빨강");
            hairStyles.add("파랑");
            hairStyles.add("노랑");
            hairStyles.add("탈색");
        }

        StyleAdapter.notifyDataSetChanged();
    }


    @Override
    public void setUpEvents() {
        super.setUpEvents();
        selectDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(mContext, "날짜 선택창이 떠야합니다.", Toast.LENGTH_SHORT).show();
                new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        mReservDate.set(Calendar.YEAR, year);
                        mReservDate.set(Calendar.MONTH, month);
                        mReservDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        dateTxt.setText(DateTimeUtil.getDateString(mReservDate));

//                        Toast.makeText(mContext, getReservvationDateString() +"", Toast.LENGTH_SHORT).show();
                    }
                }, mReservDate.get(Calendar.YEAR), mReservDate.get(Calendar.MONTH), mReservDate.get(Calendar.DAY_OF_MONTH)).show();
            }
        }); // 이벤트 핸들링 (이벤트 처리)

        selectTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(mContext, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        mReservDate.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        mReservDate.set(Calendar.MINUTE, minute);
                        timeTxt.setText(DateTimeUtil.getTimeString(mReservDate));
//                        Toast.makeText(mContext, getReservationTimeString() + "", Toast.LENGTH_SHORT).show();
                    }
                }, mReservDate.get(Calendar.HOUR_OF_DAY), mReservDate.get(Calendar.MINUTE), false).show();
            }
        });

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtil.globalDesignCase.add(new DesignCase(-1, mReservDate, -1, mDesigner, new User(), -1, null));
                Log.d("미용사례개수", GeneralUtil.globalDesignCase.size()+"개");
                Toast.makeText(mContext, R.string.reservation_message, Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                addHairStyles(position);
//                Toast.makeText(mContext, position + "번 줄 선택", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void setValues() {
        super.setValues();
        mDesigner = (Designer)getIntent().getSerializableExtra("designer");
        StyleAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, hairStyles);
        styleHairSpinner.setAdapter(StyleAdapter);

        String dateText = DateTimeUtil.getDateString(mReservDate);
        dateTxt.setText(dateText);

        String timeText = DateTimeUtil.getTimeString(mReservDate);
        timeTxt.setText(timeText);
    }

    @Override
    public void bindViews() {
        super.bindViews();
        this.confirmBtn = (Button) findViewById(R.id.confirmBtn);
        this.selectTimeBtn = (Button) findViewById(R.id.selectTimeBtn);
        this.timeTxt = (TextView) findViewById(R.id.timeTxt);
        this.selectDateBtn = (Button) findViewById(R.id.selectDateBtn);
        this.dateTxt = (TextView) findViewById(R.id.dateTxt);
        this.styleHairSpinner = (Spinner) findViewById(R.id.styleHairSpinner);
        this.categorySpinner = (Spinner) findViewById(R.id.categorySpinner);
    }
}
