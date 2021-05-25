package com.example.mdtest.Setting;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.mdtest.Bean.MyOrder;
import com.example.mdtest.Bean.MyUser;
import com.example.mdtest.DrawMenu.OrderActivity;
import com.example.mdtest.R;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class TakePickActivity extends AppCompatActivity {

    double total_offer = 0;
    public static final String PICK_ID = "pick_id";
    public static final String TYPE_ID = "type_id";

    private TextView pick_tv_tag;
    private EditText pick_ed_pickUpCode;
    private EditText pick_ed_username;
    private EditText pick_ed_phoneTail;
    private EditText pick_ed_message;
    private TextView pick_pickAddress;
    private TextView pick_sendAddress;
    private EditText pick_tv_pickAddress;
    private EditText pick_tv_sendAddress;
    private TextView pick_tv_standard;
    private TextView pick_more_standard;
    private EditText pick_ed_time;
    private EditText pick_tv_time_endLine;
    private EditText pick_ed_describe;
    private EditText pick_ed_extraOffer;
    private TextView pick_tv_picture;
    private TextView pick_tv_totalOffer;
    private Button pick_btn_submit;

    public TakePickActivity() {
    }

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_pick);
        Bmob.resetDomain("https://open3.bmob.cn/8/");
        Bmob.initialize(this, "36d91b12a94a333fbd7281eb1265abb3");

        Toolbar toolbar = findViewById(R.id.take_pick_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
        {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Intent intent = getIntent();
        String pickId = intent.getStringExtra(PICK_ID);
        String typeId = intent.getStringExtra(TYPE_ID);

        pick_tv_tag = (TextView) findViewById(R.id.pick_tv_tag);
        pick_tv_tag.setText(pickId);

        pick_ed_pickUpCode = (EditText) findViewById(R.id.pick_ed_pickUpCode);
        pick_ed_username = (EditText) findViewById(R.id.pick_ed_username);
        pick_ed_phoneTail = (EditText) findViewById(R.id.pick_ed_phoneTail);

        pick_ed_message = (EditText) findViewById(R.id.pick_ed_message);

        pick_pickAddress = (TextView) findViewById(R.id.pick_pickAddress);
        pick_sendAddress = (TextView) findViewById(R.id.pick_sendAddress);
        pick_tv_pickAddress = (EditText) findViewById(R.id.pick_tv_pickAddress);
        pick_tv_sendAddress = (EditText) findViewById(R.id.pick_tv_sendAddress);

        pick_tv_standard = (EditText) findViewById(R.id.pick_tv_standard);
        pick_more_standard = (TextView) findViewById(R.id.pick_more_standard);
        pick_ed_time = (EditText) findViewById(R.id.pick_ed_time);
        pick_tv_time_endLine = (EditText) findViewById(R.id.pick_tv_time_endLine);
        pick_ed_describe = (EditText) findViewById(R.id.pick_ed_describe);
        pick_ed_extraOffer = (EditText) findViewById(R.id.pick_ed_extraOffer);
        pick_tv_picture = (TextView) findViewById(R.id.pick_tv_picture);

        pick_tv_totalOffer = (TextView) findViewById(R.id.pick_tv_totalOffer);
        pick_btn_submit = (Button) findViewById(R.id.pick_btn_submit);

        submit(typeId);
        //addressSelect();
        //moreStandard();
    }


    public void submit(String typeId)
    {
        //total_offer = 3+Double.parseDouble(pick_ed_extraOffer.getText().toString());

        //Intent intent = getIntent();


        MyUser myUser = MyUser.getCurrentUser(MyUser.class);
        MyOrder myOrder = new MyOrder();

        myOrder.setUserId(myUser.getObjectId());
        myOrder.setTag(pick_tv_tag.getText().toString());
        myOrder.setPickUpCode(pick_ed_pickUpCode.getText().toString());
        myOrder.setCustomer(pick_ed_username.getText().toString());
        myOrder.setPhoneTail(pick_ed_phoneTail.getText().toString());
        myOrder.setMessage(pick_ed_message.getText().toString().trim());
        myOrder.setCompany(pick_tv_pickAddress.getText().toString());
        myOrder.setAddress(pick_tv_sendAddress.getText().toString());
        myOrder.setStandard(pick_tv_standard.getText().toString());
        myOrder.setStatus("未接单");

        if (pick_ed_time.getText().toString().equals(""))
        {
            myOrder.setCome_time("1小时内");
        }else {
            myOrder.setCome_time(pick_ed_time.getText().toString());
        }
        if(pick_tv_time_endLine.getText().toString().equals(""))
        {
            myOrder.setEnd_time("1小时内有效");
        }else {
            myOrder.setEnd_time(pick_tv_time_endLine.getText().toString());
        }
        if (pick_ed_describe.getText().toString().equals(""))
        {
            myOrder.setDescribe("无");
        }else {
            myOrder.setDescribe(pick_ed_describe.getText().toString());
        }
        if (pick_ed_extraOffer.getText().toString().equals(""))
        {
            myOrder.setOffer(String.valueOf(2));
        }else {
            myOrder.setOffer(String.valueOf(Double.parseDouble("2")+Double.parseDouble(pick_ed_extraOffer.getText().toString())));
        }

        myOrder.setType(typeId);

        pick_btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myOrder.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        if (e == null) {
                            Toast.makeText(TakePickActivity.this,"悬赏发布成功，返回悬赏Id为："+ myOrder.getObjectId() + ",悬赏在服务端的创建时间为："
                                    + myOrder.getCreatedAt(), Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(TakePickActivity.this, OrderActivity.class);
                            startActivity(intent);

                            pick_ed_pickUpCode.setText("");
                            pick_ed_username.setText("");
                            pick_ed_phoneTail.setText("");
                            pick_ed_message.setText("");
                            pick_tv_pickAddress.setText("");
                            pick_tv_sendAddress.setText("");
                            pick_tv_standard.setText("");
                            pick_ed_time.setText("");
                            pick_tv_time_endLine.setText("");
                            pick_ed_describe.setText("");
                            pick_ed_extraOffer.setText("");
                            pick_tv_totalOffer.setText("2");

                            TakePickActivity.this.finish();

                        } else {
                            Toast.makeText(TakePickActivity.this,"悬赏发布失败：请检查注册信息是否符合要求或稍后重试", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }


    public void addressSelect()
    {
        pick_pickAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TakePickActivity.this, AddressActivity.class);
                startActivity(intent);
            }
        });

        pick_sendAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(TakePickActivity.this, AddressActivity.class);
                startActivity(intent1);
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


//    public void moreStandard()
//    {
//        pick_more_standard.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(TakePickActivity.this);
//                builder.setIcon(R.mipmap.ic_more_standard);
//                builder.setTitle("更多标准");
//
//                View view = LayoutInflater.from(TakePickActivity.this).inflate(R.layout.pick_more_standard, null);  //通过LayoutInflater来加载一个xml的布局文件作为一个View对象
//                builder.setView(view);      //设置我们自己定义的布局文件作为弹出框的Content
//
//                builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
//                {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which)
//                    {
//                        String reach_time = pick_ed_time.getText().toString().trim();
//                        String end_time = pick_tv_time_endLine.getText().toString().trim();
//                        String describe = pick_ed_describe.getText().toString().trim();
//                        String extraOffer = pick_ed_extraOffer.getText().toString().trim();
//                        String picture = pick_tv_picture.getText().toString().trim();
//
//                        pick_ed_time.setText(reach_time);
//                        pick_tv_time_endLine.setText(end_time);
//                        pick_ed_describe.setText(describe);
//                        pick_ed_extraOffer.setText(extraOffer);
//                        pick_tv_picture.setText(picture);
//
//                        pick_tv_totalOffer.setText(String.valueOf(3+Double.parseDouble(pick_ed_extraOffer.getText().toString().trim())));
//
//                    }
//                });
//                builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
//                {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which)
//                    {
//                        pick_ed_time.setText("");
//                        pick_tv_time_endLine.setText("");
//                        pick_ed_describe.setText("");
//                        pick_ed_extraOffer.setText("");
//                        pick_tv_totalOffer.setText("3");
//                    }
//                });
//                builder.show();
//            }
//        });
//    }


}