package com.example.mdtest.Util;

import android.util.Log;

import com.example.mdtest.Bean.MyOrder;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class OrderUtils {
    public static void queryOrders(String key) {

        String TAG = "OrderUtil";

//        String sql = "select * from MyOrder where objectId =?";//查询用户ID

        BmobQuery<MyOrder> bmobQuery = new BmobQuery<>();
        bmobQuery.order("-createAt");
        bmobQuery.addWhereEqualTo("userId", key);
        bmobQuery.findObjects(new FindListener<MyOrder>() {
            @Override
            public void done(List<MyOrder> list, BmobException e) {
                if (e == null) {
                    List<MyOrder> orderList = list;
                    order1.getOrderList(orderList);
                } else {
                    Log.i(TAG, "done: 查询错误" + e.getMessage());
                }
            }
        });

//        bmobQuery.doSQLQuery(sql, new SQLQueryListener<MyOrder>() {
//            @Override
//            public void done(BmobQueryResult<MyOrder> bmobQueryResult, BmobException e) {
//                if (e == null) {
//                    List<MyOrder> orderList = bmobQueryResult.getResults();
//                    order1.getOrderList(orderList);
//                } else {
//                    Log.i(TAG, "done: 查询ID错误" + e.getMessage());
//                }
//            }
//        }, key);
    }


    private static IOrder order1;

    public static void setIOrderListener(IOrder order2) {
        order1 = order2;
    }

    public interface IOrder{
        void getOrderList(List<MyOrder> list);
    }


}

