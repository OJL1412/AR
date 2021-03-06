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
     * "???????????????"????????????
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
     * "??????AR??????"????????????
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
     * "???????????????"????????????
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
     * "????????????"????????????
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
     * "????????????"????????????
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
     * ??????"??????"??????
     */
    private void setDialog(){
        mDialog = new Dialog(SetActivity.this, R.style.BottomDialog);
        LinearLayout root = (LinearLayout) LayoutInflater.from(SetActivity.this).inflate(
                R.layout.dialog_quit_log, null);

        RadioButton bt_quit_log = root.findViewById(R.id.bt_quit_log);
        RadioButton bt_off_app = root.findViewById(R.id.bt_off_app);

        //???????????????
        root.findViewById(R.id.bt_quit_log).setOnClickListener(SetActivity.this);
        root.findViewById(R.id.bt_off_app).setOnClickListener(SetActivity.this);
        root.findViewById(R.id.bt_dismiss).setOnClickListener(SetActivity.this);
        mDialog.setContentView(root);

        Window dialogWindow = mDialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
//        dialogWindow.setWindowAnimations(R.style.dialogstyle); // ????????????

        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // ?????????????????????????????????
        lp.x = 0; // ?????????X??????
        lp.y = 0; // ?????????Y??????
        lp.width = (int) getResources().getDisplayMetrics().widthPixels; // ??????
        root.measure(0, 0);
        lp.height = root.getMeasuredHeight();
        lp.alpha = 9f; // ?????????

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
                                //    ??????AlertDialog.Builder????????????????????????????????????AlertDialog?????????
                                AlertDialog.Builder builder = new AlertDialog.Builder(SetActivity.this);

                                //    ??????Content?????????????????????
                                builder.setMessage("????????????????????????????????????????????????????????????????????????????????????");
                                //    ??????Title?????????
//                                builder.setIcon(R.drawable.ic_launcher);
                                //    ??????Title?????????
//                                builder.setTitle("???????????????");


                                //    ????????????NegativeButton????????????
                                builder.setNegativeButton("??????", new DialogInterface.OnClickListener()
                                {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which)
                                    {
//                                        mDialog.dismiss();
                                    }
                                });
                                //    ????????????PositiveButton????????????
                                builder.setPositiveButton("??????", new DialogInterface.OnClickListener()
                                {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which)
                                    {
                                        BmobUser.logOut();
                                        Toast.makeText(SetActivity.this, "??????????????????", Toast.LENGTH_SHORT).show();

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
                                builder.show();     //?????????????????????
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
                                //    ??????AlertDialog.Builder????????????????????????????????????AlertDialog?????????
                                AlertDialog.Builder builder = new AlertDialog.Builder(SetActivity.this);

                                //    ??????Content?????????????????????
                                builder.setMessage("?????????????????????????????????????????????????????????????????????????????????????????????");
                                //    ??????Title?????????
//                                builder.setIcon(R.drawable.ic_launcher);
                                //    ??????Title?????????
//                                builder.setTitle("??????");


                                //    ????????????NegativeButton????????????
                                builder.setNegativeButton("??????", new DialogInterface.OnClickListener()
                                {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which)
                                    {

                                    }
                                });
                                //    ????????????PositiveButton????????????
                                builder.setPositiveButton("??????", new DialogInterface.OnClickListener()
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
                                builder.show();     //?????????????????????
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
     * ?????????
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