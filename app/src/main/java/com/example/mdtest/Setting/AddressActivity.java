package com.example.mdtest.Setting;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mdtest.Adapter.AddressAdapter;
import com.example.mdtest.Bean.MyAddress;
import com.example.mdtest.Bean.MyUser;
import com.example.mdtest.R;
import com.example.mdtest.Util.AddressUtils;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;

public class AddressActivity extends AppCompatActivity implements View.OnClickListener {

    private final List<MyAddress> myAddressList = new ArrayList<>();
    private RecyclerView recyclerView;
    private AddressAdapter addressAdapter;

    private TextView tv_add_address;
    private Button btn_keep;

    private Dialog mDialog;

    private final MyAddress[] addresses = new MyAddress[30];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        Bmob.resetDomain("https://open3.bmob.cn/8/");
        Bmob.initialize(this, "36d91b12a94a333fbd7281eb1265abb3");

        Toolbar toolbar = (Toolbar)findViewById(R.id.address_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        initView();
        initAddressList();
        add_address();

    }



    /**
     * 初始化view
     */
    private void initView() {
        recyclerView = findViewById(R.id.address_recycle_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);//参数：context，列数
        recyclerView.setLayoutManager(layoutManager);
        addressAdapter = new AddressAdapter(AddressActivity.this, myAddressList);
        recyclerView.setAdapter(addressAdapter);
    }


    /**
     * 从bomb数据库中获取数据，用以初始化地址列表
     */
    private void initAddressList() {
        MyUser myUser = MyUser.getCurrentUser(MyUser.class);

        AddressUtils.queryAddresses(myUser.getObjectId());

        AddressUtils.setIAddressListener(new AddressUtils.IAddress() {
            @Override
            public void getAddressList(List<MyAddress> list) {
                if (list.size() == 0) {
                    Toast.makeText(AddressActivity.this, "你还没有添加过任何常用地址哦~", Toast.LENGTH_SHORT).show();
                }else {
                    myAddressList.clear();

                    for (int i = 0; i < list.size(); i++) {
                        addresses[i] = list.get(i);
                        myAddressList.add(addresses[i]);
                    }

//                    Toast.makeText(TakeActivity.this, "查询成功：共" + list.size() + "条接单。", Toast.LENGTH_SHORT).show();
                    addressAdapter.notifyDataSetChanged();
                }
            }
        });

    }


    /**
     * "添加"按钮跳转
     */
    public void add_address() {
        tv_add_address.setOnClickListener(new View.OnClickListener() {
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
        mDialog = new Dialog(AddressActivity.this, R.style.BottomDialog);
        LinearLayout root = (LinearLayout) LayoutInflater.from(AddressActivity.this).inflate(
                R.layout.dialog_quit_log, null);

        TextView tv_province = root.findViewById(R.id.province);
        TextView tv_city = root.findViewById(R.id.city);
        TextView tv_region = root.findViewById(R.id.region);
        TextView tv_school = root.findViewById(R.id.school);
        TextView tv_building = root.findViewById(R.id.building);
        Button bt_keep = root.findViewById(R.id.btn_keep);

        //初始化视图
        root.findViewById(R.id.province).setOnClickListener(this);
        root.findViewById(R.id.city).setOnClickListener(this);
        root.findViewById(R.id.region).setOnClickListener(this);
        root.findViewById(R.id.school).setOnClickListener(this);
        root.findViewById(R.id.building).setOnClickListener(this);
        root.findViewById(R.id.btn_keep).setOnClickListener(this);
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

        tv_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //MyUser myUser = MyUser.getCurrentUser(MyUser.class);
                AlertDialog.Builder builder = new AlertDialog.Builder(AddressActivity.this);
                builder.setIcon(R.mipmap.ic_city);
                builder.setTitle("城市选择");
                final String[] city = {"海口市", "三亚市", "三沙市", "儋州市", "省直辖县级行政区划"};      //设置一个单项选择下拉框

//                if (myUser.getImageId() == null)
//                {
//                    myUser.setImageId(0);
//                }

                /**
                 * 第一个参数指定我们要显示的一组下拉单选框的数据集合
                 * 第二个参数代表索引，指定默认哪一个单选框被勾选上，0表示'默认头像' 会被勾选上
                 * 第三个参数给每一个单选项绑定一个监听器
                 */
                builder.setSingleChoiceItems(city, 0, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
//                        MyUser myUser = MyUser.getCurrentUser(MyUser.class);
                        switch (which) {
                            case 0:
//                                info_icon.setImageResource(R.mipmap.ic_common2);
//                                myUser.setImageId(0);
//                                myUser.update(myUser.getObjectId(), new UpdateListener() {
//                                    @Override
//                                    public void done(BmobException e) {
//                                        if(e==null){
//                                            Toast.makeText(InfoActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
//                                        }else{
//                                            Toast.makeText(InfoActivity.this, "修改失败"+e.getMessage(), Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//                                });
                                tv_city.setText("海口市");
                                break;

                            case 1:
//                                info_icon.setImageResource(R.mipmap.ic_boy);
//                                myUser.setImageId(1);
//                                myUser.update(myUser.getObjectId(), new UpdateListener() {
//                                    @Override
//                                    public void done(BmobException e) {
//                                        if(e==null){
//                                            Toast.makeText(InfoActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
//                                        }else{
//                                            Toast.makeText(InfoActivity.this, "修改失败"+e.getMessage(), Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//                                });
                                tv_city.setText("三亚市");
                                break;

                            case 2:
//                                info_icon.setImageResource(R.mipmap.ic_girl);
//                                myUser.setImageId(2);
//                                myUser.update(myUser.getObjectId(), new UpdateListener() {
//                                    @Override
//                                    public void done(BmobException e) {
//                                        if(e==null){
//                                            Toast.makeText(InfoActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
//                                        }else{
//                                            Toast.makeText(InfoActivity.this, "修改失败:"+e.getMessage(), Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//                                });
                                tv_city.setText("三沙市");
                                break;

                            case 3:
//                                info_icon.setImageResource(R.mipmap.ic_girl);
//                                myUser.setImageId(2);
//                                myUser.update(myUser.getObjectId(), new UpdateListener() {
//                                    @Override
//                                    public void done(BmobException e) {
//                                        if(e==null){
//                                            Toast.makeText(InfoActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
//                                        }else{
//                                            Toast.makeText(InfoActivity.this, "修改失败:"+e.getMessage(), Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//                                });
                                tv_city.setText("儋州市");
                                break;

                            case 4:
//                                info_icon.setImageResource(R.mipmap.ic_girl);
//                                myUser.setImageId(2);
//                                myUser.update(myUser.getObjectId(), new UpdateListener() {
//                                    @Override
//                                    public void done(BmobException e) {
//                                        if(e==null){
//                                            Toast.makeText(InfoActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
//                                        }else{
//                                            Toast.makeText(InfoActivity.this, "修改失败:"+e.getMessage(), Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//                                });
                                tv_city.setText("省直辖县级行政区划");
                                break;

                            default:
                                break;
                        }

                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {

                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
//                        MyUser myUser = MyUser.getCurrentUser(MyUser.class);
                    }
                });
                builder.show();
            }
        });



        tv_region.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //MyUser myUser = MyUser.getCurrentUser(MyUser.class);
                AlertDialog.Builder builder = new AlertDialog.Builder(AddressActivity.this);
                builder.setIcon(R.mipmap.ic_region);
                builder.setTitle("区域选择");
                final String[] region = {"市辖区", "秀英区", "龙华区", "琼山区", "美兰区"};      //设置一个单项选择下拉框

//                if (myUser.getImageId() == null)
//                {
//                    myUser.setImageId(0);
//                }

                /**
                 * 第一个参数指定我们要显示的一组下拉单选框的数据集合
                 * 第二个参数代表索引，指定默认哪一个单选框被勾选上，0表示'默认头像' 会被勾选上
                 * 第三个参数给每一个单选项绑定一个监听器
                 */
                builder.setSingleChoiceItems(region, 0, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
//                        MyUser myUser = MyUser.getCurrentUser(MyUser.class);
                        switch (which) {
                            case 0:
//                                info_icon.setImageResource(R.mipmap.ic_common2);
//                                myUser.setImageId(0);
//                                myUser.update(myUser.getObjectId(), new UpdateListener() {
//                                    @Override
//                                    public void done(BmobException e) {
//                                        if(e==null){
//                                            Toast.makeText(InfoActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
//                                        }else{
//                                            Toast.makeText(InfoActivity.this, "修改失败"+e.getMessage(), Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//                                });
                                tv_region.setText("市辖区");
                                break;

                            case 1:
//                                info_icon.setImageResource(R.mipmap.ic_boy);
//                                myUser.setImageId(1);
//                                myUser.update(myUser.getObjectId(), new UpdateListener() {
//                                    @Override
//                                    public void done(BmobException e) {
//                                        if(e==null){
//                                            Toast.makeText(InfoActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
//                                        }else{
//                                            Toast.makeText(InfoActivity.this, "修改失败"+e.getMessage(), Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//                                });
                                tv_region.setText("秀英区");
                                break;

                            case 2:
//                                info_icon.setImageResource(R.mipmap.ic_girl);
//                                myUser.setImageId(2);
//                                myUser.update(myUser.getObjectId(), new UpdateListener() {
//                                    @Override
//                                    public void done(BmobException e) {
//                                        if(e==null){
//                                            Toast.makeText(InfoActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
//                                        }else{
//                                            Toast.makeText(InfoActivity.this, "修改失败:"+e.getMessage(), Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//                                });
                                tv_region.setText("龙华区");
                                break;

                            case 3:
//                                info_icon.setImageResource(R.mipmap.ic_girl);
//                                myUser.setImageId(2);
//                                myUser.update(myUser.getObjectId(), new UpdateListener() {
//                                    @Override
//                                    public void done(BmobException e) {
//                                        if(e==null){
//                                            Toast.makeText(InfoActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
//                                        }else{
//                                            Toast.makeText(InfoActivity.this, "修改失败:"+e.getMessage(), Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//                                });
                                tv_region.setText("琼山区");
                                break;

                            case 4:
//                                info_icon.setImageResource(R.mipmap.ic_girl);
//                                myUser.setImageId(2);
//                                myUser.update(myUser.getObjectId(), new UpdateListener() {
//                                    @Override
//                                    public void done(BmobException e) {
//                                        if(e==null){
//                                            Toast.makeText(InfoActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
//                                        }else{
//                                            Toast.makeText(InfoActivity.this, "修改失败:"+e.getMessage(), Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//                                });
                                tv_region.setText("美兰区");
                                break;

                            default:
                                break;
                        }

                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {

                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
//                        MyUser myUser = MyUser.getCurrentUser(MyUser.class);
                    }
                });
                builder.show();
            }
        });


        tv_school.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //MyUser myUser = MyUser.getCurrentUser(MyUser.class);
                AlertDialog.Builder builder = new AlertDialog.Builder(AddressActivity.this);
                builder.setIcon(R.mipmap.ic_location);
                builder.setTitle("站点选择");
                final String[] school = {"海南师范大学桂林洋校区", "海口经济学院", "海南大学海淀校区", "海南经贸职业技术学院", "海南科技职业大学中心校区",
                        "琼台师范学院桂林洋校区"};      //设置一个单项选择下拉框

//                if (myUser.getImageId() == null)
//                {
//                    myUser.setImageId(0);
//                }

                /**
                 * 第一个参数指定我们要显示的一组下拉单选框的数据集合
                 * 第二个参数代表索引，指定默认哪一个单选框被勾选上，0表示'默认头像' 会被勾选上
                 * 第三个参数给每一个单选项绑定一个监听器
                 */
                builder.setSingleChoiceItems(school, 0, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
//                        MyUser myUser = MyUser.getCurrentUser(MyUser.class);
                        switch (which) {
                            case 0:
//                                info_icon.setImageResource(R.mipmap.ic_common2);
//                                myUser.setImageId(0);
//                                myUser.update(myUser.getObjectId(), new UpdateListener() {
//                                    @Override
//                                    public void done(BmobException e) {
//                                        if(e==null){
//                                            Toast.makeText(InfoActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
//                                        }else{
//                                            Toast.makeText(InfoActivity.this, "修改失败"+e.getMessage(), Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//                                });
                                tv_school.setText("海南师范大学桂林洋校区");
                                break;

                            case 1:
//                                info_icon.setImageResource(R.mipmap.ic_boy);
//                                myUser.setImageId(1);
//                                myUser.update(myUser.getObjectId(), new UpdateListener() {
//                                    @Override
//                                    public void done(BmobException e) {
//                                        if(e==null){
//                                            Toast.makeText(InfoActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
//                                        }else{
//                                            Toast.makeText(InfoActivity.this, "修改失败"+e.getMessage(), Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//                                });
                                tv_school.setText("海口经济学院");
                                break;

                            case 2:
//                                info_icon.setImageResource(R.mipmap.ic_girl);
//                                myUser.setImageId(2);
//                                myUser.update(myUser.getObjectId(), new UpdateListener() {
//                                    @Override
//                                    public void done(BmobException e) {
//                                        if(e==null){
//                                            Toast.makeText(InfoActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
//                                        }else{
//                                            Toast.makeText(InfoActivity.this, "修改失败:"+e.getMessage(), Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//                                });
                                tv_school.setText("海南大学海淀校区");
                                break;

                            case 3:
//                                info_icon.setImageResource(R.mipmap.ic_girl);
//                                myUser.setImageId(2);
//                                myUser.update(myUser.getObjectId(), new UpdateListener() {
//                                    @Override
//                                    public void done(BmobException e) {
//                                        if(e==null){
//                                            Toast.makeText(InfoActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
//                                        }else{
//                                            Toast.makeText(InfoActivity.this, "修改失败:"+e.getMessage(), Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//                                });
                                tv_school.setText("海南经贸职业技术学院");
                                break;

                            case 4:
//                                info_icon.setImageResource(R.mipmap.ic_girl);
//                                myUser.setImageId(2);
//                                myUser.update(myUser.getObjectId(), new UpdateListener() {
//                                    @Override
//                                    public void done(BmobException e) {
//                                        if(e==null){
//                                            Toast.makeText(InfoActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
//                                        }else{
//                                            Toast.makeText(InfoActivity.this, "修改失败:"+e.getMessage(), Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//                                });
                                tv_school.setText("海南科技职业大学中心校区");
                                break;

                            case 5:
//                                info_icon.setImageResource(R.mipmap.ic_girl);
//                                myUser.setImageId(2);
//                                myUser.update(myUser.getObjectId(), new UpdateListener() {
//                                    @Override
//                                    public void done(BmobException e) {
//                                        if(e==null){
//                                            Toast.makeText(InfoActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
//                                        }else{
//                                            Toast.makeText(InfoActivity.this, "修改失败:"+e.getMessage(), Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//                                });
                                tv_school.setText("琼台师范学院桂林洋校区");
                                break;

                            default:
                                break;
                        }

                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {

                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
//                        MyUser myUser = MyUser.getCurrentUser(MyUser.class);
                    }
                });
                builder.show();
            }
        });

        tv_building.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //MyUser myUser = MyUser.getCurrentUser(MyUser.class);
                AlertDialog.Builder builder = new AlertDialog.Builder(AddressActivity.this);
                builder.setIcon(R.mipmap.ic_building);
                builder.setTitle("楼房选择");
                final String[] building = {"教学楼区", "快递站点", "海师大宿舍区", "琼台师范宿舍区", "海经贸宿舍区"};      //设置一个单项选择下拉框

//                if (myUser.getImageId() == null)
//                {
//                    myUser.setImageId(0);
//                }

                /**
                 * 第一个参数指定我们要显示的一组下拉单选框的数据集合
                 * 第二个参数代表索引，指定默认哪一个单选框被勾选上，0表示'默认头像' 会被勾选上
                 * 第三个参数给每一个单选项绑定一个监听器
                 */
                builder.setSingleChoiceItems(building, 0, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
//                        MyUser myUser = MyUser.getCurrentUser(MyUser.class);
                        switch (which) {
                            case 0:
//                                info_icon.setImageResource(R.mipmap.ic_common2);
//                                myUser.setImageId(0);
//                                myUser.update(myUser.getObjectId(), new UpdateListener() {
//                                    @Override
//                                    public void done(BmobException e) {
//                                        if(e==null){
//                                            Toast.makeText(InfoActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
//                                        }else{
//                                            Toast.makeText(InfoActivity.this, "修改失败"+e.getMessage(), Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//                                });
                                tv_building.setText("教学楼区");
                                break;

                            case 1:
//                                info_icon.setImageResource(R.mipmap.ic_boy);
//                                myUser.setImageId(1);
//                                myUser.update(myUser.getObjectId(), new UpdateListener() {
//                                    @Override
//                                    public void done(BmobException e) {
//                                        if(e==null){
//                                            Toast.makeText(InfoActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
//                                        }else{
//                                            Toast.makeText(InfoActivity.this, "修改失败"+e.getMessage(), Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//                                });
                                tv_building.setText("快递站点");
                                break;

                            case 2:
//                                info_icon.setImageResource(R.mipmap.ic_girl);
//                                myUser.setImageId(2);
//                                myUser.update(myUser.getObjectId(), new UpdateListener() {
//                                    @Override
//                                    public void done(BmobException e) {
//                                        if(e==null){
//                                            Toast.makeText(InfoActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
//                                        }else{
//                                            Toast.makeText(InfoActivity.this, "修改失败:"+e.getMessage(), Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//                                });
                                tv_building.setText("海师大宿舍区");
                                break;

                            case 3:
//                                info_icon.setImageResource(R.mipmap.ic_girl);
//                                myUser.setImageId(2);
//                                myUser.update(myUser.getObjectId(), new UpdateListener() {
//                                    @Override
//                                    public void done(BmobException e) {
//                                        if(e==null){
//                                            Toast.makeText(InfoActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
//                                        }else{
//                                            Toast.makeText(InfoActivity.this, "修改失败:"+e.getMessage(), Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//                                });
                                tv_building.setText("琼台师范宿舍区");
                                break;

                            case 4:
//                                info_icon.setImageResource(R.mipmap.ic_girl);
//                                myUser.setImageId(2);
//                                myUser.update(myUser.getObjectId(), new UpdateListener() {
//                                    @Override
//                                    public void done(BmobException e) {
//                                        if(e==null){
//                                            Toast.makeText(InfoActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
//                                        }else{
//                                            Toast.makeText(InfoActivity.this, "修改失败:"+e.getMessage(), Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//                                });
                                tv_building.setText("海经贸宿舍区");
                                break;

                            default:
                                break;
                        }

                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {

                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
//                        MyUser myUser = MyUser.getCurrentUser(MyUser.class);
                    }
                });
                builder.show();
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

    @Override
    public void onClick(View v) {

    }
}