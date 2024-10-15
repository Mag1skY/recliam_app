package com.wythe.mall.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.wythe.mall.activity.LoginActivity;
import com.wythe.mall.activity.MessageCenterActivity;
import com.wythe.mall.activity.MoreSettingActivity;
import com.wythe.mall.activity.PocketActivity;
import com.wythe.mall.activity.ServiceFeedbackActivity;
import com.wythe.mall.utils.GotoActivity;
import com.wythe.mall.utils.UserManager;

import net.micode.wcnm.R;

public class MyPersonalFragment extends Fragment implements View.OnClickListener{

    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.my_fragment_personal, container, false);
        initView();
        return view;
    }

    private void initView(){
        view.findViewById(R.id.personal_click_for_login).setOnClickListener(this);
        view.findViewById(R.id.more_setting).setOnClickListener(this);
        view.findViewById(R.id.messages).setOnClickListener(this);
        view.findViewById(R.id.personal_guanzhu).setOnClickListener(this);
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
        } else {
            view.findViewById(R.id.personal_for_not_login).setVisibility(View.VISIBLE);
            view.findViewById(R.id.personal_for_login_info).setVisibility(View.GONE);
            view.findViewById(R.id.personal_recommend_layout).setVisibility(View.GONE);
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
            GotoActivity.gotoActiviy(getActivity(), PocketActivity.class);
        }
    }
}
