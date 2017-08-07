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

import com.thejoeunit.www.beautysalon.R;
import com.thejoeunit.www.beautysalon.datas.Designer;

import java.util.ArrayList;

/**
 * Created by the on 2017-07-28.
 */

public class DesignerAdapter extends ArrayAdapter<Designer> {
    private Context mContext;
    private ArrayList<Designer> mList;
    LayoutInflater inf;
    // LayoutInflater : 화면에 그림을 그려주는 역할을 담당 > 인플레이팅
    // layout 폴더에 있는 xml을 분석해서 화면에 뿌려줌

    public DesignerAdapter(Context context, ArrayList<Designer> list) {

        super(context, R.layout.designer_list_item, list);
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
            row = inf.inflate(R.layout.designer_list_item, null);
        }

        Designer mDesigner = mList.get(position);

        TextView designerNameTxt = (TextView)row.findViewById(R.id.designerNameTxt);
        TextView majorTxt = (TextView)row.findViewById(R.id.majorTxt);
        TextView genderTxt = (TextView)row.findViewById(R.id.genderTxt);
        designerNameTxt.setText(mDesigner.getNickName() + " (" + mDesigner.getName() + ")");
        majorTxt.setText(mDesigner.getMajorAge()+"대 주력");

        if(mDesigner.getGender() == 1) {
            genderTxt.setText(R.string.female);
        } else {
            genderTxt.setText(R.string.male);
        }

        ImageView star1 = (ImageView)row.findViewById(R.id.star1);
        ImageView star2 = (ImageView)row.findViewById(R.id.star2);
        ImageView star3 = (ImageView)row.findViewById(R.id.star3);
        ImageView star4 = (ImageView)row.findViewById(R.id.star4);
        ImageView star5 = (ImageView)row.findViewById(R.id.star5);

        ArrayList<ImageView> stars = new ArrayList<ImageView>();
        stars.add(star1);
        stars.add(star2);
        stars.add(star3);
        stars.add(star4);
        stars.add(star5);

        for(ImageView iv : stars) {
            // 배열 내부 객체 조회할 때
            iv.setVisibility(View.INVISIBLE);
        }

        int rating = (int)mDesigner.getAvgRating();

        for(int i=0; i<rating; i++) {
            stars.get(i).setVisibility(View.VISIBLE);
        }



        return row;
    }
}
