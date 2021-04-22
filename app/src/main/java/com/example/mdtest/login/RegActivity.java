package com.example.mdtest.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.mdtest.R;
import com.example.mdtest.Bean.MyUser;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class RegActivity extends AppCompatActivity {


    /**
     * 注册按钮
     */
    private Button btn_reg;


    /**
     * 注册信息
     */
    private EditText reg_user;      //昵称
//    private EditText et_nickname;       //昵称
    private EditText et_phone;      //电话号码
    private EditText et_email;      //邮箱地址
    private EditText reg_psw;       //密码
    private EditText et_psw_again;      //确认密码


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_reg);
        Bmob.resetDomain("https://open3.bmob.cn/8/");
        Bmob.initialize(this, "36d91b12a94a333fbd7281eb1265abb3");      //数据库初始化


        /*
          标题栏
         */
        Toolbar toolbar = (Toolbar)findViewById(R.id.reg_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        register();     //注册

    }


    /**
     * "注册账号"功能实现
     */
    private void register() {

        btn_reg = findViewById(R.id.btn_reg);

        reg_user = findViewById(R.id.reg_user);
//        et_nickname = findViewById(R.id.et_nickname);
        et_phone = findViewById(R.id.et_phone);
        et_email = findViewById(R.id.et_email);
        reg_psw = findViewById(R.id.reg_psw);
        et_psw_again = findViewById(R.id.et_psw_again);

        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyUser myUser = new MyUser();     //创建user表对象存放数据

                myUser.setUsername(reg_user.getText().toString());
                myUser.setMobilePhoneNumber(et_phone.getText().toString());
                myUser.setEmail(et_email.getText().toString());
                myUser.setPassword(reg_psw.getText().toString());
                myUser.setTeam("否");
                myUser.setIdCardNum("");
                myUser.setRealName("");
                myUser.setSex("保密");
                myUser.setLevel("");
                myUser.setIdentity("尚未完成实名认证");


                    //注册实现
                    myUser.signUp(new SaveListener<MyUser>() {
                        @Override
                        public void done(MyUser myUser1, BmobException e) {
                            if (e == null) {
                                Toast.makeText(RegActivity.this, "注册成功", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(RegActivity.this, LogActivity.class);
                                startActivity(intent);

                                RegActivity.this.finish();      //销毁注册界面
                            } else {
//                            Log.e("注册失败", "原因: ", e);
                                Toast.makeText(RegActivity.this, "注册失败:"+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

//                    myUser.save(new SaveListener() {
//                        @Override
//                        public void done(Object o, BmobException e) {
//                            if (e == null) {
//                                Toast.makeText(RegActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
//
//                                Intent intent = new Intent(RegActivity.this, LogActivity.class);
//                                startActivity(intent);
//
//                                RegActivity.this.finish();      //销毁注册界面
//                            } else {
////                            Log.e("注册失败", "原因: ", e);
//                                Toast.makeText(RegActivity.this, "注册失败：请检查注册信息是否符合要求或稍后重试", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });

//                }else {
//                    Toast.makeText(RegActivity.this, "确认密码与设置的密码不同，请重新输入", Toast.LENGTH_SHORT).show();
//                }
            }
        });
    }


    /**
     * "返回键"功能实现
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}