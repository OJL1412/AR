package com.example.mdtest.Mine;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.mdtest.R;

public class AboutActivity extends AppCompatActivity {

    private TextView AR_about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_about);

        Toolbar toolbar = (Toolbar)findViewById(R.id.info_about_toolbar);
        AR_about = (TextView)findViewById(R.id.AR_about);

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        AR_about.setText("一个专门为在校学生服务的平台\n以悬赏的形式实现用户之间的快递互取，实现虚拟资源的实际化");



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