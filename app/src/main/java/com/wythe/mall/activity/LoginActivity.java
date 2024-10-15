package com.wythe.mall.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//import com.wythe.mall.R;
import com.wythe.mall.Client.ClientThread;
import com.wythe.mall.Client.ClientVal;
import com.wythe.mall.utils.GotoActivity;
import com.wythe.mall.utils.UserManager;

import net.micode.wcnm.R;

/**
 * Created by wythe on 2016/7/8.
 */
public class LoginActivity extends BaseActivity {

    private EditText edUsername;
    private EditText edPassword;
    private CheckBox switchButton;
    private ImageView loginPasswordDelete;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initListener();
    }

    @Override
    protected void initView() {
        super.initView();
        ((TextView)findViewById(R.id.titleText)).setText(R.string.login_yipei);
        edUsername = (EditText) findViewById(R.id.login_username);
        edPassword = (EditText) findViewById(R.id.login_password);
        switchButton = (CheckBox) findViewById(R.id.login_switchBtn);
        switchButton.setOnClickListener(this);
        loginPasswordDelete = (ImageView) findViewById(R.id.login_password_delete);
        loginPasswordDelete.setOnClickListener(this);
        btnLogin = (Button) findViewById(R.id.login_comfirm_button);
        btnLogin.setOnClickListener(this);
        findViewById(R.id.register_link).setOnClickListener(this);
        findViewById(R.id.login_page_find_password).setOnClickListener(this);
        findViewById(R.id.register_link).setOnClickListener(this);
    }

    @Override
    protected void initListener() {
        super.initListener();
        edPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    if (edPassword != null) {
                        String nameText = edPassword.getText().toString().trim();
                        if (TextUtils.isEmpty(nameText)) {
                            loginPasswordDelete.setVisibility(View.INVISIBLE);
                        } else {
                            loginPasswordDelete.setVisibility(View.VISIBLE);
                        }
                    }

                } else {
                    loginPasswordDelete.setVisibility(View.INVISIBLE);

                }

            }
        });

        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                Editable pwText = edPassword.getText();
                String pwTextString;
                if (null == pwText) {
                    pwTextString = "";
                } else {
                    pwTextString = pwText.toString();
                }
                if (switchButton.isChecked()) {
                    // 设置EditText的密码为可见的
                    edPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    // 设置密码为隐藏的
                    edPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                if (!TextUtils.isEmpty(pwTextString)) {
                    edPassword.setSelection(pwTextString.length());
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if(v.getId()==R.id.login_password_delete){
            if (edPassword != null) {
                edPassword.setText("");
                edPassword.requestFocus();
            }
        }else if(v.getId()==R.id.login_comfirm_button){
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("请稍后");
            progressDialog.setMessage("正在登录账户");
            progressDialog.setMax(100);
            progressDialog.setProgress(0);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            ClientThread clientThread = new ClientThread();
            ClientThread.username = edUsername.getText().toString();
            ClientThread.password = edPassword.getText().toString();
            ClientThread.operators = ClientVal.VERIFY;
            ClientThread.point = 0;
            Activity activity=this;
            new AsyncTask<View, View, Boolean>() {
                @Override
                protected void onPreExecute() {
                    // 显示加载指示器
                    progressDialog.show();
                }

                @Override
                protected Boolean doInBackground(View... views) {
                    return clientThread.run();
                }

                @Override
                protected void onPostExecute(Boolean success) {
                    progressDialog.dismiss();
                    if (success) {
                        if(clientThread.check()==false){
                            RegisterCompleteActivity.s="登录成功";
                            UserManager.getInstance().setLogin(true);
                            UserManager.getInstance().point=clientThread.val_point;
                        }
                    } else {
                        RegisterCompleteActivity.s="服务器错误";
                        // 登录失败，显示错误信息
//                        Toast.makeText(context, "服务器错误", Toast.LENGTH_SHORT).show();

                    }
                    GotoActivity.gotoActiviy(activity, RegisterCompleteActivity.class, true);
                }
            }.execute();
//            Toast.makeText(this,"denglu",Toast.LENGTH_LONG).show();
//            UserManager.getInstance().setLogin(true);
//            finish();
        }else if(v.getId()==R.id.register_link) {
            GotoActivity.gotoActiviy(LoginActivity.this, RegisterActivity.class);
        }else if(v.getId()==R.id.login_page_find_password) {
            GotoActivity.gotoActiviy(LoginActivity.this, FindPasswordActivity.class);
        }


    }
}
