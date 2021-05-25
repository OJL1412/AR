package com.example.mdtest.Setting;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.mdtest.PageFirst.BuyListFragment;
import com.example.mdtest.PageFirst.NoNameListFragment;
import com.example.mdtest.PageFirst.OwnListFragment;
import com.example.mdtest.PageFirst.PickListFragment;
import com.example.mdtest.PageFirst.SendListFragment;
import com.example.mdtest.PageFirst.TotalListFragment;
import com.example.mdtest.R;

import cn.bmob.v3.Bmob;

public class TotalTakeActivity extends AppCompatActivity {

    //底部导航栏的wid_get
    private RadioGroup mNavGroup;
    private FragmentTransaction mTransaction;

    //页面设置
    private Fragment totalFragment, pickFragment, buyFragment, sendFragment, ownFragment, noNameFragment;
    public static final int VIEW_PICK_INDEX = 0;
    public static final int VIEW_BUY_INDEX = 1;
    public static final int VIEW_SEND_INDEX = 2;
    public static final int VIEW_OWN_INDEX = 3;
    public static final int VIEW_NONAME_INDEX = 4;
    public static final int VIEW_TOTAL_INDEX = 5;
    private int temp_position_index = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_take);
        Bmob.resetDomain("https://open3.bmob.cn/8/");
        Bmob.initialize(this, "36d91b12a94a333fbd7281eb1265abb3");

        Toolbar toolbar = findViewById(R.id.total_take_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
        {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        initView();
    }


    //导航栏初始化
    private void initView() {
        mNavGroup = (RadioGroup) findViewById(R.id.rdg_total_take);
        totalFragment = TotalListFragment.getNewInstance();
        pickFragment = PickListFragment.getNewInstance();
        buyFragment = BuyListFragment.getNewInstance();
        sendFragment = SendListFragment.getNewInstance();
        ownFragment = OwnListFragment.getNewInstance();
        noNameFragment = NoNameListFragment.getNewInstance();

        //显示
        mTransaction = getSupportFragmentManager().beginTransaction();
        mTransaction.replace(R.id.fragment_takeList_content, totalFragment);
        mTransaction.commit();
    }

    //导航栏选择
    @SuppressLint("NonConstantResourceId")
    public void switchView(View view) {
        switch (view.getId()) {
            case R.id.rbt_take_total:
                if (temp_position_index != VIEW_TOTAL_INDEX) {
                    //显示
                    mTransaction = getSupportFragmentManager().beginTransaction();
                    mTransaction.replace(R.id.fragment_takeList_content, totalFragment);
                    mTransaction.commit();
                }
                temp_position_index = VIEW_TOTAL_INDEX;
                break;

            case R.id.rbt_take_pick:
                if (temp_position_index != VIEW_PICK_INDEX) {
                    //显示
                    mTransaction = getSupportFragmentManager().beginTransaction();
                    mTransaction.replace(R.id.fragment_takeList_content, pickFragment);
                    mTransaction.commit();
                }
                temp_position_index = VIEW_PICK_INDEX;
                break;

            case R.id.rbt_take_buy:
                if (temp_position_index != VIEW_BUY_INDEX) {
                    //显示
                    mTransaction = getSupportFragmentManager().beginTransaction();
                    mTransaction.replace(R.id.fragment_takeList_content, buyFragment);
                    mTransaction.commit();
                }
                temp_position_index = VIEW_BUY_INDEX;
                break;

            case R.id.rbt_take_send:
                if (temp_position_index != VIEW_SEND_INDEX) {
                    //显示
                    mTransaction = getSupportFragmentManager().beginTransaction();
                    mTransaction.replace(R.id.fragment_takeList_content, sendFragment);
                    mTransaction.commit();
                }
                temp_position_index = VIEW_SEND_INDEX;
                break;

            case R.id.rbt_take_own:
                if (temp_position_index != VIEW_OWN_INDEX) {
                    //显示
                    mTransaction = getSupportFragmentManager().beginTransaction();
                    mTransaction.replace(R.id.fragment_takeList_content, ownFragment);
                    mTransaction.commit();
                }
                temp_position_index = VIEW_OWN_INDEX;
                break;

            case R.id.rbt_take_noName:
                if (temp_position_index != VIEW_NONAME_INDEX) {
                    //显示
                    mTransaction = getSupportFragmentManager().beginTransaction();
                    mTransaction.replace(R.id.fragment_takeList_content, noNameFragment);
                    mTransaction.commit();
                }
                temp_position_index = VIEW_NONAME_INDEX;
                break;

            default:
                break;
        }
    }

    /**
     * 返回键
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}