package com.wythe.mall.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.amap.api.services.core.LatLonPoint;
import com.wythe.mall.activity.ShowMap;
import com.wythe.mall.utils.GotoActivity;

import net.micode.wcnm.R;

public class TotalDest extends Fragment implements View.OnClickListener {

    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.totaldest, container, false);
        initView();
        return view;
    }
    private void initView(){
        view.findViewById(R.id.dest_button1).setOnClickListener(this);
        view.findViewById(R.id.dest_button2).setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.dest_button1){
            ShowMap.mEndPoint=new LatLonPoint(39.11360516680258,117.35223882307065);
            GotoActivity.gotoActiviy(this.getActivity(), ShowMap.class);
        }else if(v.getId()==R.id.dest_button2){
            ShowMap.mEndPoint=new LatLonPoint(39.103206,117.351595);
            GotoActivity.gotoActiviy(this.getActivity(), ShowMap.class);
        }
    }
}
