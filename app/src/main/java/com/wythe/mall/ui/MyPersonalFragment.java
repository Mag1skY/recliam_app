package com.wythe.mall.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.wythe.mall.Client.ClientThread;
import com.wythe.mall.Client.ClientVal;
import com.wythe.mall.activity.LoginActivity;
import com.wythe.mall.activity.ManageActivity;
import com.wythe.mall.activity.MessageCenterActivity;
import com.wythe.mall.activity.MoreSettingActivity;
import com.wythe.mall.activity.PocketActivity;
import com.wythe.mall.activity.RegisterCompleteActivity;
import com.wythe.mall.activity.ServiceFeedbackActivity;
import com.wythe.mall.utils.GotoActivity;
import com.wythe.mall.utils.UserManager;

import net.micode.wcnm.R;

public class MyPersonalFragment extends Fragment implements View.OnClickListener{

    private View view;

    private TextView point;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.my_fragment_personal, container, false);
        initView();
        point=(TextView) view.findViewById(R.id.my_point);
        return view;
    }

    private void initView(){
        view.findViewById(R.id.personal_click_for_login).setOnClickListener(this);
        view.findViewById(R.id.more_setting).setOnClickListener(this);
        view.findViewById(R.id.messages).setOnClickListener(this);
        view.findViewById(R.id.personal_guan).setOnClickListener(this);
        view.findViewById(R.id.personal_zuji).setOnClickListener(this);
        view.findViewById(R.id.personal_hongbao).setOnClickListener(this);
        view.findViewById(R.id.personal_feedback).setOnClickListener(this);
        view.findViewById(R.id.personal_pocket_title).setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        //登录未登录界面切换
        if (UserManager.getInstance().isLogin()){
            view.findViewById(R.id.personal_for_not_login).setVisibility(View.GONE);
            view.findViewById(R.id.personal_for_login_info).setVisibility(View.VISIBLE);
            view.findViewById(R.id.personal_recommend_layout).setVisibility(View.VISIBLE);
            point.setText(Integer.toString(UserManager.getInstance().point));
            point.setTextSize(70);
        } else {
            view.findViewById(R.id.personal_for_not_login).setVisibility(View.VISIBLE);
            view.findViewById(R.id.personal_for_login_info).setVisibility(View.GONE);
            view.findViewById(R.id.personal_recommend_layout).setVisibility(View.GONE);
            point.setText("登录后查看");
            point.setTextSize(50);
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.personal_click_for_login){
            GotoActivity.gotoActiviy(getActivity(), LoginActivity.class);
        }else if(v.getId()==R.id.more_setting){
            GotoActivity.gotoActiviy(getActivity(), MoreSettingActivity.class);
        }else if(v.getId()==R.id.messages){
            GotoActivity.gotoActiviy(getActivity(), MessageCenterActivity.class);
        }else if(v.getId()== R.id.personal_feedback){
            GotoActivity.gotoActiviy(getActivity(), ServiceFeedbackActivity.class);
        }else if(v.getId()==R.id.personal_pocket_title){
            if(UserManager.getInstance().isLogin()==false) return;
            ProgressDialog progressDialog = new ProgressDialog(this.getContext());
            progressDialog.setTitle("请稍后");
            progressDialog.setMessage("正在刷新积分");
            progressDialog.setMax(100);
            progressDialog.setProgress(0);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            ClientThread clientThread = new ClientThread();
            ClientThread.username = UserManager.getInstance().getUsername();
            ClientThread.password = "1";
            ClientThread.operators = ClientVal.SUB_POINT;
            ClientThread.point = 0;
            Activity activity=this.getActivity();
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
                            RegisterCompleteActivity.s="刷新成功";
                            UserManager.getInstance().point=clientThread.val_point;
                        }
                    } else {
                        RegisterCompleteActivity.s="服务器错误";
                        // 登录失败，显示错误信息
//                        Toast.makeText(context, "服务器错误", Toast.LENGTH_SHORT).show();

                    }
                    GotoActivity.gotoActiviy(activity, RegisterCompleteActivity.class, false);
                }
            }.execute();
//            GotoActivity.gotoActiviy(getActivity(), PocketActivity.class);
        }else if(v.getId()==R.id.personal_guan){
            GotoActivity.gotoActiviy(getActivity(), ManageActivity.class);
        }
    }
}
