package com.wythe.mall.activity;

import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentActivity;
//import android.support.v4.app.FragmentPagerAdapter;
//import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

//import com.wythe.mall.R;
//import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.wythe.mall.ui.ProductInfoFragment;
import com.wythe.mall.ui.TotalDest;
import com.wythe.mall.view.Indicator;

import net.micode.wcnm.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wythe on 2016/7/24.
 */
public class ProductDetailActivity extends FragmentActivity implements View.OnClickListener {

    private Indicator indicator;
    private ViewPager viewPager;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> list;
    private TextView tvProductInfo,tvProductDetail,tvProductComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        initView();
    }

    protected void initView() {
        findViewById(R.id.title_left_button).setOnClickListener(this);
        findViewById(R.id.title_right_button).setOnClickListener(this);
        indicator = (Indicator) findViewById(R.id.indicator);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        tvProductInfo = (TextView) findViewById(R.id.product_info_tab);
        tvProductDetail = (TextView) findViewById(R.id.product_detail_tab);
        tvProductComment = (TextView) findViewById(R.id.product_comment_tab);
        tvProductInfo.setOnClickListener(this);
        tvProductDetail.setOnClickListener(this);
        tvProductComment.setOnClickListener(this);

        //List
        list = new ArrayList<Fragment>();
        list.add(new ProductInfoFragment());
        list.add(new TotalDest());
        list.add(new TotalDest());
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

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.title_left_button){
            finish();
        }else if(v.getId()==R.id.title_right_button){

        }else if(v.getId()==R.id.product_info_tab){
            viewPager.setCurrentItem(0);
        }else if(v.getId()==R.id.product_detail_tab){
            viewPager.setCurrentItem(1);
        }else if(v.getId()==R.id.product_comment_tab){
            viewPager.setCurrentItem(2);
        }
    }
}
