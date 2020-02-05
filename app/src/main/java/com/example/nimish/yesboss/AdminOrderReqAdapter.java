package com.example.nimish.yesboss;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdminOrderReqAdapter extends RecyclerView.Adapter<AdminOrderReqAdapter.AdminOrderReqViewHolder> {
    private ArrayList<AdminOrderReqItems> adminOrderReqItems;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        onItemClickListener = listener;
    }

    public AdminOrderReqAdapter(ArrayList<AdminOrderReqItems> adminOrderReqList){
        adminOrderReqItems = adminOrderReqList;
    }

    @NonNull
    @Override
    public AdminOrderReqViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_req_cardview,parent,false);
        AdminOrderReqViewHolder adminOrderReqViewHolder = new AdminOrderReqViewHolder(view,onItemClickListener);
        return adminOrderReqViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdminOrderReqViewHolder holder, int position) {
        AdminOrderReqItems currentItem = adminOrderReqItems.get(position);
        holder.orderReqImg.setImageResource(currentItem.getOrdReqImg());
        holder.orderReqTime.setText(currentItem.getOrdReqTime());
        holder.orderReqStatus.setText(currentItem.getOrdReqStatus());
    }

    @Override
    public int getItemCount() {
        return adminOrderReqItems.size();
    }

    public static class AdminOrderReqViewHolder extends RecyclerView.ViewHolder {
        public ImageView orderReqImg;
        public TextView orderReqTime;
        public TextView orderReqStatus;
        public ImageView orderDelete;
        public AdminOrderReqViewHolder(@NonNull final View itemView, final OnItemClickListener listener) {
            super(itemView);
            orderReqImg = itemView.findViewById(R.id.card_order_req_img);
            orderReqTime = itemView.findViewById(R.id.card_order_req_time);
            orderReqStatus = itemView.findViewById(R.id.card_order_req_product);
            orderDelete = itemView.findViewById(R.id.delete_order);

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

            orderDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });
        }
    }
}
