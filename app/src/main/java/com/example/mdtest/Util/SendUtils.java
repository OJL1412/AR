package com.example.mdtest.Util;

import android.util.Log;

import com.example.mdtest.Bean.MyOrder;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class SendUtils {
    public static void queryTakeList() {
        String TAG = "SendUtils";

        BmobQuery<MyOrder> bmobQuery = new BmobQuery<>();
        bmobQuery.order("-createAt");
        bmobQuery.addWhereEqualTo("type", "快送");
        bmobQuery.findObjects(new FindListener<MyOrder>() {
            @Override
            public void done(List<MyOrder> list, BmobException e) {
                if (e == null) {
                    send1.getSendList(list);
                } else {
                    Log.i(TAG, "done: 获取数据失败" + e.getMessage());
                }
            }
        });
    }

    private static SendUtils.ISend send1;

    public static void setISendListener(SendUtils.ISend send2) {
        send1 = send2;
    }

    public interface ISend{
        void getSendList(List<MyOrder> list);
    }
}
