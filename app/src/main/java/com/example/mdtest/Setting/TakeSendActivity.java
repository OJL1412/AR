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

public class TakeSendActivity extends AppCompatActivity {

    double total_offer = 0;
    public static final String SEND_ID = "send_id";
    public static final String TYPE_ID = "type_id";


    private TextView send_tv_tag;
    private EditText send_ed_username;
    private EditText send_ed_achieveName;
    private EditText send_ed_phoneTail;
    private EditText send_ed_message;
    private TextView send_pickAddress;
    private TextView send_sendAddress;
    private EditText send_tv_pickAddress;
    private EditText send_tv_sendAddress;
    private TextView send_tv_standard;
    private EditText send_ed_time;
    private EditText send_tv_time_endLine;
    private EditText send_ed_describe;
    private EditText send_ed_extraOffer;
    private TextView send_tv_picture;
    private TextView send_tv_totalOffer;
    private Button send_btn_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_send);
        Bmob.resetDomain("https://open3.bmob.cn/8/");
        Bmob.initialize(this, "36d91b12a94a333fbd7281eb1265abb3");

        Toolbar toolbar = findViewById(R.id.take_send_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
        {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Intent intent = getIntent();
        String sendId = intent.getStringExtra(SEND_ID);
        String typeId = intent.getStringExtra(TYPE_ID);

        send_tv_tag = (TextView) findViewById(R.id.send_tv_tag);
        send_tv_tag.setText(sendId);

        send_ed_username = (EditText) findViewById(R.id.send_ed_username);
        send_ed_achieveName = (EditText) findViewById(R.id.send_ed_achieveName);
        send_ed_phoneTail = (EditText) findViewById(R.id.send_ed_phoneTail);

        send_ed_message = (EditText) findViewById(R.id.send_ed_message);

        send_pickAddress = (TextView) findViewById(R.id.send_pickAddress);
        send_sendAddress = (TextView) findViewById(R.id.send_sendAddress);
        send_tv_pickAddress = (EditText) findViewById(R.id.send_tv_pickAddress);
        send_tv_sendAddress = (EditText) findViewById(R.id.send_tv_sendAddress);

        send_tv_standard = (TextView) findViewById(R.id.send_tv_standard);
        send_ed_time = (EditText) findViewById(R.id.send_ed_time);
        send_tv_time_endLine = (EditText) findViewById(R.id.send_tv_time_endLine);
        send_ed_describe = (EditText) findViewById(R.id.send_ed_describe);
        send_ed_extraOffer = (EditText) findViewById(R.id.send_ed_extraOffer);
        send_tv_picture = (TextView) findViewById(R.id.send_tv_picture);

        send_tv_totalOffer = (TextView) findViewById(R.id.send_tv_totalOffer);
        send_btn_submit = (Button) findViewById(R.id.send_btn_submit);

        submit(typeId);
        //addressSelect();
    }

    public void submit(String typeId)
    {
        //total_offer = 3+Double.parseDouble(pick_ed_extraOffer.getText().toString());

        MyUser myUser = MyUser.getCurrentUser(MyUser.class);
        MyOrder myOrder = new MyOrder();

        myOrder.setUserId(myUser.getObjectId());
        myOrder.setTag(send_tv_tag.getText().toString());
        myOrder.setAchiever(send_ed_achieveName.getText().toString());
        myOrder.setCustomer(send_ed_username.getText().toString());
        myOrder.setPhoneTail(send_ed_phoneTail.getText().toString());
        myOrder.setMessage(send_ed_message.getText().toString().trim());
        myOrder.setCompany(send_tv_pickAddress.getText().toString());
        myOrder.setAddress(send_tv_sendAddress.getText().toString());
        myOrder.setStandard(send_tv_standard.getText().toString());

        if (send_ed_time.getText().toString().equals(""))
        {
            myOrder.setCome_time("1小时内");
        }else {
            myOrder.setCome_time(send_ed_time.getText().toString());
        }

        if (send_tv_time_endLine.getText().toString().equals(""))
        {
            myOrder.setEnd_time("1小时内有效");
        }else {
            myOrder.setEnd_time(send_tv_time_endLine.getText().toString());
        }

        if (send_ed_describe.getText().toString().equals(""))
        {
            myOrder.setDescribe("无");
        }else {
            myOrder.setDescribe(send_ed_describe.getText().toString());
        }

        myOrder.setStatus("未接单");
        myOrder.setOffer(String.valueOf(Double.parseDouble("3")+Double.parseDouble(send_ed_extraOffer.getText().toString())));
        myOrder.setType(typeId);

        send_btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myOrder.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        if (e == null) {
                            Toast.makeText(TakeSendActivity.this,"悬赏发布成功，返回悬赏Id为："+ myOrder.getObjectId() + ",悬赏在服务端的创建时间为："
                                    + myOrder.getCreatedAt(), Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(TakeSendActivity.this, OrderActivity.class);
                            startActivity(intent);

                            send_ed_achieveName.setText("");
                            send_ed_username.setText("");
                            send_ed_phoneTail.setText("");
                            send_ed_message.setText("");
                            send_tv_pickAddress.setText("");
                            send_tv_sendAddress.setText("");
                            send_tv_standard.setText("");
                            send_ed_time.setText("");
                            send_tv_time_endLine.setText("");
                            send_ed_describe.setText("");
                            send_ed_extraOffer.setText("");
                            send_tv_totalOffer.setText("3");

                            TakeSendActivity.this.finish();

                        } else {
                            Toast.makeText(TakeSendActivity.this,"悬赏发布失败：请检查注册信息是否符合要求或稍后重试", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }


    public void addressSelect()
    {
        send_pickAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TakeSendActivity.this, AddressActivity.class);
                startActivity(intent);
            }
        });

        send_sendAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(TakeSendActivity.this, AddressActivity.class);
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