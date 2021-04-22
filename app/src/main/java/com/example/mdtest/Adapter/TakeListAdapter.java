package com.example.mdtest.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mdtest.Bean.MyOrder;
import com.example.mdtest.CardInit.TakeListActivity;
import com.example.mdtest.R;

import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class TakeListAdapter extends RecyclerView.Adapter<TakeListAdapter.ViewHolder> {

    private final List<MyOrder> mTakeList;
    private final Context context;

    public TakeListAdapter(Context context, List<MyOrder> mTakeList) {
        this.context = context;
        this.mTakeList = mTakeList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_take,
                parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.company.setText(mTakeList.get(position).getCompany());
        holder.address.setText(mTakeList.get(position).getAddress());
        holder.offer.setText(mTakeList.get(position).getOffer());
        holder.tv_inform.setText("+"+mTakeList.get(position).getRush()+"次");

        //得到集合中的状态值
        String i = mTakeList.get(position).getStatus();
        //进行判断 并赋值
        switch (i) {
            case "未支付":
                holder.status.setText("未支付");
                holder.tvTakeChoice.setText("取消接单");
                holder.takeOffer.setText("0");
                break;
            case "已支付":
                holder.status.setText("已支付");
                holder.tvTakeChoice.setText("删除接单");
                holder.takeOffer.setText(mTakeList.get(position).getOffer());
                break;

            case "已接单":
                holder.status.setText("接单进行中");
                holder.tvTakeChoice.setText("取消接单");
                holder.takeOffer.setText("0");
                break;

            default:
                holder.status.setText("支付异常！");
                holder.tvTakeChoice.setText("取消接单");
                holder.takeOffer.setText("@#￥*#￥");
                break;
        }


        /**
         * 点击卡片查看接单详细信息
         */
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                MyOrder orders = mTakeList.get(position);

                Intent intent = new Intent(context, TakeListActivity.class);
                intent.putExtra(TakeListActivity.TAKE_ID, orders.getObjectId());
                intent.putExtra(TakeListActivity.TAKE_IMAGE_ID, orders.getTag());
                context.startActivity(intent);
            }
        });



        if (holder.tvTakeChoice.getText().toString().equals("取消接单"))
        {
            /*
             * "取消接单"按钮功能
             */
            holder.tvTakeChoice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = holder.getAdapterPosition();
                    MyOrder orders = mTakeList.get(position);
                    orders.setStatus("未接单");
                    orders.setArId("");
                    orders.update(orders.getObjectId(), new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if(e==null){
                                Toast.makeText(context, "取消接单成功,请刷新界面", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(context, "取消接单失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            });
        }else if (holder.tvTakeChoice.getText().toString().equals("删除接单"))
        {
            /*
             * "删除接单"按钮功能
             */
            holder.tvTakeChoice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getAdapterPosition();
                    MyOrder orders = mTakeList.get(position);
                    orders.delete(new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e == null) {
                                Toast.makeText(context, "删除接单成功", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "删除接单失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mTakeList.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;

        public TextView company;
        public TextView address;
        public TextView offer;
        public TextView takeOffer;
        public TextView status;
        public TextView tv_inform;

        public TextView tvTakeChoice;
//        public TextView tvTakeDelete;

        public ViewHolder(View view) {
            super(view);

            cardView = (CardView) view;
            company = (TextView) view.findViewById(R.id.take_card_company);
            address = (TextView) view.findViewById(R.id.take_card_address);
            offer = (TextView) view.findViewById(R.id.take_card_offer1);
            takeOffer = (TextView) view.findViewById(R.id.take_card_offer2);
            status = (TextView) view.findViewById(R.id.take_card_status);
            tvTakeChoice = (TextView) view.findViewById(R.id.tv_take_choice);
            tv_inform = (TextView) view.findViewById(R.id.tv_inform);
//            tvTakeDelete = (TextView) view.findViewById(R.id.tv_take_delete);
        }

    }
}
