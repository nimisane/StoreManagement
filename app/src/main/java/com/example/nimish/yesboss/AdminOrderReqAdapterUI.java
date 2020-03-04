package com.example.nimish.yesboss;

import android.content.Context;
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

public class AdminOrderReqAdapterUI extends FirestoreRecyclerAdapter<AdminOrderItem, AdminOrderReqAdapterUI.AdminOrderReqViewHolder> {

    private OnItemClicklistener listener;
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public AdminOrderReqAdapterUI(@NonNull FirestoreRecyclerOptions<AdminOrderItem> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull AdminOrderReqViewHolder holder, int position, @NonNull AdminOrderItem model) {
        holder.orderReqStatus.setText(model.getProductName());
        holder.orderReqTime.setText(model.getOrderDate().toString());
        Picasso.get().load(model.getImgLink().get(0)).placeholder(R.drawable.eclipse_refresh).into(holder.orderReqImg);
        holder.orderReqMrp.setText("MRP: Rs."+model.getMrp());
        holder.orderReqCode.setText("Product Code: "+model.getProductCode());
        holder.orderReqShop.setText(model.getShopName());
        holder.orderReqCategory.setText("Category: "+model.getCategory());
    }

    @NonNull
    @Override
    public AdminOrderReqViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_req_cardview,parent,false);
        AdminOrderReqViewHolder adminOrderReqViewHolder = new AdminOrderReqViewHolder(view);
        return adminOrderReqViewHolder;
    }

    class AdminOrderReqViewHolder extends RecyclerView.ViewHolder {

        public ImageView orderReqImg;
        public TextView orderReqTime;
        public TextView orderReqStatus;
        public TextView orderReqMrp;
        public TextView orderReqCode;
        public TextView orderReqShop;
        public TextView orderReqCategory;
        public ImageView orderDelete;

        public AdminOrderReqViewHolder(@NonNull View itemView) {
            super(itemView);

            orderReqImg = itemView.findViewById(R.id.card_order_req_img);
            orderReqTime = itemView.findViewById(R.id.card_order_req_time);
            orderReqStatus = itemView.findViewById(R.id.card_order_req_product);
            orderReqMrp = itemView.findViewById(R.id.card_order_req_product_mrp);
            orderReqCode = itemView.findViewById(R.id.card_order_req_product_code);
            orderReqShop = itemView.findViewById(R.id.card_order_req_shop);
            orderReqCategory = itemView.findViewById(R.id.card_order_req_category);
            orderDelete = itemView.findViewById(R.id.delete_order);

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

            orderDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listener != null){
                        if(position != RecyclerView.NO_POSITION){
                            listener.onDeleteClick(getSnapshots().getSnapshot(position),position);
                        }
                    }
                }
            });
        }
    }

    public interface OnItemClicklistener {
        void onItemClick(DocumentSnapshot documentSnapshot,int position);
        void onDeleteClick(DocumentSnapshot documentSnapshot, int position);
    }

    public void deleteItem(int position){
        getSnapshots().getSnapshot(position).getReference().delete();
    }

    public void setOnItemClickListener(OnItemClicklistener listener){
        this.listener = listener;
    }
}