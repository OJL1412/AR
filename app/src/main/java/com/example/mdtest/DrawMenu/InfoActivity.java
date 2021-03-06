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

import com.example.mdtest.Bean.MyUser;
import com.example.mdtest.R;
import com.example.mdtest.Setting.AddressActivity;

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
     * ????????????
     */
    public void checkImage() {

    }


    /**
     * ????????????
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
                builder.setTitle("???????????????");
                final String[] icon = {"????????????", "????????????", "????????????"};      //?????????????????????????????????

                if (myUser.getImageId() == null)
                {
                    myUser.setImageId(0);
                }

                /**
                 * ???????????????????????????????????????????????????????????????????????????
                 * ???????????????????????????????????????????????????????????????????????????0??????'????????????' ???????????????
                 * ?????????????????????????????????????????????????????????
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
                                            Toast.makeText(InfoActivity.this, "????????????", Toast.LENGTH_SHORT).show();
                                        }else{
                                            Toast.makeText(InfoActivity.this, "????????????"+e.getMessage(), Toast.LENGTH_SHORT).show();
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
                                            Toast.makeText(InfoActivity.this, "????????????", Toast.LENGTH_SHORT).show();
                                        }else{
                                            Toast.makeText(InfoActivity.this, "????????????"+e.getMessage(), Toast.LENGTH_SHORT).show();
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
                                            Toast.makeText(InfoActivity.this, "????????????", Toast.LENGTH_SHORT).show();
                                        }else{
                                            Toast.makeText(InfoActivity.this, "????????????:"+e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                                break;

                            default:
                                break;
                        }

                    }
                });
                builder.setPositiveButton("??????", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {

                    }
                });
                builder.setNegativeButton("??????", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
//                        MyUser myUser = MyUser.getCurrentUser(MyUser.class);
                        switch (myUser.getSex())
                        {
                            case "???":
                                info_icon.setImageResource(R.mipmap.ic_boy);
                                break;

                            case "???":
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
     * ????????????
     */
    public void updateUserName() {
        MyUser myUser = MyUser.getCurrentUser(MyUser.class);    //??????????????????????????????

        tv_info_nickname.setText(myUser.getUsername());

        info_nickname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(InfoActivity.this);
                builder.setIcon(R.mipmap.ic_nn);
                builder.setTitle("????????????");

                View view = LayoutInflater.from(InfoActivity.this).inflate(R.layout.update_username, null);  //??????LayoutInflater???????????????xml???????????????????????????View??????
                builder.setView(view);      //?????????????????????????????????????????????????????????Content

                final EditText username = (EditText)view.findViewById(R.id.username);

                builder.setPositiveButton("??????", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        String un = username.getText().toString().trim();
                        Toast.makeText(InfoActivity.this, "?????????????????????" + un, Toast.LENGTH_SHORT).show();  //??????????????????????????????????????????
                        tv_info_nickname.setText(un);

                        myUser.setUsername(un);
                        myUser.update(myUser.getObjectId(), new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if(e==null){
                                    Toast.makeText(InfoActivity.this,"??????????????????:"+myUser.getUpdatedAt(), Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(InfoActivity.this, "??????????????????:"+e.getMessage(), Toast.LENGTH_SHORT).show();
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
                        MyUser myUser = MyUser.getCurrentUser(MyUser.class);    //??????????????????????????????
                        tv_info_nickname.setText(myUser.getUsername());
                    }
                });
                builder.show();
            }
        });

    }


    /**
     * ????????????
     */
    public void updateSex() {
        MyUser myUser = MyUser.getCurrentUser(MyUser.class);
        tv_info_sex.setText(myUser.getSex());

        info_sex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(InfoActivity.this);
                builder.setIcon(R.mipmap.ic_sex);
                builder.setTitle("???????????????");
                final String[] sex = {"??????", "???", "???"};      //?????????????????????????????????

                /**
                 * ???????????????????????????????????????????????????????????????????????????
                 * ???????????????????????????????????????????????????????????????????????????0????????????'??????' ???????????????
                 * ?????????????????????????????????????????????????????????
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
                                    Toast.makeText(InfoActivity.this, "????????????" + sex[which], Toast.LENGTH_SHORT).show();
                                    tv_info_sex.setText(sex[which]);
                                }else{
                                    Toast.makeText(InfoActivity.this, "??????????????????", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
                builder.setPositiveButton("??????", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {

                    }
                });
                builder.setNegativeButton("??????", new DialogInterface.OnClickListener()
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
     * ???????????????
     */
    public void updatePhone() {
        MyUser myUser = MyUser.getCurrentUser(MyUser.class);    //??????????????????????????????

        tv_info_phone2.setText(myUser.getMobilePhoneNumber());

        info_phone2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(InfoActivity.this);
                builder.setIcon(R.mipmap.ic_phone2);
                builder.setTitle("???????????????");

                View view = LayoutInflater.from(InfoActivity.this).inflate(R.layout.update_phone, null);  //??????LayoutInflater???????????????xml???????????????????????????View??????
                builder.setView(view);      //?????????????????????????????????????????????????????????Content

                final EditText phone = (EditText)view.findViewById(R.id.phone);

                builder.setPositiveButton("??????", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        String ph = phone.getText().toString().trim();
                        Toast.makeText(InfoActivity.this, "????????????????????????" + ph, Toast.LENGTH_SHORT).show();  //??????????????????????????????????????????
                        tv_info_phone2.setText(ph);

                        myUser.setMobilePhoneNumber(ph);
                        myUser.update(myUser.getObjectId(), new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if(e==null){
                                    Toast.makeText(InfoActivity.this,"?????????????????????:" + myUser.getUpdatedAt(), Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(InfoActivity.this, "?????????????????????:" + e.getMessage(), Toast.LENGTH_SHORT).show();
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
                        MyUser myUser = MyUser.getCurrentUser(MyUser.class);    //??????????????????????????????

                        tv_info_phone2.setText(myUser.getMobilePhoneNumber());
                    }
                });
                builder.show();
            }
        });

    }


    /**
     * ??????????????????
     */
    public void updateEmail() {
        MyUser myUser = MyUser.getCurrentUser(MyUser.class);    //??????????????????????????????

        tv_info_email.setText(myUser.getEmail());

        info_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(InfoActivity.this);
                builder.setIcon(R.mipmap.ic_email);
                builder.setTitle("??????????????????");

                View view = LayoutInflater.from(InfoActivity.this).inflate(R.layout.update_email, null);  //??????LayoutInflater???????????????xml???????????????????????????View??????
                builder.setView(view);      //?????????????????????????????????????????????????????????Content

                final EditText email = (EditText)view.findViewById(R.id.email);

                builder.setPositiveButton("??????", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        String em = email.getText().toString().trim();
                        Toast.makeText(InfoActivity.this, "???????????????????????????" + em, Toast.LENGTH_SHORT).show();  //??????????????????????????????????????????
                        tv_info_email.setText(em);

                        myUser.setEmail(em);
                        myUser.update(myUser.getObjectId(), new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if(e==null){
                                    Toast.makeText(InfoActivity.this,"????????????????????????:" + myUser.getUpdatedAt(), Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(InfoActivity.this, "????????????????????????:" + e.getMessage(), Toast.LENGTH_SHORT).show();
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
                        MyUser myUser = MyUser.getCurrentUser(MyUser.class);    //??????????????????????????????

                        tv_info_email.setText(myUser.getEmail());
                    }
                });
                builder.show();

            }
        });

    }


    /**
     * ??????????????????
     */
    public void updateAddress() {
        info_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InfoActivity.this, AddressActivity.class);
                startActivity(intent);
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