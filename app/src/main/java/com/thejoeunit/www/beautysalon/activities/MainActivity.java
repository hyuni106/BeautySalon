package com.thejoeunit.www.beautysalon.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.thejoeunit.www.beautysalon.R;
import com.thejoeunit.www.beautysalon.adapters.DesignerAdapter;
import com.thejoeunit.www.beautysalon.datas.DesignCase;
import com.thejoeunit.www.beautysalon.datas.Designer;
import com.thejoeunit.www.beautysalon.utils.GeneralUtil;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {
    private android.widget.ListView designerList;
    // 일반 사용자가 로그인했을 때 나타나는 Main

    private DesignerAdapter desigenersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
        setValues();
        setUpEvents();
    }

    @Override
    public void setUpEvents() {
        super.setUpEvents();
        designerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Designer tmpDesigner = GeneralUtil.designers.get(position);
//                Toast.makeText(mContext, designers.get(position).getName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, ViewDesignerDetailActivity.class);
                intent.putExtra("info", tmpDesigner);
                startActivity(intent);
            }
        });
    }

    @Override
    public void setValues() {
        super.setValues();
        desigenersAdapter = new DesignerAdapter(mContext, GeneralUtil.designers);
        designerList.setAdapter(desigenersAdapter);
    }



    @Override
    public void bindViews() {
        super.bindViews();
        this.designerList = (ListView) findViewById(R.id.designerList);
    }
}
