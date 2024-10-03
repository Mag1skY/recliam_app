package com.wythe.mall.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.amap.api.maps.MapsInitializer;
import com.amap.api.services.core.LatLonPoint;

import net.micode.wcnm.R;

public class Destination extends Fragment {

    private View view;
    private LinearLayout linearLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.totaldest, container, false);

        //初始化
        {
            linearLayout=view.findViewById(R.id.dest_button);
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }


        return view;
    }

}
