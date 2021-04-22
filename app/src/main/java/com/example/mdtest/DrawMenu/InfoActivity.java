package com.example.mdtest.DrawMenu;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.mdtest.Mine.MyAddrActivity;
import com.example.mdtest.R;
import com.example.mdtest.Bean.MyUser;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import de.hdodenhof.circleimageview.CircleImageView;

public class InfoActivity extends AppCompatActivity {

    private TextView info_image;
    private CircleImageView info_icon;
    private TextView info_nickname;
    private TextView tv_info_nickname;
    private TextView info_sex;
    private TextView tv_info_sex;
    private TextView info_phone2;
    private TextView tv_info_phone2;
    private TextView info_email;
    private TextView tv_info_email;
    private TextView info_address;
    private TextView tv_info_address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_info);
        Bmob.resetDomain("https://open3.bmob.cn/8/");
        Bmob.initialize(this, "36d91b12a94a333fbd7281eb1265abb3");

        Toolbar toolbar = (Toolbar)findViewById(R.id.info_info_toolbar);
        setSupportActionBar(toolbar);

        info_image = findViewById(R.id.info_image);
        info_icon = findViewById(R.id.info_icon);
        info_nickname = findViewById(R.id.info_nickname);
        tv_info_nickname = findViewById(R.id.tv_info_nickname);
        info_sex = findViewById(R.id.info_sex);
        tv_info_sex = findViewById(R.id.tv_info_sex);
        info_phone2 = findViewById(R.id.info_phone2);
        tv_info_phone2 = findViewById(R.id.tv_info_phone2);
        info_email = findViewById(R.id.info_email);
        tv_info_email = findViewById(R.id.tv_info_email);
        info_address = findViewById(R.id.info_address);
        tv_info_address = findViewById(R.id.tv_info_address);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        checkImage();
        updateIcon();

        updateUserName();
        updateSex();
        updatePhone();
        updateEmail();
        updateAddress();

    }

    /**
     * 查看图片
     */
    public void checkImage() {

    }


    /**
     * 修改头像
     */
    public void updateIcon() {
        MyUser myUser = MyUser.getCurrentUser(MyUser.class);

        if (myUser.getImageId() != null)
        {
            switch (myUser.getImageId())
            {
                case 0:
                    info_icon.setImageResource(R.mipmap.ic_common2);
                    break;

                case 1:
                    info_icon.setImageResource(R.mipmap.ic_boy);
                    break;

                case 2:
                    info_icon.setImageResource(R.mipmap.ic_girl);
                    break;
            }
        }else {
            info_icon.setImageResource(R.mipmap.ic_dog);
        }


        info_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MyUser myUser = MyUser.getCurrentUser(MyUser.class);
                AlertDialog.Builder builder = new AlertDialog.Builder(InfoActivity.this);
                builder.setIcon(R.mipmap.ic_icon);
                builder.setTitle("请选择头像");
                final String[] icon = {"默认头像", "男生头像", "女生头像"};      //设置一个单项选择下拉框

                if (myUser.getImageId() == null)
                {
                    myUser.setImageId(0);
                }

                /**
                 * 第一个参数指定我们要显示的一组下拉单选框的数据集合
                 * 第二个参数代表索引，指定默认哪一个单选框被勾选上，0表示'默认头像' 会被勾选上
                 * 第三个参数给每一个单选项绑定一个监听器
                 */
                builder.setSingleChoiceItems(icon, myUser.getImageId(), new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
//                        MyUser myUser = MyUser.getCurrentUser(MyUser.class);
                        switch (which) {
                            case 0:
                                info_icon.setImageResource(R.mipmap.ic_common2);
                                myUser.setImageId(0);
                                myUser.update(myUser.getObjectId(), new UpdateListener() {
                                    @Override
                                    public void done(BmobException e) {
                                        if(e==null){
                                            Toast.makeText(InfoActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                                        }else{
                                            Toast.makeText(InfoActivity.this, "修改失败"+e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                                break;

                            case 1:
                                info_icon.setImageResource(R.mipmap.ic_boy);
                                myUser.setImageId(1);
                                myUser.update(myUser.getObjectId(), new UpdateListener() {
                                    @Override
                                    public void done(BmobException e) {
                                        if(e==null){
                                            Toast.makeText(InfoActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                                        }else{
                                            Toast.makeText(InfoActivity.this, "修改失败"+e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                                break;

                            case 2:
                                info_icon.setImageResource(R.mipmap.ic_girl);
                                myUser.setImageId(2);
                                myUser.update(myUser.getObjectId(), new UpdateListener() {
                                    @Override
                                    public void done(BmobException e) {
                                        if(e==null){
                                            Toast.makeText(InfoActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                                        }else{
                                            Toast.makeText(InfoActivity.this, "修改失败:"+e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                                break;

                            default:
                                break;
                        }

                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {

                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
//                        MyUser myUser = MyUser.getCurrentUser(MyUser.class);
                        switch (myUser.getSex())
                        {
                            case "男":
                                info_icon.setImageResource(R.mipmap.ic_boy);
                                break;

                            case "女":
                                info_icon.setImageResource(R.mipmap.ic_girl);
                                break;

                            default:
                                info_icon.setImageResource(R.mipmap.ic_dog);
                                break;
                        }
                    }
                });
                builder.show();
            }
        });
    }


    /**
     * 修改昵称
     */
    public void updateUserName() {
        MyUser myUser = MyUser.getCurrentUser(MyUser.class);    //获取当前登录用户信息

        tv_info_nickname.setText(myUser.getUsername());

        info_nickname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(InfoActivity.this);
                builder.setIcon(R.mipmap.ic_nn);
                builder.setTitle("新的昵称");

                View view = LayoutInflater.from(InfoActivity.this).inflate(R.layout.update_username, null);  //通过LayoutInflater来加载一个xml的布局文件作为一个View对象
                builder.setView(view);      //设置我们自己定义的布局文件作为弹出框的Content

                final EditText username = (EditText)view.findViewById(R.id.username);

                builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        String un = username.getText().toString().trim();
                        Toast.makeText(InfoActivity.this, "修改后的昵称：" + un, Toast.LENGTH_SHORT).show();  //将输入的用户名和密码打印出来
                        tv_info_nickname.setText(un);

                        myUser.setUsername(un);
                        myUser.update(myUser.getObjectId(), new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if(e==null){
                                    Toast.makeText(InfoActivity.this,"昵称更新成功:"+myUser.getUpdatedAt(), Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(InfoActivity.this, "昵称更新失败:"+e.getMessage(), Toast.LENGTH_SHORT).show();
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
                        MyUser myUser = MyUser.getCurrentUser(MyUser.class);    //获取当前登录用户信息
                        tv_info_nickname.setText(myUser.getUsername());
                    }
                });
                builder.show();
            }
        });

    }


    /**
     * 修改性别
     */
    public void updateSex() {
        MyUser myUser = MyUser.getCurrentUser(MyUser.class);
        tv_info_sex.setText(myUser.getSex());

        info_sex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(InfoActivity.this);
                builder.setIcon(R.mipmap.ic_sex);
                builder.setTitle("请选择性别");
                final String[] sex = {"保密", "男", "女"};      //设置一个单项选择下拉框

                /**
                 * 第一个参数指定我们要显示的一组下拉单选框的数据集合
                 * 第二个参数代表索引，指定默认哪一个单选框被勾选上，0表示默认'保密' 会被勾选上
                 * 第三个参数给每一个单选项绑定一个监听器
                 */
                builder.setSingleChoiceItems(sex, 0, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        MyUser myUser = MyUser.getCurrentUser(MyUser.class);
                        myUser.setSex(sex[which]);
                        myUser.update(myUser.getObjectId(), new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if(e==null){
                                    Toast.makeText(InfoActivity.this, "性别为：" + sex[which], Toast.LENGTH_SHORT).show();
                                    tv_info_sex.setText(sex[which]);
                                }else{
                                    Toast.makeText(InfoActivity.this, "性别修改失败", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {

                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        MyUser myUser = MyUser.getCurrentUser(MyUser.class);
                        tv_info_sex.setText(myUser.getSex());
                    }
                });
                builder.show();
            }
        });
    }


    /**
     * 修改手机号
     */
    public void updatePhone() {
        MyUser myUser = MyUser.getCurrentUser(MyUser.class);    //获取当前登录用户信息

        tv_info_phone2.setText(myUser.getMobilePhoneNumber());

        info_phone2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(InfoActivity.this);
                builder.setIcon(R.mipmap.ic_phone2);
                builder.setTitle("新的手机号");

                View view = LayoutInflater.from(InfoActivity.this).inflate(R.layout.update_phone, null);  //通过LayoutInflater来加载一个xml的布局文件作为一个View对象
                builder.setView(view);      //设置我们自己定义的布局文件作为弹出框的Content

                final EditText phone = (EditText)view.findViewById(R.id.phone);

                builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        String ph = phone.getText().toString().trim();
                        Toast.makeText(InfoActivity.this, "修改后的手机号：" + ph, Toast.LENGTH_SHORT).show();  //将输入的用户名和密码打印出来
                        tv_info_phone2.setText(ph);

                        myUser.setMobilePhoneNumber(ph);
                        myUser.update(myUser.getObjectId(), new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if(e==null){
                                    Toast.makeText(InfoActivity.this,"手机号更新成功:" + myUser.getUpdatedAt(), Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(InfoActivity.this, "手机号更新失败:" + e.getMessage(), Toast.LENGTH_SHORT).show();
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
                        MyUser myUser = MyUser.getCurrentUser(MyUser.class);    //获取当前登录用户信息

                        tv_info_phone2.setText(myUser.getMobilePhoneNumber());
                    }
                });
                builder.show();
            }
        });

    }


    /**
     * 修改邮箱地址
     */
    public void updateEmail() {
        MyUser myUser = MyUser.getCurrentUser(MyUser.class);    //获取当前登录用户信息

        tv_info_email.setText(myUser.getEmail());

        info_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(InfoActivity.this);
                builder.setIcon(R.mipmap.ic_email);
                builder.setTitle("新的邮箱地址");

                View view = LayoutInflater.from(InfoActivity.this).inflate(R.layout.update_email, null);  //通过LayoutInflater来加载一个xml的布局文件作为一个View对象
                builder.setView(view);      //设置我们自己定义的布局文件作为弹出框的Content

                final EditText email = (EditText)view.findViewById(R.id.email);

                builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        String em = email.getText().toString().trim();
                        Toast.makeText(InfoActivity.this, "修改后的邮箱地址：" + em, Toast.LENGTH_SHORT).show();  //将输入的用户名和密码打印出来
                        tv_info_email.setText(em);

                        myUser.setEmail(em);
                        myUser.update(myUser.getObjectId(), new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if(e==null){
                                    Toast.makeText(InfoActivity.this,"邮箱地址更新成功:" + myUser.getUpdatedAt(), Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(InfoActivity.this, "邮箱地址更新失败:" + e.getMessage(), Toast.LENGTH_SHORT).show();
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
                        MyUser myUser = MyUser.getCurrentUser(MyUser.class);    //获取当前登录用户信息

                        tv_info_email.setText(myUser.getEmail());
                    }
                });
                builder.show();

            }
        });

    }


    /**
     * 修改常用地址
     */
    public void updateAddress() {
        info_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InfoActivity.this, MyAddrActivity.class);
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
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}