package com.example.nimish.yesboss;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import static com.example.nimish.yesboss.AllOrdersFragment.CATEGORY;

public class CategoryPatternGrid extends AppCompatActivity {

    RecyclerView patternRecycler;
    CategoryPatternAdapterUI categoryPatternAdapter;
    RecyclerView.LayoutManager layoutManager;
    RelativeLayout totalOrder;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference shopRef = db.collection("CategoryPattern");
    private CollectionReference ordRef = db.collection("ShopOrders");
    public static final String PROCODE = "productCode";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_pattern_grid);

        patternRecycler = findViewById(R.id.pattern_recycler);
        totalOrder = findViewById(R.id.register);

        Intent intent = getIntent();
        final String category_type = intent.getStringExtra(CATEGORY);
        loadRecyclerView(category_type);

        totalOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),TotalCategoryOrderDetails.class);
                intent.putExtra(CATEGORY,category_type);
                startActivity(intent);
            }
        });
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

        categoryPatternAdapter.setOnItemClickListener(new CategoryPatternAdapterUI.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                Intent intent = new Intent(getApplicationContext(),AllOrderCategoryList.class);
                intent.putExtra(CATEGORY,documentSnapshot.get("category").toString());
                intent.putExtra(PROCODE,documentSnapshot.get("pCode").toString());
                startActivity(intent);
            }
        });

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
