package com.example.mdtest.Setting;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mdtest.R;

import cn.bmob.v3.Bmob;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Bmob.resetDomain("https://open3.bmob.cn/8/");
        Bmob.initialize(this, "36d91b12a94a333fbd7281eb1265abb3");
    }
}