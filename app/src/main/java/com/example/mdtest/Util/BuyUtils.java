package com.example.mdtest.Util;

import android.util.Log;

import com.example.mdtest.Bean.MyOrder;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class BuyUtils {
    public static void queryTakeList() {
        String TAG = "BuyUtils";

        BmobQuery<MyOrder> bmobQuery = new BmobQuery<>();
        bmobQuery.order("-createAt");
        bmobQuery.addWhereEqualTo("type", "快买");
        bmobQuery.findObjects(new FindListener<MyOrder>() {
            @Override
            public void done(List<MyOrder> list, BmobException e) {
                if (e == null) {
                    buy1.getBuyList(list);
                } else {
                    Log.i(TAG, "done: 获取数据失败" + e.getMessage());
                }
            }
        });
    }

    private static BuyUtils.IBuy buy1;

    public static void setIBuyListener(BuyUtils.IBuy buy2) {
        buy1 = buy2;
    }

    public interface IBuy{
        void getBuyList(List<MyOrder> list);
    }
}
