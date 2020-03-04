package com.example.nimish.yesboss;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;

import static com.example.nimish.yesboss.AdminOrderReqActivity.FULL36;
import static com.example.nimish.yesboss.AdminOrderReqActivity.FULL40;
import static com.example.nimish.yesboss.AdminOrderReqActivity.FULL42;
import static com.example.nimish.yesboss.AdminOrderReqActivity.FULL44;
import static com.example.nimish.yesboss.AdminOrderReqActivity.FULL46;
import static com.example.nimish.yesboss.AdminOrderReqActivity.FULL48;
import static com.example.nimish.yesboss.AdminOrderReqActivity.FULL50;
import static com.example.nimish.yesboss.AdminOrderReqActivity.FULL54;
import static com.example.nimish.yesboss.AdminOrderReqActivity.FULL55;
import static com.example.nimish.yesboss.AdminOrderReqActivity.FULl38;
import static com.example.nimish.yesboss.AdminOrderReqActivity.HALF36;
import static com.example.nimish.yesboss.AdminOrderReqActivity.HALF38;
import static com.example.nimish.yesboss.AdminOrderReqActivity.HALF40;
import static com.example.nimish.yesboss.AdminOrderReqActivity.HALF42;
import static com.example.nimish.yesboss.AdminOrderReqActivity.HALF44;
import static com.example.nimish.yesboss.AdminOrderReqActivity.HALF46;
import static com.example.nimish.yesboss.AdminOrderReqActivity.HALF48;
import static com.example.nimish.yesboss.AdminOrderReqActivity.HALF50;
import static com.example.nimish.yesboss.AdminOrderReqActivity.HALF54;
import static com.example.nimish.yesboss.AdminOrderReqActivity.HALF55;
import static com.example.nimish.yesboss.AdminOrderReqActivity.SLIM36;
import static com.example.nimish.yesboss.AdminOrderReqActivity.SLIM38;
import static com.example.nimish.yesboss.AdminOrderReqActivity.SLIM40;
import static com.example.nimish.yesboss.AdminOrderReqActivity.SLIM42;
import static com.example.nimish.yesboss.AdminOrderReqActivity.SLIM44;
import static com.example.nimish.yesboss.AdminOrderReqActivity.SLIM46;
import static com.example.nimish.yesboss.AdminOrderReqActivity.SLIM48;
import static com.example.nimish.yesboss.AdminOrderReqActivity.SLIM50;
import static com.example.nimish.yesboss.AdminOrderReqActivity.SLIM54;
import static com.example.nimish.yesboss.AdminOrderReqActivity.SLIM55;
import static com.example.nimish.yesboss.AdminOrdersReqFragment.DOCUMENTID;
import static com.example.nimish.yesboss.AllOrdersFragment.CATEGORY;
import static com.example.nimish.yesboss.CategoryPatternGrid.PROCODE;

public class AllOrderCategoryList extends AppCompatActivity {

    RecyclerView allordercatergory;
    RelativeLayout total;
    private StoreOrderAdapterUI storeOrderAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference allOrderRef = db.collection("ShopOrders");
   // TextView totalOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_order_category_list);

        allordercatergory = findViewById(R.id.pattern_order_recycler);
        total = findViewById(R.id.register);
        //totalOrder = findViewById(R.id.total_order_text);

        Intent intent = getIntent();
        final String category = intent.getStringExtra(CATEGORY);
        final String proCode = intent.getStringExtra(PROCODE);
        loadRecyclerView(proCode,category);

        total.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),TotalOrderDetails.class);
                intent.putExtra(CATEGORY,category);
                intent.putExtra(PROCODE,proCode);
                startActivity(intent);
            }
        });
        //calculateTotal(proCode,category);
    }

    public void loadRecyclerView(String proCode,String category){

        Query query = allOrderRef.whereEqualTo("productCode",proCode).whereEqualTo("category",category).orderBy("sortDate", Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<AdminOrdersItem> options = new FirestoreRecyclerOptions.Builder<AdminOrdersItem>()
                .setQuery(query,AdminOrdersItem.class)
                .build();

        storeOrderAdapter = new StoreOrderAdapterUI(options);
        allordercatergory.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);

        allordercatergory.setLayoutManager(layoutManager);
        allordercatergory.setAdapter(storeOrderAdapter);

        storeOrderAdapter.setOnItemClickListener(new StoreOrderAdapterUI.OnItemClicklistener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                Intent intent = new Intent(getApplicationContext(),UpdateStoreOrder.class);
                String docID = documentSnapshot.getId();
                intent.putExtra(DOCUMENTID,docID);
                startActivity(intent);
            }

            @Override
            public void onDeleteClick(DocumentSnapshot documentSnapshot, int position) {
                storeOrderAdapter.deleteItem(position);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        storeOrderAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        storeOrderAdapter.stopListening();
    }

    public void calculateTotal(String proCode,String category){
        allOrderRef.whereEqualTo("productCode",proCode).whereEqualTo("category",category)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        int fullTotal = 0;
                        int halfTotal = 0;
                        int slimTotal = 0;
                        int totalSum = 0;
                        int fullTotal36 = 0;
                        int halfTotal36 = 0;
                        int slimTotal36 = 0;
                        int fullTotal38 = 0;
                        int halfTotal38 = 0;
                        int slimTotal38 = 0;
                        int fullTotal40 = 0;
                        int halfTotal40 = 0;
                        int slimTotal40 = 0;
                        int fullTotal42 = 0;
                        int halfTotal42 = 0;
                        int slimTotal42 = 0;
                        int fullTotal44 = 0;
                        int halfTotal44 = 0;
                        int slimTotal44 = 0;
                        int fullTotal46 = 0;
                        int halfTotal46 = 0;
                        int slimTotal46 = 0;
                        int fullTotal48 = 0;
                        int halfTotal48 = 0;
                        int slimTotal48 = 0;
                        int fullTotal50 = 0;
                        int halfTotal50 = 0;
                        int slimTotal50 = 0;
                        int fullTotal54 = 0;
                        int halfTotal54 = 0;
                        int slimTotal54 = 0;
                        int fullTotal55 = 0;
                        int halfTotal55 = 0;
                        int slimTotal55 = 0;
                        Map<String,Integer> orderData;
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                            AdminOrdersItem adminOrdersItem = documentSnapshot.toObject(AdminOrdersItem.class);
                            orderData = adminOrdersItem.getOrderData();
                            int full36 = orderData.get(FULL36);
                            int half36 = orderData.get(HALF36);
                            int slim36 = orderData.get(SLIM36);
                            int full38 = orderData.get(FULl38);
                            int half38 = orderData.get(HALF38);
                            int slim38 = orderData.get(SLIM38);
                            int full40 = orderData.get(FULL40);
                            int half40 = orderData.get(HALF40);
                            int slim40 = orderData.get(SLIM40);
                            int full42 = orderData.get(FULL42);
                            int half42 = orderData.get(HALF42);
                            int slim42 = orderData.get(SLIM42);
                            int full44 = orderData.get(FULL44);
                            int half44 = orderData.get(HALF44);
                            int slim44 = orderData.get(SLIM44);
                            int full46 = orderData.get(FULL46);
                            int half46 = orderData.get(HALF46);
                            int slim46 = orderData.get(SLIM46);
                            int full48 = orderData.get(FULL48);
                            int half48 = orderData.get(HALF48);
                            int slim48 = orderData.get(SLIM48);
                            int full50 = orderData.get(FULL50);
                            int half50 = orderData.get(HALF50);
                            int slim50 = orderData.get(SLIM50);
                            int full54 = orderData.get(FULL54);
                            int half54 = orderData.get(HALF54);
                            int slim54 = orderData.get(SLIM54);
                            int full55 = orderData.get(FULL55);
                            int half55 = orderData.get(HALF55);
                            int slim55 = orderData.get(SLIM55);

                            fullTotal36 += full36;
                            fullTotal38 += full38;
                            fullTotal40 += full40;
                            fullTotal42 += full42;
                            fullTotal44 += full44;
                            fullTotal46 += full46;
                            fullTotal48 += full48;
                            fullTotal50 += full50;
                            fullTotal54 += full54;
                            fullTotal55 += full55;

                            halfTotal36 += half36;
                            halfTotal38 += half38;
                            halfTotal40 += half40;
                            halfTotal42 += half42;
                            halfTotal44 += half44;
                            halfTotal46 += half46;
                            halfTotal48 += half48;
                            halfTotal50 += half50;
                            halfTotal54 += half54;
                            halfTotal55 += half55;

                            slimTotal36 += slim36;
                            slimTotal38 += slim38;
                            slimTotal40 += slim40;
                            slimTotal42 += slim42;
                            slimTotal44 += slim44;
                            slimTotal46 += slim46;
                            slimTotal48 += slim48;
                            slimTotal50 += slim50;
                            slimTotal54 += slim54;
                            slimTotal55 += slim55;

                            fullTotal += full36+full38+full40+full42+full44+full46+full48+full50+full54+full55;
                            halfTotal += half36+half38+half40+half42+half44+half46+half48+half50+half54+half55;
                            slimTotal += slim36+slim38+slim40+slim42+slim44+slim46+slim48+slim50+slim54+slim55;

                        }
                        totalSum = fullTotal+slimTotal+halfTotal;
                        Toast.makeText(getApplicationContext(),String.valueOf(fullTotal),Toast.LENGTH_SHORT).show();
//                        totalOrder.setText("Full sleeves :"+String.valueOf(fullTotal)
//                        +"\n"+"Half sleeves :"+String.valueOf(halfTotal)+"\n"+"Slim Fit: "+String.valueOf(slimTotal)
//                                +"\n"+"Total Order:"+totalSum
//                                +"\n Full 36"+fullTotal36
//                                        +"\n Full 38: "+fullTotal38
//                                        +"\n Full 40: "+fullTotal40
//                                        +"\n Full 42: "+fullTotal42
//                                        +"\n Full 44: "+fullTotal44
//                                        +"\n Full 46: "+fullTotal46
//                                        +"\n Full 48: "+fullTotal48
//                                        +"\n Full 50: "+fullTotal50
//                                        +"\n Full 54: "+fullTotal54
//                                        +"\n Full 55: "+fullTotal55
//
//                                        +"\n Half 36"+halfTotal36
//                                        +"\n Half 38: "+halfTotal38
//                                        +"\n Half 40: "+halfTotal40
//                                        +"\n Half 42: "+halfTotal42
//                                        +"\n Half 44: "+halfTotal44
//                                        +"\n Half 46: "+halfTotal46
//                                        +"\n Half 48: "+halfTotal48
//                                        +"\n Half 50: "+halfTotal50
//                                        +"\n Half 54: "+halfTotal54
//                                        +"\n Half 55: "+halfTotal55
//
//                                        +"\n Slim 36"+slimTotal36
//                                        +"\n Slim 38: "+slimTotal38
//                                        +"\n Slim 40: "+slimTotal40
//                                        +"\n Slim 42: "+slimTotal42
//                                        +"\n Slim 44: "+slimTotal44
//                                        +"\n Slim 46: "+slimTotal46
//                                        +"\n Slim 48: "+slimTotal48
//                                        +"\n Slim 50: "+slimTotal50
//                                        +"\n Slim 54: "+slimTotal54
//                                        +"\n Slim 55: "+slimTotal55
//                        );
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

    }
}
