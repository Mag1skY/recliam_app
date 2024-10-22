package com.wythe.mall.ui;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentPagerAdapter;
//import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

//import com.wythe.mall.R;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.wythe.mall.Client.ClientThread;
import com.wythe.mall.Client.ClientVal;
import com.wythe.mall.activity.LoginActivity;
import com.wythe.mall.activity.RegisterCompleteActivity;
import com.wythe.mall.adapter.ProductListItem;
import com.wythe.mall.tool.GlideImageLoader;
import com.wythe.mall.utils.GotoActivity;
import com.wythe.mall.utils.UserManager;
import com.wythe.mall.view.CircleIndicator;
import com.wythe.mall.view.CountDownTimerView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerClickListener;

import net.micode.wcnm.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by wythe on 2016/7/24.
 */
public class ProductInfoFragment extends Fragment {
    public static ProductListItem productListItem;
    private View view;
    private Banner banner;

    private TextView my_product_price;
    private TextView my_product_name;
    private TextView my_product_info;

    private TextView buy;
    private CountDownTimerView countDownTimerView;
    private CircleIndicator circleIndicator;
    private ViewPager vpSuggestion;
    List<Fragment> list = new ArrayList<>();

    List<Integer> images= Arrays.asList(R.drawable.message_item,R.drawable.message_item,R.drawable.message_item,R.drawable.message_item);
//    Integer[] images = new Integer[]{R.drawable.message_item,R.drawable.message_item,R.drawable.message_item,R.drawable.message_item};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_product_info, container, false);
        initView();
        myinit();
        return view;
    }

    private void myinit(){
        my_product_price=view.findViewById(R.id.my_product_price);
        my_product_price.setText(productListItem.getPrice()+"积分");
        my_product_name=view.findViewById(R.id.my_product_name);
        my_product_name.setText(productListItem.getTitle());
        my_product_info=view.findViewById(R.id.my_product_info);
        my_product_info.setText(productListItem.getInfo());
        buy=view.findViewById(R.id.buy);
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(UserManager.getInstance().isLogin()){
                    ProgressDialog progressDialog = new ProgressDialog(getActivity());
                    progressDialog.setTitle("请稍后");
                    progressDialog.setMessage("正在购买中");
                    progressDialog.setMax(100);
                    progressDialog.setProgress(0);
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    ClientThread clientThread = new ClientThread();
                    ClientThread.username = UserManager.getInstance().getUsername();
                    ClientThread.password = "1";
                    ClientThread.operators = ClientVal.SUB_POINT;
                    ClientThread.point=Integer.parseInt(productListItem.getPrice());
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
                                    RegisterCompleteActivity.s="购买成功";
                                    UserManager.getInstance().point=clientThread.val_point;
                                }
                            } else {
                                RegisterCompleteActivity.s="服务器错误";
                                // 登录失败，显示错误信息
//                        Toast.makeText(context, "服务器错误", Toast.LENGTH_SHORT).show();

                            }
                            GotoActivity.gotoActiviy(getActivity(), RegisterCompleteActivity.class, true);
                        }
                    }.execute();
                }else{
                    Toast.makeText(getActivity(),"请先登录",Toast.LENGTH_LONG).show();
                    GotoActivity.gotoActiviy(getActivity(), LoginActivity.class);
                }
            }
        });
    }



    protected void initView(){
        banner = (Banner) view.findViewById(R.id.home_banner1);
        //设置样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.setDelayTime(5000);//设置轮播间隔时间
        if(productListItem.getImgPath().length()>=10){
            banner.setImageLoader(new GlideImageLoader());

            List<String>my_image=Arrays.asList(productListItem.getImgPath());
            banner.setImages(my_image);
        }else {
//            banner.setImages(images);//可以选择设置图片网址，或者资源文件，默认用Glide加载
        }
        banner.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void OnBannerClick(int view) {
                Toast.makeText(getActivity(),"你点击了",Toast.LENGTH_LONG).show();
            }
        });

        countDownTimerView = (CountDownTimerView) view.findViewById(R.id.product_countDownTimerView);
        countDownTimerView.setDownTime(24*60*60*1000);
        countDownTimerView.startDownTimer();

        vpSuggestion = (ViewPager) view.findViewById(R.id.suggestion_viewpager);
        circleIndicator = (CircleIndicator) view.findViewById(R.id.suggestion_indicator);
        list.add(new SuggestionFragment());
        list.add(new SuggestionFragment());
        list.add(new SuggestionFragment());
        list.add(new SuggestionFragment());
        vpSuggestion.setAdapter(new FragmentPagerAdapter(this.getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        circleIndicator.setViewPager(vpSuggestion);

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
}
