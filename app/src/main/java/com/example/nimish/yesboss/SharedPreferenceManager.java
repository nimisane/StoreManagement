package com.example.nimish.yesboss;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import static android.content.Context.MODE_PRIVATE;

public class SharedPreferenceManager {

    private static SharedPreferenceManager mInstance;
    private static Context context;
    String shop_name;
    String docID;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference shopRef = db.collection("Shops");
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String USEREMAIL = "email";
    public static final String USERSHOP = "shop_name";
    public static final String USERDOCID = "docID";

    public SharedPreferenceManager(Context context) {
        this.context = context;
    }


    public static synchronized SharedPreferenceManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPreferenceManager(context);
        }
        return mInstance;
    }

    public void setUser(final String user){
        shopRef.whereEqualTo("email",user).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                            shop_name = documentSnapshot.get("shop_name").toString();
                            docID = documentSnapshot.getId();

                        }
                        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(USEREMAIL,user);
                        editor.putString(USERSHOP,shop_name);
                        editor.putString(USERDOCID,docID);
                        editor.apply();
                    }
                });

    }

    public String getUseremail(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        return sharedPreferences.getString(USEREMAIL,"");
    }

    public String getDocumentID(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        return sharedPreferences.getString(USERDOCID,"");
    }

    public String getShopName(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        return sharedPreferences.getString(USERSHOP,"");
    }
}
