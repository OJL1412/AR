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
import com.example.mdtest.Setting.TakeBuyActivity;

public class BuyFragment extends Fragment {
    private View rootView;
    private static BuyFragment bFragment;
    private String str = null;

    private LinearLayout linearLayout;
    private EditText ed_selfName;

    private RadioGroup radioGroup;
    private RadioGroup radioGroup3;
    private RadioGroup radioGroup4;

    private RadioButton rbt_snacks;
    private RadioButton rbt_books;
    private RadioButton rbt_fruits;
    private RadioButton rbt_flowers;
    private RadioButton rbt_own;

    private Button btn_take;

    public BuyFragment() {
    }

    public static BuyFragment getNewInstance() {
        if (bFragment == null) {
            bFragment = new BuyFragment();
        }
        return bFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_main_buy, container, false);
            linearLayout = (LinearLayout) rootView.findViewById(R.id.invisible_buy);
            ed_selfName = (EditText) rootView.findViewById(R.id.buy_self_name);
            radioGroup = (RadioGroup) rootView.findViewById(R.id.btn_radio);
            radioGroup3 = (RadioGroup) rootView.findViewById(R.id.rdg_pick3);
            radioGroup4 = (RadioGroup) rootView.findViewById(R.id.rdg_pick4);
            rbt_snacks = (RadioButton) rootView.findViewById(R.id.buy_snacks);
            rbt_books = (RadioButton) rootView.findViewById(R.id.buy_books);
            rbt_fruits = (RadioButton) rootView.findViewById(R.id.buy_fruits);
            rbt_flowers = (RadioButton) rootView.findViewById(R.id.buy_flowers);
            rbt_own = (RadioButton) rootView.findViewById(R.id.buy_own);
            btn_take = (Button) rootView.findViewById(R.id.btn_buy);

            radioGroup3.setOnCheckedChangeListener(new group3ChangeListener());
            radioGroup4.setOnCheckedChangeListener(new group4ChangeListener());

        }
        // 缓存的rootView需要判断是否已经被加过parent，
        // 如果有parent需要从parent删除，要不然会发生这个rootView已经有parent的错误。
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }

        return rootView;
    }

    private class group3ChangeListener implements RadioGroup.OnCheckedChangeListener
    {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (rbt_snacks.isChecked() || rbt_books.isChecked() || rbt_fruits.isChecked())
            {
                radioGroup4.clearCheck();

                if (rbt_snacks.isChecked())
                {
                    str = "买零食";
                    linearLayout.setVisibility(View.INVISIBLE);
                    btn_take.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), TakeBuyActivity.class);
                            intent.putExtra(TakeBuyActivity.BUY_ID, str);
                            intent.putExtra(TakeBuyActivity.TYPE_ID, "快买");
                            startActivity(intent);
                        }
                    });

                } else if(rbt_books.isChecked())
                {
                    str = "买书本";
                    linearLayout.setVisibility(View.INVISIBLE);
                    btn_take.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), TakeBuyActivity.class);
                            intent.putExtra(TakeBuyActivity.BUY_ID, str);
                            intent.putExtra(TakeBuyActivity.TYPE_ID, "快买");
                            startActivity(intent);
                        }
                    });

                } else if (rbt_fruits.isChecked())
                {
                    str = "买水果";
                    linearLayout.setVisibility(View.INVISIBLE);
                    btn_take.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), TakeBuyActivity.class);
                            intent.putExtra(TakeBuyActivity.BUY_ID, str);
                            intent.putExtra(TakeBuyActivity.TYPE_ID, "快买");
                            startActivity(intent);
                        }
                    });

                } else {
                    str = "未命名";
                    linearLayout.setVisibility(View.INVISIBLE);
                    btn_take.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), TakeBuyActivity.class);
                            intent.putExtra(TakeBuyActivity.BUY_ID, str);
                            intent.putExtra(TakeBuyActivity.TYPE_ID, "未命名");
                            startActivity(intent);
                        }
                    });

                }
            }

        }
    }

    private class group4ChangeListener implements RadioGroup.OnCheckedChangeListener
    {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (rbt_flowers.isChecked() || rbt_own.isChecked())
            {
                radioGroup3.clearCheck();

                if (rbt_flowers.isChecked())
                {
                    str = "买鲜花";
                    linearLayout.setVisibility(View.INVISIBLE);
                    btn_take.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), TakeBuyActivity.class);
                            intent.putExtra(TakeBuyActivity.BUY_ID, str);
                            intent.putExtra(TakeBuyActivity.TYPE_ID, "快买");
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
                                Intent intent = new Intent(getActivity(), TakeBuyActivity.class);
                                intent.putExtra(TakeBuyActivity.BUY_ID, str);
                                intent.putExtra(TakeBuyActivity.TYPE_ID, "自定义");
                                startActivity(intent);
                            }else {
                                str = "未命名";
                                Intent intent = new Intent(getActivity(), TakeBuyActivity.class);
                                intent.putExtra(TakeBuyActivity.BUY_ID, str);
                                intent.putExtra(TakeBuyActivity.TYPE_ID, "未命名");
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
                            Intent intent = new Intent(getActivity(), TakeBuyActivity.class);
                            intent.putExtra(TakeBuyActivity.BUY_ID, str);
                            intent.putExtra(TakeBuyActivity.TYPE_ID, "未命名");
                            startActivity(intent);
                        }
                    });
                }
            }
        }
    }
}
