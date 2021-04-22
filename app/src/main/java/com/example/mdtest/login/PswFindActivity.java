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

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class PswFindActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_psw_find);
        Bmob.resetDomain("https://open3.bmob.cn/8/");
        Bmob.initialize(this, "36d91b12a94a333fbd7281eb1265abb3");      //数据库初始化


        //标题栏操作
        Toolbar toolbar = (Toolbar)findViewById(R.id.psw_find_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        update_psw();       //更改密码

    }


    /**
     * "更改密码"功能实现
     */
    public void update_psw() {
        Button psw_update_find = findViewById(R.id.up_psw_find);

        psw_update_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText up_psw_phone = findViewById(R.id.up_psw_phone);
                EditText up_psw_old = findViewById(R.id.up_psw_old_find);
                EditText up_psw_new = findViewById(R.id.up_psw_new_find);
                EditText up_psw_new_again = findViewById(R.id.up_psw_new_find_again);

                String phone = up_psw_phone.getText().toString();
                String oldPsw = up_psw_old.getText().toString();
                String newPsw = up_psw_new.getText().toString();
                String newPswAgain = up_psw_new_again.getText().toString();

                if (phone.isEmpty() | oldPsw.isEmpty() | newPsw.isEmpty() | newPswAgain.isEmpty()) {
                    Toast.makeText(PswFindActivity.this,"有未填写的内容", Toast.LENGTH_SHORT).show();
                }else if (oldPsw.equals(newPsw)) {
                    Toast.makeText(PswFindActivity.this,"输入的新密码不能与原始密码一致", Toast.LENGTH_SHORT).show();
                }else if (!newPswAgain.equals(newPsw)) {
                    Toast.makeText(PswFindActivity.this,"再次输入的密码与设置的新密码不同，请检查", Toast.LENGTH_SHORT).show();
                }else {
//                    BmobQuery<MyUser> bmobQuery = new BmobQuery<MyUser>();
//                    bmobQuery.addWhereEqualTo("mobilePhoneNumber", phone);
//                    bmobQuery.findObjects(new FindListener<MyUser>() {
//                        @Override
//                        public void done(List<MyUser> list, BmobException e) {
//                            list.get(0).
//                        }
//                    });

                    BmobUser.updateCurrentUserPassword(oldPsw, newPsw, new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if(e==null){
                                Toast.makeText(PswFindActivity.this,"密码修改成功，可以用新密码进行登录啦", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(PswFindActivity.this, LogActivity.class);
                                startActivity(intent);
                                PswFindActivity.this.finish();

                            }else{
                                Toast.makeText(PswFindActivity.this,"失败："+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }


    /**
     * "返回"功能实现
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