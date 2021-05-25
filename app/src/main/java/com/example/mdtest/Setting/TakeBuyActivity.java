package com.example.mdtest.Setting;

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

public class TakeBuyActivity extends AppCompatActivity {

    double total_offer = 0;
    public static final String BUY_ID = "buy_id";
    public static final String TYPE_ID = "type_id";


    private TextView buy_tv_tag;
    private EditText buy_ed_username;
    private EditText buy_ed_phoneTail;
    private EditText buy_ed_message;
    private TextView buy_pickAddress;
    private TextView buy_sendAddress;
    private EditText buy_tv_pickAddress;
    private EditText buy_tv_sendAddress;
    private EditText buy_tv_num;
    private TextView pick_more_standard;
    private EditText buy_ed_offer;
    private TextView buy_ed_time;
    private EditText buy_tv_time_endLine;
    private EditText buy_ed_describe;
    private EditText buy_ed_extraOffer;
    private TextView buy_tv_picture;
    private TextView buy_tv_totalOffer;
    private Button buy_btn_submit;

    public TakeBuyActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_buy);
        Bmob.resetDomain("https://open3.bmob.cn/8/");
        Bmob.initialize(this, "36d91b12a94a333fbd7281eb1265abb3");

        Toolbar toolbar = findViewById(R.id.take_buy_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
        {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Intent intent = getIntent();
        String buyId = intent.getStringExtra(BUY_ID);
        String typeId = intent.getStringExtra(TYPE_ID);

        buy_tv_tag = (TextView) findViewById(R.id.buy_tv_tag);
        buy_tv_tag.setText(buyId);

        buy_ed_username = (EditText) findViewById(R.id.buy_ed_username);
        buy_ed_phoneTail = (EditText) findViewById(R.id.buy_ed_phoneTail);

        buy_ed_message = (EditText) findViewById(R.id.buy_ed_message);

        buy_pickAddress = (TextView) findViewById(R.id.buy_pickAddress);
        buy_sendAddress = (TextView) findViewById(R.id.buy_sendAddress);
        buy_tv_pickAddress = (EditText) findViewById(R.id.buy_tv_pickAddress);
        buy_tv_sendAddress = (EditText) findViewById(R.id.buy_tv_sendAddress);

        buy_tv_num = (EditText) findViewById(R.id.buy_tv_num);
        buy_ed_offer = (EditText) findViewById(R.id.buy_ed_offer);

        buy_ed_time = (EditText) findViewById(R.id.buy_ed_time);
        buy_tv_time_endLine = (EditText) findViewById(R.id.buy_tv_time_endLine);
        buy_ed_describe = (EditText) findViewById(R.id.buy_ed_describe);
        buy_ed_extraOffer = (EditText) findViewById(R.id.buy_ed_extraOffer);
        buy_tv_picture = (TextView) findViewById(R.id.buy_tv_picture);

        buy_tv_totalOffer = (TextView) findViewById(R.id.buy_tv_totalOffer);
        buy_btn_submit = (Button) findViewById(R.id.buy_btn_submit);

        submit(typeId);
        //addressSelect();

    }


    public void submit(String typeId)
    {
        //total_offer = 3+Double.parseDouble(pick_ed_extraOffer.getText().toString());

        MyUser myUser = MyUser.getCurrentUser(MyUser.class);
        MyOrder myOrder = new MyOrder();

        myOrder.setUserId(myUser.getObjectId());
        myOrder.setTag(buy_tv_tag.getText().toString());
        myOrder.setCustomer(buy_ed_username.getText().toString());
        myOrder.setPhoneTail(buy_ed_phoneTail.getText().toString());
        myOrder.setMessage(buy_ed_message.getText().toString().trim());
        myOrder.setCompany(buy_tv_pickAddress.getText().toString());
        myOrder.setAddress(buy_tv_sendAddress.getText().toString());
        myOrder.setStandard(buy_tv_num.getText().toString());

        if (buy_ed_time.getText().toString().equals(""))
        {
            myOrder.setCome_time("1小时内");
        }else {
            myOrder.setCome_time(buy_ed_time.getText().toString());
        }

        if (buy_tv_time_endLine.getText().toString().equals(""))
        {
            myOrder.setEnd_time("1小时内有效");
        }else {
            myOrder.setEnd_time(buy_tv_time_endLine.getText().toString());
        }

        if (buy_ed_describe.getText().toString().equals(""))
        {
            myOrder.setDescribe("无");
        }else {
            myOrder.setDescribe(buy_ed_describe.getText().toString());
        }

        if (buy_ed_offer.getText().toString().equals(""))
        {
            myOrder.setOffer(String.valueOf(Double.parseDouble("2")+Double.parseDouble(buy_ed_extraOffer.getText().toString())));
        }else {
            myOrder.setOffer(String.valueOf(Double.parseDouble("2")+Double.parseDouble(buy_ed_extraOffer.getText().toString())+Double.parseDouble(buy_ed_offer.getText().toString())));
        }

        myOrder.setStatus("未接单");
        myOrder.setType(typeId);

        buy_btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myOrder.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        if (e == null) {
                            Toast.makeText(TakeBuyActivity.this,"悬赏发布成功，返回悬赏Id为："+ myOrder.getObjectId() + ",悬赏在服务端的创建时间为："
                                    + myOrder.getCreatedAt(), Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(TakeBuyActivity.this, OrderActivity.class);
                            startActivity(intent);

                            buy_ed_username.setText("");
                            buy_ed_phoneTail.setText("");
                            buy_ed_message.setText("");
                            buy_tv_pickAddress.setText("");
                            buy_tv_sendAddress.setText("");
                            buy_tv_num.setText("");
                            buy_ed_time.setText("");
                            buy_tv_time_endLine.setText("");
                            buy_ed_describe.setText("");
                            buy_ed_extraOffer.setText("");
                            buy_tv_totalOffer.setText("2");

                            TakeBuyActivity.this.finish();

                        } else {
                            Toast.makeText(TakeBuyActivity.this,"悬赏发布失败：请检查注册信息是否符合要求或稍后重试", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }


    public void addressSelect()
    {
        buy_pickAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TakeBuyActivity.this, AddressActivity.class);
                startActivity(intent);
            }
        });

        buy_sendAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(TakeBuyActivity.this, AddressActivity.class);
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

}