package com.wythe.mall.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.wythe.mall.activity.MainActivity;
import com.wythe.mall.tool.GlideImageLoader;
import com.wythe.mall.utils.Constants;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerClickListener;

import net.micode.wcnm.R;

import java.util.Arrays;
import java.util.List;

public class My_HomeFragment extends Fragment implements View.OnClickListener  {
    private View view;
    private Banner banner;
    List<String> images= Arrays.asList("https://sfile.chatglm.cn/chatglm4/18912068-78ef-4407-b14f-feb770db1133.jpg?image_process=format,webp"
            ,"https://img02.ma.scrmtech.com/11285/1716/meeting/1698134491/Picture12.png"
            ,"https://ts1.cn.mm.bing.net/th/id/R-C.bdd1436859012c228f8b7bb96425fd07?rik=yGutORUDSWkC5A&riu=http%3a%2f%2fwww.gongyishibao.com%2fuploads%2fallimg%2f190906%2f3-1ZZ6220R5912.JPG&ehk=2wIIiBDHdDFnbiG7Ib%2fxJG4f6KR7FyDRNC9VbIHSHss%3d&risl=&pid=ImgRaw&r=0");


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.my_fragment_home, container, false);
        initView();
        return view;
    }

    private void initView() {
        banner=view.findViewById(R.id.my_home_banner);
        banner.setImageLoader(new GlideImageLoader());
        //设置样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.setDelayTime(5000);//设置轮播间隔时间
        banner.setImages(images);//可以选择设置图片网址，或者资源文件，默认用Glide加载

        view.findViewById(R.id.my_home_category).setOnClickListener(this);
        view.findViewById(R.id.my_home_reclaim).setOnClickListener(this);
        view.findViewById(R.id.my_home_my_yipei).setOnClickListener(this);
        view.findViewById(R.id.my_home_wuliu).setOnClickListener(this);
        view.findViewById(R.id.my_home_ticket).setOnClickListener(this);
        view.findViewById(R.id.my_home_bbs).setOnClickListener(this);
        view.findViewById(R.id.my_home_score).setOnClickListener(this);
        view.findViewById(R.id.my_home_invoice).setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        banner.isAutoPlay(true);
        banner.start();
    }
    @Override
    public void onStop() {
        super.onStop();
        banner.isAutoPlay(false);
    }
    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.my_home_category) {
            ((MainActivity) this.getActivity()).navigateTo(Constants.NAVIGATE_CATEGORY);
        }else if(v.getId()==R.id.my_home_reclaim){
            ((MainActivity) this.getActivity()).navigateTo(Constants.NAVIGATE_SHARE);
        }
    }
}

