package com.wythe.mall.mapfuction;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.MapsInitializer;
import com.amap.api.services.core.ServiceSettings;

import android.Manifest;
import android.widget.TextView;
import android.widget.Toast;

import net.micode.wcnm.R;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class MapApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Context context = this;
        //定位隐私政策同意
        AMapLocationClient.updatePrivacyShow(context,true,true);
        AMapLocationClient.updatePrivacyAgree(context,true);
        //地图隐私政策同意
        MapsInitializer.updatePrivacyShow(context,true,true);
        MapsInitializer.updatePrivacyAgree(context,true);
        //搜索隐私政策同意
        ServiceSettings.updatePrivacyShow(context,true,true);
        ServiceSettings.updatePrivacyAgree(context,true);
    }
}



//public class MapApplication extends Fragment implements AMapLocationListener {
//    private static final int REQUEST_PERMISSIONS = 9527;
//
//    //声明AMapLocationClient类对象
//    public AMapLocationClient mLocationClient = null;
//    //声明AMapLocationClientOption对象
//    public AMapLocationClientOption mLocationOption = null;
//    //内容
//    private TextView tvContent;
//    private View view;
//
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        view = inflater.inflate(R.layout.tab_c, container, false);
//        Context context = this.getContext();
//        //定位隐私政策同意
//        AMapLocationClient.updatePrivacyShow(context,true,true);
//        AMapLocationClient.updatePrivacyAgree(context,true);
//        //地图隐私政策同意
//        MapsInitializer.updatePrivacyShow(context,true,true);
//        MapsInitializer.updatePrivacyAgree(context,true);
//        //搜索隐私政策同意
//        ServiceSettings.updatePrivacyShow(context,true,true);
//        ServiceSettings.updatePrivacyAgree(context,true);
//
//        tvContent = view.findViewById(R.id.tv_content);
//
//
//        initLocation();
//        checkingAndroidVersion();
//
//        return view;
//    }
//    /**
//     * 检查Android版本
//     */
//    private void checkingAndroidVersion() {
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
//            //Android6.0及以上先获取权限再定位
//            requestPermission();
//        }else {
//            //Android6.0以下直接定位
//            mLocationClient.startLocation();
//        }
//    }
//
//
//    /**
//     * Toast提示
//     * @param msg 提示内容
//     */
//    private void showMsg(String msg){
//        Toast.makeText(this.getContext(),msg,Toast.LENGTH_SHORT).show();
//    }
//
//
//    /**
//     * 请求权限结果
//     * @param requestCode
//     * @param permissions
//     * @param grantResults
//     */
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        //设置权限请求结果
//        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
//    }
//
//
//    /*动态请求权限*/
//    @AfterPermissionGranted(REQUEST_PERMISSIONS)
//    private void requestPermission(){
//        String [] permissions= {
//                Manifest.permission.ACCESS_COARSE_LOCATION,
//                Manifest.permission.ACCESS_FINE_LOCATION,
//                Manifest.permission.READ_PHONE_STATE,
//                Manifest.permission.WRITE_EXTERNAL_STORAGE
//        };
//
//        if (EasyPermissions.hasPermissions(this.getContext(), permissions)) {
//            //true 有权限 开始定位
//            showMsg("已获得权限，可以定位啦！");
//
//            mLocationClient.startLocation();
//        }else{
//            EasyPermissions.requestPermissions(this, "需要权限", REQUEST_PERMISSIONS, permissions);
//        }
//    }
//
//    /**
//     * 初始化定位
//     */
//    private void initLocation() {
//        //初始化定位
//        try {
//            mLocationClient = new AMapLocationClient(getApplicationContext());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        if (mLocationClient != null) {
//            //设置定位回调监听
//            mLocationClient.setLocationListener(this);
//            //初始化AMapLocationClientOption对象
//            mLocationOption = new AMapLocationClientOption();
//            //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
//            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
//            //获取最近3s内精度最高的一次定位结果：
//            //设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
//            mLocationOption.setOnceLocationLatest(true);
//            //设置是否返回地址信息（默认返回地址信息）
//            mLocationOption.setNeedAddress(true);
//            //设置定位请求超时时间，单位是毫秒，默认30000毫秒，建议超时时间不要低于8000毫秒。
//            mLocationOption.setHttpTimeOut(20000);
//            //关闭缓存机制，高精度定位会产生缓存。
//            mLocationOption.setLocationCacheEnable(false);
//            //给定位客户端对象设置定位参数
//            mLocationClient.setLocationOption(mLocationOption);
//        }
//    }
//
//    /**
//     * 接收异步返回的定位结果
//     *
//     * @param aMapLocation
//     */
//    @Override
//    public void onLocationChanged(AMapLocation aMapLocation) {
//        if (aMapLocation != null) {
//            if (aMapLocation.getErrorCode() == 0) {
//                //地址
//                String address = aMapLocation.getAddress();
//                tvContent.setText(address==null?"无地址":address);
//            } else {
//                //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
//                Log.e("AmapError", "location Error, ErrCode:"
//                        + aMapLocation.getErrorCode() + ", errInfo:"
//                        + aMapLocation.getErrorInfo());
//            }
//        }
//    }
//}
