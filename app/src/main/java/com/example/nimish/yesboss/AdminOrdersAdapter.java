package com.example.nimish.yesboss;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdminOrdersAdapter extends RecyclerView.Adapter<AdminOrdersAdapter.AdminOrderViewHolder>  {
    private ArrayList<AdminOrdersItem> mAdminOrdersItemArrayList;
    private OnItemClickListener mlistener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mlistener = listener;
    }

    public static class AdminOrderViewHolder extends RecyclerView.ViewHolder{
        public ImageView mOrderImg;
        public TextView mDateTime;
        public TextView mStatus;

        public AdminOrderViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
//            mOrderImg = itemView.findViewById(R.id.card_order_img);
//            mDateTime = itemView.findViewById(R.id.card_order_time);
//            mStatus = itemView.findViewById(R.id.card_order_status);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public AdminOrdersAdapter(ArrayList<AdminOrdersItem> adminOrdersItems){
        mAdminOrdersItemArrayList = adminOrdersItems;
    }

    @NonNull
    @Override
    public AdminOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.orders_cardview,parent,false);
        AdminOrderViewHolder adminOrderViewHolder = new AdminOrderViewHolder(view,mlistener);
        return adminOrderViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdminOrderViewHolder holder, int position) {
        AdminOrdersItem currentItem = mAdminOrdersItemArrayList.get(position);

//        holder.mOrderImg.setImageResource(currentItem.getOrderImg());
//        holder.mDateTime.setText(currentItem.getOrderTime());
//        holder.mStatus.setText(currentItem.getOrderStatus());

    }

    @Override
    public int getItemCount() {
        return mAdminOrdersItemArrayList.size();
    }
}
