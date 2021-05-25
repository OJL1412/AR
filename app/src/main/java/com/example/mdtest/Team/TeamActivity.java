package com.example.mdtest.Team;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.mdtest.R;

import cn.bmob.v3.Bmob;

public class TeamActivity extends AppCompatActivity {

    private TextView tv_add_team;
    private TextView tv_create_team;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);
        Bmob.resetDomain("https://open3.bmob.cn/8/");
        Bmob.initialize(this, "36d91b12a94a333fbd7281eb1265abb3");

        tv_add_team = (TextView) findViewById(R.id.tv_add_team);
        tv_create_team = (TextView) findViewById(R.id.tv_create_team);

        Toolbar toolbar = findViewById(R.id.team_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
        {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        addTeam();
        createTeam();
    }

    /**
     * 加入团队
     */
    public void addTeam()
    {
        tv_add_team.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TeamActivity.this, TeamListActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 创建团队
     */
    public void createTeam()
    {
        tv_create_team.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TeamActivity.this, TeamRegActivity.class);
                startActivity(intent);
            }
        });
    }


    /**
     * 返回键
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}