package com.example.nimish.yesboss;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;


public class AdminOrdersReqFragment extends Fragment {
    FloatingActionButton reqOrder;
    private RecyclerView adminOrderReqRecyclerview;
    private AdminOrderReqAdapterUI adminOrderReqAdapter;
    private RecyclerView.LayoutManager layoutManager;
    //  private ArrayList<AdminOrderReqItems> adminOrdersItems;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference orderReqRef = db.collection("AdminOrders");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_admin_order_req,container,false);

        reqOrder = rootView.findViewById(R.id.add_order);
        adminOrderReqRecyclerview  = rootView.findViewById(R.id.order_req_recyclerview);

        loadRecyclerView();

        reqOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(),AdminOrderReqActivity.class);
                startActivity(i);
            }
        });

//        adminOrderReqAdapter.setOnItemClickListener(new AdminOrderReqAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(int position) {
//                Intent intent = new Intent(getContext(),PlacedOrder.class);
//                AdminOrderReqItems clickedItem = adminOrdersItems.get(position);
//                startActivity(intent);
//            }
//
//            @Override
//            public void onDeleteClick(int position) {
//                removeItem(position);
//            }
//        });
        return rootView;
    }

    public void loadRecyclerView(){
        Query query = orderReqRef.orderBy("orderDate",Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<AdminOrderItem> options = new FirestoreRecyclerOptions.Builder<AdminOrderItem>()
                .setQuery(query,AdminOrderItem.class)
                .build();

        adminOrderReqAdapter = new AdminOrderReqAdapterUI(options);
        adminOrderReqRecyclerview.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());

        adminOrderReqRecyclerview.setLayoutManager(layoutManager);
        adminOrderReqRecyclerview.setAdapter(adminOrderReqAdapter);

        adminOrderReqAdapter.setOnItemClickListener(new AdminOrderReqAdapterUI.OnItemClicklistener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                Intent intent = new Intent(getContext(),PlacedOrder.class);
                startActivity(intent);
            }

            @Override
            public void onDeleteClick(DocumentSnapshot documentSnapshot, int position) {
                adminOrderReqAdapter.deleteItem(position);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        adminOrderReqAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adminOrderReqAdapter.stopListening();
    }

    //    public void removeItem(int position){
//        adminOrdersItems.remove(position);
//        adminOrderReqAdapter.notifyItemRemoved(position);
//    }
}