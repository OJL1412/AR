package com.example.mdtest.CardInit;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.mdtest.Bean.MyOrder;
import com.example.mdtest.Bean.MyUser;
import com.example.mdtest.DrawMenu.TakeActivity;
import com.example.mdtest.R;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.UpdateListener;

public class DeliveryActivity extends AppCompatActivity {

    public static final String DV_IMAGE_ID = "dv_image_id";
    public static final String DV_ID = "dv_id";

    private String dvId;
    private String dvTag;

    //private CircleImageView deli_icon;
    private TextView deli_status;
    private TextView deli_tag;
    private TextView deli_offer;
    private TextView deli_standard;
    private TextView deli_message;
    private TextView deli_end_time;
    private TextView deli_time;
    private TextView deli_company;
    private TextView deli_address;
    private TextView deli_phone_tail;
    private TextView deli_PUC;
    private TextView deli_customer;
    private TextView deli_objectId;
    private TextView deli_time2;
    private TextView deli_describe;
    private Button bt_deli;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cardinit_delivery);
        Bmob.resetDomain("https://open3.bmob.cn/8/");
        Bmob.initialize(this, "36d91b12a94a333fbd7281eb1265abb3");      //??????????????????

        Intent intent = getIntent();
        dvTag = intent.getStringExtra(DV_IMAGE_ID);
        dvId = intent.getStringExtra(DV_ID);
//        int dvImageId = intent.getIntExtra(DV_IMAGE_ID, 0);

        Toolbar toolbar = (Toolbar) findViewById(R.id.delivery_toolBar);
        toolbar.setTitle("??????"+dvId);
        toolbar.setTitleTextColor(R.color.white);
//        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
//        ImageView deliveryImageView = (ImageView) findViewById(R.id.delivery_image_view);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
        {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


//        deli_icon = (CircleImageView)findViewById(R.id.deli_icon);
        deli_status = (TextView)findViewById(R.id.deli_status);
        deli_tag = (TextView)findViewById(R.id.deli_tag);
        deli_offer = (TextView)findViewById(R.id.deli_offer);
        deli_standard = (TextView)findViewById(R.id.deli_standard);
        deli_message = (TextView)findViewById(R.id.deli_message);
        deli_end_time = (TextView)findViewById(R.id.deli_end_time);
        deli_time = (TextView)findViewById(R.id.deli_time);
        deli_company = (TextView)findViewById(R.id.deli_company);
        deli_address = (TextView)findViewById(R.id.deli_address);
        deli_phone_tail = (TextView)findViewById(R.id.deli_phone_tail);
        deli_PUC = (TextView)findViewById(R.id.deli_PUC);
        deli_customer = (TextView)findViewById(R.id.deli_customer);
        deli_objectId = (TextView)findViewById(R.id.deli_objectId);
        deli_time2 = (TextView)findViewById(R.id.deli_time2);
        deli_describe = (TextView)findViewById(R.id.deli_describe);
        bt_deli = (Button) findViewById(R.id.bt_deli_yes);


//        collapsingToolbar.setTitle("??????"+dvId);
//        Glide.with(this).load(R.mipmap.ic_image).into(deliveryImageView);
//        switch (dvTag) {
//            case "??????":
//                Glide.with(this).load(R.mipmap.ic_fruit).into(deliveryImageView);
//                break;
//            case "????????????":
//                Glide.with(this).load(R.mipmap.ic_electric).into(deliveryImageView);
//                break;
//            case "??????":
//                Glide.with(this).load(R.mipmap.ic_book).into(deliveryImageView);
//                break;
//            case "??????":
//                Glide.with(this).load(R.mipmap.ic_clothes).into(deliveryImageView);
//                break;
//            default:
//                Glide.with(this).load(R.mipmap.ic_box).into(deliveryImageView);
//                break;
//        }

        initDelivery();     //?????????????????????

        clickStatus();
        take();     //????????????

    }


    /**
     * ?????????????????????
     */
    public void initDelivery() {
        BmobQuery<MyOrder> bmobQuery = new BmobQuery<>();

        bmobQuery.getObject(dvId, new QueryListener<MyOrder>() {
            @Override
            public void done(MyOrder order,BmobException e) {
                if(e==null){
//                    switch (dvTag) {
//                        case "??????":
//                            deli_icon.setImageResource(R.mipmap.ic_fruit);
//                            break;
//                        case "????????????":
//                            deli_icon.setImageResource(R.mipmap.ic_electric);
//                            break;
//                        case "??????":
//                            deli_icon.setImageResource(R.mipmap.ic_book);
//                            break;
//                        case "??????":
//                            deli_icon.setImageResource(R.mipmap.ic_clothes);
//                            break;
//                        default:
//                            deli_icon.setImageResource(R.mipmap.ic_box);
//                            break;
//                    }
                    deli_tag.setText(String.valueOf(order.getTag()));
                    deli_standard.setText(order.getStandard());
                    deli_message.setText(order.getMessage());
                    deli_offer.setText(String.valueOf(order.getOffer()));
                    deli_time.setText(String.valueOf(order.getCome_time()));
                    deli_company.setText(String.valueOf(order.getCompany()));
                    deli_address.setText(String.valueOf(order.getAddress()));
                    deli_phone_tail.setText(String.valueOf(order.getPhoneTail()));
//                    deli_PUC.setText(String.valueOf(order.getPickUpCode()));
                    deli_customer.setText(String.valueOf(order.getCustomer()));
                    deli_objectId.setText(String.valueOf(order.getObjectId()));
                    deli_time2.setText(String.valueOf(order.getCreatedAt()));
                    deli_describe.setText(String.valueOf(order.getDescribe()));
                    deli_end_time.setText(order.getEnd_time());

                    switch (order.getStatus())
                    {
                        case "?????????":
                            deli_status.setText("???????????????");
                            break;

                        case "?????????":
                            deli_status.setText("???????????????");
                            break;

                        case "?????????":
                            deli_status.setText("???????????????");
                            break;

                        case "?????????":
                            deli_status.setText("???????????????");
                            break;
                    }

//                    Toast.makeText(DeliveryActivity.this, "??????????????????", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(DeliveryActivity.this, "??????????????????", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    /**
     * ??????????????????
     */
    public void take() {
        bt_deli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyOrder myOrder = new MyOrder();
                MyUser myUser = MyUser.getCurrentUser(MyUser.class);
                if (myUser.getIdentity().equals("????????????????????????")) {
                    Toast.makeText(DeliveryActivity.this, "?????????????????????????????????", Toast.LENGTH_SHORT).show();
                } else if (myUser.getIdentity().equals("???????????????")) {
                    myOrder.setStatus(null);        //????????????????????????,??????setXXXXX(null)?????????????????????
                    myOrder.setStatus("?????????");
                    myOrder.setArId(myUser.getObjectId());
                    myOrder.update(dvId, new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e == null) {
//                            deli_status.setText("?????????");
                                Toast.makeText(DeliveryActivity.this, "????????????", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(DeliveryActivity.this, TakeActivity.class);
                                startActivity(intent);
                                DeliveryActivity.this.finish();
                            } else {
                                Toast.makeText(DeliveryActivity.this, "????????????" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }

                    });
                }
            }
        });
    }


    /**
     * ???????????????
     */
    public void clickStatus()
    {
        deli_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDialog();
            }
        });
    }



    /**
     * ??????"??????"??????
     */
    private void setDialog(){
        Dialog mDialog = new Dialog(DeliveryActivity.this, R.style.BottomDialog);
        LinearLayout root = (LinearLayout) this.getLayoutInflater().inflate(
                R.layout.dialog_order_tracking, null);

        TextView tv_reward_paid = root.findViewById(R.id.tv_reward_paid);
        TextView tv_reward_finish = root.findViewById(R.id.tv_reward_finish);
        TextView tv_reward_running = root.findViewById(R.id.tv_reward_running);
        TextView tv_reward_onOffer = root.findViewById(R.id.tv_reward_onOffer);

        //???????????????
        root.findViewById(R.id.tv_reward_paid);
        root.findViewById(R.id.tv_reward_finish);
        root.findViewById(R.id.tv_reward_running);
        root.findViewById(R.id.tv_reward_onOffer);
        mDialog.setContentView(root);

        Window dialogWindow = mDialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
//        dialogWindow.setWindowAnimations(R.style.dialogstyle); // ????????????

        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // ?????????????????????????????????
        lp.x = 0; // ?????????X??????
        lp.y = 0; // ?????????Y??????
        lp.width = (int) getResources().getDisplayMetrics().widthPixels; // ??????
        root.measure(0, 0);
        lp.height = root.getMeasuredHeight();
        lp.alpha = 9f; // ?????????

        dialogWindow.setAttributes(lp);
        mDialog.show();

        BmobQuery<MyOrder> bmobQuery = new BmobQuery();
        bmobQuery.getObject(dvId, new QueryListener<MyOrder>() {
            @Override
            public void done(MyOrder myOrder, BmobException e) {
                if(e==null){
                    switch (myOrder.getStatus())
                    {
                        case "?????????":
                            tv_reward_paid.setBackgroundColor(getResources().getColor(R.color.purple));
                            tv_reward_finish.setBackgroundColor(getResources().getColor(R.color.purple));
                            tv_reward_running.setBackgroundColor(getResources().getColor(R.color.purple));
                            tv_reward_onOffer.setBackgroundColor(getResources().getColor(R.color.purple));
                            deli_status.setText("???????????????");
                            break;

                        case "?????????":
                            tv_reward_finish.setBackgroundColor(getResources().getColor(R.color.purple));
                            tv_reward_running.setBackgroundColor(getResources().getColor(R.color.purple));
                            tv_reward_onOffer.setBackgroundColor(getResources().getColor(R.color.purple));
                            deli_status.setText("???????????????");
                            break;

                        case "?????????":
                            tv_reward_running.setBackgroundColor(getResources().getColor(R.color.purple));
                            tv_reward_onOffer.setBackgroundColor(getResources().getColor(R.color.purple));
                            deli_status.setText("???????????????");
                            break;

                        case "?????????":
                            tv_reward_onOffer.setBackgroundColor(getResources().getColor(R.color.purple));
                            deli_status.setText("???????????????");
                            break;
                    }
                }else{
//                    toast("???????????????" + e.getMessage());
                }
            }
        });

    }



    /**
     * "?????????"????????????
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

//    private String generateFruitContent(String fruitName) {
//        StringBuilder fruitContent = new StringBuilder();
//        for (int i = 0;i < 500; i++)
//        {
//            fruitContent.append(fruitName);
//        }
//
//        return fruitContent.toString();
//    }

}