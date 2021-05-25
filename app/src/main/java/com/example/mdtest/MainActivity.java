package com.example.mdtest;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.multidex.MultiDex;

import com.example.mdtest.Bean.MyUser;
import com.example.mdtest.DrawMenu.CreditActivity;
import com.example.mdtest.DrawMenu.InfoActivity;
import com.example.mdtest.DrawMenu.OrderActivity;
import com.example.mdtest.DrawMenu.TakeActivity;
import com.example.mdtest.PageTwo.FindFragment;
import com.example.mdtest.PageFirst.MainFragment;
import com.example.mdtest.PageThree.MyFragment;
import com.example.mdtest.Mine.FeedbackActivity;
import com.example.mdtest.DrawMenu.MyTeamActivity;
import com.example.mdtest.Setting.ScanActivity;
import com.example.mdtest.Team.TeamActivity;
import com.example.mdtest.Setting.TotalTakeActivity;
import com.google.android.material.navigation.NavigationView;

import cn.bmob.v3.Bmob;
import de.hdodenhof.circleimageview.CircleImageView;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private CircleImageView circleImageView;
    private TextView nav_username;
    private TextView nav_email;
    private NavigationView navView;

    //底部导航栏的wid_get
    private RadioGroup mNavGroup;
    private FragmentTransaction mTransaction;

    //页面设置
    private Fragment syFragment, cxFragment, myFragment;
    public static final int VIEW_MAIN_INDEX = 0;
    public static final int VIEW_FIND_INDEX = 1;
    public static final int VIEW_ME_INDEX = 2;
    private int temp_position_index = -1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MultiDex.install(this);
        setContentView(R.layout.activity_main);
        Bmob.resetDomain("https://open3.bmob.cn/8/");
        Bmob.initialize(this, "36d91b12a94a333fbd7281eb1265abb3");



//        LayoutInflater inflater = this.getLayoutInflater();                           //先获取当前布局的填充器
//        View view = inflater.inflate(R.layout.nav_header, null);   //通过填充器获取另外一个布局的对象

        navView = (NavigationView)findViewById(R.id.nav_view);
        View view = navView.inflateHeaderView(R.layout.nav_header);

        initView();

        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        circleImageView = (CircleImageView)view.findViewById(R.id.nav_icon_image);
        nav_username = (TextView)view.findViewById(R.id.nav_head_username);
        nav_email = (TextView)view.findViewById(R.id.nav_head_email);

        initNavView();
        initHead();

    }


    /**
     * 个人信息面板选择功能
     */
    public void initNavView()
    {
        navView.setCheckedItem(R.id.nav_info);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId())
                {
                    case R.id.nav_info:
//                        Toast.makeText(MainActivity.this, "你选择了个人资料", Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent(MainActivity.this, InfoActivity.class);
                        startActivity(intent1);
                        break;


                    case R.id.nav_orders:
//                        Toast.makeText(MainActivity.this, "你选择了我的悬赏", Toast.LENGTH_SHORT).show();
                        Intent intent3 = new Intent(MainActivity.this, OrderActivity.class);
                        startActivity(intent3);
                        break;

                    case R.id.nav_take:
//                        Toast.makeText(MainActivity.this, "你选择了我的接单", Toast.LENGTH_SHORT).show();
                        Intent intent4 = new Intent(MainActivity.this, TakeActivity.class);
                        startActivity(intent4);
                        break;

                    case R.id.nav_credit:
//                        Toast.makeText(MainActivity.this, "你选择了信誉等级", Toast.LENGTH_SHORT).show();
                        Intent intent5 = new Intent(MainActivity.this, CreditActivity.class);
                        startActivity(intent5);
                        break;

                    case R.id.nav_team:
                        MyUser myUser = MyUser.getCurrentUser(MyUser.class);
                        Intent intent6;
                        if (myUser.getTeam().equals("否"))
                        {
                            intent6 = new Intent(MainActivity.this, TeamActivity.class);
                        }else {
                            intent6 = new Intent(MainActivity.this, MyTeamActivity.class);
                        }
                        startActivity(intent6);
                        break;


                    default:
                        break;
                }
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }


    /**
     * 用于在销毁上一个活动返回时自我刷新
     */
    @Override
    public void onResume()
    {
        super.onResume();
        initHead();
    }


    /**
     * 获取menu的注入器(Inflater)并将我们配置的toolbar文件加载到menu中
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }


    /**
     * toolbar_menu各功能
     * @param item
     * @return
     */
    @SuppressLint("NonConstantResourceId")
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.scan:
//                Toast.makeText(this, "你选择了扫一扫", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(MainActivity.this, ScanActivity.class);
                startActivity(intent1);
                break;

            case R.id.helps:
//                Toast.makeText(this, "你选择了帮助与反馈", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(MainActivity.this, FeedbackActivity.class);
                startActivity(intent2);
                break;

            case R.id.takeList:
//                Toast.makeText(this, "你选择了悬赏大全", Toast.LENGTH_SHORT).show();
                Intent intent3 = new Intent(MainActivity.this, TotalTakeActivity.class);
                startActivity(intent3);
                break;

            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;

            default:
        }
        return true;
    }


    /**
     * 信息面板点击效果
     */
    public void head(View view)
    {
         Intent intent = new Intent(MainActivity.this, InfoActivity.class);
         startActivity(intent);
    }


    /**
     * 初始化头部面板
     */
    public void initHead()
    {
        MyUser myUser = MyUser.getCurrentUser(MyUser.class);
        if (myUser.getImageId() != null)
        {
            switch (myUser.getImageId()) {
                case 0:
                    circleImageView.setImageResource(R.mipmap.ic_common2);
                    break;

                case 1:
                    circleImageView.setImageResource(R.mipmap.ic_boy);
                    break;

                case 2:
                    circleImageView.setImageResource(R.mipmap.ic_girl);
                    break;

                default:
                    circleImageView.setImageResource(R.mipmap.ic_dog);
                    break;
            }
        }else {
            circleImageView.setImageResource(R.mipmap.ic_dog);
        }
        nav_username.setText(myUser.getUsername());
        nav_email.setText(myUser.getEmail());
    }


    //导航栏初始化
    private void initView() {
        mNavGroup = (RadioGroup) findViewById(R.id.id_nav_content);
        syFragment = MainFragment.getNewInstance();
        cxFragment = FindFragment.getNewInstance();
        myFragment = MyFragment.getNewInstance();

        //显示
        mTransaction = getSupportFragmentManager().beginTransaction();
        mTransaction.replace(R.id.fragment_content, syFragment);
        mTransaction.commit();
    }


    //导航栏选择
    @SuppressLint("NonConstantResourceId")
    public void switchView(View view) {
        switch (view.getId()) {
            case R.id.id_nav_bt_shouye:
                if (temp_position_index != VIEW_MAIN_INDEX) {
                    //显示
                    mTransaction = getSupportFragmentManager().beginTransaction();
                    mTransaction.replace(R.id.fragment_content, syFragment);
                    mTransaction.commit();
                }
                temp_position_index = VIEW_MAIN_INDEX;
                break;

            case R.id.id_nav_bt_chaxun:
                if (temp_position_index != VIEW_FIND_INDEX) {
                    //显示
                    mTransaction = getSupportFragmentManager().beginTransaction();
                    mTransaction.replace(R.id.fragment_content, cxFragment);
                    mTransaction.commit();
                }
                temp_position_index = VIEW_FIND_INDEX;
                break;

            case R.id.id_nav_bt_wode:
                if (temp_position_index != VIEW_ME_INDEX) {
                    //显示
                    mTransaction = getSupportFragmentManager().beginTransaction();
                    mTransaction.replace(R.id.fragment_content, myFragment);
                    mTransaction.commit();
                }
                temp_position_index = VIEW_ME_INDEX;
                break;

            default:
                break;
        }
    }


}