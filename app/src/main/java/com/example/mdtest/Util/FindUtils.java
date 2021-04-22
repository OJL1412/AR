package com.example.mdtest.Util;

import android.util.Log;

import com.example.mdtest.Bean.MyOrder;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class FindUtils {
    public static void queryOrders(String key) {

        String TAG = "OrderUtil";

        BmobQuery<MyOrder> bmobQuery = new BmobQuery<>();
        bmobQuery.order("-createAt");
        bmobQuery.addWhereEqualTo("pickUpCode", key);
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
    }

        private static OrderUtils.IOrder order1;

        public static void setIOrderListener(OrderUtils.IOrder order2) {
            order1 = order2;
        }

        public interface IOrder{
            void getOrderList(List<MyOrder> list);
        }
}
