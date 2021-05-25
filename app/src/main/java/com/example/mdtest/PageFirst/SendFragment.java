package com.example.mdtest.PageFirst;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;

import com.example.mdtest.R;
import com.example.mdtest.Setting.TakeSendActivity;

public class SendFragment extends Fragment {
    private View rootView;
    private static SendFragment sFragment;
    private String str = null;

    private LinearLayout linearLayout;
    private EditText ed_selfName;

    private RadioGroup radioGroup;
    private RadioGroup radioGroup5;
    private RadioGroup radioGroup6;

    private RadioButton rbt_fruits;
    private RadioButton rbt_references;
    private RadioButton rbt_flowers;
    private RadioButton rbt_boxes;
    private RadioButton rbt_own;

    private Button btn_take;

    public SendFragment() {
    }

    public static SendFragment getNewInstance() {
        if (sFragment == null) {
            sFragment = new SendFragment();
        }
        return sFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_main_send, container, false);
            linearLayout = (LinearLayout) rootView.findViewById(R.id.invisible_send);
            ed_selfName = (EditText) rootView.findViewById(R.id.send_self_name);
            radioGroup = (RadioGroup) rootView.findViewById(R.id.btn_radio);
            radioGroup5 = (RadioGroup) rootView.findViewById(R.id.rdg_pick5);
            radioGroup6 = (RadioGroup) rootView.findViewById(R.id.rdg_pick6);
            rbt_fruits = (RadioButton) rootView.findViewById(R.id.send_fruits);
            rbt_references = (RadioButton) rootView.findViewById(R.id.send_references);
            rbt_flowers = (RadioButton) rootView.findViewById(R.id.send_flowers);
            rbt_boxes = (RadioButton) rootView.findViewById(R.id.send_boxes);
            rbt_own = (RadioButton) rootView.findViewById(R.id.send_own);
            btn_take = (Button) rootView.findViewById(R.id.btn_send);

            radioGroup5.setOnCheckedChangeListener(new group5ChangeListener());
            radioGroup6.setOnCheckedChangeListener(new group6ChangeListener());


        }
        // 缓存的rootView需要判断是否已经被加过parent，
        // 如果有parent需要从parent删除，要不然会发生这个rootView已经有parent的错误。
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }

        return rootView;
    }


    private class group5ChangeListener implements RadioGroup.OnCheckedChangeListener
    {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (rbt_fruits.isChecked() || rbt_references.isChecked() || rbt_flowers.isChecked())
            {
                radioGroup6.clearCheck();

                if (rbt_fruits.isChecked())
                {
                    str = "送水果";
                    linearLayout.setVisibility(View.INVISIBLE);
                    btn_take.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), TakeSendActivity.class);
                            intent.putExtra(TakeSendActivity.SEND_ID, str);
                            intent.putExtra(TakeSendActivity.TYPE_ID, "快送");
                            startActivity(intent);
                        }
                    });

                } else if(rbt_references.isChecked())
                {
                    str = "送资料";
                    linearLayout.setVisibility(View.INVISIBLE);
                    btn_take.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), TakeSendActivity.class);
                            intent.putExtra(TakeSendActivity.SEND_ID, str);
                            intent.putExtra(TakeSendActivity.TYPE_ID, "快送");
                            startActivity(intent);
                        }
                    });

                } else if (rbt_flowers.isChecked())
                {
                    str = "送鲜花";
                    linearLayout.setVisibility(View.INVISIBLE);
                    btn_take.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), TakeSendActivity.class);
                            intent.putExtra(TakeSendActivity.SEND_ID, str);
                            intent.putExtra(TakeSendActivity.TYPE_ID, "快送");
                            startActivity(intent);
                        }
                    });

                } else {
                    str = "未命名";
                    linearLayout.setVisibility(View.INVISIBLE);
                    btn_take.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), TakeSendActivity.class);
                            intent.putExtra(TakeSendActivity.SEND_ID, str);
                            intent.putExtra(TakeSendActivity.TYPE_ID, "未命名");
                            startActivity(intent);
                        }
                    });

                }
            }

        }
    }

    private class group6ChangeListener implements RadioGroup.OnCheckedChangeListener
    {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (rbt_boxes.isChecked() || rbt_own.isChecked())
            {
                radioGroup5.clearCheck();

                if (rbt_boxes.isChecked())
                {
                    str = "送包裹";
                    linearLayout.setVisibility(View.INVISIBLE);
                    btn_take.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), TakeSendActivity.class);
                            intent.putExtra(TakeSendActivity.SEND_ID, str);
                            intent.putExtra(TakeSendActivity.TYPE_ID, "快送");
                            startActivity(intent);
                        }
                    });

                } else if (rbt_own.isChecked())
                {
                    linearLayout.setVisibility(View.VISIBLE);
                    btn_take.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            str = ed_selfName.getText().toString();
                            if (!str.equals(""))
                            {
                                Intent intent = new Intent(getActivity(), TakeSendActivity.class);
                                intent.putExtra(TakeSendActivity.SEND_ID, str);
                                intent.putExtra(TakeSendActivity.TYPE_ID, "自定义");
                                startActivity(intent);
                            }else {
                                str = "未命名";
                                Intent intent = new Intent(getActivity(), TakeSendActivity.class);
                                intent.putExtra(TakeSendActivity.SEND_ID, str);
                                intent.putExtra(TakeSendActivity.TYPE_ID, "未命名");
                                startActivity(intent);
                            }
                            ed_selfName.setText("");

                        }
                    });
                } else {
                    str = "未命名";
                    linearLayout.setVisibility(View.INVISIBLE);
                    btn_take.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), TakeSendActivity.class);
                            intent.putExtra(TakeSendActivity.SEND_ID, str);
                            intent.putExtra(TakeSendActivity.TYPE_ID, "未命名");
                            startActivity(intent);
                        }
                    });
                }
            }

        }
    }

}
