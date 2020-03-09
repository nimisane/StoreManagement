package com.example.nimish.yesboss;

import android.graphics.Color;
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

    private OnItemClicklistener listener;
    private FirestoreRecyclerOptions<AdminOrdersItem> options;
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public StoreOrderAdapterUI(@NonNull FirestoreRecyclerOptions<AdminOrdersItem> options) {
        super(options);
        this.options = options;
    }

    @Override
    protected void onBindViewHolder(@NonNull StoreOrderViewHolder holder, int position, @NonNull AdminOrdersItem model) {
        Picasso.get().load(model.getImageLink().get(0)).placeholder(R.drawable.eclipse_refresh).into(holder.orderImg);
        holder.shopName.setText(model.getShopName());
        holder.dateTime.setText(model.getOrderDate().toString());
        holder.pName.setText(model.getProductName());
        holder.pCode.setText("Product Code: "+model.getProductCode());
        holder.pMrp.setText("MRP: Rs."+model.getMrp());
        holder.pCategory.setText("Category: "+model.getCategory());
        if (model.getDeliverStatus().equals("Pending")){
            holder.deliveryStatus.setTextColor(Color.RED);
            holder.deliveryStatus.setText("Delivery Status: "+model.getDeliverStatus());
        }
        else if (model.getDeliverStatus().equals("Complete")) {
            holder.deliveryStatus.setTextColor(Color.rgb(50,205,50));
            holder.deliveryStatus.setText("Delivery Status: " + model.getDeliverStatus());
        }
        else {
            holder.deliveryStatus.setText("Delivery Status: " + model.getDeliverStatus());
        }
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
        TextView deliveryStatus;
        public ImageView deleteOrder;

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
            deliveryStatus = itemView.findViewById(R.id.card_order_delivery);

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
                        if(position != RecyclerView.NO_POSITION){
                            listener.onDeleteClick(getSnapshots().getSnapshot(position),position);
                        }
                    }
                }
            });

        }
    }

    public interface OnItemClicklistener{
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
