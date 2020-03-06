package com.example.nimish.yesboss;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class AddUserActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView addShop;
    TextView addUserId;
    TextView addPwd;
    TextView addAddr;
    Button addUserDetails;
    EditText addShopText;
    EditText addUserIdText;
    EditText addPwdText;
    EditText addAddressText;
    Spinner profile;
    ProgressBar progressBar;

    String profileType;

    private FirebaseAuth mAuth;
    final static String TAG = "AddUser";
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference shopRef = db.collection("Shops");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_clear_black_24dp);// set drawable icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAuth = FirebaseAuth.getInstance();
        addShop = findViewById(R.id.add_shop_name);
        addUserId = findViewById(R.id.add_user_id);
        addPwd = findViewById(R.id.add_password);
        addAddr = findViewById(R.id.add_address);
        addShopText = findViewById(R.id.add_shop_name_text);
        addUserIdText = findViewById(R.id.add_user_id_text);
        addPwdText = findViewById(R.id.add_pwd_text);
        addAddressText = findViewById(R.id.add_address_text);
        addUserDetails = findViewById(R.id.submit);
        progressBar = findViewById(R.id.register_progress);
        profile = findViewById(R.id.profile_spinner);

        progressBar.setVisibility(View.GONE);

        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this,R.array.profile_type,android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        profile.setAdapter(spinnerAdapter);
        profile.setOnItemSelectedListener(this);

        addUserDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    public void registerUser(){
        progressBar.setVisibility(View.VISIBLE);
        final String shopName = addShopText.getText().toString();
        final String email = addUserIdText.getText().toString();
        final String pwd = addPwdText.getText().toString();
        final String address = addAddressText.getText().toString();
        final int[] shopExists = {0};

        if(shopName.isEmpty() || email.isEmpty() || pwd.isEmpty() || address.isEmpty()){
           Toast.makeText(getApplicationContext(),"Please Fill all the details correctly",Toast.LENGTH_SHORT).show();
           return;
        }

            shopRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    for(DocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                        String shopname = documentSnapshot.get("shop_name").toString();
                        if(shopName.equals(shopname)){
                            shopExists[0] = 1;
                            Toast.makeText(getApplicationContext(),"Shop is already registered in app",Toast.LENGTH_LONG).show();
                            return;

                        }
                    }
                    if(shopExists[0] == 0){
                        createAccount(email,pwd,shopName,address);
                    }

                }
            });
    }

    public void createAccount(final String email, final String password, final String shopName, final String address){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressBar.setVisibility(View.GONE);
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            UserItems userItems = new UserItems(shopName,email,password,address,profileType);
                            shopRef.add(userItems).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Toast.makeText(getApplicationContext(),"User Registered with id:"+mAuth.getCurrentUser().getEmail(),Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(),UserActivity.class);
                                    startActivity(intent);
                                }
                            });

                            //updateUI(user);
                        } else {
                            progressBar.setVisibility(View.GONE);
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(AddUserActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                           // updateUI(null);
                        }

                        // ...
                    }
                });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        profileType = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
