package com.example.nimish.yesboss;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class AddUserActivity extends AppCompatActivity {

    RecyclerView userRecyclerview;
    UserAdapter userAdapter;
    ArrayList<UserItems> userItems;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        userItems = new ArrayList<>();
        userItems.add(new UserItems("Shop 1","ShopNo:1","pwd",R.drawable.ic_tshirt));
        userItems.add(new UserItems("Shop 2","ShopNo:2","pwd",R.drawable.ic_tshirt));

        userRecyclerview = findViewById(R.id.users_recycler_view);
        userRecyclerview.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        userAdapter = new UserAdapter(userItems);
        userRecyclerview.setLayoutManager(layoutManager);
        userRecyclerview.setAdapter(userAdapter);

        userAdapter.setOnItemClickListener(new UserAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getApplicationContext(),"Shop",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDeleteClick(int position) {
                removeItem(position);
            }

            @Override
            public void onEditClick(int position) {
                Intent i = new Intent(getApplicationContext(),ModifyUserActivity.class);
                startActivity(i);
            }
        });
    }

    public void removeItem(int position){
        userItems.remove(position);
        userAdapter.notifyItemRemoved(position);
    }
}
