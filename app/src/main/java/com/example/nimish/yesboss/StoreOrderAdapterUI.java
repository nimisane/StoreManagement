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
import com.squareup.picasso.Picasso;

public class StoreOrderAdapterUI extends FirestoreRecyclerAdapter<AdminOrdersItem, StoreOrderAdapterUI.StoreOrderViewHolder> {

    private OnItemClickListener listener;
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public StoreOrderAdapterUI(@NonNull FirestoreRecyclerOptions<AdminOrdersItem> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull StoreOrderViewHolder holder, int position, @NonNull AdminOrdersItem model) {
        Picasso.get().load(model.getImageLink().get(0)).into(holder.orderImg);
        holder.shopName.setText(model.getShopName());
        holder.dateTime.setText(model.getOrderDate().toString());
        holder.pName.setText(model.getProductName());
        holder.pCode.setText("Product Code"+model.getProductCode());
        holder.pMrp.setText("MRP: Rs."+model.getMrp());
        holder.pCategory.setText("Category: "+model.getCategory());
    }

    @NonNull
    @Override
    public StoreOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.orders_cardview,parent,false);
        StoreOrderViewHolder storeOrderViewHolder = new StoreOrderViewHolder(view);
        return storeOrderViewHolder;
    }

    public class StoreOrderViewHolder extends RecyclerView.ViewHolder{

        ImageView orderImg;
        TextView shopName;
        TextView dateTime;
        TextView pName;
        TextView pCode;
        TextView pMrp;
        TextView pCategory;
        ImageView deleteOrder;

        public StoreOrderViewHolder(@NonNull View itemView) {
            super(itemView);

            orderImg = itemView.findViewById(R.id.card_order_img);
            shopName = itemView.findViewById(R.id.card_order_shop);
            dateTime = itemView.findViewById(R.id.card_order_time);
            pName = itemView.findViewById(R.id.card_order_product);
            pCode = itemView.findViewById(R.id.card_order_product_code);
            pMrp = itemView.findViewById(R.id.card_order_product_mrp);
            pCategory = itemView.findViewById(R.id.card_order_category);
            deleteOrder = itemView.findViewById(R.id.delete_order);

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

            deleteOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listener != null){
                        if (position != RecyclerView.NO_POSITION){
                            listener.onDeleteClick(getSnapshots().getSnapshot(position),position);
                        }
                    }
                }
            });

        }
    }

    public interface OnItemClickListener{
        void onItemClick(DocumentSnapshot documentSnapshot,int position);
        void onDeleteClick(DocumentSnapshot documentSnapshot,int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    public void deleteItem(int position){
        getSnapshots().getSnapshot(position).getReference().delete();
    }
}
