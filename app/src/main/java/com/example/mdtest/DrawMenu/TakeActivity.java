package com.example.mdtest.DrawMenu;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.mdtest.Adapter.TakeListAdapter;
import com.example.mdtest.R;
import com.example.mdtest.Util.TakeUtils;
import com.example.mdtest.Bean.MyOrder;
import com.example.mdtest.Bean.MyUser;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;

public class TakeActivity extends AppCompatActivity {

    private final List<MyOrder> takeList = new ArrayList<>();
    private RecyclerView recyclerView;
    private TakeListAdapter takeListAdapter;
    private SwipeRefreshLayout swipeRefresh;

    private final MyOrder[] takes = new MyOrder[50];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawmenu_take);
        Bmob.resetDomain("https://open3.bmob.cn/8/");
        Bmob.initialize(this, "36d91b12a94a333fbd7281eb1265abb3");


        /*
         * 标题栏初始化
         */
        Toolbar toolbar = findViewById(R.id.take_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        initTakeList();

        swipeRefresh = findViewById(R.id.take_swipe_refresh);
        swipeRefresh.setColorSchemeResources(R.color.purple_500);
        swipeRefresh.setOnRefreshListener(() -> refreshTakes());

        initView();

        backToTop();

    }


    /**
     * 用于在销毁上一个活动返回时自我刷新
     */
    @Override
    public void onResume() {
        super.onResume();
        initTakeList();
    }


    /**
     * 初始化view
     */
    private void initView() {
        recyclerView = findViewById(R.id.take_recycle_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);//参数：context，列数
        recyclerView.setLayoutManager(layoutManager);
        takeListAdapter = new TakeListAdapter(TakeActivity.this, takeList);
        recyclerView.setAdapter(takeListAdapter);
    }



    /**
     * 从bomb数据库中获取数据，用以初始化接单列表
     */
    private void initTakeList() {
        MyUser myUser = MyUser.getCurrentUser(MyUser.class);

        TakeUtils.queryTakes(myUser.getObjectId());

        TakeUtils.setITakeListener(new TakeUtils.ITake() {
            @Override
            public void getTakeList(List<MyOrder> list) {
                if (list.size() == 0) {
                    Toast.makeText(TakeActivity.this, "你还没有进行过接单哦~", Toast.LENGTH_SHORT).show();
                }else {
                    takeList.clear();

                    for (int i = 0; i < list.size(); i++) {
                        takes[i] = list.get(i);
                        takeList.add(takes[i]);
                    }

//                    Toast.makeText(TakeActivity.this, "查询成功：共" + list.size() + "条接单。", Toast.LENGTH_SHORT).show();
                    takeListAdapter.notifyDataSetChanged();
                }
            }
        });

    }



    /**
     * 刷新take列表
     */
    private void refreshTakes() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initTakeList();
                        takeListAdapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }



    /**
     * 悬浮球操作——返回顶部
     */
    private void backToTop() {
        FloatingActionButton fab = findViewById(R.id.take_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.smoothScrollToPosition(0);
            }
        });
    }


    /**
     * 返回键
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}