package com.example.nimish.yesboss;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AllOrdersFragment extends Fragment {

    RecyclerView categoryRecycler;
    CategoryGridAdapterUI categoryGridAdapter;
    RecyclerView.LayoutManager layoutManager;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference catRef = db.collection("Shirt Category");

    public static final String CATEGORY = "category";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_all_orders,container,false);

        categoryRecycler = rootView.findViewById(R.id.all_order_category_recycler);
        loadRecyclerView();

        return rootView;
    }

    public void loadRecyclerView(){

        Query query = catRef.orderBy("category_name");
        FirestoreRecyclerOptions<AddCategoryItem> options = new FirestoreRecyclerOptions.Builder<AddCategoryItem>()
                .setQuery(query,AddCategoryItem.class)
                .build();

        categoryGridAdapter = new CategoryGridAdapterUI(options);
        categoryRecycler.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getContext(),2);
        categoryRecycler.setLayoutManager(layoutManager);
        categoryRecycler.setAdapter(categoryGridAdapter);

        categoryGridAdapter.setOnItemClickListener(new CategoryGridAdapterUI.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                Intent intent = new Intent(getContext(),CategoryPatternGrid.class);
                intent.putExtra(CATEGORY,documentSnapshot.get("category_name").toString());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        categoryGridAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        categoryGridAdapter.stopListening();
    }
}
