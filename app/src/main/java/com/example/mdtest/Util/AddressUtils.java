package com.example.mdtest.Util;

import android.util.Log;

import com.example.mdtest.Bean.MyAddress;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class AddressUtils {
    public static void queryAddresses(String str) {

        String TAG = "AddressUtil";

        BmobQuery<MyAddress> bmobQuery = new BmobQuery<>();
        bmobQuery.order("-createAt");
        bmobQuery.addWhereEqualTo("userId", str);
        bmobQuery.findObjects(new FindListener<MyAddress>() {
            @Override
            public void done(List<MyAddress> list, BmobException e) {
                if (e == null) {
                    List<MyAddress> myAddressList = list;
                    address1.getAddressList(myAddressList);
                } else {
                    Log.i(TAG, "done: 获取地址失败" + e.getMessage());
                }
            }
        });
    }

    private static AddressUtils.IAddress address1;

    public static void setIAddressListener(AddressUtils.IAddress address2) {
        address1 = address2;
    }

    public interface IAddress{
        void getAddressList(List<MyAddress> list);
    }
}
