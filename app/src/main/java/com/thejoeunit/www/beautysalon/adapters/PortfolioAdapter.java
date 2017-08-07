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
import com.thejoeunit.www.beautysalon.utils.DateTimeUtil;

import java.util.ArrayList;

/**
 * Created by the on 2017-07-28.
 */

public class PortfolioAdapter extends ArrayAdapter<DesignCase> {
    private Context mContext;
    // 어느 화면에서 쓰는지
    private ArrayList<DesignCase> mList;
    // 표시할 데이터 배열 > 접근하는 장소가 많아 멤버변수로 선언이 용이
    private LayoutInflater inf;
    // layout xml file을 그리는 역할 > 메모리 절약을 위해 멤버변수로 선언

    public PortfolioAdapter(Context context, ArrayList<DesignCase> list) {
        // arrayadapter에 상응하는 일 해야함 => super
        super(context, R.layout.portfolio_list_item, list);
        // 1. 어디서 표시할건지 > context
        // 2. 어떤 모양으로 보여줄건지
        // 3. 어떤 데이터 보여줄건지 > list

        // 생성자 만들고 super(3가지 파라미터)를 대응해줘야 에러가 나지 않음
        // 우선은 에러가 안나게 이 두가지 작업부터 코딩
        // layout이 필요해져서 res > layout > new layout resource file

        mContext = context; // 어느 화면 사용되는지 기록
        mList = list;   // 어떤 데이터 사용할지 기록
        inf = LayoutInflater.from(mContext);    // xml을 그려주는 객체를 초기화
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // 커스터마이징의 가장 중요한 부분
        // 만들어둔 layout style에 유효한 데이터를 출력시켜주는 부분

        View row = convertView;
        // super.getView() > 그대로 리턴하면 원하는 위치에 원하는 데이터 출력 불가
        // 커스터마이징 할 때는 super 삭제 후 코딩

        if(row == null) {
            // layout을 새로 그려줘야하는 경우
            // 새로 그려준다 > 재활용 할 수 없는 타이밍 > listview가 처음 그려져야하는 경우
            row = inf.inflate(R.layout.portfolio_list_item, null);
            // LayoutInflater 를 이용하여 리스트뷰의 한 줄을 그려내는 방법
        }

        DesignCase mDesignCase = mList.get(position);

        ImageView profileImg = (ImageView)row.findViewById(R.id.profileImg);
        Glide.with(mContext).load(mDesignCase.getUser().getProfilePicPath()).into(profileImg);

        TextView userNameTxt = (TextView)row.findViewById(R.id.userNameTxt);
        userNameTxt.setText(mDesignCase.getUser().getName());

        TextView createdOnTxt = (TextView)row.findViewById(R.id.createdOnTxt);
        createdOnTxt.setText(DateTimeUtil.getDateString2(mDesignCase.getCreateOn()));

        ArrayList<View> stars = new ArrayList<View>();
        stars.add(row.findViewById(R.id.star1));
        stars.add(row.findViewById(R.id.star2));
        stars.add(row.findViewById(R.id.star3));
        stars.add(row.findViewById(R.id.star4));
        stars.add(row.findViewById(R.id.star5));

        for(View v : stars) {
            v.setVisibility(View.GONE);
        }

        for(int i=0; i<mDesignCase.getUserRating(); i++) {
            stars.get(i).setVisibility(View.VISIBLE);
        }

        TextView reviewTxt = (TextView)row.findViewById(R.id.reviewTxt);
        reviewTxt.setText(mDesignCase.getUserReview());

        return row;
    }
}
