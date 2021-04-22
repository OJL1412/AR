 package com.example.mdtest.Util;

import android.util.Log;

import com.example.mdtest.Bean.MyOrder;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class util {
    public static void queryOrders(String str) {

        String TAG = "Util";

        BmobQuery<MyOrder> bmobQuery = new BmobQuery<>();

        bmobQuery.addWhereEqualTo("userId", str);
        bmobQuery.findObjects(new FindListener<MyOrder>() {
            @Override
            public void done(List<MyOrder> list, BmobException e) {
                if (e == null) {
                    List<MyOrder> orderList = list;
                    order1.getOrderList(orderList);
                } else {
                    Log.i(TAG, "done: 获取数据失败" + e.getMessage());
                }
            }
        });
    }

    private static OrderUtils.IOrder order1;

    public static void setIOrderListener(OrderUtils.IOrder order2) {
        order1 = order2;
    }

    public interface IOrder{
        void getOrderList(List<MyOrder> list);
    }
}
