package com.example.mdtest.Team;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.mdtest.Bean.MyTeam;
import com.example.mdtest.Bean.MyUser;
import com.example.mdtest.R;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class TeamRegActivity extends AppCompatActivity {

    private EditText ed_team_name;
    private EditText ed_team_manager;
    private EditText ed_team_num;
    private EditText ed_team_email;
    private EditText ed_team_phone;
    private EditText ed_team_introduce;

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_reg);
        Bmob.resetDomain("https://open3.bmob.cn/8/");
        Bmob.initialize(this, "36d91b12a94a333fbd7281eb1265abb3");

        Toolbar toolbar = findViewById(R.id.team_reg_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        ed_team_name = (EditText) findViewById(R.id.ed_team_name);
        ed_team_manager = (EditText) findViewById(R.id.ed_team_manager);
        ed_team_num = (EditText) findViewById(R.id.ed_team_num);
        ed_team_email = (EditText) findViewById(R.id.ed_team_email);
        ed_team_phone = (EditText) findViewById(R.id.ed_team_phone);
        ed_team_introduce = (EditText) findViewById(R.id.ed_team_introduce);
        button = (Button) findViewById(R.id.team_reg);

        register();
    }

    public void register()
    {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyUser myUser = MyUser.getCurrentUser(MyUser.class);
                MyTeam myTeam = new MyTeam();

                myTeam.setUserId(myUser.getObjectId());
                myTeam.setName(ed_team_name.getText().toString());
                myTeam.setManager(ed_team_manager.getText().toString());
                myTeam.setNum(ed_team_num.getText().toString());
                myTeam.setEmail(ed_team_email.getText().toString());
                myTeam.setPhone(ed_team_phone.getText().toString());
                myTeam.setIntroduce(ed_team_introduce.getText().toString());
                myTeam.setStatus("未审核");
                myTeam.setGoal("0");
                myTeam.setLevel("青铜");

                myTeam.save(new SaveListener<String>() {
                    @Override
                    public void done(String objectId, BmobException e) {

                        if (e == null) {
                            Toast.makeText(TeamRegActivity.this,"提交申请成功，返回申请Id为："+ myTeam.getObjectId() + ",订单申请时间为："
                                    + myTeam.getCreatedAt(), Toast.LENGTH_SHORT).show();

                            TeamRegActivity.this.finish();

                        } else {
                            Toast.makeText(TeamRegActivity.this,"提交申请失败：请检查注册信息是否符合要求或稍后重试", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                
            }
        });
    }

    /**
     * 返回键
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