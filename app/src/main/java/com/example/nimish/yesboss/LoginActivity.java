package com.example.nimish.yesboss;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;

public class LoginActivity extends AppCompatActivity {

    ProgressBar progressBar;
    Button loginButton;
    TextInputLayout userID;
    TextInputLayout password;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference shopRef = db.collection("Shops");
    final static String TAG = "LoginActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        loginButton = findViewById(R.id.login_button);
        progressBar = findViewById(R.id.progressBar);
        userID = findViewById(R.id.user_ID_layout);
        password = findViewById(R.id.password_login_layout);

        progressBar.setVisibility(View.GONE);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String email = userID.getEditText().getText().toString();
                String pwd = password.getEditText().getText().toString();

                if(email.isEmpty() || pwd.isEmpty()){
                    showToast("Please enter valid login details",0);
                    //Toast.makeText(getApplicationContext(),"Please enter valid login details",Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    return;
                }

                signIn(email,pwd);



//                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
//                startActivity(intent);
            }
        });
    }


    public void signIn(final String email, final String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            progressBar.setVisibility(View.GONE);
                            FirebaseUser user = mAuth.getCurrentUser();
                            String userEmail = mAuth.getCurrentUser().getEmail();
                            SharedPreferenceManager.getInstance(getApplicationContext()).setUser(userEmail);
                            final String[] proType = {""};
                            shopRef.whereEqualTo("email",email).whereEqualTo("profile_type","Admin Account").get()
                                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                        @Override
                                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                            for(DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                                proType[0] = documentSnapshot.get("profile_type").toString();
                                              //  Toast.makeText(getApplicationContext(), proType, Toast.LENGTH_SHORT).show();

                                            }
                                            if (proType[0].equals("Admin Account")){
                                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                                startActivity(intent);
                                                finish();
                                            }
                                            else {
                                                showToast("Invalid login details",0);
                                               // Toast.makeText(getApplicationContext(), "Invalid login details", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            showToast("Invaild Account Details",0);
                                            //Toast.makeText(getApplicationContext(),"Invaild Account Details",Toast.LENGTH_SHORT).show();
                                        }
                                    });

                       //     updateUI(user);
                        } else {
                            progressBar.setVisibility(View.GONE);
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            showToast("Authentication failed.",0);
                           // Toast.makeText(LoginActivity.this, "Authentication failed.",Toast.LENGTH_SHORT).show();

                         //   updateUI(null);
                        }

                        // ...
                    }
                });
    }

    public void showToast(String message,int status){
        LayoutInflater inflater = getLayoutInflater();
        View layout = null;
        if(status == 1) {
            layout = inflater.inflate(R.layout.toast_layout, (ViewGroup) findViewById(R.id.toast_msg_layout));
        }
        else if(status == 0) {
            layout = inflater.inflate(R.layout.fail_toast_layout, (ViewGroup) findViewById(R.id.toast_msg_layout));
        }
        TextView toastText = layout.findViewById(R.id.toast_message);
        toastText.setText(message);

        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();

    }

}
