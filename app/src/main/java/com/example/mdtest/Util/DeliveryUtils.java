package com.example.mdtest.Util;


import android.util.Log;

import com.example.mdtest.Bean.MyOrder;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;


public class DeliveryUtils {
    public static void queryOrders() {
        String TAG = "DeliveryUtil";

        BmobQuery<MyOrder> bmobQuery = new BmobQuery<>();
        bmobQuery.order("-createAt");
        bmobQuery.addWhereEqualTo("status", "未接单");
        bmobQuery.findObjects(new FindListener<MyOrder>() {
            @Override
            public void done(List<MyOrder> list, BmobException e) {
                if (e == null) {
                    delivery1.getDeliveryList(list);
                } else {
                    Log.i(TAG, "done: 获取数据失败" + e.getMessage());
                }
            }
        });

//        String sql = " select * from MyOrder " ;//查询所有订单信息
//
//        BmobQuery<MyOrder> bmobQuery = new BmobQuery<>();
//        bmobQuery.order("createAt");
//        bmobQuery.setSQL(sql);
//        bmobQuery.doSQLQuery(new SQLQueryListener<MyOrder>() {
//            @Override
//            public void done(BmobQueryResult<MyOrder> bmobQueryResult, BmobException e) {
//                if (e == null) {
//                    List<MyOrder> deliveryList = bmobQueryResult.getResults();
//                    delivery1.getDeliveryList(deliveryList);
//                } else {
//                    Log.i(TAG, "done: 获取数据失败" + e.getMessage());
//                }
//            }
//        });
    }

    private static IDelivery delivery1;

    public static void setIDeliveryListener(IDelivery delivery2) {
        delivery1 = delivery2;
    }

    public interface IDelivery{
        void getDeliveryList(List<MyOrder> list);
    }

}



