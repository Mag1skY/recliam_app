package com.wythe.mall.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
//import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

//import com.wythe.mall.R;
import androidx.drawerlayout.widget.DrawerLayout;

import com.squareup.picasso.Picasso;
import com.wythe.mall.adapter.ProductGridItem;
import com.wythe.mall.adapter.ProductListItem;
import com.wythe.mall.tool.ImageDownloader;
import com.wythe.mall.ui.ProductInfoFragment;
import com.wythe.mall.utils.GotoActivity;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import net.micode.wcnm.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wythe on 2016/7/24.
 */
public class ProductActivity extends BaseActivity {

    private ListView lvProductList,lvProductGrid;
    private List<ProductListItem> listItems = new ArrayList<>();
    private List<ProductGridItem> gridItems = new ArrayList<>();
    private ImageView imgSwitch;
    private boolean isGrid = true;
    private DrawerLayout mDrawerLayout;
    private LinearLayout llRightMenu;
    private AutoCompleteTextView atvSearchText;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        context=this;
        initView();
        myinit();
    }

    private void myinit(){
        lvProductList.setVisibility(View.VISIBLE);
        lvProductGrid.setVisibility(View.GONE);
        if (isGrid){
            imgSwitch.setImageDrawable(getResources().getDrawable(R.drawable.product_list));
        } else {
            imgSwitch.setImageDrawable(getResources().getDrawable(R.drawable.product_grid));
        }
        isGrid = !isGrid;
    }
    @Override
    protected void initView() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        llRightMenu = (LinearLayout) findViewById(R.id.right_menu);

        atvSearchText = (AutoCompleteTextView) findViewById(R.id.search_text);

        findViewById(R.id.sort_search_button_composite).setOnClickListener(this);
        findViewById(R.id.sort_search_button_price).setOnClickListener(this);
        findViewById(R.id.sort_search_button_sales).setOnClickListener(this);
        findViewById(R.id.sort_search_button_yipei).setOnClickListener(this);

        lvProductList = (ListView) findViewById(R.id.product_list);
        lvProductGrid = (ListView) findViewById(R.id.product_grid);
        imgSwitch = (ImageView) findViewById(R.id.right_switch);
        imgSwitch.setOnClickListener(this);

        findViewById(R.id.product_title_left_button).setOnClickListener(this);

        initMenu();
        initData();
        initAdapter();
    }

    private void initMenu(){
        findViewById(R.id.title_left_button).setOnClickListener(this);
        ((TextView)findViewById(R.id.titleText)).setText(R.string.yipei_car_manager);
    }

    private void initData(){
//        https://img.moegirl.org.cn/common/9/90/Quagmire.jpg
        listItems.add(new ProductListItem("https://bkimg.cdn.bcebos.com/pic/b3fb43166d224f4a20a4c6efa9b887529822730e7bb5?x-bce-process=image/format,f_auto/watermark,image_d2F0ZXIvYmFpa2UyNzI,g_7,xp_5,yp_5,P_20/resize,m_lfit,limit_1,h_1080",
                "出生拉波尔塔","1","出生"));
        listItems.add(new ProductListItem("https://img.moegirl.org.cn/common/9/90/Quagmire.jpg",
                "出生衡红军","0","出生中的出生，免费送"));
        listItems.add(new ProductListItem("https://img.moegirl.org.cn/common/9/90/Quagmire.jpg",
                "离合器压盘总成 铁流德萨离合器压盘总成(380)杠杆","100","购买1-199件时享受优惠"));
        listItems.add(new ProductListItem("",
                "离合器压盘总成 铁流德萨离合器压盘总成(380)杠杆","101","购买1-199件时享受优惠"));
        listItems.add(new ProductListItem(""
                ,"离合器压盘总成 铁流德萨离合器压盘总成(380)杠杆","120","购买1-199件时享受优惠"));
        listItems.add(new ProductListItem(""
                ,"离合器压盘总成 铁流德萨离合器压盘总成(380)杠杆","133","购买1-199件时享受优惠"));

        gridItems.add(new ProductGridItem("","淮柴动力发动机五配套(003)","¥23156.90","","淮柴动力发动机五配套(003)","¥23156.90"));
        gridItems.add(new ProductGridItem("","淮柴动力发动机五配套(003)","¥23156.90","","淮柴动力发动机五配套(003)","¥23156.90"));
        gridItems.add(new ProductGridItem("","淮柴动力发动机五配套(003)","¥23156.90","","淮柴动力发动机五配套(003)","¥23156.90"));
        gridItems.add(new ProductGridItem("","淮柴动力发动机五配套(003)","¥23156.90","","淮柴动力发动机五配套(003)","¥23156.90"));
        gridItems.add(new ProductGridItem("","淮柴动力发动机五配套(003)","¥23156.90","","淮柴动力发动机五配套(003)","¥23156.90"));
        gridItems.add(new ProductGridItem("","淮柴动力发动机五配套(003)","¥23156.90","","淮柴动力发动机五配套(003)","¥23156.90"));
        gridItems.add(new ProductGridItem("","淮柴动力发动机五配套(003)","¥23156.90","","淮柴动力发动机五配套(003)","¥23156.90"));
        gridItems.add(new ProductGridItem("","淮柴动力发动机五配套(003)","¥23156.90","","淮柴动力发动机五配套(003)","¥23156.90"));
    }

    private void initAdapter(){
        //右侧菜单
        mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                //Toast.makeText(ProductActivity.this,"slide",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                //Toast.makeText(ProductActivity.this,"open",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                //Toast.makeText(ProductActivity.this,"close",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                //Toast.makeText(ProductActivity.this,"stateChanged",Toast.LENGTH_SHORT).show();
            }
        });
        //列表
        lvProductList.setAdapter(new CommonAdapter<ProductListItem>(this, R.layout.product_list_item, listItems) {
            @Override
            protected void convert(ViewHolder viewHolder, ProductListItem item, int position) {
                Log.e("WCNM123",Integer.toString(position));
                View view = viewHolder.getConvertView();
//                if(item.getImgPath().length()>=10){
//                    new ImageDownloader((ImageView) view.findViewById(R.id.list_item_image)).execute(item.getImgPath());
//                }
                ((TextView)view.findViewById(R.id.list_item_title)).setText(item.getTitle());
                ((TextView)view.findViewById(R.id.list_item_price)).setText(item.getPrice()+"积分");
                ((TextView)view.findViewById(R.id.list_item_info)).setText(item.getInfo());
                if(item.getImgPath().length()>=10){
                    Picasso.with(context).load(item.getImgPath()).into((ImageView) view.findViewById(R.id.list_item_image));
                }
            }
        });
        lvProductList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ProductInfoFragment.productListItem=listItems.get(position);
                GotoActivity.gotoActiviy(ProductActivity.this, ProductDetailActivity.class);
            }
        });
        //表格
        lvProductGrid.setAdapter(new CommonAdapter<ProductGridItem>(this, R.layout.product_grid_item, gridItems) {
            @Override
            protected void convert(ViewHolder viewHolder, ProductGridItem item, int position) {
                View view = viewHolder.getConvertView();
                ((TextView)view.findViewById(R.id.grid_item_title1)).setText(item.getTitle1());
                ((TextView)view.findViewById(R.id.grid_item_price1)).setText(item.getPrice1());
                ((TextView)view.findViewById(R.id.grid_item_title2)).setText(item.getTitle2());
                ((TextView)view.findViewById(R.id.grid_item_price2)).setText(item.getPrice2());
            }
        });
        lvProductGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GotoActivity.gotoActiviy(ProductActivity.this, ProductDetailActivity.class);
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.product_title_left_button){
            finish();
        }else if(v.getId()==R.id.title_left_button){
            mDrawerLayout.closeDrawer(llRightMenu);
        }else if(v.getId()==R.id.sort_search_button_yipei){
            mDrawerLayout.openDrawer(llRightMenu);
        }
//        else if(v.getId()== R.id.right_switch){
//            if (lvProductList.getVisibility() == View.VISIBLE){
//                lvProductList.setVisibility(View.GONE);
//            } else {
//                lvProductList.setVisibility(View.VISIBLE);
//            }
//            if (lvProductGrid.getVisibility() == View.VISIBLE){
//                lvProductGrid.setVisibility(View.GONE);
//            } else {
//                lvProductGrid.setVisibility(View.VISIBLE);
//            }
//            //切换图标
//            if (isGrid){
//                imgSwitch.setImageDrawable(getResources().getDrawable(R.drawable.product_list));
//            } else {
//                imgSwitch.setImageDrawable(getResources().getDrawable(R.drawable.product_grid));
//            }
//            isGrid = !isGrid;
//        }
    }
}
