package com.wythe.mall.Client;

import android.app.ProgressDialog;
import android.os.AsyncTask;

public class Mythread extends AsyncTask<Void,Void,Boolean> {
    public ProgressDialog progressDialog;
    public ClientThread clientThread;
    public boolean ok;

    public Mythread(ProgressDialog progressDialog,ClientThread clientThread){
        super();
        this.progressDialog=progressDialog;
        this.clientThread=clientThread;
    }

    @Override
    protected void onPreExecute() {
        // 显示加载指示器
        progressDialog.show();
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        return clientThread.run();
    }
    protected void onPostExecute(Boolean success) {
        // 隐藏加载指示器
        progressDialog.dismiss();
        if (success) {
            // 登录成功，更新UI
            ok=true;
        } else {
            // 登录失败，显示错误信息
            ok=false;
        }
    }
}
