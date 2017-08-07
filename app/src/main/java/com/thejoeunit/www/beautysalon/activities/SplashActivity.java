package com.thejoeunit.www.beautysalon.activities;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import com.thejoeunit.www.beautysalon.R;
import com.thejoeunit.www.beautysalon.datas.DesignCase;
import com.thejoeunit.www.beautysalon.datas.Designer;
import com.thejoeunit.www.beautysalon.datas.User;
import com.thejoeunit.www.beautysalon.utils.GeneralUtil;

import java.util.ArrayList;
import java.util.Calendar;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        addDesigners();
        addUser();
        addDesignCase();

        // postDelayed => 어떤 할 일을 재료1) 몇초 후에(millisecond) 재료2) 할일 => new Runnable()
        // 재료1만큼 시간이 지나면 재료2 실행
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent myIntent = new Intent(mContext, SelectLoginMode.class);
                startActivity(myIntent);
                finish();
            }
        }, 2000);
    }

    private void addDesigners() {
        GeneralUtil.designers.clear();
        GeneralUtil.designers.add(new Designer("이가자", 1, "KAJA", 40, 4.5f, new ArrayList<DesignCase>()));
        GeneralUtil.designers.add(new Designer("박승철", 1, "SC.PARK", 20, 4.0f, new ArrayList<DesignCase>()));
        GeneralUtil.designers.add(new Designer("김정남", 0, "JNAM", 30, 3.8f, new ArrayList<DesignCase>()));
        GeneralUtil.designers.add(new Designer("이승철", 0, "LSC", 50, 2.5f, new ArrayList<DesignCase>()));
        GeneralUtil.designers.add(new Designer("박준", 1, "JUN", 10, 4.8f, new ArrayList<DesignCase>()));
    }

    private void addUser() {
        GeneralUtil.users.clear();
        GeneralUtil.users.add(new User("한상열", 0, Calendar.getInstance(), new ArrayList<Designer>(), "https://s3.ap-northeast-2.amazonaws.com/slws3/imgs/tje_practice/images.jpg"));
        GeneralUtil.users.add(new User("최종환", 0, Calendar.getInstance(), new ArrayList<Designer>(), "https://s3.ap-northeast-2.amazonaws.com/slws3/imgs/tje_practice/images+(1).jpg"));
        GeneralUtil.users.add(new User("이요한", 0, Calendar.getInstance(), new ArrayList<Designer>(), "https://s3.ap-northeast-2.amazonaws.com/slws3/imgs/tje_practice/14.jpg"));
        GeneralUtil.users.add(new User("이승헌", 0, Calendar.getInstance(), new ArrayList<Designer>(), "https://s3.ap-northeast-2.amazonaws.com/slws3/imgs/tje_practice/13731255_1566785090293996_693997005_n.jpg"));
        GeneralUtil.users.add(new User("손익상", 0, Calendar.getInstance(), new ArrayList<Designer>(), "https://s3.ap-northeast-2.amazonaws.com/slws3/imgs/tje_practice/11379757_445206435653478_1894580131_n.jpg"));
    }

    private void addDesignCase() {
        GeneralUtil.globalDesignCase.clear();
        GeneralUtil.globalDesignCase.add(new DesignCase(R.drawable.salon_logo, Calendar.getInstance(), 5, GeneralUtil.designers.get(0), GeneralUtil.users.get(0), 10000, "5점 드릴게요"));
        GeneralUtil.globalDesignCase.add(new DesignCase(R.drawable.salon_logo, Calendar.getInstance(), 4, GeneralUtil.designers.get(0), GeneralUtil.users.get(1), 20000, "4점 드릴게요"));
        GeneralUtil.globalDesignCase.add(new DesignCase(R.drawable.salon_logo, Calendar.getInstance(), 3, GeneralUtil.designers.get(0), GeneralUtil.users.get(2), 30000, "3점 드릴게요"));
        GeneralUtil.globalDesignCase.add(new DesignCase(R.drawable.salon_logo, Calendar.getInstance(), 2, GeneralUtil.designers.get(0), GeneralUtil.users.get(3), 40000, "2점 드릴게요"));
        GeneralUtil.globalDesignCase.add(new DesignCase(R.drawable.salon_logo, Calendar.getInstance(), 1, GeneralUtil.designers.get(0), GeneralUtil.users.get(4), 50000, "1점 드릴게요"));

        Designer leekj = GeneralUtil.designers.get(0);
        for(DesignCase dc : GeneralUtil.globalDesignCase) {
            leekj.getPortfolio().add(dc);
        }
    }
}
