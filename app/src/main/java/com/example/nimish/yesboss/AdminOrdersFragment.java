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

import java.util.ArrayList;

public class AdminOrdersFragment extends Fragment {
    private RecyclerView adminOrderRecyclerView;
    private AdminOrdersAdapter adminOrderAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_admin_orders,container,false);

        adminOrderRecyclerView = rootView.findViewById(R.id.orders_recyclerview);

        final ArrayList<AdminOrdersItem> adminOrdersItems = new ArrayList<>();
//        adminOrdersItems.add(new AdminOrdersItem(R.drawable.ic_request_order,"09:10 am","pending"));
//        adminOrdersItems.add(new AdminOrdersItem(R.drawable.ic_request_order,"09:15 am","pending"));

        adminOrderRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        adminOrderAdapter = new AdminOrdersAdapter(adminOrdersItems);
        adminOrderRecyclerView.setLayoutManager(layoutManager);
        adminOrderRecyclerView.setAdapter(adminOrderAdapter);
        adminOrderAdapter.setOnItemClickListener(new AdminOrdersAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getContext(),PlacedOrder.class);
                AdminOrdersItem clickedItem = adminOrdersItems.get(position);
                startActivity(intent);

            }
        });
        return rootView;
    }
}
