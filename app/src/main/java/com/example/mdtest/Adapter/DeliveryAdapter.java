package com.example.mdtest.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mdtest.Bean.MyOrder;
import com.example.mdtest.CardInit.DeliveryActivity;
import com.example.mdtest.R;

import java.util.List;

public class DeliveryAdapter extends RecyclerView.Adapter<DeliveryAdapter.ViewHolder> {

    private List<MyOrder> mDeliveryList;
    private Context context;

    public DeliveryAdapter(List<MyOrder> mOrderList) {
        this.mDeliveryList = mOrderList;
    }



    @NonNull
    @Override
    public DeliveryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (context == null) {
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.item_delivery,
                parent, false);

        return new DeliveryAdapter.ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(DeliveryAdapter.ViewHolder holder, int position) {

        holder.type.setText(String.valueOf(mDeliveryList.get(position).getType()));
        holder.message.setText(String.valueOf(mDeliveryList.get(position).getMessage()));
        holder.time.setText(String.valueOf(mDeliveryList.get(position).getCreatedAt()));
        holder.status.setText(String.valueOf(mDeliveryList.get(position).getStatus()));
        holder.offer.setText("¥"+String.valueOf(mDeliveryList.get(position).getOffer()));
//        holder.tag.setText(String.valueOf(mDeliveryList.get(position).getTag()));


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               int position = holder.getAdapterPosition();
               MyOrder deliveries = mDeliveryList.get(position);

               Intent intent = new Intent(context, DeliveryActivity.class);
               intent.putExtra(DeliveryActivity.DV_ID, deliveries.getObjectId());
               //intent.putExtra(DeliveryActivity.DV_IMAGE_ID, deliveries.getTag());
               context.startActivity(intent);
            }
        });


//        //得到集合中的状态值
//        String i = mDeliveryList.get(position).getTag();
//        //进行判断 并赋值
//        switch (i) {
//            case "水果":
//                Glide.with(context).load(R.mipmap.ic_fruit).into(holder.imageView);
//                break;
//            case "电子器件":
//                Glide.with(context).load(R.mipmap.ic_electric).into(holder.imageView);
//                break;
//            case "书本":
//                Glide.with(context).load(R.mipmap.ic_book).into(holder.imageView);
//                break;
//            case "衣服":
//                Glide.with(context).load(R.mipmap.ic_clothes).into(holder.imageView);
//                break;
//            default:
//                Glide.with(context).load(R.mipmap.ic_box).into(holder.imageView);
//                break;
//        }

    }

    @Override
    public int getItemCount() {
        return mDeliveryList.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;
        //public ImageView imageView;

        //public TextView company;
        public TextView type;
        public TextView message;
        public TextView time;
        public TextView status;
        public TextView offer;

        public ViewHolder(View view) {
            super(view);

            cardView = (CardView) view;
            //imageView = (ImageView) view.findViewById(R.id.deli_image);
            //company = (TextView) view.findViewById(R.id.deli_company);
            type = (TextView) view.findViewById(R.id.deli_tag);
            message = (TextView) view.findViewById(R.id.deli_describe);
            time = (TextView) view.findViewById(R.id.deli_time3);
            status = (TextView) view.findViewById(R.id.deli_status);
            offer = (TextView) view.findViewById(R.id.deli_offer);

        }

    }
}
