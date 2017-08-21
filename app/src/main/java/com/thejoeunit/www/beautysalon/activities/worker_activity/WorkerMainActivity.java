package com.thejoeunit.www.beautysalon.activities.worker_activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.thejoeunit.www.beautysalon.R;
import com.thejoeunit.www.beautysalon.activities.BaseActivity;
import com.thejoeunit.www.beautysalon.datas.DesignCase;
import com.thejoeunit.www.beautysalon.utils.GlobalData;

public class WorkerMainActivity extends BaseActivity {

    private android.widget.ListView reservationListView;
    private ArrayAdapter<DesignCase> reservationAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_main);
        bindViews();
        setUpEvents();
        setValues();
    }

    @Override
    public void setUpEvents() {
        super.setUpEvents();
    }

    @Override
    public void setValues() {
        super.setValues();
        reservationAdapter = new ArrayAdapter<DesignCase>(mContext, android.R.layout.simple_list_item_1, GlobalData.globalDesignCase);
        reservationListView.setAdapter(reservationAdapter);
    }

    @Override
    public void bindViews() {
        super.bindViews();
        this.reservationListView = (ListView) findViewById(R.id.reservationListView);
    }


}
