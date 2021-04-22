package com.example.mdtest.Mine;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mdtest.R;
import com.example.mdtest.Bean.MyUser;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class PhoneActivity extends AppCompatActivity {

    private Button bt_change_phone;
    private EditText old_phone;
    private EditText new_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_phone);

        bt_change_phone = (Button)findViewById(R.id.bt_change_phone);


        Toolbar toolbar = (Toolbar)findViewById(R.id.info_account_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        change_phone();

    }


    public void change_phone() {
        bt_change_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                old_phone = (EditText)findViewById(R.id.old_phone);
                new_phone = (EditText)findViewById(R.id.new_phone);

                final String oldPhone = old_phone.getText().toString();
                final String newPhone = new_phone.getText().toString();

                if (TextUtils.isEmpty(oldPhone) | TextUtils.isEmpty(newPhone)) {
                    Toast.makeText(PhoneActivity.this,"有未填写的内容", Toast.LENGTH_SHORT).show();
                }else if (newPhone.equals(oldPhone)) {
                    Toast.makeText(PhoneActivity.this,"输入的新手机号与原手机号一致，无需换绑", Toast.LENGTH_SHORT).show();
                }else {
                    BmobUser bmobUser = BmobUser.getCurrentUser(MyUser.class);      //获取当前登录用户信息
                    String phone = bmobUser.getMobilePhoneNumber();
                    if (!phone.equals(oldPhone)) {
                        Toast.makeText(PhoneActivity.this, "输入的原手机号有问题", Toast.LENGTH_SHORT).show();
                    }else {
                        bmobUser.setMobilePhoneNumber(newPhone);
                        bmobUser.update(bmobUser.getObjectId(), new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if(e==null){
                                    Toast.makeText(PhoneActivity.this,"新手机号绑定成功", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(PhoneActivity.this,"绑定失败", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }
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