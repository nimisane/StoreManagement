package com.example.nimish.yesboss;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.squareup.picasso.Picasso;

public class CategoryPatternAdapterUI extends FirestoreRecyclerAdapter<PatternItems, CategoryPatternAdapterUI.CategoryPatternViewHolder> {

    private OnItemClickListener listener;

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
        Picasso.get().load(model.getImageLink()).placeholder(R.drawable.eclipse_refresh).into(holder.category_img);
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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if( listener != null){
                        if (position != RecyclerView.NO_POSITION){
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
