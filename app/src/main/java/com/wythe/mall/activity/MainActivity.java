package com.wythe.mall.activity;

import android.os.Build;
import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.facebook.drawee.backends.pipeline.Fresco;
//import com.wythe.mall.R;
import com.wythe.mall.adapter.FragmentTabAdapter;
import com.wythe.mall.mapfuction.MapApplication;
import com.wythe.mall.ui.HomeFragment;
import com.wythe.mall.ui.CategoryFragment;
import com.wythe.mall.ui.My_HomeFragment;
import com.wythe.mall.ui.TabCFm;
import com.wythe.mall.ui.CartFragment;
import com.wythe.mall.ui.PersonalFragment;

import net.micode.wcnm.R;

import java.util.ArrayList;
import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

import android.Manifest;
import android.widget.Toast;




public class MainActivity extends FragmentActivity {


    public static final String TAG = MainActivity.class.getSimpleName();
    private RadioGroup radio_button_group;
    private FragmentTabAdapter tabAdapter;
    public List<Fragment> fragments = new ArrayList<Fragment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 初始化Fresco图片加载库，并将其与当前应用程序的上下文关联
        Fresco.initialize(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    protected void initView() {
        radio_button_group = (RadioGroup) findViewById(R.id.radio_button_group);
        fragments.add(new My_HomeFragment());
        fragments.add(new CategoryFragment());
        fragments.add(new TabCFm());
        fragments.add(new CartFragment());
        fragments.add(new PersonalFragment());
        tabAdapter = new FragmentTabAdapter(this, fragments, R.id.fragment_content, radio_button_group);
//		tabAdapter.onCheckedChanged(radio_button_group,R.id.navigation_tab_cart);
//		((RadioButton)radio_button_group.getChildAt(3)).setChecked(true);
    }

    public void navigateTo(int index) {
        ((RadioButton) radio_button_group.getChildAt(index)).setChecked(true);
    }



}
//4A:A0:29:4A:8C:0D:76:9E:65:61:E3:1F:B7:65:88:D4:CA:70:E7:E7
//0E:FF:59:81:22:9C:26:73:EE:45:B2:13:14:3D:C8:BC:31:B4:98:6D