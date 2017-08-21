package com.thejoeunit.www.beautysalon.activities.user_activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.thejoeunit.www.beautysalon.R;
import com.thejoeunit.www.beautysalon.activities.BaseActivity;
import com.thejoeunit.www.beautysalon.adapters.DesignCaseAdapter;
import com.thejoeunit.www.beautysalon.datas.DesignCase;
import com.thejoeunit.www.beautysalon.datas.Designer;
import com.thejoeunit.www.beautysalon.utils.GlobalData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class ViewDesignerDetailActivity extends BaseActivity {

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
    private android.widget.ListView portfolioListView;
    private android.widget.Button makeReviewBtn;
    private android.widget.Button reservationBtn;
    List<ImageView> stars = new ArrayList<>();
    DesignCaseAdapter designCaseAdapter;

    Designer mDesigner = null;

    int MAKE_REVIEWS = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_designer_detail);
        mDesigner = (Designer) getIntent().getSerializableExtra("designer");
        bindViews();
        setUpEvents();
        setValues();
    }

    @Override
    public void setUpEvents() {
        super.setUpEvents();
        makeReviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MakeReviewActivity.class);
                startActivityForResult(intent, MAKE_REVIEWS);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MAKE_REVIEWS) {
            if (resultCode == RESULT_OK) {
                String review = data.getStringExtra("review");
                int rating = data.getIntExtra("rating", 0);
                DesignCase newReviewCase = new DesignCase(R.drawable.salon_logo, Calendar.getInstance(), rating,
                        mDesigner, GlobalData.loginUser, 30000, review);
                mDesigner.getPortfolio().add(newReviewCase);
                designCaseAdapter.notifyDataSetChanged();

                portfolioListView.smoothScrollToPosition(mDesigner.getPortfolio().size()-1);
            }
        }
    }

    @Override
    public void setValues() {
        super.setValues();
        nameTxt.setText(mDesigner.getName());
        nickNameTxt.setText(mDesigner.getNickName());
        String major = String.format(Locale.KOREA, "%d대 주력", mDesigner.getMajorAge());
        majorTxt.setText(major);
        if (mDesigner.getGender() == 1) {
            genderTxt.setText(R.string.female);
        } else {
            genderTxt.setText(R.string.male);
        }

        for (ImageView iv : stars) {
            iv.setVisibility(View.GONE);
        }

        int rate = (int) mDesigner.getAvgRating();
        for (int i=0; i<rate; i++) {
            stars.get(i).setVisibility(View.VISIBLE);
        }

        designCaseAdapter = new DesignCaseAdapter(mContext, mDesigner.getPortfolio());
        portfolioListView.setAdapter(designCaseAdapter);
    }

    @Override
    public void bindViews() {
        this.reservationBtn = (Button) findViewById(R.id.reservationBtn);
        this.makeReviewBtn = (Button) findViewById(R.id.makeReviewBtn);
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
        stars.add(star1);
        stars.add(star2);
        stars.add(star3);
        stars.add(star4);
        stars.add(star5);

    }
}
