package com.thejoeunit.www.beautysalon.activities.user_activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.thejoeunit.www.beautysalon.R;
import com.thejoeunit.www.beautysalon.activities.BaseActivity;
import com.thejoeunit.www.beautysalon.activities.RequestPracticeActivity;
import com.thejoeunit.www.beautysalon.adapters.DesignerAdapter;
import com.thejoeunit.www.beautysalon.datas.Designer;
import com.thejoeunit.www.beautysalon.utils.GlobalData;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    DesignerAdapter mAdapter;
    private ListView designerListView;
    private android.widget.ImageView filterBtnImg;

    boolean manSelect = true;
    boolean womanSelect = true;
    int minRating = 0; // 몇점 이상의 디자이너를 보여줄건지
    String searchNickName;

    private int REQUEST_FOR_DESIGNER_FILTER = 1;
    private int REQUEST_PRACTICE = 2;
    private android.widget.Button reqTestBtn;

    List<Designer> mDisplayDesignerList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
        setUpEvents();
        setValues();
    }

    @Override
    public void setValues() {

        // 처음에는 조건없이 모든 디자이너를 화면에 출력해야함
        // 화면에 표시될 List에 Global 데이터의 모든 디자이너 추가
        mDisplayDesignerList.addAll(GlobalData.designers);

        // 글로벌 데이터를 바로 화면에 띄우는게 아니라
        // 화면 표시용 리스트를 기반으로 나타내도록 설정
        // > 필터 / 검색 등 임시로 결과를 추려내야 할 경우
        mAdapter = new DesignerAdapter(mContext, mDisplayDesignerList);
        designerListView.setAdapter(mAdapter);

    }

    // 언제가 될진 모르지만 언젠가 데이터를 돌려받는다
    // 돌려받는 이벤트에 대응되는 메소드를 구현

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 어떤 요청이었는지
        if (requestCode == REQUEST_FOR_DESIGNER_FILTER) {
            if (resultCode == RESULT_OK) {
                manSelect = data.getBooleanExtra("manSelect", true);
                womanSelect = data.getBooleanExtra("womanSelect", true);
                minRating = data.getIntExtra("ratingSelect", 0);
                searchNickName = data.getStringExtra("searchNick");

                filterAndRefreshListView();
            } else if (requestCode == REQUEST_PRACTICE) {
                if (resultCode == RESULT_OK) {
//                String str = data.getStringExtra("practice");
                    int db = data.getIntExtra("practice", 0);
                    // 기본형 변수(int, double) > 첨부되지 않은 경우에 쓸 기본값 세팅
                    // 참조형 변수(String, 데이터 클래스) > 기본값 없음 null로 들어감
//                Toast.makeText(mContext, str, Toast.LENGTH_SHORT).show();
                    Toast.makeText(mContext, db + "", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void filterAndRefreshListView() {
        mDisplayDesignerList.clear();

        // 필터 > 조건에 맞는 객체들만 화면에 보여준다
        // 원본들은 보존하고 원본을 검사하여 조건이 맞을 경우 화면에 표시되도록 함

        for (Designer ds : GlobalData.designers) {

            // 성별이 올바른지 기록하는 boolean
            boolean genderOk = false;
            boolean ratingOk = false;
            boolean nicknameOk = false;

            // 남자가 선택되어야 하는지?
            if (manSelect) {
                // 실제로 성별이 남성인지?
                if (ds.getGender() == 0) {
                    // 상황이 맞으므로, 성별이 올바르다고 기록.
                    genderOk = true;
                }
            }

            // 여성에 대해 확인.
            if (womanSelect) {
                if (ds.getGender() == 1) {
                    genderOk = true;
                }
            }

            if (ds.getAvgRating() >= minRating) {
                ratingOk = true;
            }

            // String 기능 응용
//            if (ds.getNickName().toLowerCase().contains(searchNickName.toLowerCase())) {
//                nicknameOk = true;
//            }

            if (ds.getNickName().toLowerCase().startsWith(searchNickName.toLowerCase())) {
                nicknameOk = true;
            }


            // 상황이 맞는지 재확인해서, 실제로 데이터를 추가
            if (genderOk && ratingOk && nicknameOk) {
                mDisplayDesignerList.add(ds);
            }

        }

//
//        if (searchNickName.equals("")) {
//
//            for (Designer ds : GlobalData.designers) {
//                if (manSelect) {
//                    if (ds.getGender() == 0 && ds.getAvgRating() >= minRating) {
//                        mDisplayDesignerList.add(ds);
//                    }
//                }
//
//                if (womanSelect) {
//                    if (ds.getGender() == 1 && ds.getAvgRating() >= minRating) {
//                        mDisplayDesignerList.add(ds);
//                    }
//                }
//            }
//        } else {
//            for (Designer ds : GlobalData.designers) {
//                if (ds.getNickName().equalsIgnoreCase(searchNickName)) {
//                    mDisplayDesignerList.add(ds);
//                } else {
//                    String upperCase = searchNickName.toUpperCase();
//                    String lowerCase = searchNickName.toLowerCase();
//                    if(ds.getNickName().contains(upperCase) || ds.getNickName().contains(lowerCase))
//                    {
//                        mDisplayDesignerList.add(ds);
//                    }
//                }
//            }
//        }

        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void setUpEvents() {
        super.setUpEvents();
        filterBtnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DesignerFilterActivity.class);
//                startActivity(intent);
                // 데이터를 돌려받기 위한 startActivity
                startActivityForResult(intent, REQUEST_FOR_DESIGNER_FILTER);
            }
        });

        reqTestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, RequestPracticeActivity.class);
                startActivityForResult(intent, REQUEST_PRACTICE);
            }
        });

        designerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Designer data = mDisplayDesignerList.get(position);
                Intent intent = new Intent(mContext, ViewDesignerDetailActivity.class);
                intent.putExtra("designer", data);
                startActivity(intent);
            }
        });
    }

    @Override
    public void bindViews() {
        this.designerListView = (ListView) findViewById(R.id.designerListView);
        this.filterBtnImg = (ImageView) findViewById(R.id.filterBtnImg);
        this.reqTestBtn = (Button) findViewById(R.id.reqTestBtn);
    }
}
