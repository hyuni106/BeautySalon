package com.thejoeunit.www.beautysalon.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.thejoeunit.www.beautysalon.R;
import com.thejoeunit.www.beautysalon.adapters.PortfolioAdapter;
import com.thejoeunit.www.beautysalon.datas.Designer;

import java.util.ArrayList;

public class ViewDesignerDetailActivity extends BaseActivity {

    private Designer mDesigner;
    private android.widget.TextView nameTxt;
    private android.widget.TextView genderTxt;
    private android.widget.TextView nickNameTxt;
    private android.widget.TextView majorTxt;
    private android.widget.TextView avgRatingTxt;
    private android.widget.ImageView star1;
    private android.widget.ImageView star2;
    private android.widget.ImageView star3;
    private android.widget.ImageView star4;
    private android.widget.ImageView star5;
    ArrayList<ImageView> stars = new ArrayList<ImageView>();
    private android.widget.Button scheCheckBtn;
    private android.widget.Button reservationBtn;
    private android.widget.ListView portfolioListView;
    PortfolioAdapter portfolioAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_designer_detail);

        bindViews();
        setUpEvents();
        setValues();
    }

    @Override
    public void setUpEvents() {
        super.setUpEvents();
        scheCheckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, R.string.preparing_message, Toast.LENGTH_SHORT).show();
            }
        });

        reservationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MakeReservationActivity.class);
                intent.putExtra("designer", mDesigner);
                startActivity(intent);
            }
        });
    }

    @Override
    public void setValues() {
        super.setValues();
        portfolioAdapter = new PortfolioAdapter(mContext, mDesigner.getPortfolio());
        portfolioListView.setAdapter(portfolioAdapter);
        nameTxt.setText(mDesigner.getName());
        if(mDesigner.getGender() == 1) {
            genderTxt.setText(R.string.female);
        } else {
            genderTxt.setText(R.string.male);
        }
        // setText에 String 이외의 객체를 넣으면 코드 작성시엔 에러 X
        // but 실행 시 Error 발생
        // String 이외의 객체 넣을 때 => 객체 + ""
        nickNameTxt.setText(mDesigner.getNickName());
        majorTxt.setText(mDesigner.getMajorAge()+"대");
        avgRatingTxt.setText(mDesigner.getAvgRating()+"");

        int index = (int) mDesigner.getAvgRating();
        for(int i=0; i<index; i++) {
            stars.get(i).setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void bindViews() {
        super.bindViews();
        this.reservationBtn = (Button) findViewById(R.id.reservationBtn);
        this.scheCheckBtn = (Button) findViewById(R.id.scheCheckBtn);
        this.portfolioListView = (ListView) findViewById(R.id.portfolioListView);
        this.star5 = (ImageView) findViewById(R.id.star5);
        this.star4 = (ImageView) findViewById(R.id.star4);
        this.star3 = (ImageView) findViewById(R.id.star3);
        this.star2 = (ImageView) findViewById(R.id.star2);
        this.star1 = (ImageView) findViewById(R.id.star1);
        this.avgRatingTxt = (TextView) findViewById(R.id.avgRatingTxt);
        this.majorTxt = (TextView) findViewById(R.id.majorTxt);
        this.nickNameTxt = (TextView) findViewById(R.id.nickNameTxt);
        this.genderTxt = (TextView) findViewById(R.id.genderTxt);
        this.nameTxt = (TextView) findViewById(R.id.nameTxt);
        mDesigner = (Designer) getIntent().getSerializableExtra("info");
        stars.add(star1);
        stars.add(star2);
        stars.add(star3);
        stars.add(star4);
        stars.add(star5);
    }
}
