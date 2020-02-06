package com.example.nimish.yesboss;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class AddCategoryActivity extends AppCompatActivity implements CategoryDialog.CategoryDialogListener {

    private RecyclerView categoryRecyclerview;
    private AddCategoryAdapter addCategoryAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private FloatingActionButton addCategoryButton;
    private ArrayList<AddCategoryItem> addCategoryItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        addCategoryButton = findViewById(R.id.add_category);
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

        addCategoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

    }

    public void removeItem(int position){
        addCategoryItems.remove(position);
        addCategoryAdapter.notifyItemRemoved(position);
    }

    public void openDialog(){
        CategoryDialog categoryDialog = new CategoryDialog();
        categoryDialog.show(getSupportFragmentManager(),"Category Dialog");
    }

    @Override
    public void getNewCategory(String category) {
        addCategoryItems.add(new AddCategoryItem(category));
    }
}
