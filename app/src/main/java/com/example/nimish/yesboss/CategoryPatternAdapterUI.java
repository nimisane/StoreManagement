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
import com.squareup.picasso.Picasso;

public class CategoryPatternAdapterUI extends FirestoreRecyclerAdapter<PatternItems, CategoryPatternAdapterUI.CategoryPatternViewHolder> {

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public CategoryPatternAdapterUI(@NonNull FirestoreRecyclerOptions<PatternItems> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull CategoryPatternViewHolder holder, int position, @NonNull PatternItems model) {
        Picasso.get().load(model.getImageLink()).into(holder.category_img);
        holder.category_text.setText(model.getpCode());
    }

    @NonNull
    @Override
    public CategoryPatternViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_order_category,parent,false);
        CategoryPatternViewHolder categoryPatternViewHolder = new CategoryPatternViewHolder(view);
        return categoryPatternViewHolder;
    }

    public class CategoryPatternViewHolder extends RecyclerView.ViewHolder{

        ImageView category_img;
        TextView category_text;
        public CategoryPatternViewHolder(@NonNull View itemView) {
            super(itemView);

            category_img = itemView.findViewById(R.id.category_image);
            category_text = itemView.findViewById(R.id.categoty_text);
        }
    }
}
