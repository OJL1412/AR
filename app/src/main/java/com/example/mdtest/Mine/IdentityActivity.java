package com.example.mdtest.Mine;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.mdtest.R;
import com.example.mdtest.Bean.MyUser;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.UpdateListener;

public class IdentityActivity extends AppCompatActivity {

    private TextView df_realName;
    private TextView df_license;
    private TextView df_phone;
//    private TextView df_team;
//    private TextView df_photo;
//    private TextView df_level;

    private TextView tv_realName;
    private TextView tv_license;
    private TextView tv_phone;
    private CheckBox cb_team;
    private TextView tv_level;

    private TextView df_verify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_identity);

        Bmob.resetDomain("https://open3.bmob.cn/8/");
        Bmob.initialize(this, "36d91b12a94a333fbd7281eb1265abb3");

        Toolbar toolbar = findViewById(R.id.info_identity_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        df_realName = findViewById(R.id.df_realName);
        df_license = findViewById(R.id.df_license);
        df_phone = findViewById(R.id.df_phone);
//        df_team = findViewById(R.id.df_team);
//        df_photo = findViewById(R.id.df_photo);
//        df_level = findViewById(R.id.df_level);
        df_verify = findViewById(R.id.df_verify);

        tv_realName = findViewById(R.id.df_tv_name);
        tv_license = findViewById(R.id.df_tv_license);
        tv_phone = findViewById(R.id.df_tv_phone);
        cb_team = findViewById(R.id.df_cb_team);
        tv_level = findViewById(R.id.df_tv_level);


        up_realName();
        up_license();
        up_phone();
        up_team();
        up_photo();
        up_level();

        update();
        updateClick();


    }


    /**
     * 实名认证——真实姓名
     */
    public void up_realName()
    {
        MyUser myUser = MyUser.getCurrentUser(MyUser.class);    //获取当前登录用户信息

        tv_realName.setText(myUser.getRealName());

        df_realName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(IdentityActivity.this);
                builder.setIcon(R.mipmap.ic_name);
                builder.setTitle("请输入真实姓名:");

                View view = LayoutInflater.from(IdentityActivity.this).inflate(R.layout.update_realname, null);  //通过LayoutInflater来加载一个xml的布局文件作为一个View对象
                builder.setView(view);      //设置我们自己定义的布局文件作为弹出框的Content

                final EditText realName = (EditText)view.findViewById(R.id.realName);

                builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        String rn = realName.getText().toString().trim();

                        tv_realName.setText(rn);

                        myUser.setRealName(rn);
                        myUser.update(myUser.getObjectId(), new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if(e==null){
                                    Toast.makeText(IdentityActivity.this,"真实姓名修改于"+myUser.getUpdatedAt(), Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(IdentityActivity.this, "修改失败:"+e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {

                    }
                });
                builder.show();
            }
        });
    }


    /**
     * 实名认证身份证号
     */
    public void up_license()
    {
        MyUser myUser = MyUser.getCurrentUser(MyUser.class);    //获取当前登录用户信息

        tv_license.setText(myUser.getIdCardNum());

        df_license.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(IdentityActivity.this);
                builder.setIcon(R.mipmap.ic_license);
                builder.setTitle("请输入身份证号:");

                View view = LayoutInflater.from(IdentityActivity.this).inflate(R.layout.update_license, null);  //通过LayoutInflater来加载一个xml的布局文件作为一个View对象
                builder.setView(view);      //设置我们自己定义的布局文件作为弹出框的Content

                final EditText license = (EditText)view.findViewById(R.id.license);

                builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String ls = license.getText().toString().trim();
//                        Toast.makeText(IdentityActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                        if (ls.length() == 18) {
                            tv_license.setText(ls);
                            myUser.setIdCardNum(ls);
                            myUser.update(myUser.getObjectId(), new UpdateListener() {
                                @Override
                                public void done(BmobException e) {
                                    if (e == null) {
                                        Toast.makeText(IdentityActivity.this, "身份证号修改于" + myUser.getUpdatedAt(), Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(IdentityActivity.this, "修改失败:" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }else {
                            Toast.makeText(IdentityActivity.this, "修改失败: 身份证号为18位", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {

                    }
                });
                builder.show();
            }
        });
    }


    /**
     * 实名认证手机号
     */
    public void up_phone()
    {
        MyUser myUser = MyUser.getCurrentUser(MyUser.class);    //获取当前登录用户信息

        tv_phone.setText(myUser.getMobilePhoneNumber());

        df_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(IdentityActivity.this);
                builder.setIcon(R.mipmap.ic_call);
                builder.setTitle("请输入用于联系的手机号");

                View view = LayoutInflater.from(IdentityActivity.this).inflate(R.layout.update_phone, null);  //通过LayoutInflater来加载一个xml的布局文件作为一个View对象
                builder.setView(view);      //设置我们自己定义的布局文件作为弹出框的Content

                final EditText phone = (EditText)view.findViewById(R.id.phone);

                builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        String ph = phone.getText().toString().trim();
                        Toast.makeText(IdentityActivity.this, "修改后的手机号：" + ph, Toast.LENGTH_SHORT).show();  //将输入的用户名和密码打印出来
                        tv_phone.setText(ph);

                        myUser.setMobilePhoneNumber(ph);
                        myUser.update(myUser.getObjectId(), new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if(e==null){
                                    Toast.makeText(IdentityActivity.this,"手机号修改于" + myUser.getUpdatedAt(), Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(IdentityActivity.this, "手机号修改失败:" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {

                    }
                });
                builder.show();
            }
        });
    }


    /**
     * 实名认证团队证明
     */
    public void up_team()
    {
        MyUser myUser = MyUser.getCurrentUser(MyUser.class);    //获取当前登录用户信息

        String str = myUser.getTeam();

        if (str.equals("是")) {
            cb_team.setChecked(true);
        }

        cb_team.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_team.isChecked())
                {
                    myUser.setTeam("是");
                    myUser.update(myUser.getObjectId(), new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if(e==null){
                                Toast.makeText(IdentityActivity.this,"团队认证修改于" + myUser.getUpdatedAt(), Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(IdentityActivity.this, "团队认证修改失败:" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else {
                    myUser.setTeam("否");
                    myUser.update(myUser.getObjectId(), new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if(e==null){
                                Toast.makeText(IdentityActivity.this,"团队认证修改于" + myUser.getUpdatedAt(), Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(IdentityActivity.this, "团队认证修改失败:" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }


    /**
     * 实名认证照片
     */
    public void up_photo()
    {

    }


    /**
     * 实名认证等级
     */
    public void up_level()
    {
        MyUser myUser = MyUser.getCurrentUser(MyUser.class);    //获取当前登录用户信息

        BmobQuery<MyUser> bmobQuery = new BmobQuery<MyUser>();
        bmobQuery.getObject(myUser.getObjectId(), new QueryListener<MyUser>() {
            @Override
            public void done(MyUser myUser, BmobException e) {
                if(e==null){
                    if(myUser.getRealName().isEmpty() | myUser.getIdCardNum().isEmpty() | myUser.getMobilePhoneNumber().isEmpty()){
                        tv_level.setText("3级");
                    }else if ((myUser.getRealName().isEmpty() && myUser.getIdCardNum().isEmpty()) |
                            (myUser.getRealName().isEmpty() && myUser.getMobilePhoneNumber().isEmpty()) |
                            (myUser.getMobilePhoneNumber().isEmpty() && myUser.getIdCardNum().isEmpty())) {

                        tv_level.setText("2级");

                    }else if (myUser.getRealName().isEmpty() && myUser.getIdCardNum().isEmpty() && myUser.getMobilePhoneNumber().isEmpty()) {
                        tv_level.setText("1级");
                    }else {
                        tv_level.setText("4级");
                    }
                }else{
                    Toast.makeText(IdentityActivity.this, "界面加载失败:" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    /**
     * 实名认证确认
     */
    public void update()
    {
        MyUser myUser = MyUser.getCurrentUser(MyUser.class);    //获取当前登录用户信息

        BmobQuery<MyUser> bmobQuery = new BmobQuery<MyUser>();
        bmobQuery.getObject(myUser.getObjectId(), new QueryListener<MyUser>() {
            @Override
            public void done(MyUser myUser, BmobException e) {
                if(e==null){
                    if (myUser.getRealName().isEmpty() | myUser.getIdCardNum().isEmpty() | myUser.getMobilePhoneNumber().isEmpty())
                    {
                        df_verify.setText("尚未完成实名认证");
                    }else {
                        df_verify.setText("已实名认证");
                    }
                }else{
                    Toast.makeText(IdentityActivity.this, "界面加载失败:" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void updateClick()
    {
        df_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                up_realName();
                up_license();
                up_phone();
                up_team();
                up_photo();
                up_level();

                update();

                MyUser myUser = MyUser.getCurrentUser(MyUser.class);    //获取当前登录用户信息
                if (df_verify.getText().toString().equals("尚未完成实名认证"))
                {
                    myUser.setIdentity("尚未完成实名认证");

                    myUser.update(myUser.getObjectId(), new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if(e==null){
                                Toast.makeText(IdentityActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(IdentityActivity.this, "修改失败:" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else {
                    myUser.setIdentity("已实名认证");

                    myUser.update(myUser.getObjectId(), new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if(e==null){
                                Toast.makeText(IdentityActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(IdentityActivity.this, "修改失败:" + e.getMessage(), Toast.LENGTH_SHORT).show();
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
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}