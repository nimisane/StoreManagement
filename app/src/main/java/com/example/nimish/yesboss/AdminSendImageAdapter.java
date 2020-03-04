package com.example.nimish.yesboss;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdminSendImageAdapter extends RecyclerView.Adapter<AdminSendImageAdapter.AdminSendImageViewHolder>{

    private ArrayList<AdminSendOrderImageItem> adminSendOrderImageItems;

    public AdminSendImageAdapter(ArrayList<AdminSendOrderImageItem> adminSendOrderImageItems) {
        this.adminSendOrderImageItems = adminSendOrderImageItems;
    }

    @NonNull
    @Override
    public AdminSendImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.send_activity_imageview,parent,false);
        AdminSendImageViewHolder adminSendImageViewHolder = new AdminSendImageViewHolder(view);
        return adminSendImageViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdminSendImageViewHolder holder, int position) {
        AdminSendOrderImageItem currentItem = adminSendOrderImageItems.get(position);
        Picasso.get().load(currentItem.getImgUrl()).placeholder(R.drawable.eclipse_refresh).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return adminSendOrderImageItems.size();
    }

    public class AdminSendImageViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;

        public AdminSendImageViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.send_recycler_imageview);
        }
    }
}
