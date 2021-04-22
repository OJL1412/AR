package com.example.mdtest.CardInit;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.mdtest.Bean.MyOrder;
import com.example.mdtest.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.UpdateListener;
import de.hdodenhof.circleimageview.CircleImageView;

public class OrderListActivity extends AppCompatActivity {

    public static final String ORDER_ID = "order_id";
    public static final String ORDER_IMAGE_ID = "order_image_id";

    private String orderId;
    private String orderTag;
    private String orderStatus;

    private CircleImageView order_list_icon;
    private TextView order_list_status;
    private TextView order_list_cui;
    private TextView order_list_update;
    private TextView order_tag;
    private TextView order_offer;
    private TextView order_time;
    private TextView order_company;
    private TextView order_address;
    private TextView order_phone_tail;
    private TextView order_PUC;
    private TextView order_customer;
    private TextView order_objectId;
    private TextView order_time2;
    private TextView order_describe;
    private Button bt_order_cash;
    private Button bt_order_cancel;

    private FloatingActionButton order_fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cardinit_order);
        Bmob.resetDomain("https://open3.bmob.cn/8/");
        Bmob.initialize(this, "36d91b12a94a333fbd7281eb1265abb3");

        Intent intent = getIntent();
        orderId = intent.getStringExtra(ORDER_ID);
        orderTag = intent.getStringExtra(ORDER_IMAGE_ID);

        Toolbar toolbar = findViewById(R.id.order_list_toolbar);
        CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapsing_toolbar2);
        ImageView orderImageView = findViewById(R.id.order_image_view);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
        {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        order_list_status = findViewById(R.id.deli_running);
        order_list_icon = findViewById(R.id.order_list_icon);
        order_list_cui = findViewById(R.id.order_list_cui);
        order_list_update = findViewById(R.id.order_list_update);
        order_tag = findViewById(R.id.order_list_tag);
        order_offer = findViewById(R.id.order_list_offer);
        order_time = findViewById(R.id.order_list_time);
        order_company = findViewById(R.id.order_list_company);
        order_address = findViewById(R.id.order_list_address);
        order_phone_tail = findViewById(R.id.order_list_phone_tail);
        order_PUC = findViewById(R.id.order_list_PUC);
        order_customer = findViewById(R.id.order_list_customer);
        order_objectId = findViewById(R.id.order_list_objectId);
        order_time2 = findViewById(R.id.order_list_time2);
        order_describe = findViewById(R.id.order_list_describe);

//        order_fab = findViewById(R.id.order_fab);

        bt_order_cash = findViewById(R.id.bt_order_cash);
        bt_order_cancel = findViewById(R.id.bt_order_cancel);

        collapsingToolbar.setTitle("悬赏"+orderId);
        switch (orderTag) {
            case "水果":
                Glide.with(this).load(R.mipmap.ic_fruit).into(orderImageView);
                break;
            case "电子器件":
                Glide.with(this).load(R.mipmap.ic_electric).into(orderImageView);
                break;
            case "书本":
                Glide.with(this).load(R.mipmap.ic_book).into(orderImageView);
                break;
            case "衣服":
                Glide.with(this).load(R.mipmap.ic_clothes).into(orderImageView);
                break;
            default:
                Glide.with(this).load(R.mipmap.ic_box).into(orderImageView);
                break;
        }


        initOrderList();
        clickStatus();
        rush();
        updateOffer();
        updatePUC();
        updateCustomer();
        updatePhoneTail();
        updateCompany();
        updateAddress();
        updateComeTime();
        updateDescribe();
//        orderStatus();
        cash();
        cancel();

    }


    /**
     * 初始化悬赏卡片
     */
    public void initOrderList() {
        BmobQuery<MyOrder> bmobQuery = new BmobQuery<>();

        bmobQuery.getObject(orderId, new QueryListener<MyOrder>() {
            @Override
            public void done(MyOrder myOrder, BmobException e) {
                if(e==null){

                    switch (orderTag) {
                        case "水果":
                            order_list_icon.setImageResource(R.mipmap.ic_fruit);
                            break;
                        case "电子器件":
                            order_list_icon.setImageResource(R.mipmap.ic_electric);
                            break;
                        case "书本":
                            order_list_icon.setImageResource(R.mipmap.ic_book);
                            break;
                        case "衣服":
                            order_list_icon.setImageResource(R.mipmap.ic_clothes);
                            break;
                        default:
                            order_list_icon.setImageResource(R.mipmap.ic_box);
                            break;
                    }

                    order_tag.setText(String.valueOf(myOrder.getTag()));
                    order_offer.setText(String.valueOf(myOrder.getOffer()));
                    order_time.setText(String.valueOf(myOrder.getCome_time()));
                    order_company.setText(String.valueOf(myOrder.getCompany()));
                    order_address.setText(String.valueOf(myOrder.getAddress()));
                    order_phone_tail.setText(String.valueOf(myOrder.getPhoneTail()));
                    order_PUC.setText(String.valueOf(myOrder.getPickUpCode()));
                    order_customer.setText(String.valueOf(myOrder.getCustomer()));
                    order_objectId.setText(String.valueOf(myOrder.getObjectId()));
                    order_time2.setText(String.valueOf(myOrder.getCreatedAt()));
                    order_describe.setText(String.valueOf(myOrder.getDescribe()));

                    switch (myOrder.getStatus())
                    {
                        case "已支付":
                            order_list_status.setText("悬赏已支付");
                            break;

                        case "未支付":
                            order_list_status.setText("悬赏已完成");
                            break;

                        case "已接单":
                            order_list_status.setText("悬赏进行中");
                            break;

                        case "未接单":
                            order_list_status.setText("悬赏待接单");
                            break;
                    }

                    Toast.makeText(OrderListActivity.this, "数据加载成功", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(OrderListActivity.this, "数据加载失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    /**
     * 催单功能
     */
    public void rush() {
        order_list_cui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyOrder myOrder = new MyOrder();
                int num = myOrder.getRush();
                num = num + 1;
                myOrder.setRush(num);
                myOrder.update(orderId, new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if(e==null){
                            Toast.makeText(OrderListActivity.this, "催单成功"+myOrder.getRush()+"次", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(OrderListActivity.this, "催单失败", Toast.LENGTH_SHORT).show();
                        }
                    }

                });
            }
        });
    }


    /**
     * 修改赏金
     */
    public void updateOffer() {
        order_list_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(OrderListActivity.this);
                builder.setIcon(R.mipmap.ic_money);
                builder.setTitle("新的赏金");

                View view = LayoutInflater.from(OrderListActivity.this).inflate(R.layout.update_offer, null);  //通过LayoutInflater来加载一个xml的布局文件作为一个View对象
                builder.setView(view);      //设置我们自己定义的布局文件作为弹出框的Content

                final EditText offer = (EditText)view.findViewById(R.id.offer);

                builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        MyOrder myOrder = new MyOrder();
                        String upOffer = offer.getText().toString().trim();

                        order_offer.setText(upOffer);

                        myOrder.setOffer(upOffer);
                        myOrder.update(orderId, new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if(e==null){
                                    Toast.makeText(OrderListActivity.this,"赏金修改于"+myOrder.getUpdatedAt(), Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(OrderListActivity.this, "修改失败:"+e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {

                    }
                });
                builder.show();
            }
        });
    }


    /**
     * 修改取货码
     */
    public void updatePUC() {
        order_PUC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(OrderListActivity.this);
                builder.setIcon(R.mipmap.ic_success);
                builder.setTitle("新的取货码");

                View view = LayoutInflater.from(OrderListActivity.this).inflate(R.layout.update_puc, null);  //通过LayoutInflater来加载一个xml的布局文件作为一个View对象
                builder.setView(view);      //设置我们自己定义的布局文件作为弹出框的Content

                final EditText puc = (EditText)view.findViewById(R.id.puc);

                builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        MyOrder myOrder = new MyOrder();
                        String upPuc = puc.getText().toString().trim();

                        order_PUC.setText(upPuc);

                        myOrder.setPickUpCode(upPuc);
                        myOrder.update(orderId, new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if(e==null){
                                    Toast.makeText(OrderListActivity.this,"取货码修改于"+myOrder.getUpdatedAt(), Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(OrderListActivity.this, "修改失败:"+e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {

                    }
                });
                builder.show();
            }
        });
    }


    /**
     * 修改收件人
     */
    public void updateCustomer() {
        order_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(OrderListActivity.this);
                builder.setIcon(R.mipmap.ic_success);
                builder.setTitle("新的收件人");

                View view = LayoutInflater.from(OrderListActivity.this).inflate(R.layout.update_customer, null);  //通过LayoutInflater来加载一个xml的布局文件作为一个View对象
                builder.setView(view);      //设置我们自己定义的布局文件作为弹出框的Content

                final EditText customer = (EditText)view.findViewById(R.id.customer);

                builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        MyOrder myOrder = new MyOrder();
                        String upCustomer = customer.getText().toString().trim();

                        order_customer.setText(upCustomer);

                        myOrder.setCustomer(upCustomer);
                        myOrder.update(orderId, new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if(e==null){
                                    Toast.makeText(OrderListActivity.this,"收件人修改于"+myOrder.getUpdatedAt(), Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(OrderListActivity.this, "修改失败:"+e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {

                    }
                });
                builder.show();
            }
        });
    }


    /**
     * 修改手机尾号
     */
    public void updatePhoneTail() {
        order_phone_tail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(OrderListActivity.this);
                builder.setIcon(R.mipmap.ic_success);
                builder.setTitle("新的手机尾号");

                View view = LayoutInflater.from(OrderListActivity.this).inflate(R.layout.update_phonetail, null);  //通过LayoutInflater来加载一个xml的布局文件作为一个View对象
                builder.setView(view);      //设置我们自己定义的布局文件作为弹出框的Content

                final EditText phone_tail = (EditText)view.findViewById(R.id.phone_tail);

                builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        MyOrder myOrder = new MyOrder();
                        String upPhoneTail = phone_tail.getText().toString().trim();

                        order_phone_tail.setText(upPhoneTail);

                        myOrder.setPhoneTail(upPhoneTail);
                        myOrder.update(orderId, new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if(e==null){
                                    Toast.makeText(OrderListActivity.this,"手机尾号修改于"+myOrder.getUpdatedAt(), Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(OrderListActivity.this, "修改失败:"+e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {

                    }
                });
                builder.show();
            }
        });
    }


    /**
     * 修改快递公司
     */
    public void updateCompany() {
        order_company.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(OrderListActivity.this);
                builder.setIcon(R.mipmap.ic_success);
                builder.setTitle("新的快递公司");

                View view = LayoutInflater.from(OrderListActivity.this).inflate(R.layout.update_company, null);  //通过LayoutInflater来加载一个xml的布局文件作为一个View对象
                builder.setView(view);      //设置我们自己定义的布局文件作为弹出框的Content

                final EditText company = (EditText)view.findViewById(R.id.company);

                builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        MyOrder myOrder = new MyOrder();
                        String upCompany = company.getText().toString().trim();

                        order_company.setText(upCompany);

                        myOrder.setCompany(upCompany);
                        myOrder.update(orderId, new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if(e==null){
                                    Toast.makeText(OrderListActivity.this,"快递公司修改于"+myOrder.getUpdatedAt(), Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(OrderListActivity.this, "修改失败:"+e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {

                    }
                });
                builder.show();
            }
        });
    }


    /**
     * 修改送达地址
     */
    public void updateAddress() {
        order_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(OrderListActivity.this);
                builder.setIcon(R.mipmap.ic_success);
                builder.setTitle("新的送达地址");

                View view = LayoutInflater.from(OrderListActivity.this).inflate(R.layout.update_address, null);  //通过LayoutInflater来加载一个xml的布局文件作为一个View对象
                builder.setView(view);      //设置我们自己定义的布局文件作为弹出框的Content

                final EditText address = (EditText)view.findViewById(R.id.address);

                builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        MyOrder myOrder = new MyOrder();
                        String upAddress = address.getText().toString().trim();

                        order_address.setText(upAddress);

                        myOrder.setAddress(upAddress);
                        myOrder.update(orderId, new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if(e==null){
                                    Toast.makeText(OrderListActivity.this,"送达地址修改于"+myOrder.getUpdatedAt(), Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(OrderListActivity.this, "修改失败:"+e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {

                    }
                });
                builder.show();
            }
        });
    }


    /**
     * 修改期望时间
     */
    public void updateComeTime() {
        order_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(OrderListActivity.this);
                builder.setIcon(R.mipmap.ic_success);
                builder.setTitle("新的期望时间");

                View view = LayoutInflater.from(OrderListActivity.this).inflate(R.layout.update_cometime, null);  //通过LayoutInflater来加载一个xml的布局文件作为一个View对象
                builder.setView(view);      //设置我们自己定义的布局文件作为弹出框的Content

                final EditText come_time = (EditText)view.findViewById(R.id.come_time);

                builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        MyOrder myOrder = new MyOrder();
                        String upComeTime = come_time.getText().toString().trim();

                        order_time.setText(upComeTime);

                        myOrder.setCome_time(upComeTime);
                        myOrder.update(orderId, new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if(e==null){
                                    Toast.makeText(OrderListActivity.this,"期望时间修改于"+myOrder.getUpdatedAt(), Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(OrderListActivity.this, "修改失败:"+e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {

                    }
                });
                builder.show();
            }
        });
    }


    /**
     * 修改备注
     */
    public void updateDescribe() {
        order_describe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(OrderListActivity.this);
                builder.setIcon(R.mipmap.ic_success);
                builder.setTitle("新的备注");

                View view = LayoutInflater.from(OrderListActivity.this).inflate(R.layout.update_describe, null);  //通过LayoutInflater来加载一个xml的布局文件作为一个View对象
                builder.setView(view);      //设置我们自己定义的布局文件作为弹出框的Content

                final EditText describe = (EditText)view.findViewById(R.id.describe);

                builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        MyOrder myOrder = new MyOrder();
                        String upDescribe = describe.getText().toString().trim();

                        order_describe.setText(upDescribe);

                        myOrder.setDescribe(upDescribe);
                        myOrder.update(orderId, new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if(e==null){
                                    Toast.makeText(OrderListActivity.this,"备注修改于"+myOrder.getUpdatedAt(), Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(OrderListActivity.this, "修改失败:"+e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {

                    }
                });
                builder.show();
            }
        });
    }


    /**
     * 赏金支付按钮功能
     */
    public void cash() {
        bt_order_cash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyOrder myOrder = new MyOrder();
                myOrder.setStatus("已支付");
                myOrder.update(orderId, new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if(e==null){
                            Toast.makeText(OrderListActivity.this, "操作成功", Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            Toast.makeText(OrderListActivity.this, "操作失败", Toast.LENGTH_SHORT).show();
                        }
                    }

                });
            }
        });
    }


    /**
     * 取消悬赏按钮功能
     */
    public void cancel() {
        bt_order_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyOrder myOrder = new MyOrder();
                myOrder.setObjectId(orderId);
                myOrder.delete(new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e == null) {
                            Toast.makeText(OrderListActivity.this, "取消悬赏成功,请刷新界面", Toast.LENGTH_SHORT).show();
                            OrderListActivity.this.finish();
                        } else {
                            Toast.makeText(OrderListActivity.this, "取消悬赏失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }


    /**
     * 状态栏点击
     */
    public void clickStatus()
    {
        order_list_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDialog();
            }
        });
    }


    /**
     * 创建"状态"弹窗
     */
    private void setDialog(){
        Dialog mDialog = new Dialog(OrderListActivity.this, R.style.BottomDialog);
        LinearLayout root = (LinearLayout) this.getLayoutInflater().inflate(
                R.layout.dialog_order_tracking, null);

        TextView tv_reward_paid = root.findViewById(R.id.tv_reward_paid);
        TextView tv_reward_finish = root.findViewById(R.id.tv_reward_finish);
        TextView tv_reward_running = root.findViewById(R.id.tv_reward_running);
        TextView tv_reward_onOffer = root.findViewById(R.id.tv_reward_onOffer);

        //初始化视图
        root.findViewById(R.id.tv_reward_paid);
        root.findViewById(R.id.tv_reward_finish);
        root.findViewById(R.id.tv_reward_running);
        root.findViewById(R.id.tv_reward_onOffer);
        mDialog.setContentView(root);

        Window dialogWindow = mDialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
//        dialogWindow.setWindowAnimations(R.style.dialogstyle); // 添加动画

        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.x = 0; // 新位置X坐标
        lp.y = 0; // 新位置Y坐标
        lp.width = (int) getResources().getDisplayMetrics().widthPixels; // 宽度
        root.measure(0, 0);
        lp.height = root.getMeasuredHeight();
        lp.alpha = 9f; // 透明度

        dialogWindow.setAttributes(lp);
        mDialog.show();

        BmobQuery<MyOrder> bmobQuery = new BmobQuery();
        bmobQuery.getObject(orderId, new QueryListener<MyOrder>() {
            @Override
            public void done(MyOrder myOrder, BmobException e) {
                if(e==null){
                    switch (myOrder.getStatus())
                    {
                        case "已支付":
                            tv_reward_paid.setBackgroundColor(getResources().getColor(R.color.purple));
                            tv_reward_finish.setBackgroundColor(getResources().getColor(R.color.purple));
                            tv_reward_running.setBackgroundColor(getResources().getColor(R.color.purple));
                            tv_reward_onOffer.setBackgroundColor(getResources().getColor(R.color.purple));
                            order_list_status.setText("悬赏已支付");
                            break;

                        case "未支付":
                            tv_reward_finish.setBackgroundColor(getResources().getColor(R.color.purple));
                            tv_reward_running.setBackgroundColor(getResources().getColor(R.color.purple));
                            tv_reward_onOffer.setBackgroundColor(getResources().getColor(R.color.purple));
                            order_list_status.setText("悬赏已完成");
                            break;

                        case "已接单":
                            tv_reward_running.setBackgroundColor(getResources().getColor(R.color.purple));
                            tv_reward_onOffer.setBackgroundColor(getResources().getColor(R.color.purple));
                            order_list_status.setText("悬赏进行中");
                            break;

                        case "未接单":
                            tv_reward_onOffer.setBackgroundColor(getResources().getColor(R.color.purple));
                            order_list_status.setText("悬赏待接单");
                            break;
                    }
                }else{
//                    toast("查询失败：" + e.getMessage());
                }
            }
        });

    }



    /**
     * 返回键
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


//    /**
//     * "图标根据悬赏状态变化而改变"功能实现
//     */
//    public void orderStatus() {
//
//        BmobQuery<MyOrder> bmobQuery = new BmobQuery<>();
//
//        bmobQuery.getObject(orderId, new QueryListener<MyOrder>() {
//            @SuppressLint("UseCompatLoadingForDrawables")
//            @Override
//            public void done(MyOrder myOrder, BmobException e) {
//                orderStatus = myOrder.getStatus();
//
//                switch(orderStatus)
//                {
//                    case "未接单":
//                        order_fab.setBackground(getResources().getDrawable(R.mipmap.ic_false));
//                        break;
//
//                    case "已接单":
//                        order_fab.setBackground(getResources().getDrawable(R.mipmap.ic_success));
//                        break;
//
//                    case "未支付":
//                        order_fab.setBackground(getResources().getDrawable(R.mipmap.ic_offer));
//                        break;
//
//                    case "已支付":
//                        order_fab.setBackground(getResources().getDrawable(R.mipmap.ic_finish));
//                        break;
//
//                    default:
//                        break;
//
//                }
//            }
//        });
//    }


}