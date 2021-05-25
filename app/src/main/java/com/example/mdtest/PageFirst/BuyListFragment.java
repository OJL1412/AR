package com.example.mdtest.PageFirst;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.mdtest.Adapter.DeliveryAdapter;
import com.example.mdtest.Bean.MyOrder;
import com.example.mdtest.R;
import com.example.mdtest.Util.BuyUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;

public class BuyListFragment extends Fragment {
    private View rootView;      //缓存Fragment view
    private static BuyListFragment buyFragment;
    private final List<MyOrder> deliveryList = new ArrayList<>();
    private RecyclerView recyclerView;
    private DeliveryAdapter deliveryAdapter;
    private FloatingActionButton fab;
    private SwipeRefreshLayout swipeRefresh;


    private final MyOrder[] deliveries = new MyOrder[50];       //默认最多50条悬赏


    public BuyListFragment() {
    }

    public static BuyListFragment getNewInstance() {
        if (buyFragment == null) {
            buyFragment = new BuyListFragment();
        }
        return buyFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Bmob.resetDomain("https://open3.bmob.cn/8/");
        Bmob.initialize(getActivity(), "36d91b12a94a333fbd7281eb1265abb3");     //初始化数据库

        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_buy_list, container, false);
            recyclerView = rootView.findViewById(R.id.recycle_view_buy);


//            //标题栏及相关控件初始化
//            Toolbar toolbar = (Toolbar)rootView.findViewById(R.id.toolbar);
//            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
//            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("首页");
//
//            ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
//            if (actionBar != null)
//            {
//                actionBar.setDisplayHomeAsUpEnabled(true);
//                actionBar.setHomeAsUpIndicator(R.mipmap.ic_pc);
//            }

            initDeliveries();       //初始化"未接单"的悬赏

            fab = rootView.findViewById(R.id.fab_buy);
            swipeRefresh = rootView.findViewById(R.id.swipe_refresh_buy);

            initView();

        }
        // 缓存的rootView需要判断是否已经被加过parent，
        // 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }

        return rootView;
    }



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);

        backToTop();

        swipeRefresh.setColorSchemeResources(R.color.purple_500);
        swipeRefresh.setOnRefreshListener(() -> refreshDeliveries());

    }



    /**
     * 用于在销毁上一个活动返回时自我刷新
     */
    @Override
    public void onResume() {
        super.onResume();
        initDeliveries();
    }


    /**
     * 初始化view
     */
    private void initView() {
        GridLayoutManager layoutManager = new GridLayoutManager(this.getContext(), 1);//参数：context，列数
        recyclerView.setLayoutManager(layoutManager);
        deliveryAdapter = new DeliveryAdapter(deliveryList);
        recyclerView.setAdapter(deliveryAdapter);
    }


    /**
     * 初始化"未接单"的悬赏
     */
    private void initDeliveries() {

        BuyUtils.queryTakeList();

        BuyUtils.setIBuyListener(new BuyUtils.IBuy() {
            @Override
            public void getBuyList(List<MyOrder> list) {
                if (list.size() == 0) {
                    Toast.makeText(getActivity(), "数据获取失败", Toast.LENGTH_SHORT).show();
                }else {
                    deliveryList.clear();

                    for (int i = 0; i < list.size(); i++) {
                        deliveries[i] = list.get(i);
                        deliveryList.add(deliveries[i]);
                    }

//                    PickUtils.queryTakeList();


//                    Toast.makeText(getActivity(), "查询成功：共" + list.size() + "条订单。", Toast.LENGTH_SHORT).show();

                    deliveryAdapter.notifyDataSetChanged();    //用于界面自我刷新
                }
            }
        });


//        DeliveryUtils.queryOrders();
//
//        DeliveryUtils.setIDeliveryListener(new DeliveryUtils.IDelivery() {
//            @Override
//            public void getDeliveryList(List<MyOrder> list) {
//                if (list.size() == 0) {
//                    Toast.makeText(getActivity(), "数据获取失败", Toast.LENGTH_SHORT).show();
//                }else {
//                    deliveryList.clear();
//
//                    for (int i = 0; i < list.size(); i++) {
//                        deliveries[i] = list.get(i);
//                        deliveryList.add(deliveries[i]);
//                    }
//
////                    PickUtils.queryTakeList();
//
//
////                    Toast.makeText(getActivity(), "查询成功：共" + list.size() + "条订单。", Toast.LENGTH_SHORT).show();
//
//                    deliveryAdapter.notifyDataSetChanged();    //用于界面自我刷新
//
//                }
//            }
//        });
    }


    /**
     * 刷新界面
     */
    private void refreshDeliveries() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);     //设置刷新时间为1秒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initDeliveries();
                        deliveryAdapter.notifyDataSetChanged();    //用于界面自我刷新
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }


    /**
     * "返回顶部"功能实现
     */
    private void backToTop() {
        //悬浮球操作
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.smoothScrollToPosition(0);
            }
        });
    }
}
