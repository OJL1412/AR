package com.example.mdtest.Util;

import android.util.Log;

import com.example.mdtest.Bean.MyOrder;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class OwnUtils {
    public static void queryTakeList() {
        String TAG = "OwnUtils";

        BmobQuery<MyOrder> bmobQuery = new BmobQuery<>();
        bmobQuery.order("-createAt");
        bmobQuery.addWhereEqualTo("type", "自定义");
        bmobQuery.findObjects(new FindListener<MyOrder>() {
            @Override
            public void done(List<MyOrder> list, BmobException e) {
                if (e == null) {
                    own1.getOwnList(list);
                } else {
                    Log.i(TAG, "done: 获取数据失败" + e.getMessage());
                }
            }
        });
    }

    private static OwnUtils.IOwn own1;

    public static void setIOwnListener(OwnUtils.IOwn own2) {
        own1 = own2;
    }

    public interface IOwn{
        void getOwnList(List<MyOrder> list);
    }
}
