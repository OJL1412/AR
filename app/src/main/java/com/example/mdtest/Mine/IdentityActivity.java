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
     * ??????????????????????????????
     */
    public void up_realName()
    {
        MyUser myUser = MyUser.getCurrentUser(MyUser.class);    //??????????????????????????????

        tv_realName.setText(myUser.getRealName());

        df_realName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(IdentityActivity.this);
                builder.setIcon(R.mipmap.ic_name);
                builder.setTitle("?????????????????????:");

                View view = LayoutInflater.from(IdentityActivity.this).inflate(R.layout.update_realname, null);  //??????LayoutInflater???????????????xml???????????????????????????View??????
                builder.setView(view);      //?????????????????????????????????????????????????????????Content

                final EditText realName = (EditText)view.findViewById(R.id.realName);

                builder.setPositiveButton("??????", new DialogInterface.OnClickListener()
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
                                    Toast.makeText(IdentityActivity.this,"?????????????????????"+myUser.getUpdatedAt(), Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(IdentityActivity.this, "????????????:"+e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
                builder.setNegativeButton("??????", new DialogInterface.OnClickListener()
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
     * ????????????????????????
     */
    public void up_license()
    {
        MyUser myUser = MyUser.getCurrentUser(MyUser.class);    //??????????????????????????????

        tv_license.setText(myUser.getIdCardNum());

        df_license.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(IdentityActivity.this);
                builder.setIcon(R.mipmap.ic_license);
                builder.setTitle("?????????????????????:");

                View view = LayoutInflater.from(IdentityActivity.this).inflate(R.layout.update_license, null);  //??????LayoutInflater???????????????xml???????????????????????????View??????
                builder.setView(view);      //?????????????????????????????????????????????????????????Content

                final EditText license = (EditText)view.findViewById(R.id.license);

                builder.setPositiveButton("??????", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String ls = license.getText().toString().trim();
//                        Toast.makeText(IdentityActivity.this, "????????????", Toast.LENGTH_SHORT).show();
                        if (ls.length() == 18) {
                            tv_license.setText(ls);
                            myUser.setIdCardNum(ls);
                            myUser.update(myUser.getObjectId(), new UpdateListener() {
                                @Override
                                public void done(BmobException e) {
                                    if (e == null) {
                                        Toast.makeText(IdentityActivity.this, "?????????????????????" + myUser.getUpdatedAt(), Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(IdentityActivity.this, "????????????:" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }else {
                            Toast.makeText(IdentityActivity.this, "????????????: ???????????????18???", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("??????", new DialogInterface.OnClickListener()
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
     * ?????????????????????
     */
    public void up_phone()
    {
        MyUser myUser = MyUser.getCurrentUser(MyUser.class);    //??????????????????????????????

        tv_phone.setText(myUser.getMobilePhoneNumber());

        df_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(IdentityActivity.this);
                builder.setIcon(R.mipmap.ic_call);
                builder.setTitle("?????????????????????????????????");

                View view = LayoutInflater.from(IdentityActivity.this).inflate(R.layout.update_phone, null);  //??????LayoutInflater???????????????xml???????????????????????????View??????
                builder.setView(view);      //?????????????????????????????????????????????????????????Content

                final EditText phone = (EditText)view.findViewById(R.id.phone);

                builder.setPositiveButton("??????", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        String ph = phone.getText().toString().trim();
                        Toast.makeText(IdentityActivity.this, "????????????????????????" + ph, Toast.LENGTH_SHORT).show();  //??????????????????????????????????????????
                        tv_phone.setText(ph);

                        myUser.setMobilePhoneNumber(ph);
                        myUser.update(myUser.getObjectId(), new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if(e==null){
                                    Toast.makeText(IdentityActivity.this,"??????????????????" + myUser.getUpdatedAt(), Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(IdentityActivity.this, "?????????????????????:" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
                builder.setNegativeButton("??????", new DialogInterface.OnClickListener()
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
     * ????????????????????????
     */
    public void up_team()
    {
        MyUser myUser = MyUser.getCurrentUser(MyUser.class);    //??????????????????????????????

        String str = myUser.getTeam();

        if (str.equals("???")) {
            cb_team.setChecked(true);
        }

        cb_team.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_team.isChecked())
                {
                    myUser.setTeam("???");
                    myUser.update(myUser.getObjectId(), new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if(e==null){
                                Toast.makeText(IdentityActivity.this,"?????????????????????" + myUser.getUpdatedAt(), Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(IdentityActivity.this, "????????????????????????:" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else {
                    myUser.setTeam("???");
                    myUser.update(myUser.getObjectId(), new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if(e==null){
                                Toast.makeText(IdentityActivity.this,"?????????????????????" + myUser.getUpdatedAt(), Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(IdentityActivity.this, "????????????????????????:" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }


    /**
     * ??????????????????
     */
    public void up_photo()
    {

    }


    /**
     * ??????????????????
     */
    public void up_level()
    {
        MyUser myUser = MyUser.getCurrentUser(MyUser.class);    //??????????????????????????????

        BmobQuery<MyUser> bmobQuery = new BmobQuery<MyUser>();
        bmobQuery.getObject(myUser.getObjectId(), new QueryListener<MyUser>() {
            @Override
            public void done(MyUser myUser, BmobException e) {
                if(e==null){
                    if(myUser.getRealName().isEmpty() | myUser.getIdCardNum().isEmpty() | myUser.getMobilePhoneNumber().isEmpty()){
                        tv_level.setText("3???");
                    }else if ((myUser.getRealName().isEmpty() && myUser.getIdCardNum().isEmpty()) |
                            (myUser.getRealName().isEmpty() && myUser.getMobilePhoneNumber().isEmpty()) |
                            (myUser.getMobilePhoneNumber().isEmpty() && myUser.getIdCardNum().isEmpty())) {

                        tv_level.setText("2???");

                    }else if (myUser.getRealName().isEmpty() && myUser.getIdCardNum().isEmpty() && myUser.getMobilePhoneNumber().isEmpty()) {
                        tv_level.setText("1???");
                    }else {
                        tv_level.setText("4???");
                    }
                }else{
                    Toast.makeText(IdentityActivity.this, "??????????????????:" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    /**
     * ??????????????????
     */
    public void update()
    {
        MyUser myUser = MyUser.getCurrentUser(MyUser.class);    //??????????????????????????????

        BmobQuery<MyUser> bmobQuery = new BmobQuery<MyUser>();
        bmobQuery.getObject(myUser.getObjectId(), new QueryListener<MyUser>() {
            @Override
            public void done(MyUser myUser, BmobException e) {
                if(e==null){
                    if (myUser.getRealName().isEmpty() | myUser.getIdCardNum().isEmpty() | myUser.getMobilePhoneNumber().isEmpty())
                    {
                        df_verify.setText("????????????????????????");
                    }else {
                        df_verify.setText("???????????????");
                    }
                }else{
                    Toast.makeText(IdentityActivity.this, "??????????????????:" + e.getMessage(), Toast.LENGTH_SHORT).show();
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

                MyUser myUser = MyUser.getCurrentUser(MyUser.class);    //??????????????????????????????
                if (df_verify.getText().toString().equals("????????????????????????"))
                {
                    myUser.setIdentity("????????????????????????");

                    myUser.update(myUser.getObjectId(), new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if(e==null){
                                Toast.makeText(IdentityActivity.this, "????????????", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(IdentityActivity.this, "????????????:" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else {
                    myUser.setIdentity("???????????????");

                    myUser.update(myUser.getObjectId(), new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if(e==null){
                                Toast.makeText(IdentityActivity.this, "????????????", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(IdentityActivity.this, "????????????:" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });
    }


    /**
     * ?????????
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