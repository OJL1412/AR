package com.example.mdtest.Util;

import android.util.Log;

import com.example.mdtest.Bean.MyOrder;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class NoNameUtils {
    public static void queryTakeList() {
        String TAG = "NoNameUtils";

        BmobQuery<MyOrder> bmobQuery = new BmobQuery<>();
        bmobQuery.order("-createAt");
        bmobQuery.addWhereEqualTo("type", "未命名");
        bmobQuery.findObjects(new FindListener<MyOrder>() {
            @Override
            public void done(List<MyOrder> list, BmobException e) {
                if (e == null) {
                    nn1.getNnList(list);
                } else {
                    Log.i(TAG, "done: 获取数据失败" + e.getMessage());
                }
            }
        });
    }

    private static NoNameUtils.INn nn1;

    public static void setINnListener(NoNameUtils.INn nn2) {
        nn1 = nn2;
    }

    public interface INn{
        void getNnList(List<MyOrder> list);
    }
}
