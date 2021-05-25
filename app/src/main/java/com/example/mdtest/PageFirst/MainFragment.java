package com.example.mdtest.PageFirst;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.mdtest.R;

public class MainFragment extends Fragment {
    private View rootView;      //缓存Fragment view
    private static MainFragment mainFragment;

    private RadioGroup radioGroup;
    private RadioButton radioButton_pick, radioButton_buy, radioButton_send;
    private FragmentTransaction fragmentTransaction;

    private Fragment pFragment, bFragment, sFragment;
    public static final int VIEW_PICK_INDEX = 0;
    public static final int VIEW_BUY_INDEX = 1;
    public static final int VIEW_SEND_INDEX = 2;
    private int temp_position_index = -1;

    public MainFragment() {
    }

    public static MainFragment getNewInstance() {
        if (mainFragment == null) {
            mainFragment = new MainFragment();
        }
        return mainFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_main, container, false);
            radioGroup = (RadioGroup) rootView.findViewById(R.id.btn_radio);
            radioButton_pick = (RadioButton) rootView.findViewById(R.id.rbt_pick);
            radioButton_buy = (RadioButton) rootView.findViewById(R.id.rbt_buy);
            radioButton_send = (RadioButton) rootView.findViewById(R.id.rbt_send);

            initView();
            select();
            //switchView1(rootView);

            Toolbar toolbar = rootView.findViewById(R.id.main_toolbar);
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
            ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
            if(actionBar != null){
                actionBar.setHomeAsUpIndicator(R.mipmap.ic_main_user);
                actionBar.setDisplayHomeAsUpEnabled(true);

            }

        }
        // 缓存的rootView需要判断是否已经被加过parent，
        // 如果有parent需要从parent删除，要不然会发生这个rootView已经有parent的错误。
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }

        return rootView;
    }

    private void initView() {
        pFragment = PickFragment.getNewInstance();
        bFragment = BuyFragment.getNewInstance();
        sFragment = SendFragment.getNewInstance();

        //显示
        fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.btn_fragment_content, pFragment);
        fragmentTransaction.commit();
    }


    public void select() {
        radioButton_pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (temp_position_index != VIEW_PICK_INDEX) {
                    //显示
                    fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.btn_fragment_content, pFragment);
                    fragmentTransaction.commit();
                }
                temp_position_index = VIEW_PICK_INDEX;
            }
        });


        radioButton_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (temp_position_index != VIEW_BUY_INDEX) {
                    //显示
                    fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.btn_fragment_content, bFragment);
                    fragmentTransaction.commit();
                }
                temp_position_index = VIEW_BUY_INDEX;
            }
        });

        radioButton_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (temp_position_index != VIEW_SEND_INDEX) {
                    //显示
                    fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.btn_fragment_content, sFragment);
                    fragmentTransaction.commit();
                }
                temp_position_index = VIEW_SEND_INDEX;
            }
        });
    }
}


