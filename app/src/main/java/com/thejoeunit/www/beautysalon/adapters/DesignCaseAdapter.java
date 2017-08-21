package com.thejoeunit.www.beautysalon.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.thejoeunit.www.beautysalon.R;
import com.thejoeunit.www.beautysalon.datas.DesignCase;
import com.thejoeunit.www.beautysalon.datas.Designer;
import com.thejoeunit.www.beautysalon.utils.DateTimeUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by user on 2017-08-21.
 */

public class DesignCaseAdapter extends ArrayAdapter<DesignCase> {
    private Context mContext;
    private List<DesignCase> mList;
    LayoutInflater inf;
    // LayoutInflater : 화면에 그림을 그려주는 역할을 담당 > 인플레이팅
    // layout 폴더에 있는 xml을 분석해서 화면에 뿌려줌

    public DesignCaseAdapter(Context context, List<DesignCase> list) {

        super(context, R.layout.portfolio_list_item, list);
        // 커스텀으로 직접 리스트뷰의 모양 구현
        // context 다음 인자 > 직접 구현한 리스트뷰의 아이템의 id값

        // 어떤 데이터 뿌려줄 것인지

        // 세 가지 멤버 변수 초기화
        mContext = context;
        mList = list;
        inf = LayoutInflater.from(mContext);
    }

    // 화면에 뿌려지는 부분
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;

        if (row == null) {
            // 리스트뷰의 각 아이템을 새로 그려야 할 때
            row = inf.inflate(R.layout.portfolio_list_item, null);
        }

        DesignCase data = mList.get(position);

        TextView userNameTxt = (TextView) row.findViewById(R.id.userNameTxt);
        TextView createdOnTxt = (TextView) row.findViewById(R.id.createdOnTxt);
        TextView reviewTxt = (TextView) row.findViewById(R.id.reviewTxt);
        ImageView profileImg = (ImageView) row.findViewById(R.id.profileImg);
        List<ImageView> stars = new ArrayList<>();
        stars.add((ImageView) row.findViewById(R.id.star1));
        stars.add((ImageView) row.findViewById(R.id.star2));
        stars.add((ImageView) row.findViewById(R.id.star3));
        stars.add((ImageView) row.findViewById(R.id.star4));
        stars.add((ImageView) row.findViewById(R.id.star5));

        // xml에는 CircleImageView로 정의됨
        // but 자바에서 ImageView형태로 저장을 해고 캐스팅하는데 문제가 없음
        // 1) JAVA의 객체지향적 특성중 하나인 다형성 때문
        // 2) CircleImageView extends ImageView
        Glide.with(mContext).load(data.getUser().getProfilePicPath()).into(profileImg);

        // 라이브러리 1. 인터넷 이미지 불러오기 > 이미지 로더 AUIL, Piccaso
        // 라이브러리 2. 동그란 이미지 View > CircleImageView

        userNameTxt.setText(data.getUser().getName());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = dateFormat.format(data.getCreateOn().getTime());
        createdOnTxt.setText(dateStr);

        reviewTxt.setText(data.getUserReview());

        for (ImageView iv : stars) {
            iv.setVisibility(View.GONE);
        }

        for (int i=0; i<data.getUserRating(); i++) {
            stars.get(i).setVisibility(View.VISIBLE);
        }

        return row;
    }
}
