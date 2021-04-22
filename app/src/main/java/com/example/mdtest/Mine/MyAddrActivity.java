package com.example.mdtest.Mine;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mdtest.R;

public class MyAddrActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_my_addr);

        Toolbar toolbar = (Toolbar)findViewById(R.id.info_psw_find_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }


    /**
     * 获取menu的注入器(Inflater)并将我们配置的address_menu文件加载到menu中
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_address, menu);
        return true;
    }


    /**
     * 添加与返回按钮
     * @param item
     * @return
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.add_address:
                Toast.makeText(this, "你选择了添加", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(MyAddrActivity.this, AddAddrActivity.class);
                startActivity(intent1);
                break;

            case android.R.id.home:
                finish();
                return true;

            default:

        }
        return true;
    }



}