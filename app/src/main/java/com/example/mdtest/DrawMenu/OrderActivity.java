package com.example.mdtest.DrawMenu;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.mdtest.Adapter.OrderAdapter;
import com.example.mdtest.Fragment.OneFragment;
import com.example.mdtest.Fragment.TwoFragment;
import com.example.mdtest.R;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager vp;
    private TextView item_order1, item_order0;

    private OneFragment oneFragment;
    private TwoFragment twoFragment;

    private List<Fragment> mFragmentList = new ArrayList<Fragment>();

    private OrderAdapter adapter;

    private LinearLayout linearLayout;

    private FragmentTransaction mTransaction;
    public static final int VIEW_ONE_INDEX = 0;
    public static final int VIEW_TWO_INDEX = 1;
    private int temp_position_index = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawmenu_order);

        initViews();

        adapter = new OrderAdapter(this.getSupportFragmentManager(), mFragmentList);

        vp.setOffscreenPageLimit(2);//ViewPager的缓存为2帧
        vp.setAdapter(adapter);
        vp.setCurrentItem(0);//初始设置ViewPager选中第一帧
        item_order0.setTextColor(Color.parseColor("#000000"));
        item_order1.setTextColor(Color.parseColor("#66CDAA"));



        /**
         * ViewPager的监听事件
         */
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                changeTextColor(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                 /*此方法是在状态改变的时候调用，其中arg0这个参数有三种状态（0，1，2）。
                arg0 ==1的时辰默示正在滑动，
                arg0==2的时辰默示滑动完毕了，
                arg0==0的时辰默示什么都没做。*/

            }
        });


        /**
         * 标题栏
         */
        Toolbar toolbar = findViewById(R.id.order_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


    }


//    /**
//     * 初始化
//     */
//    private void initViews() {
//        item_order1 = findViewById(R.id.item_order1);
//        item_order0 = findViewById(R.id.item_order0);
//
//        item_order1.setOnClickListener(this);
//        item_order0.setOnClickListener(this);
//
//        vp = findViewById(R.id.mainViewPager);
//        oneFragment = new OneFragment();
//        twoFragment = new TwoFragment();
//
//        //给FragmentList添加数据
//        mFragmentList.add(this.oneFragment);
//        mFragmentList.add(this.twoFragment);
//
//    }


    /**
     * 点击底部Text 动态修改ViewPager的内容
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.item_order1:
                if (temp_position_index != VIEW_ONE_INDEX) {
                    changeTextColor(0);
//                    vp.setCurrentItem(0, true);
                    //显示
                    mTransaction = getSupportFragmentManager().beginTransaction();
                    mTransaction.replace(R.id.order_content, oneFragment);
                    mTransaction.commit();
                }
                temp_position_index = VIEW_ONE_INDEX;
                break;

            case R.id.item_order0:
                if (temp_position_index != VIEW_TWO_INDEX) {
                    changeTextColor(1);
//                    vp.setCurrentItem(1, true);
                    //显示
                    mTransaction = getSupportFragmentManager().beginTransaction();
                    mTransaction.replace(R.id.order_content, twoFragment);
                    mTransaction.commit();
                }
                temp_position_index = VIEW_TWO_INDEX;
                break;

            default:
                break;

        }
    }



    /**
     * 由ViewPager的滑动修改底部导航Text的颜色
     */
    private void changeTextColor(int position) {
        if (position == 0) {
            item_order0.setTextColor(Color.parseColor("#000000"));
            item_order1.setTextColor(Color.parseColor("#66CDAA"));
        } else if (position == 1) {
            item_order1.setTextColor(Color.parseColor("#000000"));
            item_order0.setTextColor(Color.parseColor("#66CDAA"));
        }
    }



    /**
     * 导航栏初始化
     */
    private void initViews() {
        vp = findViewById(R.id.mainViewPager);
        linearLayout = findViewById(R.id.linear_layout);

        item_order1 = findViewById(R.id.item_order1);
        item_order0 = findViewById(R.id.item_order0);

        item_order1.setOnClickListener(this);
        item_order0.setOnClickListener(this);

        oneFragment = OneFragment.getNewInstance();
        twoFragment = TwoFragment.getNewInstance();

        //显示
        mTransaction = getSupportFragmentManager().beginTransaction();
        mTransaction.replace(R.id.order_content, oneFragment);
        mTransaction.commit();
    }


//    //导航栏选择
//    public void switchView(View view) {
//        switch (view.getId()) {
//            case R.id.item_order1:
//                if (temp_position_index != VIEW_ONE_INDEX) {
//                    //显示
//                    mTransaction = getSupportFragmentManager().beginTransaction();
//                    mTransaction.replace(R.id.order_content, oneFragment);
//                    mTransaction.commit();
//                }
//                temp_position_index = VIEW_ONE_INDEX;
//                break;
//
//            case R.id.item_order0:
//                if (temp_position_index != VIEW_TWO_INDEX) {
//                    //显示
//                    mTransaction = getSupportFragmentManager().beginTransaction();
//                    mTransaction.replace(R.id.order_content, twoFragment);
//                    mTransaction.commit();
//                }
//                temp_position_index = VIEW_TWO_INDEX;
//                break;
//
//            default:
//                break;
//        }
//    }



    /**
     * 返回键
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}