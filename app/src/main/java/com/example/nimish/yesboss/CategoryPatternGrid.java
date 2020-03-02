package com.example.nimish.yesboss;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import static com.example.nimish.yesboss.AllOrdersFragment.CATEGORY;

public class CategoryPatternGrid extends AppCompatActivity {

    RecyclerView patternRecycler;
    CategoryPatternAdapterUI categoryPatternAdapter;
    RecyclerView.LayoutManager layoutManager;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference shopRef = db.collection("CategoryPattern");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_pattern_grid);

        patternRecycler = findViewById(R.id.pattern_recycler);

        Intent intent = getIntent();
        String category_type = intent.getStringExtra(CATEGORY);
        Toast.makeText(this,category_type,Toast.LENGTH_SHORT).show();
        loadRecyclerView(category_type);
    }

    public void loadRecyclerView(String category_type){

        Query query = shopRef.whereEqualTo("category",category_type);
        FirestoreRecyclerOptions<PatternItems> options = new FirestoreRecyclerOptions.Builder<PatternItems>()
                .setQuery(query,PatternItems.class)
                .build();

        patternRecycler.setHasFixedSize(true);
        categoryPatternAdapter = new CategoryPatternAdapterUI(options);
        layoutManager = new GridLayoutManager(getApplicationContext(),2);
        patternRecycler.setLayoutManager(layoutManager);
        patternRecycler.setAdapter(categoryPatternAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        categoryPatternAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        categoryPatternAdapter.stopListening();
    }
}
