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
import com.example.mdtest.Setting.TakePickActivity;

public class PickFragment extends Fragment {
    private View rootView;
    private static PickFragment pFragment;
    private String str = null;

    private LinearLayout linearLayout;
    private EditText ed_selfName;

    private RadioGroup radioGroup;
    private RadioGroup radioGroup1;
    private RadioGroup radioGroup2;

    private RadioButton rbt_delivery;
    private RadioButton rbt_fruit;
    private RadioButton rbt_box;
    private RadioButton rbt_books;
    private RadioButton rbt_files;
    private RadioButton rbt_own;

    private Button btn_take;

    public PickFragment() {
    }

    public static PickFragment getNewInstance() {
        if (pFragment == null) {
            pFragment = new PickFragment();
        }
        return pFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_main_pick, container, false);
            linearLayout = (LinearLayout) rootView.findViewById(R.id.invisible_pick);
            ed_selfName = (EditText) rootView.findViewById(R.id.pick_self_name);
            radioGroup = (RadioGroup) rootView.findViewById(R.id.btn_radio);
            radioGroup1 = (RadioGroup) rootView.findViewById(R.id.rdg_pick1);
            radioGroup2 = (RadioGroup) rootView.findViewById(R.id.rdg_pick2);
            rbt_delivery = (RadioButton) rootView.findViewById(R.id.pick_delivery);
            rbt_fruit = (RadioButton) rootView.findViewById(R.id.pick_fruit);
            rbt_box = (RadioButton) rootView.findViewById(R.id.pick_box);
            rbt_books = (RadioButton) rootView.findViewById(R.id.pick_books);
            rbt_files = (RadioButton) rootView.findViewById(R.id.pick_files);
            rbt_own = (RadioButton) rootView.findViewById(R.id.pick_own);
            btn_take = (Button) rootView.findViewById(R.id.btn_pick);

            radioGroup1.setOnCheckedChangeListener(new group1ChangeListener());
            radioGroup2.setOnCheckedChangeListener(new group2ChangeListener());

            //submitTakeType();
            //submitTake();

        }
        // 缓存的rootView需要判断是否已经被加过parent，
        // 如果有parent需要从parent删除，要不然会发生这个rootView已经有parent的错误。
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }

        return rootView;
    }



    private class group1ChangeListener implements RadioGroup.OnCheckedChangeListener
    {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (rbt_delivery.isChecked() || rbt_fruit.isChecked() || rbt_box.isChecked())
            {
                radioGroup2.clearCheck();

                if (rbt_delivery.isChecked())
                {
                    str = "取快递";
                    linearLayout.setVisibility(View.INVISIBLE);
                    btn_take.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), TakePickActivity.class);
                            intent.putExtra(TakePickActivity.PICK_ID, str);
                            intent.putExtra(TakePickActivity.TYPE_ID, "快取");
                            startActivity(intent);
                        }
                    });

                } else if(rbt_fruit.isChecked())
                {
                    str = "取水果";
                    linearLayout.setVisibility(View.INVISIBLE);
                    btn_take.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), TakePickActivity.class);
                            intent.putExtra(TakePickActivity.PICK_ID, str);
                            intent.putExtra(TakePickActivity.TYPE_ID, "快取");
                            startActivity(intent);
                        }
                    });

                } else if (rbt_box.isChecked())
                {
                    str = "取包裹";
                    linearLayout.setVisibility(View.INVISIBLE);
                    btn_take.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), TakePickActivity.class);
                            intent.putExtra(TakePickActivity.PICK_ID, str);
                            intent.putExtra(TakePickActivity.TYPE_ID, "快取");
                            startActivity(intent);
                        }
                    });

                } else {
                    str = "未命名";
                    linearLayout.setVisibility(View.INVISIBLE);
                    btn_take.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), TakePickActivity.class);
                            intent.putExtra(TakePickActivity.PICK_ID, str);
                            intent.putExtra(TakePickActivity.TYPE_ID, "未命名");
                            startActivity(intent);
                        }
                    });

                }
            }

        }
    }

    private class group2ChangeListener implements RadioGroup.OnCheckedChangeListener
    {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (rbt_books.isChecked() || rbt_files.isChecked() || rbt_own.isChecked())
            {
                radioGroup1.clearCheck();

                if (rbt_books.isChecked())
                {
                    str = "取书本";
                    linearLayout.setVisibility(View.INVISIBLE);
                    btn_take.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), TakePickActivity.class);
                            intent.putExtra(TakePickActivity.PICK_ID, str);
                            intent.putExtra(TakePickActivity.TYPE_ID, "快取");
                            startActivity(intent);
                        }
                    });

                } else if (rbt_files.isChecked())
                {
                    str = "取文件";
                    linearLayout.setVisibility(View.INVISIBLE);
                    btn_take.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), TakePickActivity.class);
                            intent.putExtra(TakePickActivity.PICK_ID, str);
                            intent.putExtra(TakePickActivity.TYPE_ID, "快取");
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
                                Intent intent = new Intent(getActivity(), TakePickActivity.class);
                                intent.putExtra(TakePickActivity.PICK_ID, str);
                                intent.putExtra(TakePickActivity.TYPE_ID, "自定义");
                                startActivity(intent);
                            }else {
                                str = "未命名";
                                Intent intent = new Intent(getActivity(), TakePickActivity.class);
                                intent.putExtra(TakePickActivity.PICK_ID, str);
                                intent.putExtra(TakePickActivity.TYPE_ID, "未命名");
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
                            Intent intent = new Intent(getActivity(), TakePickActivity.class);
                            intent.putExtra(TakePickActivity.PICK_ID, str);
                            intent.putExtra(TakePickActivity.TYPE_ID, "未命名");
                            startActivity(intent);
                        }
                    });
                }
            }

        }
    }

//    public void submitTake()
//    {
//        btn_take.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), TakePickActivity.class);
//                intent.putExtra(TakePickActivity.PICK_ID, str);
//                startActivity(intent);
//            }
//        });
//    }


}
