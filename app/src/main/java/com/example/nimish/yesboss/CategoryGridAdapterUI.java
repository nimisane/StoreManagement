package com.example.nimish.yesboss;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

public class CategoryGridAdapterUI extends FirestoreRecyclerAdapter<AddCategoryItem, CategoryGridAdapterUI.CategoryGridViewHolder> {

    private OnItemClickListener listener;
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public CategoryGridAdapterUI(@NonNull FirestoreRecyclerOptions<AddCategoryItem> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull CategoryGridViewHolder holder, int position, @NonNull AddCategoryItem model) {
        holder.category.setText(model.getCategory_name());
    }

    @NonNull
    @Override
    public CategoryGridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_order_category,parent,false);
        CategoryGridViewHolder categoryGridViewHolder = new CategoryGridViewHolder(view);
        return categoryGridViewHolder;
    }

    public class CategoryGridViewHolder extends RecyclerView.ViewHolder{

        ImageView categoryImg;
        TextView category;

        public CategoryGridViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryImg = itemView.findViewById(R.id.category_image);
            category = itemView.findViewById(R.id.categoty_text);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listener != null){
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(getSnapshots().getSnapshot(position),position);
                        }
                    }
                }
            });
        }
    }

    public interface OnItemClickListener{
         void onItemClick(DocumentSnapshot documentSnapshot,int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
