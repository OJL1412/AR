package com.example.mdtest.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mdtest.Bean.MyOrder;
import com.example.mdtest.CardInit.OrderListActivity;
import com.example.mdtest.R;

import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.ViewHolder> {

    private final List<MyOrder> mOrderList;
    private final Context context;

    public OrderListAdapter(Context context, List<MyOrder> mOrderList) {
        this.context = context;
        this.mOrderList = mOrderList;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order,
                parent, false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.company.setText(String.valueOf(mOrderList.get(position).getCompany()));
        holder.offer.setText(String.valueOf(mOrderList.get(position).getOffer()));
        holder.pickUpCode.setText(String.valueOf(mOrderList.get(position).getPickUpCode()));

        //得到集合中的状态值
        String i = mOrderList.get(position).getStatus();
        //进行判断 并赋值
        switch (i) {
            case "未接单":
                holder.status.setText("未接单");
                holder.orderCardBt.setText("查看悬赏");
                break;
            case "已接单":
                holder.status.setText("已接单");
                holder.orderCardBt.setText("查看悬赏");
                break;
            case "未支付":
                holder.status.setText("未支付");
                holder.orderCardBt.setText("查看悬赏");
                break;
            case "已支付":
                holder.status.setText("已支付");
                holder.orderCardBt.setText("取消悬赏");
                break;
            default:
                holder.status.setText("悬赏异常！");
                holder.orderCardBt.setText("查看悬赏");
                break;
        }


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                MyOrder orders = mOrderList.get(position);

                Intent intent = new Intent(context, OrderListActivity.class);
                intent.putExtra(OrderListActivity.ORDER_ID, orders.getObjectId());
                intent.putExtra(OrderListActivity.ORDER_IMAGE_ID, orders.getTag());
                context.startActivity(intent);
            }
        });


        //给查看订单设置点击事件
        holder.orderCardBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.orderCardBt.getText().equals("取消悬赏")) {
                    int position = holder.getAdapterPosition();
                    MyOrder orders = mOrderList.get(position);
                    orders.setUserId("");
                    orders.update(orders.getObjectId(), new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if(e==null){
                                Toast.makeText(context, "取消悬赏成功,请刷新", Toast.LENGTH_SHORT).show();

                            }else{
                                Toast.makeText(context, "取消悬赏失败", Toast.LENGTH_SHORT).show();
                            }
                        }

                    });
                }else {
                    int position = holder.getAdapterPosition();
                    MyOrder orders = mOrderList.get(position);

                    Intent intent = new Intent(context, OrderListActivity.class);
                    intent.putExtra(OrderListActivity.ORDER_ID, orders.getObjectId());
                    intent.putExtra(OrderListActivity.ORDER_IMAGE_ID, orders.getTag());
                    context.startActivity(intent);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mOrderList.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;
        public TextView company;
        public TextView offer;
        public TextView pickUpCode;
        public TextView status;
        public Button orderCardBt;

        public ViewHolder(View view) {
            super(view);

            cardView = (CardView) view;
            company = (TextView) view.findViewById(R.id.order_card_company);
            offer = (TextView) view.findViewById(R.id.order_card_offer);
            pickUpCode = (TextView) view.findViewById(R.id.order_card_PUC);
            status = (TextView) view.findViewById(R.id.order_card_status);
            orderCardBt = (Button) view.findViewById(R.id.order_card_bt);
        }

    }

}
