package com.example.mdtest.Mine;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.mdtest.R;

public class FeedbackActivity extends AppCompatActivity {

    private TextView tv;

    private TextView tv_problem1;
    private TextView tv_problem2;
    private TextView tv_problem3;
    private TextView tv_problem4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_help);

        tv = (TextView)findViewById(R.id.info_help_title);
        tv_problem1 = (TextView)findViewById(R.id.info_help_pro1);
        tv_problem2 = (TextView)findViewById(R.id.info_help_pro2);
        tv_problem3 = (TextView)findViewById(R.id.info_help_pro3);
        tv_problem4 = (TextView)findViewById(R.id.info_help_pro4);

        Toolbar toolbar = (Toolbar)findViewById(R.id.info_help_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

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