package com.example.nimish.yesboss;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;

public class UserActivity extends AppCompatActivity {

    RecyclerView userRecyclerview;
    UserAdapterUI userAdapter;
  //  ArrayList<UserItems> userItems;
    RecyclerView.LayoutManager layoutManager;
    FloatingActionButton addUser;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference categoryRef = db.collection("Shops");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        addUser = findViewById(R.id.add_user_button);

        loadRecyclerView();

        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),AddUserActivity.class);
                startActivity(i);
            }
        });

    }

    public void loadRecyclerView(){
        Query query = categoryRef.orderBy("shop_name");
        FirestoreRecyclerOptions<UserItems> options = new FirestoreRecyclerOptions.Builder<UserItems>()
                .setQuery(query,UserItems.class)
                .build();
        userRecyclerview = findViewById(R.id.users_recycler_view);
        userRecyclerview.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        userAdapter = new UserAdapterUI(options);
        userRecyclerview.setLayoutManager(layoutManager);
        userRecyclerview.setAdapter(userAdapter);

        userAdapter.setOnItemClickListener(new UserAdapterUI.OnItemClickListener() {
            @Override
            public void onDeleteClick(DocumentSnapshot documentSnapshot, int position) {
                userAdapter.deleteItem(position);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        userAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        userAdapter.stopListening();
    }
}
