package com.wythe.mall.activity;

import android.os.Bundle;
import android.view.View;

//import com.wythe.mall.R;
import com.wythe.mall.utils.GotoActivity;

import net.micode.wcnm.R;

/**
 * Created by wythe on 2016/7/17.
 */
public class BalanceActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        R.layout
        setContentView(R.layout.activity_balance);
        initView();
    }

    @Override
    protected void initView() {
        super.initView();
        super.setNormalTitle(R.string.pocket_yue);
        findViewById(R.id.balance_chongzhi).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if(v.getId()==R.id.balance_chongzhi){
            GotoActivity.gotoActiviy(this, RechargeActivity.class);
        }
    }
}
