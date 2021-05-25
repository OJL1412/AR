package com.example.mdtest.Util;

import android.util.Log;

import com.example.mdtest.Bean.MyOrder;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class PickUtils {
    public static void queryTakeList() {
        String TAG = "PickUtils";

        BmobQuery<MyOrder> bmobQuery = new BmobQuery<>();
        bmobQuery.order("-createAt");
        bmobQuery.addWhereEqualTo("type", "快取");
        bmobQuery.findObjects(new FindListener<MyOrder>() {
            @Override
            public void done(List<MyOrder> list, BmobException e) {
                if (e == null) {
                    pick1.getPickList(list);
                } else {
                    Log.d(TAG, "done: 获取数据失败" + e.getMessage());
                }
            }
        });

//        String sql = " select * from MyOrder where type='快取' and state='未接单'" ;//查询所有订单信息
//
//        BmobQuery<MyOrder> bmobQuery = new BmobQuery<>();
//        bmobQuery.order("-createAt");
//        bmobQuery.setSQL(sql);
//        bmobQuery.doSQLQuery(new SQLQueryListener<MyOrder>() {
//            @Override
//            public void done(BmobQueryResult<MyOrder> bmobQueryResult, BmobException e) {
//                if (e == null) {
//                    List<MyOrder> PickList = bmobQueryResult.getResults();
//                    pick1.getPickList(PickList);
//                } else {
//                    Log.i(TAG, "done: 获取数据失败" + e.getMessage());
//                }
//            }
//        });
    }

    private static PickUtils.IPick pick1;

    public static void setIPickListener(PickUtils.IPick pick2) {
        pick1 = pick2;
    }

    public interface IPick{
        void getPickList(List<MyOrder> list);
    }
}
