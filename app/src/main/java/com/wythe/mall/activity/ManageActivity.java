package com.wythe.mall.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.wythe.mall.Client.ClientThread;
import com.wythe.mall.Client.ClientVal;
import com.wythe.mall.utils.BPUtil;
import com.wythe.mall.utils.GotoActivity;
import com.wythe.mall.utils.UserManager;
import com.wythe.mall.view.MyEditText;

import net.micode.wcnm.R;

public class ManageActivity extends BaseActivity{
    private EditText points;
    private EditText username;
    private Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_manage);
        initView();
    }
    @Override
    protected void initView() {
        super.initView();
        super.setNormalTitle("后台管理");
        points=(EditText) findViewById(R.id.find_point);
        username=(EditText) findViewById(R.id.find_username);
        btnLogin=(Button) findViewById(R.id.add_point);
        btnLogin.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        super.onClick(v);
        if(v.getId()==R.id.add_point){
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("请稍后");
            progressDialog.setMessage("增加积分");
            progressDialog.setMax(100);
            progressDialog.setProgress(0);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            ClientThread clientThread = new ClientThread();
            ClientThread.username = username.getText().toString();
            ClientThread.password = "0";
            ClientThread.operators = ClientVal.ADD_POINT;
            ClientThread.point = Integer.parseInt(points.getText().toString());
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
                            RegisterCompleteActivity.s="操作成功";
                        }
                    } else {
                        RegisterCompleteActivity.s="服务器错误";
                        // 登录失败，显示错误信息
//                        Toast.makeText(context, "服务器错误", Toast.LENGTH_SHORT).show();

                    }
                    GotoActivity.gotoActiviy(activity, RegisterCompleteActivity.class, true);
                    finish();
                }
            }.execute();
        }
    }
}
