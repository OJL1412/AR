package com.example.mdtest.Util;

import android.util.Log;

import com.example.mdtest.Bean.MyOrder;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class TakeUtils {
    public static void queryTakes(String str) {

        String TAG = "TakeUtil";

        BmobQuery<MyOrder> bmobQuery = new BmobQuery<>();
        bmobQuery.order("-createAt");
        bmobQuery.addWhereEqualTo("arId", str);
        bmobQuery.findObjects(new FindListener<MyOrder>() {
            @Override
            public void done(List<MyOrder> list, BmobException e) {
                if (e == null) {
                    List<MyOrder> takeList = list;
                    take1.getTakeList(takeList);
                } else {
                    Log.i(TAG, "done: 获取数据失败" + e.getMessage());
                }
            }
        });
    }

    private static TakeUtils.ITake take1;

    public static void setITakeListener(ITake take2) {
        take1 = take2;
    }

    public interface ITake{
        void getTakeList(List<MyOrder> list);
    }
}
