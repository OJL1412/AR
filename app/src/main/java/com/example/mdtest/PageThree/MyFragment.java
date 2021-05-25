package com.example.mdtest.PageThree;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.mdtest.Bean.MyUser;
import com.example.mdtest.DrawMenu.CreditActivity;
import com.example.mdtest.DrawMenu.InfoActivity;
import com.example.mdtest.DrawMenu.OrderActivity;
import com.example.mdtest.DrawMenu.TakeActivity;
import com.example.mdtest.Mine.FeedbackActivity;
import com.example.mdtest.R;
import com.example.mdtest.Setting.AddressActivity;
import com.example.mdtest.Setting.BusinessActivity;
import com.example.mdtest.Setting.CommandActivity;
import com.example.mdtest.Setting.HistoryActivity;
import com.example.mdtest.Setting.SetActivity;
import com.example.mdtest.Team.TeamActivity;

import cn.bmob.v3.Bmob;
import de.hdodenhof.circleimageview.CircleImageView;

public class MyFragment extends Fragment implements View.OnClickListener {
    // 缓存Fragment view
    private View rootView;
    private static MyFragment myFragment;
    //private Dialog mDialog;

    private RelativeLayout head;

    private CircleImageView circleImageView;
    private TextView tv_username;
    private TextView tv_setting;
    private TextView tv_command;
    private TextView tv_credit;
    private TextView tv_team;
    private TextView tv_cs;
    private TextView tv_my_info;
    private TextView tv_order;
    private TextView tv_take;
    private TextView tv_history;
    private TextView tv_address;
    private TextView tv_business;
    private TextView tv_problem;


    public MyFragment(){}


    public static MyFragment getNewInstance(){
        if (myFragment ==null){
            myFragment =new MyFragment();
        }
        return myFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Bmob.resetDomain("https://open3.bmob.cn/8/");
        Bmob.initialize(getActivity(), "36d91b12a94a333fbd7281eb1265abb3");

        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_my, container, false);
//            Toolbar toolbar = (Toolbar)rootView.findViewById(R.id.toolbar);
//            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

            //head = (RelativeLayout) rootView.findViewById(R.id.info_my_head);

            circleImageView = (CircleImageView)rootView.findViewById(R.id.my_icon_image);
            tv_username = (TextView)rootView.findViewById(R.id.username);
            tv_command = (TextView)rootView.findViewById(R.id.fragment_my_command);
            tv_setting = (TextView)rootView.findViewById(R.id.fragment_my_setting);
            tv_credit  =(TextView)rootView.findViewById(R.id.fragment_my_credit);
            tv_team = (TextView)rootView.findViewById(R.id.fragment_my_team);
            tv_cs = (TextView)rootView.findViewById(R.id.fragment_my_cs);
            tv_my_info = (TextView)rootView.findViewById(R.id.fragment_my_info);
            tv_order = (TextView)rootView.findViewById(R.id.fragment_my_order);
            tv_take = (TextView)rootView.findViewById(R.id.fragment_my_take);
            tv_history = (TextView)rootView.findViewById(R.id.fragment_my_history);
            tv_address = (TextView)rootView.findViewById(R.id.fragment_my_address);
            tv_business = (TextView)rootView.findViewById(R.id.fragment_my_business);
            tv_problem = (TextView)rootView.findViewById(R.id.fragment_my_problem);

            //head();
            initHead();
            command();
            setting();
            credit();
            team();
            cs();
            info();
            order();
            take();
            history();
            address();
            business();
            problem();

        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        return rootView;
    }


    @Override
    public void onResume() {
        super.onResume();
        initHead();
    }


//    /**
//     * "面板"跳转
//     */
//    public void head() {
//        head.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), InfoActivity.class);
//                startActivity(intent);
//            }
//        });
//    }
//
//
    public void initHead() {
        MyUser myUser = MyUser.getCurrentUser(MyUser.class);

        if (myUser.getImageId() != null)
        {
            switch (myUser.getImageId())
            {
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
        tv_username.setText(myUser.getUsername());
        //tv_email.setText(myUser.getEmail());
    }

    /**
     * "消息"按钮跳转
     */
    public void command() {
        tv_command.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CommandActivity.class);
                startActivity(intent);
            }
        });
    }


    /**
     * "设置"按钮跳转
     */
    public void setting() {
        tv_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SetActivity.class);
                startActivity(intent);
            }
        });
    }


    /**
     * "信誉"按钮跳转
     */
    public void credit() {
        tv_credit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CreditActivity.class);
                startActivity(intent);
            }
        });
    }


    /**
     * "团队"按钮跳转
     */
    public void team() {
        tv_team.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TeamActivity.class);
                startActivity(intent);
            }
        });
    }


    /**
     * "客服"按钮跳转
     */
    public void cs() {
        tv_cs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CustomerServiceActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * "个人信息"按钮跳转
     */
    public void info() {
        tv_my_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), InfoActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * "我的悬赏"按钮跳转
     */
    public void order() {
        tv_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), OrderActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * "我的接单"按钮跳转
     */
    public void take() {
        tv_take.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TakeActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * "历史记录"按钮跳转
     */
    public void history() {
        tv_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HistoryActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * "常用地址"按钮跳转
     */
    public void address() {
        tv_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddressActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * "加盟商家"按钮跳转
     */
    public void business() {
        tv_business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BusinessActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * "问题反馈"按钮跳转
     */
    public void problem() {
        tv_problem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FeedbackActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {

    }

//
//
//
//    /**
//     * "退出登录"按钮跳转
//     */
//    public void quit() {
//        tv_quit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                setDialog();
//            }
//        });
//    }
//
//
//    /**
//     * 创建"退出"弹窗
//     */
//    private void setDialog(){
//        mDialog = new Dialog(getActivity(), R.style.BottomDialog);
//        LinearLayout root = (LinearLayout) LayoutInflater.from(getContext()).inflate(
//                R.layout.dialog_quit_log, null);
//
//        RadioButton bt_quit_log = root.findViewById(R.id.bt_quit_log);
//        RadioButton bt_off_app = root.findViewById(R.id.bt_off_app);
//
//        //初始化视图
//        root.findViewById(R.id.bt_quit_log).setOnClickListener(this);
//        root.findViewById(R.id.bt_off_app).setOnClickListener(this);
//        root.findViewById(R.id.bt_dismiss).setOnClickListener(this);
//        mDialog.setContentView(root);
//
//        Window dialogWindow = mDialog.getWindow();
//        dialogWindow.setGravity(Gravity.BOTTOM);
////        dialogWindow.setWindowAnimations(R.style.dialogstyle); // 添加动画
//
//        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
//        lp.x = 0; // 新位置X坐标
//        lp.y = 0; // 新位置Y坐标
//        lp.width = (int) getResources().getDisplayMetrics().widthPixels; // 宽度
//        root.measure(0, 0);
//        lp.height = root.getMeasuredHeight();
//        lp.alpha = 9f; // 透明度
//
//        dialogWindow.setAttributes(lp);
//        mDialog.show();
//
//        RadioGroup radioGroup = (RadioGroup)root.findViewById(R.id.modify);
//
//        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                switch (group.getCheckedRadioButtonId()) {
//                    case R.id.bt_quit_log:
//
//                        mDialog.dismiss();
//
//                        bt_quit_log.setOnClickListener(new View.OnClickListener()
//                        {
//                            @Override
//                            public void onClick(View v)
//                            {
//                                //    通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
//                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//
//                                //    设置Content来显示一个信息
//                                builder.setMessage("退出后不会删除任何历史记录，下次登录依然可以使用本账号。");
//                                //    设置Title的图标
////                                builder.setIcon(R.drawable.ic_launcher);
//                                //    设置Title的内容
////                                builder.setTitle("弹出警告框");
//
//
//                                //    设置一个NegativeButton用来取消
//                                builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
//                                {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which)
//                                    {
////                                        mDialog.dismiss();
//                                    }
//                                });
//                                //    设置一个PositiveButton用来确定
//                                builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
//                                {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which)
//                                    {
//                                        BmobUser.logOut();
//                                        Toast.makeText(getActivity(), "退出登录成功", Toast.LENGTH_SHORT).show();
//
//                                        Intent intent2 = new Intent(Intent.ACTION_MAIN);
//                                        intent2.addCategory(Intent.CATEGORY_HOME);
//                                        intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                                        startActivity(intent2);
//                                        System.exit(0);
//
////                                        Intent intent = new Intent(getActivity(), LogActivity.class);
////                                        startActivity(intent);
////                                        getActivity().finish();
//                                    }
//                                });
//                                builder.show();     //显示出该对话框
//                            }
//                        });
//                        break;
//
//                    case R.id.bt_off_app:
//
//                        mDialog.dismiss();
//
//                        bt_off_app.setOnClickListener(new View.OnClickListener()
//                        {
//                            @Override
//                            public void onClick(View v)
//                            {
//                                //    通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
//                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//
//                                //    设置Content来显示一个信息
//                                builder.setMessage("关闭后，你将不能及时收到订单接收的消息，可能会影响到你的体验。");
//                                //    设置Title的图标
////                                builder.setIcon(R.drawable.ic_launcher);
//                                //    设置Title的内容
////                                builder.setTitle("警告");
//
//
//                                //    设置一个NegativeButton用来取消
//                                builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
//                                {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which)
//                                    {
//
//                                    }
//                                });
//                                //    设置一个PositiveButton用来确定
//                                builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
//                                {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which)
//                                    {
//                                        Intent intent1 = new Intent(Intent.ACTION_MAIN);
//                                        intent1.addCategory(Intent.CATEGORY_HOME);
//                                        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                                        startActivity(intent1);
//                                        System.exit(0);
//                                    }
//                                });
//                                builder.show();     //显示出该对话框
//                            }
//                        });
//
//                        break;
//
//                    case R.id.bt_dismiss:
//                        mDialog.dismiss();
//                        break;
//
//                    default:
//                        break;
//
//                }
//            }
//        });
//    }
//
//    public void onClick(View v) {
//
//    }

}
