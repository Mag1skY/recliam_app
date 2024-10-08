package com.wythe.mall.activity;

import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentActivity;
//import android.support.v4.app.FragmentPagerAdapter;
//import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

//import com.wythe.mall.R;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.wythe.mall.ui.RedPacketNotUsedFragment;
import com.wythe.mall.ui.RedPacketOutDateFragment;
import com.wythe.mall.ui.RedPacketUsedFragment;
import com.wythe.mall.ui.TicketNotUsedFragment;
import com.wythe.mall.ui.TicketOutDateFragment;
import com.wythe.mall.ui.TicketUsedFragment;
import com.wythe.mall.view.Indicator;

import net.micode.wcnm.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wythe on 2016/7/21.
 */
public class TicketActivity extends FragmentActivity implements View.OnClickListener {
    private Indicator indicator;
    private ViewPager viewPager;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> list;
    private TextView tvNotUsed,tvOutDate,tvUsed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);
        initView();
    }

    protected void initView() {
        findViewById(R.id.title_left_button).setOnClickListener(this);
        ((TextView)findViewById(R.id.titleText)).setText(R.string.pocket_ticket);
        indicator = (Indicator) findViewById(R.id.indicator);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        tvNotUsed = (TextView) findViewById(R.id.not_used_tab);
        tvOutDate = (TextView) findViewById(R.id.out_date_tab);
        tvUsed = (TextView) findViewById(R.id.used_tab);
        tvNotUsed.setOnClickListener(this);
        tvOutDate.setOnClickListener(this);
        tvUsed.setOnClickListener(this);

        //List
        list = new ArrayList<Fragment>();
        list.add(new TicketNotUsedFragment());
        list.add(new TicketOutDateFragment());
        list.add(new TicketUsedFragment());
        //Adapter
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        };

        viewPager.setAdapter(mAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                indicator.scroll(position,positionOffset);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void onClick(View v) {
        if(v.getId()==R.id.title_left_button){
            finish();
        }else if(v.getId()==R.id.not_used_tab){
            viewPager.setCurrentItem(0);
        }else if(v.getId()==R.id.out_date_tab){
            viewPager.setCurrentItem(1);
        }else if(v.getId()==R.id.used_tab){
            viewPager.setCurrentItem(2);
        }
    }
}
