package com.wythe.mall.activity;

import android.os.Bundle;
import android.view.View;

//import com.wythe.mall.R;
import com.wythe.mall.utils.GotoActivity;

import net.micode.wcnm.R;

/**
 * Created by wythe on 2016/7/17.
 */
public class ServiceFeedbackActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_feedback);
        initView();
    }

    @Override
    protected void initView() {
        super.initView();
        super.setNormalTitle(R.string.service_feedback);
        findViewById(R.id.service_feedback).setOnClickListener(this);
        findViewById(R.id.service_online).setOnClickListener(this);
        findViewById(R.id.service_phone).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if(v.getId()==R.id.service_feedback){
            GotoActivity.gotoActiviy(this, FeedBackActivity.class);
        }else if(v.getId()==R.id.service_online){

        }else if(v.getId()==R.id.service_phone){

        }

    }
}
