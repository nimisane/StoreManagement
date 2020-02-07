package com.example.nimish.yesboss;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdminProductPhotoAdapter extends RecyclerView.Adapter<AdminProductPhotoAdapter.AdminProductPhotoViewHolder>{

    private OnItemClickListener onItemClickListener;

    private ArrayList<AdminProductPhotoItems> adminProductPhotoItems;

        public AdminProductPhotoAdapter(ArrayList<AdminProductPhotoItems> adminProductPhotoItems) {
        this.adminProductPhotoItems = adminProductPhotoItems;
    }

    public interface OnItemClickListener{
            void onDelete(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public AdminProductPhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_photo_view,parent,false);
        AdminProductPhotoViewHolder adminProductPhotoViewHolder = new AdminProductPhotoViewHolder(view,onItemClickListener);
        return adminProductPhotoViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdminProductPhotoViewHolder holder, int position) {

        AdminProductPhotoItems currentItem = adminProductPhotoItems.get(position);
        holder.photoName.setText(currentItem.getPhotoName());
        holder.proPhoto.setImageURI(currentItem.getImg());
    }

    @Override
    public int getItemCount() {

        return adminProductPhotoItems.size();
    }

    public static class AdminProductPhotoViewHolder extends RecyclerView.ViewHolder {

        private ImageView proPhoto;
        private TextView photoName;
        private ImageView deletePhoto;

        public AdminProductPhotoViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            proPhoto = itemView.findViewById(R.id.pro_photo);
            photoName = itemView.findViewById(R.id.photo_name);
            deletePhoto = itemView.findViewById(R.id.delete_photo);

            deletePhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onDelete(position);
                        }
                    }
                }
            });
        }
    }
}
