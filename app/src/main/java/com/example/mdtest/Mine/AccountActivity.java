package com.example.mdtest.Mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.mdtest.R;
import com.example.mdtest.Bean.MyUser;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;

public class AccountActivity extends AppCompatActivity {

    private TextView info_account_id;
    private TextView info_phone;
    private TextView info_manage;
    private TextView info_set;

    private TextView object_id;
    private TextView phone_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_account);
        Bmob.resetDomain("https://open3.bmob.cn/8/");
        Bmob.initialize(this, "36d91b12a94a333fbd7281eb1265abb3");

        info_account_id = (TextView)findViewById(R.id.info_account_ID);
        info_phone = (TextView)findViewById(R.id.info_phone);
        info_manage = (TextView)findViewById(R.id.info_manage);
        info_set = (TextView)findViewById(R.id.info_set);

        object_id = (TextView)findViewById(R.id.object_id);
        phone_id = (TextView)findViewById(R.id.phone_id);


        Toolbar toolbar = (Toolbar)findViewById(R.id.info_account_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Account_Id();
        Phone_Id();
        PSW_Manage();

    }


    public void Account_Id() {
        BmobUser bmobUser = BmobUser.getCurrentUser(MyUser.class);      //获取当前登录用户信息
        object_id.setText(bmobUser.getObjectId());

    }


    public void Phone_Id() {
        BmobUser bmobUser = BmobUser.getCurrentUser(MyUser.class);
        phone_id.setText(bmobUser.getMobilePhoneNumber());

        info_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountActivity.this, PhoneActivity.class);
                startActivity(intent);
            }
        });
    }


    public void PSW_Manage() {
        info_manage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountActivity.this, PSWActivity.class);
                startActivity(intent);
            }
        });
    }


    /**
     * 返回键
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}