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

public class UserAdapterUI extends FirestoreRecyclerAdapter<UserItems, UserAdapterUI.UserViewHolder> {

    private OnItemClickListener listener;
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public UserAdapterUI(@NonNull FirestoreRecyclerOptions<UserItems> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull UserViewHolder holder, int position, @NonNull UserItems model) {

        holder.profileType.setText("Profile Type: "+model.getProfile_type());
        holder.shopName.setText(model.getShop_name());
        holder.shopUserID.setText("User ID: "+model.getEmail());
        holder.shopAddr.setText("Address:-\n"+model.getAddress());
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_account_card,parent,false);
        UserViewHolder userViewHolder = new UserViewHolder(view);
        return userViewHolder;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{

        private ImageView shopImage;
        private TextView shopName;
        private TextView shopUserID;
        private ImageView deleteUser;
        private TextView profileType;
        private TextView shopAddr;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            shopImage = itemView.findViewById(R.id.shop_photo);
            shopName = itemView.findViewById(R.id.user_shop_name);
            shopUserID = itemView.findViewById(R.id.add_user_id);
            deleteUser = itemView.findViewById(R.id.delete_shop_user);
            profileType = itemView.findViewById(R.id.account_type);
            shopAddr = itemView.findViewById(R.id.user_addr);

            deleteUser.setOnClickListener(new View.OnClickListener() {
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

    public interface OnItemClickListener{
        void onDeleteClick(DocumentSnapshot documentSnapshot, int position);
    }

    public void deleteItem(int position){
        getSnapshots().getSnapshot(position).getReference().delete();
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
