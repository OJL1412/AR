package com.example.mdtest.PageTwo;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mdtest.Adapter.OrderListAdapter;
import com.example.mdtest.Bean.MyOrder;
import com.example.mdtest.R;
import com.example.mdtest.Util.FindUtils;
import com.example.mdtest.Util.OrderUtils;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;

import static android.content.ContentValues.TAG;

public class FindFragment extends Fragment  {

    private View rootView;
    private static FindFragment findFragment;
    private final MyOrder[] orders = new MyOrder[50];
    private final List<MyOrder> orderList = new ArrayList<>();
    private OrderListAdapter orderListAdapter;
//    private String key;



    /**
     * 搜索结果列表view
     */
    private RecyclerView recyclerView;

    /**
     * 搜索view
     */
    private SearchView searchView;


    public FindFragment(){}

    public static FindFragment getNewInstance(){
        if (findFragment ==null){
            findFragment =new FindFragment();
        }
        return findFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bmob.resetDomain("https://open3.bmob.cn/8/");
        Bmob.initialize(getActivity(), "36d91b12a94a333fbd7281eb1265abb3");

        if (rootView == null) {

            rootView = inflater.inflate(R.layout.fragment_find, container, false);
            Toolbar toolbar = (Toolbar)rootView.findViewById(R.id.find_toolbar);
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("查询");

            searchView = rootView.findViewById(R.id.search_input);
            recyclerView = rootView.findViewById(R.id.search_list);

            initMsgs();

            initViews();

        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void initMsgs() {
//        searchView.setIconified(false);//设置searchView处于展开状态
        searchView.onActionViewExpanded();// 当展开无输入内容的时候，没有关闭的图标
        searchView.setIconifiedByDefault(false);//默认为true在框内，设置false则在框外
        searchView.setSubmitButtonEnabled(true);
        searchView.setQueryHint("请输入取货码进行搜索");//设置默认无内容时的文字提示

        //设置输入框提示文字样式
//        mSearchView.setHintTextColor(getResources().getColor(android.R.color.white));//设置提示文字颜色
//        mSearchView.setTextColor(getResources().getColor(android.R.color.black));//设置内容文字颜色

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //提交按钮的点击事件
                Toast.makeText(getActivity(), query, Toast.LENGTH_SHORT).show();
                orderListAdapter.notifyDataSetChanged();
                Toast.makeText(getActivity(), "查询成功：共" + orderList.size() + "条订单。", Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //当输入框内容改变的时候回调
                queryOrders(newText);
                return true;
            }



            private void queryOrders(String key) {

                if (!TextUtils.isEmpty(key)) {

                    FindUtils.queryOrders(key);

                    FindUtils.setIOrderListener(new OrderUtils.IOrder() {
                        @Override
                        public void getOrderList(List<MyOrder> list) {
                            if (list.size() == 0) {
                                Log.i(TAG, "获取数据失败");
                            } else {
                                orderList.clear();

                                for (int i = 0; i < list.size(); i++) {
                                    orders[i] = list.get(i);
                                    orderList.add(orders[i]);
                                }
//                                Toast.makeText(getActivity(), "查询成功：共" + list.size() + "条订单。", Toast.LENGTH_SHORT).show();
//                                orderListAdapter.notifyDataSetChanged();
                            }

                        }
                    });
                }else {
                    orderList.clear();
                }
            }
        });


//        private void queryOrders(String key) {
//
//            if(!TextUtils.isEmpty(key)) {
//                OrderUtil.queryObjectID(key);
//
//                OrderUtil.setIPersonListener(new OrderUtil.IPerson() {
//                    @Override
//                    public void getPersonList(List<MyOrder> list) {
//                        if (list.size() == 0) {
//                            Log.i(TAG, "获取数据失败");
//                        }else {
//                            for (int i = 0; i < list.size(); i++) {
//                                orders[i] = list.get(i);
//                                orderList.add(orders[i]);
//
//                                GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);//参数：context，列数
//                                recyclerView.setLayoutManager(layoutManager);
//                                orderListAdapter = new OrderListAdapter(getActivity(), orderList);
//                                recyclerView.setAdapter(orderListAdapter);
//
////                                ArrayList<MyOrder> adapter = new ArrayList<MyOrder>(this, android.R.layout.simple_list_item_1, orders);
////                                orderList.setAdapter(adapter);
//                            }
//                        }
//                    }
//                });
//
//            }
//            List<MyOrder> adapter = new l<>(this, android.R.layout.simple_list_item_1, orders);
//            listView.setAdapter(adapter);

//        }


    }

    /**
     * 初始化view
     */
    private void initViews() {
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);//参数：context，列数
        recyclerView.setLayoutManager(layoutManager);
        orderListAdapter = new OrderListAdapter(this.getActivity(), orderList);
        recyclerView.setAdapter(orderListAdapter);
    }

}

