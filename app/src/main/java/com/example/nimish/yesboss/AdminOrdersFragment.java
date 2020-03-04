package com.example.nimish.yesboss;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.nimish.yesboss.AdminOrdersReqFragment.DOCUMENTID;
import static com.example.nimish.yesboss.AdminOrdersReqFragment.ORDATE;


public class AdminOrdersFragment extends Fragment {
    private RecyclerView adminOrderRecyclerView;
    private StoreOrderAdapterUI storeOrderAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference shopOrderRef = db.collection("ShopOrders");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_admin_orders,container,false);

        adminOrderRecyclerView = rootView.findViewById(R.id.orders_recyclerview);
        SimpleDateFormat dateOnlyFormat = new SimpleDateFormat("yyyy-MM-dd");
        final String dateOnly = dateOnlyFormat.format(new Date());
        loadRecyclerView(dateOnly);
        return rootView;
    }

    public void loadRecyclerView(String dateOnly){

        Query query = shopOrderRef.whereEqualTo("dateOnly",dateOnly).orderBy("sortDate", Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<AdminOrdersItem> options = new FirestoreRecyclerOptions.Builder<AdminOrdersItem>()
                .setQuery(query,AdminOrdersItem.class)
                .build();

        storeOrderAdapter = new StoreOrderAdapterUI(options);
        adminOrderRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());

        adminOrderRecyclerView.setLayoutManager(layoutManager);
        adminOrderRecyclerView.setAdapter(storeOrderAdapter);

        storeOrderAdapter.setOnItemClickListener(new StoreOrderAdapterUI.OnItemClicklistener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                Intent intent = new Intent(getContext(),UpdateStoreOrder.class);
                String docID = documentSnapshot.getId();
                String ordDate = documentSnapshot.get("dateOnly").toString();
                intent.putExtra(DOCUMENTID,docID);
                intent.putExtra(ORDATE,ordDate);
                startActivity(intent);
            }

            @Override
            public void onDeleteClick(DocumentSnapshot documentSnapshot, int position) {
                storeOrderAdapter.deleteItem(position);
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        SimpleDateFormat dateOnlyFormat = new SimpleDateFormat("yyyy-MM-dd");
        final String dateOnly = dateOnlyFormat.format(new Date());
        loadRecyclerView(dateOnly);
        storeOrderAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        storeOrderAdapter.stopListening();
    }
}
