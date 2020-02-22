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

public class AddCategoryAdapterUI extends FirestoreRecyclerAdapter<AddCategoryItem, AddCategoryAdapterUI.AddCategoryViewHolder> {
    private OnItemClickListener listener;


    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public AddCategoryAdapterUI(@NonNull FirestoreRecyclerOptions<AddCategoryItem> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull AddCategoryViewHolder holder, int position, @NonNull AddCategoryItem model) {
        holder.categoryName.setText(model.getCategory_name());
    }

    @NonNull
    @Override
    public AddCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_category_card,parent,false);
        AddCategoryViewHolder addCategoryViewHolder = new AddCategoryViewHolder(view);
        return addCategoryViewHolder;
    }

    public class AddCategoryViewHolder extends RecyclerView.ViewHolder {

        private TextView categoryName;
        private ImageView deleteCategory;

        public AddCategoryViewHolder(@NonNull final View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.category_name);
            deleteCategory = itemView.findViewById(R.id.delete_category);
            deleteCategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onDeleteClick(getSnapshots().getSnapshot(position),position);
                        }
                    }
                }
            });

        }
    }

    public void deleteItem(int position) {
        getSnapshots().getSnapshot(position).getReference().delete();
    }

    public interface OnItemClickListener{
        void onDeleteClick(DocumentSnapshot documentSnapshot, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
