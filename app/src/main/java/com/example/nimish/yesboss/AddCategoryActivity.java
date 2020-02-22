package com.example.nimish.yesboss;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;


public class AddCategoryActivity extends AppCompatActivity implements CategoryDialog.CategoryDialogListener {

    private RecyclerView categoryRecyclerview;
    private AddCategoryAdapterUI addCategoryAdapterUI;
    private RecyclerView.LayoutManager layoutManager;
    private FloatingActionButton addCategoryButton;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference categoryRef = db.collection("Shirt Category");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        addCategoryButton = findViewById(R.id.add_category);

        loadRecyclerView();

        addCategoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        addCategoryAdapterUI.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        addCategoryAdapterUI.stopListening();
    }


    public void openDialog(){
        CategoryDialog categoryDialog = new CategoryDialog();
        categoryDialog.show(getSupportFragmentManager(),"Category Dialog");
    }

    @Override
    public void getNewCategory(String category) {

        if(category.trim().isEmpty()){
            Toast.makeText(this,"Please enter a valid category",Toast.LENGTH_SHORT).show();
            return;
        }
        categoryRef.add(new AddCategoryItem(category))
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(AddCategoryActivity.this,"added",Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddCategoryActivity.this,"failed",Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void loadRecyclerView(){

        Query query = categoryRef.orderBy("category_name");
        FirestoreRecyclerOptions<AddCategoryItem> options = new FirestoreRecyclerOptions.Builder<AddCategoryItem>()
                .setQuery(query,AddCategoryItem.class)
                .build();

        addCategoryAdapterUI = new AddCategoryAdapterUI(options);
        categoryRecyclerview = findViewById(R.id.category_recyclerview);
        categoryRecyclerview.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        categoryRecyclerview.setLayoutManager(layoutManager);
        categoryRecyclerview.setAdapter(addCategoryAdapterUI);

        addCategoryAdapterUI.setOnItemClickListener(new AddCategoryAdapterUI.OnItemClickListener() {
            @Override
            public void onDeleteClick(DocumentSnapshot documentSnapshot, int position) {
                addCategoryAdapterUI.deleteItem(position);
            }
        });
    }


}
