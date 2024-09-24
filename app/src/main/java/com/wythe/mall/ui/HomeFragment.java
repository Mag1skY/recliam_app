package com.wythe.mall.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

//import com.wythe.mall.R;
import com.wythe.mall.activity.LoginActivity;
import com.wythe.mall.activity.MainActivity;
import com.wythe.mall.tool.GlideImageLoader;
import com.wythe.mall.utils.Constants;
import com.wythe.mall.utils.GotoActivity;
import com.wythe.mall.view.CountDownTimerView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerClickListener;
import com.youth.banner.loader.ImageLoader;
//import com.youth.banner.Banner;

import net.micode.wcnm.R;

import java.util.Arrays;
import java.util.List;


public class HomeFragment extends Fragment implements View.OnClickListener {

    private View view;
    private Banner banner, bannerActivity;
    private CountDownTimerView countDownTimerView;

    List<String>images=Arrays.asList("https://img-s-msn-com.akamaized.net/tenant/amp/entityid/AA1r0NFp.img?w=768&h=513&m=6&x=584&y=184&s=61&d=61","https://img-s-msn-com.akamaized.net/tenant/amp/entityid/AA1r0NFm.img?w=768&h=512&m=6");
//    List<Integer> images = Arrays.asList(R.drawable.image01, R.drawable.image02, R.drawable.image03, R.drawable.image04, R.drawable.image05, R.drawable.image06, R.drawable.image07, R.drawable.image08);
    //    Integer[] images = new Integer[]{R.drawable.image01,R.drawable.image02,R.drawable.image03,R.drawable.image04,R.drawable.image05,R.drawable.image06,R.drawable.image07,R.drawable.image08};
    List<String> images2 = Arrays.asList("https://img-s-msn-com.akamaized.net/tenant/amp/entityid/AA1r0PrA.img?w=768&h=512&m=6&x=289&y=81&s=311&d=67");
//    Integer[] images2 = new Integer[]{R.drawable.image03,R.drawable.image04,R.drawable.image05,R.drawable.image06};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        initView();
        return view;
    }

    private void initView() {
        view.findViewById(R.id.login_btn).setOnClickListener(this);
        banner = (Banner) view.findViewById(R.id.home_banner);
        bannerActivity = (Banner) view.findViewById(R.id.home_activity_banner);
        banner.setImageLoader(new GlideImageLoader());
        //设置样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.setDelayTime(5000);//设置轮播间隔时间
        banner.setImages(images);//可以选择设置图片网址，或者资源文件，默认用Glide加载
        banner.setOnBannerClickListener(new OnBannerClickListener() {//设置点击事件
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(getActivity(), "你点击了：" + position, Toast.LENGTH_LONG).show();
            }
        });

//        Log.d("HomeFragment", "Banner is initialized: " + (banner != null));

        bannerActivity.setImageLoader(new GlideImageLoader());
        //设置样式
        bannerActivity.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        bannerActivity.setIndicatorGravity(BannerConfig.CENTER);
        bannerActivity.setDelayTime(5000);//设置轮播间隔时间
        bannerActivity.setImages(images2);//可以选择设置图片网址，或者资源文件，默认用Glide加载
        bannerActivity.setOnBannerClickListener(new OnBannerClickListener() {//设置点击事件
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(getActivity(), "你点击了：" + position, Toast.LENGTH_LONG).show();
            }
        });

        //首页按钮
        view.findViewById(R.id.home_category).setOnClickListener(this);
        view.findViewById(R.id.home_my_like).setOnClickListener(this);
        view.findViewById(R.id.home_my_yipei).setOnClickListener(this);
        view.findViewById(R.id.home_wuliu).setOnClickListener(this);
        view.findViewById(R.id.home_ticket).setOnClickListener(this);
        view.findViewById(R.id.home_bbs).setOnClickListener(this);
        view.findViewById(R.id.home_score).setOnClickListener(this);
        view.findViewById(R.id.home_invoice).setOnClickListener(this);
        view.findViewById(R.id.home_enter_miaosha).setOnClickListener(this);

        countDownTimerView = (CountDownTimerView) view.findViewById(R.id.home_countDownTimerView);
        countDownTimerView.setDownTime(24 * 60 * 60 * 1000);
        countDownTimerView.startDownTimer();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        banner.isAutoPlay(true);
        banner.start();
        bannerActivity.start();
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
        if (v.getId() == R.id.login_btn) {
            GotoActivity.gotoActiviy(this.getActivity(), LoginActivity.class);
        } else if (v.getId() == R.id.home_category) {
            ((MainActivity) this.getActivity()).navigateTo(Constants.NAVIGATE_CATEGORY);
        } else if (v.getId() == R.id.home_my_like) {

        }
    }
}
