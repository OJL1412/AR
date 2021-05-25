package com.example.mdtest.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mdtest.Bean.MyAddress;
import com.example.mdtest.R;

import java.util.List;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder> {

    private final List<MyAddress> myAddressList;
    private final Context context;

    public AddressAdapter(Context context, List<MyAddress> myAddressList) {
        this.context = context;
        this.myAddressList = myAddressList;
    }



    @NonNull
    @Override
    public AddressAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_address,
                parent, false);
        return new AddressAdapter.ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(AddressAdapter.ViewHolder holder, int position) {

        holder.addressName.setText(String.valueOf(myAddressList.get(position).getSelfName()));
        holder.address_detailed.setText(myAddressList.get(position).getDetailedAdd());


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                MyAddress myAddress = myAddressList.get(position);

//                Intent intent = new Intent(context, AddressActivity.class);
//                intent.putExtra(OrderListActivity.ADDRESS_NAME, myAddress.getSelfName());
//                intent.putExtra(OrderListActivity.ADDRESS_DETAILED, myAddress.getRegion()
//                +myAddress.getSchool()+myAddress.getBuilding()+myAddress.getDetailedAdd());
//                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return myAddressList.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;
        public TextView addressName;
        public TextView address_detailed;

        public ViewHolder(View view) {
            super(view);

            cardView = (CardView) view;
            addressName = (TextView) view.findViewById(R.id.address_name);
            address_detailed = (TextView) view.findViewById(R.id.address_detailed);

        }

    }
}
