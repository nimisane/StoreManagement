package com.example.nimish.yesboss;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import static com.example.nimish.yesboss.AdminOrdersReqFragment.ORDATE;

public class UpdateStoreOrder extends AppCompatActivity {

    TextView select_category_spinner;
    TextView select_shop_spinner;

    ProgressBar progressBar;
    Button submit;
    TextView productName;
    TextView productCode;
    TextView productMrp;
    EditText fs36, hs36, sf36;
    EditText fs38, hs38, sf38;
    EditText fs40, hs40, sf40;
    EditText fs42, hs42, sf42;
    EditText fs44, hs44, sf44;
    EditText fs46, hs46, sf46;
    EditText fs48, hs48, sf48;
    EditText fs50, hs50, sf50;
    EditText fs54, hs54, sf54;
    EditText fs55, hs55, sf55;
    RecyclerView imgRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    List<String> imageLink;

    AdminSendImageAdapter adminSendOrderImgAdapter;
    ArrayList<AdminSendOrderImageItem> adminSendOrderImageItems;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference reqRef;
    private CollectionReference sendRef = db.collection("ShopOrders");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_store_order);

        imageLink = new ArrayList<>();

        productName = findViewById(R.id.add_shop_name_text);
        productCode = findViewById(R.id.add_user_id_text);
        productMrp = findViewById(R.id.mrp_edit);
        progressBar = findViewById(R.id.progress_bar_update);
        progressBar.setVisibility(View.GONE);

        imgRecyclerView = findViewById(R.id.update_product_image);

        fs36 = findViewById(R.id.fs_36);
        hs36 = findViewById(R.id.hs_36);
        sf36 = findViewById(R.id.sf_36);
        fs38 = findViewById(R.id.fs_38);
        hs38 = findViewById(R.id.hs_38);
        sf38 = findViewById(R.id.sf_38);
        fs40 = findViewById(R.id.fs_40);
        hs40 = findViewById(R.id.hs_40);
        sf40 = findViewById(R.id.sf_40);
        fs42 = findViewById(R.id.fs_42);
        hs42 = findViewById(R.id.hs_42);
        sf42 = findViewById(R.id.sf_42);
        fs44 = findViewById(R.id.fs_44);
        hs44 = findViewById(R.id.hs_44);
        sf44 = findViewById(R.id.sf_44);
        fs46 = findViewById(R.id.fs_46);
        hs46 = findViewById(R.id.hs_46);
        sf46 = findViewById(R.id.sf_46);
        fs48 = findViewById(R.id.fs_48);
        hs48 = findViewById(R.id.hs_48);
        sf48 = findViewById(R.id.sf_48);
        fs50 = findViewById(R.id.fs_50);
        hs50 = findViewById(R.id.hs_50);
        sf50 = findViewById(R.id.sf_50);
        fs54 = findViewById(R.id.fs_54);
        hs54 = findViewById(R.id.hs_54);
        sf54 = findViewById(R.id.sf_54);
        fs55 = findViewById(R.id.fs_55);
        hs55 = findViewById(R.id.hs_55);
        sf55 = findViewById(R.id.sf_55);

        submit = findViewById(R.id.submit);
        select_category_spinner = findViewById(R.id.select_category);
        select_shop_spinner = findViewById(R.id.add_pwd_text);
        adminSendOrderImageItems = new ArrayList<>();
        Intent intent = getIntent();
        String docID = intent.getStringExtra(DOCUMENTID);
        final String ordDate = intent.getStringExtra(ORDATE);
        reqRef = db.collection("ShopOrders").document(docID);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateOrder(ordDate);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        reqRef.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Toast.makeText(UpdateStoreOrder.this, "Error while loading!", Toast.LENGTH_SHORT).show();
                    Log.d("UpdateOrder", e.toString());
                    return;
                }

                if (documentSnapshot.exists()) {
                    AdminOrdersItem adminOrderItem = documentSnapshot.toObject(AdminOrdersItem.class);
                    String category = adminOrderItem.getCategory();
                    String proName = adminOrderItem.getProductName();
                    String proCode = adminOrderItem.getProductCode();
                    String shopName = adminOrderItem.getShopName();

                    adminSendOrderImageItems.clear();

                    for (String imgUrl : adminOrderItem.getImageLink()) {
                        adminSendOrderImageItems.add(new AdminSendOrderImageItem(imgUrl));
                    }

                    String mrp = adminOrderItem.getMrp();
                    Map<String, Integer> orderData = adminOrderItem.getOrderData();
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

                    imgRecyclerView.setHasFixedSize(true);
                    layoutManager = new LinearLayoutManager(UpdateStoreOrder.this, LinearLayoutManager.HORIZONTAL, false);
                    adminSendOrderImgAdapter = new AdminSendImageAdapter(adminSendOrderImageItems);
                    imgRecyclerView.setLayoutManager(layoutManager);
                    imgRecyclerView.setAdapter(adminSendOrderImgAdapter);

                    select_category_spinner.setText(category);
                    productName.setText(proName);
                    productCode.setText(proCode);
                    select_shop_spinner.setText(shopName);
                    productMrp.setText(mrp);
                    fs36.setText(String.valueOf(full36));
                    hs36.setText(String.valueOf(half36));
                    sf36.setText(String.valueOf(slim36));
                    fs38.setText(String.valueOf(full38));
                    hs38.setText(String.valueOf(half38));
                    sf38.setText(String.valueOf(slim38));
                    fs40.setText(String.valueOf(full40));
                    hs40.setText(String.valueOf(half40));
                    sf40.setText(String.valueOf(slim40));
                    fs42.setText(String.valueOf(full42));
                    hs42.setText(String.valueOf(half42));
                    sf42.setText(String.valueOf(slim42));
                    fs44.setText(String.valueOf(full44));
                    hs44.setText(String.valueOf(half44));
                    sf44.setText(String.valueOf(slim44));
                    fs46.setText(String.valueOf(full46));
                    hs46.setText(String.valueOf(half46));
                    sf46.setText(String.valueOf(slim46));
                    fs48.setText(String.valueOf(full48));
                    hs48.setText(String.valueOf(half48));
                    sf48.setText(String.valueOf(slim48));
                    fs50.setText(String.valueOf(full50));
                    hs50.setText(String.valueOf(half50));
                    sf50.setText(String.valueOf(slim50));
                    fs54.setText(String.valueOf(full54));
                    hs54.setText(String.valueOf(half54));
                    sf54.setText(String.valueOf(slim54));
                    fs55.setText(String.valueOf(full55));
                    hs55.setText(String.valueOf(half55));
                    sf55.setText(String.valueOf(slim55));
                }
            }
        });
    }

    public void updateOrder(String ordDate){
        progressBar.setVisibility(View.VISIBLE);
        final String proName = productName.getText().toString();
        final String proCode = productCode.getText().toString();
        final String proMrp = productMrp.getText().toString();
        final String category_name = select_category_spinner.getText().toString();
        final String shop_name = select_shop_spinner.getText().toString();

//        final String fs_36 = fs36.getText().toString();
//        final String hs_36 = hs36.getText().toString();
//        final String sf_36 = sf36.getText().toString();
//        final String fs_38 = fs38.getText().toString();
//        final String hs_38 = hs38.getText().toString();
//        final String sf_38 = sf38.getText().toString();
//        final String fs_40 = fs40.getText().toString();
//        final String hs_40 = hs40.getText().toString();
//        final String sf_40 = sf40.getText().toString();
//        final String fs_42 = fs42.getText().toString();
//        final String hs_42 = hs42.getText().toString();
//        final String sf_42 = sf42.getText().toString();
//        final String fs_44 = fs44.getText().toString();
//        final String hs_44 = hs44.getText().toString();
//        final String sf_44 = sf44.getText().toString();
//        final String fs_46 = fs46.getText().toString();
//        final String hs_46 = hs46.getText().toString();
//        final String sf_46 = sf46.getText().toString();
//        final String fs_48 = fs48.getText().toString();
//        final String hs_48 = hs48.getText().toString();
//        final String sf_48 = sf48.getText().toString();
//        final String fs_50 = fs50.getText().toString();
//        final String hs_50 = hs50.getText().toString();
//        final String sf_50 = sf50.getText().toString();
//        final String fs_54 = fs54.getText().toString();
//        final String hs_54 = hs54.getText().toString();
//        final String sf_54 = sf54.getText().toString();
//        final String fs_55 = fs55.getText().toString();
//        final String hs_55 = hs55.getText().toString();
//        final String sf_55 = sf55.getText().toString();

        final int fs_36 = Integer.parseInt(fs36.getText().toString());
        final int hs_36 = Integer.parseInt(hs36.getText().toString());
        final int sf_36 = Integer.parseInt(sf36.getText().toString());
        final int fs_38 = Integer.parseInt(fs38.getText().toString());
        final int hs_38 = Integer.parseInt(hs38.getText().toString());
        final int sf_38 = Integer.parseInt(sf38.getText().toString());
        final int fs_40 = Integer.parseInt(fs40.getText().toString());
        final int hs_40 = Integer.parseInt(hs40.getText().toString());
        final int sf_40 = Integer.parseInt(sf40.getText().toString());
        final int fs_42 = Integer.parseInt(fs42.getText().toString());
        final int hs_42 = Integer.parseInt(hs42.getText().toString());
        final int sf_42 = Integer.parseInt(sf42.getText().toString());
        final int fs_44 = Integer.parseInt(fs44.getText().toString());
        final int hs_44 = Integer.parseInt(hs44.getText().toString());
        final int sf_44 = Integer.parseInt(sf44.getText().toString());
        final int fs_46 = Integer.parseInt(fs46.getText().toString());
        final int hs_46 = Integer.parseInt(hs46.getText().toString());
        final int sf_46 = Integer.parseInt(sf46.getText().toString());
        final int fs_48 = Integer.parseInt(fs48.getText().toString());
        final int hs_48 = Integer.parseInt(hs48.getText().toString());
        final int sf_48 = Integer.parseInt(sf48.getText().toString());
        final int fs_50 = Integer.parseInt(fs50.getText().toString());
        final int hs_50 = Integer.parseInt(hs50.getText().toString());
        final int sf_50 = Integer.parseInt(sf50.getText().toString());
        final int fs_54 = Integer.parseInt(fs54.getText().toString());
        final int hs_54 = Integer.parseInt(hs54.getText().toString());
        final int sf_54 = Integer.parseInt(sf54.getText().toString());
        final int fs_55 = Integer.parseInt(fs55.getText().toString());
        final int hs_55 = Integer.parseInt(hs55.getText().toString());
        final int sf_55 = Integer.parseInt(sf55.getText().toString());

        final Map<String, Integer> orderData = new HashMap<String, Integer>();
        orderData.put(FULL36, fs_36);
        orderData.put(HALF36, hs_36);
        orderData.put(SLIM36, sf_36);
        orderData.put(FULl38, fs_38);
        orderData.put(HALF38, hs_38);
        orderData.put(SLIM38, sf_38);
        orderData.put(FULL40, fs_40);
        orderData.put(HALF40, hs_40);
        orderData.put(SLIM40, sf_40);
        orderData.put(FULL42, fs_42);
        orderData.put(HALF42, hs_42);
        orderData.put(SLIM42, sf_42);
        orderData.put(FULL44, fs_44);
        orderData.put(HALF44, hs_44);
        orderData.put(SLIM44, sf_44);
        orderData.put(FULL46, fs_46);
        orderData.put(HALF46, hs_46);
        orderData.put(SLIM46, sf_46);
        orderData.put(FULL48, fs_48);
        orderData.put(HALF48, hs_48);
        orderData.put(SLIM48, sf_48);
        orderData.put(FULL50, fs_50);
        orderData.put(HALF50, hs_50);
        orderData.put(SLIM50, sf_50);
        orderData.put(FULL54, fs_54);
        orderData.put(HALF54, hs_54);
        orderData.put(SLIM54, sf_54);
        orderData.put(FULL55, fs_55);
        orderData.put(HALF55, hs_55);
        orderData.put(SLIM55, sf_55);

        for(int i=0;i<adminSendOrderImageItems.size();i++){
            imageLink.add(adminSendOrderImageItems.get(i).getImgUrl());
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh.mm aa");
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        SimpleDateFormat dateOnlyFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateOnly = dateOnlyFormat.format(new Date());
        String sortDate = dateFormat2.format(new Date());
        String currentDate = dateFormat.format(new Date());

        reqRef.set(new AdminOrdersItem(proName,proCode,shop_name,category_name,imageLink,orderData,proMrp,currentDate,sortDate,dateOnly,ordDate,"Pending"))
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(),"Data Updated Successfully",Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
//                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
//                        startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(),"Operation Failed",Toast.LENGTH_SHORT).show();
                        Log.d("updateFailed",e.getMessage());
                    }
                });
    }
}
