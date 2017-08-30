package com.thejoeunit.www.beautysalon.activities;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Handler;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;

import com.thejoeunit.www.beautysalon.R;
import com.thejoeunit.www.beautysalon.datas.DesignCase;
import com.thejoeunit.www.beautysalon.datas.Designer;
import com.thejoeunit.www.beautysalon.datas.User;
import com.thejoeunit.www.beautysalon.utils.GlobalData;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.thejoeunit.www.beautysalon",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }

        // postDelayed => 어떤 할 일을 재료1) 몇초 후에(millisecond) 재료2) 할일 => new Runnable()
        // 재료1만큼 시간이 지나면 재료2 실행
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent myIntent = new Intent(mContext, SelectLoginModeActivity.class);
                startActivity(myIntent);
                finish();
            }
        }, 2000);
    }

    private void addDesigners() {
        GlobalData.designers.clear();
        GlobalData.designers.add(new Designer("이가자", 1, "KAJA", 40, 4.5f, new ArrayList<DesignCase>()));
        GlobalData.designers.add(new Designer("박승철", 1, "SC.PARK", 20, 4.0f, new ArrayList<DesignCase>()));
        GlobalData.designers.add(new Designer("김정남", 0, "JNAM", 30, 3.8f, new ArrayList<DesignCase>()));
        GlobalData.designers.add(new Designer("이승철", 0, "LSC", 50, 2.5f, new ArrayList<DesignCase>()));
        GlobalData.designers.add(new Designer("박준", 1, "JUN", 10, 4.8f, new ArrayList<DesignCase>()));
        GlobalData.designers.add(new Designer("A", 1, "aaa", 40, 3.5f, new ArrayList<DesignCase>()));
        GlobalData.designers.add(new Designer("B", 1, "bbb", 20, 4.4f, new ArrayList<DesignCase>()));
        GlobalData.designers.add(new Designer("C", 0, "ccc", 30, 1.8f, new ArrayList<DesignCase>()));
        GlobalData.designers.add(new Designer("D", 0, "ddd", 50, 2.3f, new ArrayList<DesignCase>()));
        GlobalData.designers.add(new Designer("E", 1, "eee", 10, 4.2f, new ArrayList<DesignCase>()));
        GlobalData.designers.add(new Designer("F", 1, "fff", 40, 3.3f, new ArrayList<DesignCase>()));
        GlobalData.designers.add(new Designer("G", 1, "ggg", 20, 3.7f, new ArrayList<DesignCase>()));
        GlobalData.designers.add(new Designer("H", 0, "hhh", 30, 2.6f, new ArrayList<DesignCase>()));
        GlobalData.designers.add(new Designer("I", 0, "iii", 50, 1.4f, new ArrayList<DesignCase>()));
        GlobalData.designers.add(new Designer("J", 1, "jjj", 10, 4.1f, new ArrayList<DesignCase>()));
    }

    private void addUser() {
        GlobalData.loginUser = new User("테스트사용자", 1, Calendar.getInstance(), new ArrayList<Designer>(), "https://image.fmkorea.com/files/attach/new/20170628/3655299/388198569/695916068/5f8f3b1803d9b9858f8d09433b9c1ee2.jpg");
        GlobalData.users.clear();
        GlobalData.users.add(new User("한상열", 0, Calendar.getInstance(), new ArrayList<Designer>(), "https://s3.ap-northeast-2.amazonaws.com/slws3/imgs/tje_practice/images.jpg"));
        GlobalData.users.add(new User("최종환", 0, Calendar.getInstance(), new ArrayList<Designer>(), "https://s3.ap-northeast-2.amazonaws.com/slws3/imgs/tje_practice/images+(1).jpg"));
        GlobalData.users.add(new User("이요한", 0, Calendar.getInstance(), new ArrayList<Designer>(), "https://s3.ap-northeast-2.amazonaws.com/slws3/imgs/tje_practice/14.jpg"));
        GlobalData.users.add(new User("이승헌", 0, Calendar.getInstance(), new ArrayList<Designer>(), "https://s3.ap-northeast-2.amazonaws.com/slws3/imgs/tje_practice/13731255_1566785090293996_693997005_n.jpg"));
        GlobalData.users.add(new User("손익상", 0, Calendar.getInstance(), new ArrayList<Designer>(), "https://s3.ap-northeast-2.amazonaws.com/slws3/imgs/tje_practice/11379757_445206435653478_1894580131_n.jpg"));
    }

    private void addDesignCase() {
        GlobalData.globalDesignCase.clear();
        GlobalData.globalDesignCase.add(new DesignCase(R.drawable.salon_logo, Calendar.getInstance(), 5, GlobalData.designers.get(0), GlobalData.users.get(0), 10000, "5점 드릴게요"));
        GlobalData.globalDesignCase.add(new DesignCase(R.drawable.salon_logo, Calendar.getInstance(), 4, GlobalData.designers.get(0), GlobalData.users.get(1), 20000, "4점 드릴게요"));
        GlobalData.globalDesignCase.add(new DesignCase(R.drawable.salon_logo, Calendar.getInstance(), 3, GlobalData.designers.get(0), GlobalData.users.get(2), 30000, "3점 드릴게요"));
        GlobalData.globalDesignCase.add(new DesignCase(R.drawable.salon_logo, Calendar.getInstance(), 2, GlobalData.designers.get(0), GlobalData.users.get(3), 40000, "2점 드릴게요"));
        GlobalData.globalDesignCase.add(new DesignCase(R.drawable.salon_logo, Calendar.getInstance(), 1, GlobalData.designers.get(0), GlobalData.users.get(4), 50000, "1점 드릴게요"));

        Designer leekj = GlobalData.designers.get(0);
        for(DesignCase dc : GlobalData.globalDesignCase) {
            leekj.getPortfolio().add(dc);
        }
    }
}
