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
     * ?????????view
     */
    private void initView() {
        recyclerView = findViewById(R.id.address_recycle_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);//?????????context?????????
        recyclerView.setLayoutManager(layoutManager);
        addressAdapter = new AddressAdapter(AddressActivity.this, myAddressList);
        recyclerView.setAdapter(addressAdapter);
    }


    /**
     * ???bomb??????????????????????????????????????????????????????
     */
    private void initAddressList() {
        MyUser myUser = MyUser.getCurrentUser(MyUser.class);

        AddressUtils.queryAddresses(myUser.getObjectId());

        AddressUtils.setIAddressListener(new AddressUtils.IAddress() {
            @Override
            public void getAddressList(List<MyAddress> list) {
                if (list.size() == 0) {
                    Toast.makeText(AddressActivity.this, "??????????????????????????????????????????~", Toast.LENGTH_SHORT).show();
                }else {
                    myAddressList.clear();

                    for (int i = 0; i < list.size(); i++) {
                        addresses[i] = list.get(i);
                        myAddressList.add(addresses[i]);
                    }

//                    Toast.makeText(TakeActivity.this, "??????????????????" + list.size() + "????????????", Toast.LENGTH_SHORT).show();
                    addressAdapter.notifyDataSetChanged();
                }
            }
        });

    }


    /**
     * "??????"????????????
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
     * ??????"??????"??????
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

        //???????????????
        root.findViewById(R.id.province).setOnClickListener(this);
        root.findViewById(R.id.city).setOnClickListener(this);
        root.findViewById(R.id.region).setOnClickListener(this);
        root.findViewById(R.id.school).setOnClickListener(this);
        root.findViewById(R.id.building).setOnClickListener(this);
        root.findViewById(R.id.btn_keep).setOnClickListener(this);
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

        tv_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //MyUser myUser = MyUser.getCurrentUser(MyUser.class);
                AlertDialog.Builder builder = new AlertDialog.Builder(AddressActivity.this);
                builder.setIcon(R.mipmap.ic_city);
                builder.setTitle("????????????");
                final String[] city = {"?????????", "?????????", "?????????", "?????????", "???????????????????????????"};      //?????????????????????????????????

//                if (myUser.getImageId() == null)
//                {
//                    myUser.setImageId(0);
//                }

                /**
                 * ???????????????????????????????????????????????????????????????????????????
                 * ???????????????????????????????????????????????????????????????????????????0??????'????????????' ???????????????
                 * ?????????????????????????????????????????????????????????
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
//                                            Toast.makeText(InfoActivity.this, "????????????", Toast.LENGTH_SHORT).show();
//                                        }else{
//                                            Toast.makeText(InfoActivity.this, "????????????"+e.getMessage(), Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//                                });
                                tv_city.setText("?????????");
                                break;

                            case 1:
//                                info_icon.setImageResource(R.mipmap.ic_boy);
//                                myUser.setImageId(1);
//                                myUser.update(myUser.getObjectId(), new UpdateListener() {
//                                    @Override
//                                    public void done(BmobException e) {
//                                        if(e==null){
//                                            Toast.makeText(InfoActivity.this, "????????????", Toast.LENGTH_SHORT).show();
//                                        }else{
//                                            Toast.makeText(InfoActivity.this, "????????????"+e.getMessage(), Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//                                });
                                tv_city.setText("?????????");
                                break;

                            case 2:
//                                info_icon.setImageResource(R.mipmap.ic_girl);
//                                myUser.setImageId(2);
//                                myUser.update(myUser.getObjectId(), new UpdateListener() {
//                                    @Override
//                                    public void done(BmobException e) {
//                                        if(e==null){
//                                            Toast.makeText(InfoActivity.this, "????????????", Toast.LENGTH_SHORT).show();
//                                        }else{
//                                            Toast.makeText(InfoActivity.this, "????????????:"+e.getMessage(), Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//                                });
                                tv_city.setText("?????????");
                                break;

                            case 3:
//                                info_icon.setImageResource(R.mipmap.ic_girl);
//                                myUser.setImageId(2);
//                                myUser.update(myUser.getObjectId(), new UpdateListener() {
//                                    @Override
//                                    public void done(BmobException e) {
//                                        if(e==null){
//                                            Toast.makeText(InfoActivity.this, "????????????", Toast.LENGTH_SHORT).show();
//                                        }else{
//                                            Toast.makeText(InfoActivity.this, "????????????:"+e.getMessage(), Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//                                });
                                tv_city.setText("?????????");
                                break;

                            case 4:
//                                info_icon.setImageResource(R.mipmap.ic_girl);
//                                myUser.setImageId(2);
//                                myUser.update(myUser.getObjectId(), new UpdateListener() {
//                                    @Override
//                                    public void done(BmobException e) {
//                                        if(e==null){
//                                            Toast.makeText(InfoActivity.this, "????????????", Toast.LENGTH_SHORT).show();
//                                        }else{
//                                            Toast.makeText(InfoActivity.this, "????????????:"+e.getMessage(), Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//                                });
                                tv_city.setText("???????????????????????????");
                                break;

                            default:
                                break;
                        }

                    }
                });
                builder.setPositiveButton("??????", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {

                    }
                });
                builder.setNegativeButton("??????", new DialogInterface.OnClickListener()
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
                builder.setTitle("????????????");
                final String[] region = {"?????????", "?????????", "?????????", "?????????", "?????????"};      //?????????????????????????????????

//                if (myUser.getImageId() == null)
//                {
//                    myUser.setImageId(0);
//                }

                /**
                 * ???????????????????????????????????????????????????????????????????????????
                 * ???????????????????????????????????????????????????????????????????????????0??????'????????????' ???????????????
                 * ?????????????????????????????????????????????????????????
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
//                                            Toast.makeText(InfoActivity.this, "????????????", Toast.LENGTH_SHORT).show();
//                                        }else{
//                                            Toast.makeText(InfoActivity.this, "????????????"+e.getMessage(), Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//                                });
                                tv_region.setText("?????????");
                                break;

                            case 1:
//                                info_icon.setImageResource(R.mipmap.ic_boy);
//                                myUser.setImageId(1);
//                                myUser.update(myUser.getObjectId(), new UpdateListener() {
//                                    @Override
//                                    public void done(BmobException e) {
//                                        if(e==null){
//                                            Toast.makeText(InfoActivity.this, "????????????", Toast.LENGTH_SHORT).show();
//                                        }else{
//                                            Toast.makeText(InfoActivity.this, "????????????"+e.getMessage(), Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//                                });
                                tv_region.setText("?????????");
                                break;

                            case 2:
//                                info_icon.setImageResource(R.mipmap.ic_girl);
//                                myUser.setImageId(2);
//                                myUser.update(myUser.getObjectId(), new UpdateListener() {
//                                    @Override
//                                    public void done(BmobException e) {
//                                        if(e==null){
//                                            Toast.makeText(InfoActivity.this, "????????????", Toast.LENGTH_SHORT).show();
//                                        }else{
//                                            Toast.makeText(InfoActivity.this, "????????????:"+e.getMessage(), Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//                                });
                                tv_region.setText("?????????");
                                break;

                            case 3:
//                                info_icon.setImageResource(R.mipmap.ic_girl);
//                                myUser.setImageId(2);
//                                myUser.update(myUser.getObjectId(), new UpdateListener() {
//                                    @Override
//                                    public void done(BmobException e) {
//                                        if(e==null){
//                                            Toast.makeText(InfoActivity.this, "????????????", Toast.LENGTH_SHORT).show();
//                                        }else{
//                                            Toast.makeText(InfoActivity.this, "????????????:"+e.getMessage(), Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//                                });
                                tv_region.setText("?????????");
                                break;

                            case 4:
//                                info_icon.setImageResource(R.mipmap.ic_girl);
//                                myUser.setImageId(2);
//                                myUser.update(myUser.getObjectId(), new UpdateListener() {
//                                    @Override
//                                    public void done(BmobException e) {
//                                        if(e==null){
//                                            Toast.makeText(InfoActivity.this, "????????????", Toast.LENGTH_SHORT).show();
//                                        }else{
//                                            Toast.makeText(InfoActivity.this, "????????????:"+e.getMessage(), Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//                                });
                                tv_region.setText("?????????");
                                break;

                            default:
                                break;
                        }

                    }
                });
                builder.setPositiveButton("??????", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {

                    }
                });
                builder.setNegativeButton("??????", new DialogInterface.OnClickListener()
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
                builder.setTitle("????????????");
                final String[] school = {"?????????????????????????????????", "??????????????????", "????????????????????????", "??????????????????????????????", "????????????????????????????????????",
                        "?????????????????????????????????"};      //?????????????????????????????????

//                if (myUser.getImageId() == null)
//                {
//                    myUser.setImageId(0);
//                }

                /**
                 * ???????????????????????????????????????????????????????????????????????????
                 * ???????????????????????????????????????????????????????????????????????????0??????'????????????' ???????????????
                 * ?????????????????????????????????????????????????????????
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
//                                            Toast.makeText(InfoActivity.this, "????????????", Toast.LENGTH_SHORT).show();
//                                        }else{
//                                            Toast.makeText(InfoActivity.this, "????????????"+e.getMessage(), Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//                                });
                                tv_school.setText("?????????????????????????????????");
                                break;

                            case 1:
//                                info_icon.setImageResource(R.mipmap.ic_boy);
//                                myUser.setImageId(1);
//                                myUser.update(myUser.getObjectId(), new UpdateListener() {
//                                    @Override
//                                    public void done(BmobException e) {
//                                        if(e==null){
//                                            Toast.makeText(InfoActivity.this, "????????????", Toast.LENGTH_SHORT).show();
//                                        }else{
//                                            Toast.makeText(InfoActivity.this, "????????????"+e.getMessage(), Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//                                });
                                tv_school.setText("??????????????????");
                                break;

                            case 2:
//                                info_icon.setImageResource(R.mipmap.ic_girl);
//                                myUser.setImageId(2);
//                                myUser.update(myUser.getObjectId(), new UpdateListener() {
//                                    @Override
//                                    public void done(BmobException e) {
//                                        if(e==null){
//                                            Toast.makeText(InfoActivity.this, "????????????", Toast.LENGTH_SHORT).show();
//                                        }else{
//                                            Toast.makeText(InfoActivity.this, "????????????:"+e.getMessage(), Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//                                });
                                tv_school.setText("????????????????????????");
                                break;

                            case 3:
//                                info_icon.setImageResource(R.mipmap.ic_girl);
//                                myUser.setImageId(2);
//                                myUser.update(myUser.getObjectId(), new UpdateListener() {
//                                    @Override
//                                    public void done(BmobException e) {
//                                        if(e==null){
//                                            Toast.makeText(InfoActivity.this, "????????????", Toast.LENGTH_SHORT).show();
//                                        }else{
//                                            Toast.makeText(InfoActivity.this, "????????????:"+e.getMessage(), Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//                                });
                                tv_school.setText("??????????????????????????????");
                                break;

                            case 4:
//                                info_icon.setImageResource(R.mipmap.ic_girl);
//                                myUser.setImageId(2);
//                                myUser.update(myUser.getObjectId(), new UpdateListener() {
//                                    @Override
//                                    public void done(BmobException e) {
//                                        if(e==null){
//                                            Toast.makeText(InfoActivity.this, "????????????", Toast.LENGTH_SHORT).show();
//                                        }else{
//                                            Toast.makeText(InfoActivity.this, "????????????:"+e.getMessage(), Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//                                });
                                tv_school.setText("????????????????????????????????????");
                                break;

                            case 5:
//                                info_icon.setImageResource(R.mipmap.ic_girl);
//                                myUser.setImageId(2);
//                                myUser.update(myUser.getObjectId(), new UpdateListener() {
//                                    @Override
//                                    public void done(BmobException e) {
//                                        if(e==null){
//                                            Toast.makeText(InfoActivity.this, "????????????", Toast.LENGTH_SHORT).show();
//                                        }else{
//                                            Toast.makeText(InfoActivity.this, "????????????:"+e.getMessage(), Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//                                });
                                tv_school.setText("?????????????????????????????????");
                                break;

                            default:
                                break;
                        }

                    }
                });
                builder.setPositiveButton("??????", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {

                    }
                });
                builder.setNegativeButton("??????", new DialogInterface.OnClickListener()
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
                builder.setTitle("????????????");
                final String[] building = {"????????????", "????????????", "??????????????????", "?????????????????????", "??????????????????"};      //?????????????????????????????????

//                if (myUser.getImageId() == null)
//                {
//                    myUser.setImageId(0);
//                }

                /**
                 * ???????????????????????????????????????????????????????????????????????????
                 * ???????????????????????????????????????????????????????????????????????????0??????'????????????' ???????????????
                 * ?????????????????????????????????????????????????????????
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
//                                            Toast.makeText(InfoActivity.this, "????????????", Toast.LENGTH_SHORT).show();
//                                        }else{
//                                            Toast.makeText(InfoActivity.this, "????????????"+e.getMessage(), Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//                                });
                                tv_building.setText("????????????");
                                break;

                            case 1:
//                                info_icon.setImageResource(R.mipmap.ic_boy);
//                                myUser.setImageId(1);
//                                myUser.update(myUser.getObjectId(), new UpdateListener() {
//                                    @Override
//                                    public void done(BmobException e) {
//                                        if(e==null){
//                                            Toast.makeText(InfoActivity.this, "????????????", Toast.LENGTH_SHORT).show();
//                                        }else{
//                                            Toast.makeText(InfoActivity.this, "????????????"+e.getMessage(), Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//                                });
                                tv_building.setText("????????????");
                                break;

                            case 2:
//                                info_icon.setImageResource(R.mipmap.ic_girl);
//                                myUser.setImageId(2);
//                                myUser.update(myUser.getObjectId(), new UpdateListener() {
//                                    @Override
//                                    public void done(BmobException e) {
//                                        if(e==null){
//                                            Toast.makeText(InfoActivity.this, "????????????", Toast.LENGTH_SHORT).show();
//                                        }else{
//                                            Toast.makeText(InfoActivity.this, "????????????:"+e.getMessage(), Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//                                });
                                tv_building.setText("??????????????????");
                                break;

                            case 3:
//                                info_icon.setImageResource(R.mipmap.ic_girl);
//                                myUser.setImageId(2);
//                                myUser.update(myUser.getObjectId(), new UpdateListener() {
//                                    @Override
//                                    public void done(BmobException e) {
//                                        if(e==null){
//                                            Toast.makeText(InfoActivity.this, "????????????", Toast.LENGTH_SHORT).show();
//                                        }else{
//                                            Toast.makeText(InfoActivity.this, "????????????:"+e.getMessage(), Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//                                });
                                tv_building.setText("?????????????????????");
                                break;

                            case 4:
//                                info_icon.setImageResource(R.mipmap.ic_girl);
//                                myUser.setImageId(2);
//                                myUser.update(myUser.getObjectId(), new UpdateListener() {
//                                    @Override
//                                    public void done(BmobException e) {
//                                        if(e==null){
//                                            Toast.makeText(InfoActivity.this, "????????????", Toast.LENGTH_SHORT).show();
//                                        }else{
//                                            Toast.makeText(InfoActivity.this, "????????????:"+e.getMessage(), Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//                                });
                                tv_building.setText("??????????????????");
                                break;

                            default:
                                break;
                        }

                    }
                });
                builder.setPositiveButton("??????", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {

                    }
                });
                builder.setNegativeButton("??????", new DialogInterface.OnClickListener()
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

    @Override
    public void onClick(View v) {

    }
}