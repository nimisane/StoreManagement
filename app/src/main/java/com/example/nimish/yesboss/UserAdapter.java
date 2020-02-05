package com.example.nimish.yesboss;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>{
    private ArrayList<UserItems> userItems;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
        void onDeleteClick(int position);
        void onEditClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        onItemClickListener = listener;
    }

    public UserAdapter(ArrayList<UserItems> userItems) {
        this.userItems = userItems;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_account_card,parent,false);
        UserViewHolder userViewHolder = new UserViewHolder(view,onItemClickListener);
        return userViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {

        UserItems currentItem = userItems.get(position);
        holder.shopImage.setImageResource(currentItem.getPhoto());
        holder.shopName.setText(currentItem.getShopName());
        holder.shopUserID.setText(currentItem.getShopUserId());
    }

    @Override
    public int getItemCount() {
        return userItems.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder{

        private ImageView shopImage;
        private TextView shopName;
        private TextView shopUserID;
        private ImageView deleteUser;
        private ImageView editUser;

        public UserViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            shopImage = itemView.findViewById(R.id.shop_photo);
            shopName = itemView.findViewById(R.id.user_shop_name);
            shopUserID = itemView.findViewById(R.id.user_id);
            deleteUser = itemView.findViewById(R.id.delete_shop_user);
            editUser = itemView.findViewById(R.id.edit_user);

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

            deleteUser.setOnClickListener(new View.OnClickListener() {
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

            editUser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onEditClick(position);
                        }
                    }
                }
            });
        }
    }
}
