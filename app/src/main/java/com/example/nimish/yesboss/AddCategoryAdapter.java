package com.example.nimish.yesboss;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AddCategoryAdapter extends RecyclerView.Adapter<AddCategoryAdapter.AddCategoryViewHolder>{
    private ArrayList<AddCategoryItem> addCategoryItems;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener{
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        onItemClickListener = listener;
    }

    public AddCategoryAdapter(ArrayList<AddCategoryItem> addCategoryItems) {
        this.addCategoryItems = addCategoryItems;
    }

    @NonNull
    @Override
    public AddCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_category_card,parent,false);
        AddCategoryViewHolder addCategoryViewHolder = new AddCategoryViewHolder(view,onItemClickListener);
        return addCategoryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AddCategoryViewHolder holder, int position) {
        AddCategoryItem currentItem = addCategoryItems.get(position);
        holder.categoryName.setText(currentItem.getCategory_name());
    }

    @Override
    public int getItemCount() {
        return addCategoryItems.size();
    }

    public static class AddCategoryViewHolder extends RecyclerView.ViewHolder {

        private TextView categoryName;
        private ImageView deleteCategory;

        public AddCategoryViewHolder(@NonNull final View itemView, final OnItemClickListener listener) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.category_name);
            deleteCategory = itemView.findViewById(R.id.delete_category);

            deleteCategory.setOnClickListener(new View.OnClickListener() {
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
