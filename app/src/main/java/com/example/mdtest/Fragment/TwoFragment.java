package com.example.mdtest.Fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mdtest.R;
import com.example.mdtest.Bean.MyOrder;
import com.example.mdtest.Bean.MyUser;
import com.example.mdtest.DrawMenu.OrderActivity;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;


public class TwoFragment extends Fragment {

    private View rootView;

    protected OrderActivity mActivity;

    private static TwoFragment twoFragment;

    private Button btn_submit;
    private EditText order_company;
    private EditText order_PUC;
    private EditText order_phone_tail;
    private EditText order_customer;
    private EditText order_describe;
    private EditText order_offer;
    private EditText order_address;
    private EditText order_come_time;
    private RadioGroup order_tag_group;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (OrderActivity)context;
    }


    public TwoFragment() {
        // Required empty public constructor
    }

    public static TwoFragment getNewInstance() {
        if (twoFragment == null) {
            twoFragment = new TwoFragment();
        }
        return twoFragment;
    }


    //控制控件布局
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Bmob.resetDomain("https://open3.bmob.cn/8/");
        Bmob.initialize(mActivity, "36d91b12a94a333fbd7281eb1265abb3");

        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_two, container, false);

            btn_submit = rootView.findViewById(R.id.btn_submit);
            order_company = rootView.findViewById(R.id.order_company);
            order_PUC = rootView.findViewById(R.id.order_PUC);
            order_phone_tail = rootView.findViewById(R.id.order_phone_tail);
            order_customer = rootView.findViewById(R.id.order_customer);
            order_describe = rootView.findViewById(R.id.order_describe);
            order_offer = rootView.findViewById(R.id.order_offer);
            order_address = rootView.findViewById(R.id.order_address);
            order_come_time = rootView.findViewById(R.id.order_come_time);
            order_tag_group = rootView.findViewById(R.id.order_tag_group);


        }
        // 缓存的rootView需要判断是否已经被加过parent，
        // 如果有parent需要从parent删除，要不然会发生这个rootView已经有parent的错误。
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }

        return rootView;
    }

    //控制控件的活动
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyUser myUser = MyUser.getCurrentUser(MyUser.class);
                MyOrder order = new MyOrder();
                order.setCompany(order_company.getText().toString());
                order.setPickUpCode(order_PUC.getText().toString());
                order.setPhoneTail(order_phone_tail.getText().toString());
                order.setCustomer(order_customer.getText().toString());
                order.setDescribe(order_describe.getText().toString());
                order.setOffer(order_offer.getText().toString());
                order.setAddress(order_address.getText().toString());
                order.setCome_time(order_come_time.getText().toString());
                order.setUserId(myUser.getObjectId());
                order.setStatus("未接单");


                int count = order_tag_group.getChildCount();
                for(int i = 0 ;i < count;i++){
                    RadioButton rb = (RadioButton)order_tag_group.getChildAt(i);
                    if(rb.isChecked()){
                        order.setTag(rb.getText().toString());
                        break;
                    }
                }


                order.save(new SaveListener<String>() {
                    @Override
                    public void done(String objectId, BmobException e) {

                        if (e == null) {
                            Toast.makeText(getActivity(),"添加数据成功，返回订单Id为："+ order.getObjectId() + ",数据在服务端的创建时间为："
                                    + order.getCreatedAt(), Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(getActivity(), OrderActivity.class);
                            startActivity(intent);

                            order_company.setText("");
                            order_PUC.setText("");
                            order_phone_tail.setText("");
                            order_customer.setText("");
                            order_describe.setText("");
                            order_offer.setText("");
                            order_address.setText("");
                            order_come_time.setText("");
                            order_tag_group.clearCheck();

                            getActivity().finish();

                        } else {
                            Toast.makeText(getActivity(),"提交失败：请检查注册信息是否符合要求或稍后重试", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
    }
}
