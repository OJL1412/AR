package com.example.mdtest.Mine;

import android.os.Bundle;
import android.text.TextUtils;
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

public class PSWActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_p_s_w);
        Bmob.resetDomain("https://open3.bmob.cn/8/");
        Bmob.initialize(PSWActivity.this, "36d91b12a94a333fbd7281eb1265abb3");

        Toolbar toolbar = findViewById(R.id.up_psw_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        update_psw();

    }


    public void update_psw() {
        Button psw_update = findViewById(R.id.psw_update);

        psw_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String oldPsw = ((EditText)findViewById(R.id.up_psw_old)).getText().toString().trim();
                final String newPsw = ((EditText)findViewById(R.id.up_psw)).getText().toString().trim();
                final String newPswAgain = ((EditText)findViewById(R.id.up_psw_again)).getText().toString().trim();

                if (TextUtils.isEmpty(oldPsw) | TextUtils.isEmpty(newPsw) | TextUtils.isEmpty(newPswAgain)) {
                    Toast.makeText(PSWActivity.this,"有未填写的内容", Toast.LENGTH_SHORT).show();
                }else if (newPsw.equals(oldPsw)) {
                    Toast.makeText(PSWActivity.this,"输入的新密码不能与原始密码一致", Toast.LENGTH_SHORT).show();
                }else if (!newPswAgain.equals(newPsw)) {
                    Toast.makeText(PSWActivity.this,"再次输入的密码与设置的新密码不同，请检查", Toast.LENGTH_SHORT).show();
                }else {

                    BmobUser.updateCurrentUserPassword(oldPsw, newPsw, new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if(e==null){
                                Toast.makeText(PSWActivity.this,"密码修改成功，可以用新密码进行登录啦", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(PSWActivity.this,"失败："+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
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