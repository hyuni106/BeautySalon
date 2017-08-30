package com.thejoeunit.www.beautysalon.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.LoginButton;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.exception.KakaoException;
import com.thejoeunit.www.beautysalon.R;
import com.thejoeunit.www.beautysalon.activities.user_activity.MainActivity;
import com.thejoeunit.www.beautysalon.activities.worker_activity.WorkerMainActivity;
import com.thejoeunit.www.beautysalon.utils.ContextUtil;
import com.thejoeunit.www.beautysalon.utils.GlobalData;

public class LoginActivity extends BaseActivity {
    boolean isWorkerMode = false;   // 직원모드를 판별해주는 변수
    private TextView workerTxt;
    private EditText idEdtTxt;
    private Button loginBtn;
    private android.widget.CheckBox autoCheckBox;
    private EditText pwEdtTxt;

    CallbackManager callbackManager;
    private com.facebook.login.widget.LoginButton fbLoginBtn;
    private com.kakao.usermgmt.LoginButton comkakaologin;
    SessionCallback callback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        isWorkerMode = getIntent().getBooleanExtra("mode", false);
        UserManagement.requestLogout(null);
        bindViews();
        setUpEvents();
        setValues();
    }

    @Override
    public void setValues() {
        callback = new SessionCallback();
        Session.getCurrentSession().addCallback(callback);
        Session.getCurrentSession().checkAndImplicitOpen();

        callbackManager = CallbackManager.Factory.create();
        fbLoginBtn.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        ProfileTracker pt = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                if (currentProfile == null) {
//                    로그아웃
                    Toast.makeText(mContext, "로그아웃 처리 되었습니다.", Toast.LENGTH_SHORT).show();
                } else {
//                    로그인
                    Toast.makeText(mContext, currentProfile.getName() + "님 접속", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent();
//                    intent.setAction(Intent.ACTION_VIEW);
//                    intent.setData(currentProfile.getLinkUri());
//                    startActivity(intent);
                    ContextUtil.setLoginUser(mContext, currentProfile.getId(), "없음");
                    Intent intent = new Intent(mContext, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };

        if (isWorkerMode) {
            workerTxt.setVisibility(View.VISIBLE);
        } else {
            workerTxt.setVisibility(View.GONE);
        }

        if (ContextUtil.getAutoLogin(mContext)) {
            autoCheckBox.setChecked(true);
            idEdtTxt.setText(ContextUtil.getLoginUserId(mContext));
            pwEdtTxt.setText(ContextUtil.getLoginUserPw(mContext));
        } else {
            ContextUtil.logoutProcess(mContext);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void setUpEvents() {
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (autoCheckBox.isChecked()) {
                    ContextUtil.setLoginUser(mContext, idEdtTxt.getText().toString(), pwEdtTxt.getText().toString());
                    ContextUtil.setLoginUser(mContext, "A사용자", 1);
                }
                Intent intent;
                if (isWorkerMode) {
                    intent = new Intent(mContext, WorkerMainActivity.class);
                } else {
                    intent = new Intent(mContext, MainActivity.class);
                }
                GlobalData.loginUser.setName(idEdtTxt.getText().toString());
                GlobalData.loginUser.setGender(1);
                startActivity(intent);
            }
        });
    }

    @Override
    public void bindViews() {
        this.comkakaologin = (LoginButton) findViewById(R.id.com_kakao_login);
        this.fbLoginBtn = (com.facebook.login.widget.LoginButton) findViewById(R.id.fbLoginBtn);
        this.loginBtn = (Button) findViewById(R.id.loginBtn);
        this.autoCheckBox = (CheckBox) findViewById(R.id.autoCheckBox);
        this.pwEdtTxt = (EditText) findViewById(R.id.pwEdtTxt);
        this.idEdtTxt = (EditText) findViewById(R.id.idEdtTxt);
        this.workerTxt = (TextView) findViewById(R.id.workerTxt);
    }

    private class SessionCallback implements ISessionCallback {

        @Override
        public void onSessionOpened() {
            UserManagement.requestMe(new MeResponseCallback() {
                @Override
                public void onSessionClosed(ErrorResult errorResult) {

                }

                @Override
                public void onNotSignedUp() {

                }

                @Override
                public void onSuccess(UserProfile result) {
                    ContextUtil.setLoginUser(mContext, result.getId() + "", "없음");
//                    Log.d("로그", ContextUtil.getLoginUserName(mContext));
                    Intent intent = new Intent(mContext, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            exception.printStackTrace();
        }
    }
}
