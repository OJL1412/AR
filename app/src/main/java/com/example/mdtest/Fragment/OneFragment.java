package com.example.mdtest.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.mdtest.Adapter.OrderListAdapter;
import com.example.mdtest.Bean.MyOrder;
import com.example.mdtest.Bean.MyUser;
import com.example.mdtest.R;
import com.example.mdtest.Util.OrderUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;

public class OneFragment extends Fragment  {

    private View rootView;
    private static OneFragment oneFragment;
    private final List<MyOrder> orderList = new ArrayList<>();
    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private OrderListAdapter orderListAdapter;
    private SwipeRefreshLayout swipeRefresh;

    private final MyOrder[] orders = new MyOrder[50];

//    @SuppressLint("HandlerLeak")
//    private final Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            if (msg.what == 0) {
//                List<MyOrder> mList = (List<MyOrder>) msg.obj;
//
//                orderList.clear();
//
//                for (int i = 0; i < mList.size(); i++) {
//                    orders[i] = mList.get(i);
//                    orderList.add(orders[i]);
//                }
//            }
//        }
//    };


    public OneFragment() {
        // Required empty public constructor
    }


    public static OneFragment getNewInstance() {
        if (oneFragment == null) {
            oneFragment = new OneFragment();
        }
        return oneFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Bmob.resetDomain("https://open3.bmob.cn/8/");
        Bmob.initialize(getActivity(), "36d91b12a94a333fbd7281eb1265abb3");

        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_one, container, false);
            recyclerView = rootView.findViewById(R.id.order_recycle_view);

            initOrderList();

            fab = rootView.findViewById(R.id.fab2);
            swipeRefresh = rootView.findViewById(R.id.order_swipe_refresh);

            initView();


        }
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
        swipeRefresh.setOnRefreshListener(() -> refreshOrders());

    }


    @Override
    public void onResume() {
        super.onResume();
        initOrderList();
    }


    /**
     * 初始化view
     */
    private void initView() {
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);//参数：context，列数
        recyclerView.setLayoutManager(layoutManager);
        orderListAdapter = new OrderListAdapter(this.getActivity(), orderList);
        recyclerView.setAdapter(orderListAdapter);
    }



    /**
     * 从bomb数据库中获取数据，用以初始化order列表
     */
    private void initOrderList() {

        BmobUser bmobUser = BmobUser.getCurrentUser(MyUser.class);

        OrderUtils.queryOrders(bmobUser.getObjectId());

        OrderUtils.setIOrderListener(new OrderUtils.IOrder() {
            @Override
            public void getOrderList(List<MyOrder> list) {
                if (list.size() == 0) {
                    Toast.makeText(getActivity(), "你还没有进行过悬赏哦~", Toast.LENGTH_SHORT).show();
                }else {
                    orderList.clear();

                    for (int i = 0; i < list.size(); i++) {
                        orders[i] = list.get(i);
                        orderList.add(orders[i]);
                    }

//                    Toast.makeText(getActivity(), "查询成功：共" + list.size() + "条悬赏。", Toast.LENGTH_SHORT).show();

                    orderListAdapter.notifyDataSetChanged();    //用于界面自我刷新

                }
            }
        });

    }




    /**
     * 刷新order列表
     */
    private void refreshOrders() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initOrderList();
                        orderListAdapter.notifyDataSetChanged();
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
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.smoothScrollToPosition(0);
            }
        });
    }
}
