package com.example.mdtest.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mdtest.Bean.MyUser;
import com.example.mdtest.MainActivity;
import com.example.mdtest.R;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class LogActivity extends AppCompatActivity {

//    private SharedPreferences pref;
//    private SharedPreferences.Editor editor;

    private Button btn_log;     //登录按钮
    //private CheckBox rememberPass;      //记住密码复选框

    private TextView tv_reg;        //注册选项
    private TextView tv_psw_find;       //密码找回选项
    private EditText et_u_p_e;       //用户名、手机号或邮箱
    private EditText et_psw;        //密码

    private CheckBox checkbox;      //服务条款勾选框


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_log);
        //pref = PreferenceManager.getDefaultSharedPreferences(this);

        Bmob.resetDomain("https://open3.bmob.cn/8/");
        Bmob.initialize(this, "36d91b12a94a333fbd7281eb1265abb3");      //数据库初始化

        login();        //登录

    }


    /**
     * 用户登录活动
     */
    private void login() {
        et_u_p_e = findViewById(R.id.log_u_p_e);
        et_psw = findViewById(R.id.log_password);
        btn_log = findViewById(R.id.bt_log);
        tv_reg = findViewById(R.id.tv_reg);
        tv_psw_find = findViewById(R.id.tv_find_psw);
        //rememberPass = findViewById(R.id.remember_pass);
        //boolean isRemember = pref.getBoolean("记住密码", false);

        // "注册界面"跳转
        tv_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(LogActivity.this, RegActivity.class);
                startActivity(intent1);
            }
        });

        // "密码找回界面"跳转
        tv_psw_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(LogActivity.this, PswFindActivity.class);
                startActivity(intent2);
            }
        });


        // 判断用户是否已登录
        BmobUser bmobUser = BmobUser.getCurrentUser(MyUser.class);
        if (bmobUser != null) {
            // 用户已登录，跳转至“主界面”
            Intent intent4 = new Intent(LogActivity.this, MainActivity.class);
            startActivity(intent4);
            LogActivity.this.finish();      //销毁登录界面
        } else {
//            if (isRemember) {
//                MyUser myUser = MyUser.getCurrentUser(MyUser.class);
//                String account = pref.getString("account", myUser.getMobilePhoneNumber());
//                String password = pref.getString("password", myUser.getPassword);
//            }
            // 用户未登录或未注册，跳转至"登录界面"
            btn_log.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MyUser user_login = new MyUser();       //创建表单对象

                    user_login.setUsername(et_u_p_e.getText().toString());
                    user_login.setPassword(et_psw.getText().toString());

                    CheckBox yes = findViewById(R.id.yes);

                    if (TextUtils.isEmpty(et_u_p_e.getText().toString() ) || TextUtils.isEmpty(et_psw.getText().toString())) {
                        Toast.makeText(LogActivity.this, "账号或密码不能为空", Toast.LENGTH_SHORT).show();
                    }else if (!yes.isChecked()) {
                        Toast.makeText(LogActivity.this, "请确认已勾选服务条款", Toast.LENGTH_SHORT).show();
                    }else{
                        user_login.login(new SaveListener<MyUser>() {
                                @Override
                                public void done(MyUser myuser, BmobException e) {
                                    if (e == null) {
                                        Toast.makeText(LogActivity.this, myuser.getUsername() + "登录成功", Toast.LENGTH_SHORT).show();

                                        Intent intent3 = new Intent(LogActivity.this, MainActivity.class);
                                        startActivity(intent3);

                                        LogActivity.this.finish();//销毁登录界面
                                    } else {
                                        Log.e("登录失败", "原因: ", e);
//                                        Toast.makeText(LogActivity.this, "登录失败：请检查登录信息或稍后重试", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    }
                });
            }
        }



}