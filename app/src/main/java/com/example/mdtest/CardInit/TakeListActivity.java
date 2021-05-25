package com.example.mdtest.CardInit;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.mdtest.Bean.MyOrder;
import com.example.mdtest.R;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.UpdateListener;

public class TakeListActivity extends AppCompatActivity {

    public static final String TAKE_ID = "take_id";
    public static final String TAKE_IMAGE_ID = "take_image_id";

    private String takeId;
    private String takeTag;

    //private CircleImageView take_image;
    private TextView take_tag;
    private TextView take_offer;
    private TextView take_time;
    private TextView take_end_time;
    private TextView take_standard;
    private TextView take_message;
    private TextView take_company;
    private TextView take_address;
    private TextView take_phone_tail;
    private TextView take_PUC;
    private TextView take_customer;
    private TextView take_objectId;
    private TextView take_offer1;
    private TextView take_time2;
    private TextView take_describe;
    private Button bt_take_cancel;
    private Button bt_take_finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cardinit_take);
        Bmob.resetDomain("https://open3.bmob.cn/8/");
        Bmob.initialize(this, "36d91b12a94a333fbd7281eb1265abb3");


        Intent intent = getIntent();
        takeId = intent.getStringExtra(TAKE_ID);
        //takeTag = intent.getStringExtra(TAKE_IMAGE_ID);

        Toolbar toolbar = (Toolbar)findViewById(R.id.take_list_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
        {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

//        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar3);
//        ImageView orderImageView = (ImageView) findViewById(R.id.take_image_view);
//        collapsingToolbar.setTitle("接单详情");
//        Glide.with(this).load(takeImageId).into(orderImageView);

        //take_image = (CircleImageView)findViewById(R.id.take_list_icon);
        take_tag = (TextView)findViewById(R.id.take_list_tag);
        take_offer = (TextView)findViewById(R.id.take_list_offer);
        take_offer1 = (TextView)findViewById(R.id.take_list_offer1);
        take_time = (TextView)findViewById(R.id.take_list_time);
        take_end_time = (TextView)findViewById(R.id.take_list_end_time);
        take_standard = (TextView)findViewById(R.id.take_list_standard);
        take_message = (TextView)findViewById(R.id.take_list_message);
        take_company = (TextView)findViewById(R.id.take_list_company);
        take_address = (TextView)findViewById(R.id.take_list_address);
        take_phone_tail = (TextView)findViewById(R.id.take_list_phoneTail);
        take_PUC = (TextView)findViewById(R.id.take_list_PUC);
        take_customer = (TextView)findViewById(R.id.take_list_customer);
        take_objectId = (TextView)findViewById(R.id.take_list_objectId);
        take_time2 = (TextView)findViewById(R.id.take_list_time2);
        take_describe = (TextView)findViewById(R.id.take_list_describe);

        bt_take_cancel = (Button)findViewById(R.id.take_list_cancel);
        bt_take_finish = (Button)findViewById(R.id.take_list_finish);


//        switch (takeTag) {
//            case "水果":
//                Glide.with(this).load(R.mipmap.ic_fruit).into(take_image);
//                break;
//            case "电子器件":
//                Glide.with(this).load(R.mipmap.ic_electric).into(take_image);
//                break;
//            case "书本":
//                Glide.with(this).load(R.mipmap.ic_book).into(take_image);
//                break;
//            case "衣服":
//                Glide.with(this).load(R.mipmap.ic_clothes).into(take_image);
//                break;
//            default:
//                Glide.with(this).load(R.mipmap.ic_box).into(take_image);
//                break;
//        }


        initTakeList();

        cancel();

        takeFinish();

    }


    /**
     * 初始化接单卡片
     */
    public void initTakeList() {
        BmobQuery<MyOrder> bmobQuery = new BmobQuery<>();

        bmobQuery.getObject(takeId, new QueryListener<MyOrder>() {
            @Override
            public void done(MyOrder myOrder, BmobException e) {
                if(e==null){
//                    switch (takeTag) {
//                        case "水果":
//                            take_image.setImageResource(R.mipmap.ic_fruit);
//                            break;
//                        case "电子器件":
//                            take_image.setImageResource(R.mipmap.ic_electric);
//                            break;
//                        case "书本":
//                            take_image.setImageResource(R.mipmap.ic_book);
//                            break;
//                        case "衣服":
//                            take_image.setImageResource(R.mipmap.ic_clothes);
//                            break;
//                        default:
//                            take_image.setImageResource(R.mipmap.ic_box);
//                            break;
//                    }
                    take_PUC.setText(myOrder.getPickUpCode());
                    take_customer.setText(myOrder.getCustomer());
                    take_phone_tail.setText(myOrder.getPhoneTail());
                    take_company.setText(myOrder.getCompany());
                    take_address.setText(myOrder.getAddress());
                    take_objectId.setText(myOrder.getObjectId());
                    take_time.setText(myOrder.getCreatedAt());
                    take_time2.setText(myOrder.getCome_time());
                    take_end_time.setText(myOrder.getEnd_time());
                    take_standard.setText(myOrder.getStandard());
                    take_message.setText(myOrder.getMessage());
                    take_offer.setText(String.valueOf(myOrder.getOffer()));
                    take_offer1.setText("0");
                    take_tag.setText(myOrder.getTag());
                    take_describe.setText(myOrder.getDescribe());

                    Toast.makeText(TakeListActivity.this, "数据加载成功", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(TakeListActivity.this, "数据加载失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    /**
     * 取消接单按钮功能
     */
    public void cancel() {
        bt_take_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyOrder myOrder = new MyOrder();
                myOrder.setStatus(null);
                myOrder.setStatus("未接单");
                myOrder.setArId("");
                myOrder.update(takeId, new UpdateListener() {

                    @Override
                    public void done(BmobException e) {
                        if(e==null){
                            Toast.makeText(TakeListActivity.this, "取消接单成功", Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            Toast.makeText(TakeListActivity.this, "取消接单失败", Toast.LENGTH_SHORT).show();
                        }
                    }

                });
            }
        });
    }


    /**
     * 接单完成按钮功能
     */
    public void takeFinish() {
        bt_take_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyOrder myOrder = new MyOrder();
                myOrder.setStatus(null);
                myOrder.setStatus("未支付");
                myOrder.update(takeId, new UpdateListener() {

                    @Override
                    public void done(BmobException e) {
                        if(e==null){
                            Toast.makeText(TakeListActivity.this, "接单已完成", Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            Toast.makeText(TakeListActivity.this, "操作失败", Toast.LENGTH_SHORT).show();
                        }
                    }

                });
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
//            Toast.makeText(TakeListActivity.this, "你点击了返回", Toast.LENGTH_SHORT).show();
//            finish();
//            Intent intent = new Intent(TakeListActivity.this, TakeActivity.class);
            TakeListActivity.this.finish();
//            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//    }


}