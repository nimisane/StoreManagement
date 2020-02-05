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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class AdminOrdersReqFragment extends Fragment {
    FloatingActionButton reqOrder;
    private RecyclerView adminOrderReqRecyclerview;
    private AdminOrderReqAdapter adminOrderReqAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<AdminOrderReqItems> adminOrdersItems;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_admin_order_req,container,false);

        reqOrder = rootView.findViewById(R.id.add_order);
        adminOrdersItems = new ArrayList<>();
        adminOrdersItems.add(new AdminOrderReqItems(R.drawable.ic_request_order,"09:10 am","pending"));
        adminOrdersItems.add(new AdminOrderReqItems(R.drawable.ic_request_order,"09:15 am","pending"));

        adminOrderReqRecyclerview  = rootView.findViewById(R.id.order_req_recyclerview);
        adminOrderReqRecyclerview.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        adminOrderReqAdapter = new AdminOrderReqAdapter(adminOrdersItems);
        adminOrderReqRecyclerview.setLayoutManager(layoutManager);
        adminOrderReqRecyclerview.setAdapter(adminOrderReqAdapter);

        reqOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(),AdminOrderReqActivity.class);
                startActivity(i);
            }
        });

        adminOrderReqAdapter.setOnItemClickListener(new AdminOrderReqAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getContext(),PlacedOrder.class);
                AdminOrderReqItems clickedItem = adminOrdersItems.get(position);
                startActivity(intent);
            }

            @Override
            public void onDeleteClick(int position) {
                removeItem(position);
            }
        });
        return rootView;
    }

    public void removeItem(int position){
        adminOrdersItems.remove(position);
        adminOrderReqAdapter.notifyItemRemoved(position);
    }
}
