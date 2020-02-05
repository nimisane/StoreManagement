package com.example.nimish.yesboss;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class AddCategoryActivity extends AppCompatActivity {

    private RecyclerView categoryRecyclerview;
    private AddCategoryAdapter addCategoryAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private FloatingActionButton addCategoryButton;
    private ArrayList<AddCategoryItem> addCategoryItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        addCategoryItems = new ArrayList<>();
        addCategoryItems.add(new AddCategoryItem("Shirt Category1"));
        addCategoryItems.add(new AddCategoryItem("Shirt Category2"));
        categoryRecyclerview = findViewById(R.id.category_recyclerview);
        categoryRecyclerview.setHasFixedSize(true);
        addCategoryAdapter = new AddCategoryAdapter(addCategoryItems);
        layoutManager = new LinearLayoutManager(this);
        categoryRecyclerview.setLayoutManager(layoutManager);
        categoryRecyclerview.setAdapter(addCategoryAdapter);

        addCategoryAdapter.setOnItemClickListener(new AddCategoryAdapter.OnItemClickListener() {

            @Override
            public void onDeleteClick(int position) {
                removeItem(position);
            }
        });

    }

    public void removeItem(int position){
        addCategoryItems.remove(position);
        addCategoryAdapter.notifyItemRemoved(position);
    }
}
