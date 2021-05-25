package com.example.mdtest.Setting;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.mdtest.Mine.AboutActivity;
import com.example.mdtest.Mine.AccountActivity;
import com.example.mdtest.Mine.FeedbackActivity;
import com.example.mdtest.Mine.IdentityActivity;
import com.example.mdtest.R;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;

public class SetActivity extends AppCompatActivity implements View.OnClickListener {
    private Dialog mDialog;

    private TextView tv_username;
    private TextView tv_account;
    private TextView tv_about;
    private TextView tv_help;
    private TextView tv_identity;
    private TextView tv_change;
    private TextView tv_quit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        Bmob.resetDomain("https://open3.bmob.cn/8/");
        Bmob.initialize(SetActivity.this, "36d91b12a94a333fbd7281eb1265abb3");

        Toolbar toolbar = (Toolbar)findViewById(R.id.info_setting_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        tv_username  =(TextView)findViewById(R.id.username);
        tv_account = (TextView)findViewById(R.id.info_my_account);
        tv_about = (TextView)findViewById(R.id.info_my_about);
        tv_help = (TextView)findViewById(R.id.info_my_help);
        tv_identity = (TextView)findViewById(R.id.info_my_identity);
        tv_quit = (TextView)findViewById(R.id.info_my_quit);

        account();
        about();
        help();
        identity();
        quit();

    }

    /**
     * "账号与安全"按钮跳转
     */
    public void account() {
        tv_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetActivity.this, AccountActivity.class);
                startActivity(intent);
            }
        });
    }


    /**
     * "关于AR跑腿"按钮跳转
     */
    public void about() {
        tv_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });
    }


    /**
     * "帮助与反馈"按钮跳转
     */
    public void help() {
        tv_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetActivity.this, FeedbackActivity.class);
                startActivity(intent);
            }
        });
    }


    /**
     * "身份认证"按钮跳转
     */
    public void identity() {
        tv_identity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetActivity.this, IdentityActivity.class);
                startActivity(intent);
            }
        });
    }



    /**
     * "退出登录"按钮跳转
     */
    public void quit() {
        tv_quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDialog();
            }
        });
    }


    /**
     * 创建"退出"弹窗
     */
    private void setDialog(){
        mDialog = new Dialog(SetActivity.this, R.style.BottomDialog);
        LinearLayout root = (LinearLayout) LayoutInflater.from(SetActivity.this).inflate(
                R.layout.dialog_quit_log, null);

        RadioButton bt_quit_log = root.findViewById(R.id.bt_quit_log);
        RadioButton bt_off_app = root.findViewById(R.id.bt_off_app);

        //初始化视图
        root.findViewById(R.id.bt_quit_log).setOnClickListener(SetActivity.this);
        root.findViewById(R.id.bt_off_app).setOnClickListener(SetActivity.this);
        root.findViewById(R.id.bt_dismiss).setOnClickListener(SetActivity.this);
        mDialog.setContentView(root);

        Window dialogWindow = mDialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
//        dialogWindow.setWindowAnimations(R.style.dialogstyle); // 添加动画

        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.x = 0; // 新位置X坐标
        lp.y = 0; // 新位置Y坐标
        lp.width = (int) getResources().getDisplayMetrics().widthPixels; // 宽度
        root.measure(0, 0);
        lp.height = root.getMeasuredHeight();
        lp.alpha = 9f; // 透明度

        dialogWindow.setAttributes(lp);
        mDialog.show();

        RadioGroup radioGroup = (RadioGroup)root.findViewById(R.id.modify);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (group.getCheckedRadioButtonId()) {
                    case R.id.bt_quit_log:

                        mDialog.dismiss();

                        bt_quit_log.setOnClickListener(new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View v)
                            {
                                //    通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
                                AlertDialog.Builder builder = new AlertDialog.Builder(SetActivity.this);

                                //    设置Content来显示一个信息
                                builder.setMessage("退出后不会删除任何历史记录，下次登录依然可以使用本账号。");
                                //    设置Title的图标
//                                builder.setIcon(R.drawable.ic_launcher);
                                //    设置Title的内容
//                                builder.setTitle("弹出警告框");


                                //    设置一个NegativeButton用来取消
                                builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
                                {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which)
                                    {
//                                        mDialog.dismiss();
                                    }
                                });
                                //    设置一个PositiveButton用来确定
                                builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
                                {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which)
                                    {
                                        BmobUser.logOut();
                                        Toast.makeText(SetActivity.this, "退出登录成功", Toast.LENGTH_SHORT).show();

                                        Intent intent2 = new Intent(Intent.ACTION_MAIN);
                                        intent2.addCategory(Intent.CATEGORY_HOME);
                                        intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent2);
                                        System.exit(0);

//                                        Intent intent = new Intent(getActivity(), LogActivity.class);
//                                        startActivity(intent);
//                                        getActivity().finish();
                                    }
                                });
                                builder.show();     //显示出该对话框
                            }
                        });
                        break;

                    case R.id.bt_off_app:

                        mDialog.dismiss();

                        bt_off_app.setOnClickListener(new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View v)
                            {
                                //    通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
                                AlertDialog.Builder builder = new AlertDialog.Builder(SetActivity.this);

                                //    设置Content来显示一个信息
                                builder.setMessage("关闭后，你将不能及时收到订单接收的消息，可能会影响到你的体验。");
                                //    设置Title的图标
//                                builder.setIcon(R.drawable.ic_launcher);
                                //    设置Title的内容
//                                builder.setTitle("警告");


                                //    设置一个NegativeButton用来取消
                                builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
                                {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which)
                                    {

                                    }
                                });
                                //    设置一个PositiveButton用来确定
                                builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
                                {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which)
                                    {
                                        Intent intent1 = new Intent(Intent.ACTION_MAIN);
                                        intent1.addCategory(Intent.CATEGORY_HOME);
                                        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent1);
                                        System.exit(0);
                                    }
                                });
                                builder.show();     //显示出该对话框
                            }
                        });

                        break;

                    case R.id.bt_dismiss:
                        mDialog.dismiss();
                        break;

                    default:
                        break;

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

    public void onClick(View v) {

    }

}