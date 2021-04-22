package com.example.mdtest.DrawMenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.mdtest.Mine.GoActivity;
import com.example.mdtest.R;
import com.example.mdtest.Bean.Histogram;
import com.example.mdtest.Bean.MyUser;

public class CreditActivity extends AppCompatActivity {

    private Histogram column_one;

    private TextView credit_name;
    private TextView credit_time;
    private TextView credit_record;
    private TextView credit_level;
    private TextView credit_success_take;
    private TextView credit_success_offer;
    private TextView credit_team;

    private Button bt_go;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawmenu_credit);

        /**
         * 标题栏
         */
        Toolbar toolbar = findViewById(R.id.credit_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        credit_name = findViewById(R.id.credit_name);
        credit_time = findViewById(R.id.credit_time);
        credit_record = findViewById(R.id.credit_record);
        credit_level = findViewById(R.id.credit_level);
        credit_success_take = findViewById(R.id.credit_success_take);
        credit_success_offer = findViewById(R.id.credit_success_offer);
        credit_team = findViewById(R.id.credit_team);
        bt_go = findViewById(R.id.credit_go);

        initAllViews();

        creditName();
        creditRecord();
        creditLevel();
        creditTake();
        creditOffer();
        creditTeam();

    }


    /**
     * 初始化柱状图
     */
    private void initAllViews() {
        column_one = (Histogram) findViewById(R.id.column_one);

        column_one.setData( 44, 100);

//        column_one.mPaint.setColor(getResources().getColor(R.color.black)); //改变柱状图的颜色
    }


    /**
     * 用户姓名
     */
    private void creditName()
    {
        MyUser myUser = MyUser.getCurrentUser(MyUser.class);
        credit_name.setText(myUser.getUsername());
        credit_time.setText(myUser.getUpdatedAt());
    }


    /**
     * 信用记录
     */
    private void creditRecord()
    {

    }


    /**
     * 信用等级
     */
    private void creditLevel()
    {

    }


    /**
     * 完成接单数
     */
    private void creditTake()
    {

    }


    /**
     * 订单成交数
     */
    private void creditOffer()
    {

    }


    /**
     * 所属团体
     */
    private void creditTeam()
    {

    }


    private void creditBt()
    {
        bt_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreditActivity.this, GoActivity.class);
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
        switch (item.getItemId())
        {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}