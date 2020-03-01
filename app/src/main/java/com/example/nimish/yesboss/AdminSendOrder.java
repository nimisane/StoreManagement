package com.example.nimish.yesboss;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;
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

public class AdminSendOrder extends AppCompatActivity {

    EditText select_category_spinner;
    EditText select_shop_spinner;
    String category_name;
    String shop_name;
    Button submit;
    EditText productName;
    EditText productCode;
    EditText productMrp;
    EditText fs36,hs36,sf36;
    EditText fs38,hs38,sf38;
    EditText fs40,hs40,sf40;
    EditText fs42,hs42,sf42;
    EditText fs44,hs44,sf44;
    EditText fs46,hs46,sf46;
    EditText fs48,hs48,sf48;
    EditText fs50,hs50,sf50;
    EditText fs54,hs54,sf54;
    EditText fs55,hs55,sf55;
    RecyclerView imgRecyclerView;
    RecyclerView.LayoutManager layoutManager;

    AdminSendImageAdapter adminSendOrderImgAdapter;
    ArrayList<AdminSendOrderImageItem> adminSendOrderImageItems;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference reqRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_send_order);

        productName = findViewById(R.id.add_shop_name_text);
        productCode = findViewById(R.id.add_user_id_text);
        productMrp = findViewById(R.id.mrp_edit);

        imgRecyclerView = findViewById(R.id.add_product_image);

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
        reqRef = db.collection("AdminOrders").document(docID);
    }

    @Override
    protected void onStart() {
        super.onStart();
        reqRef.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if(e != null){
                    Toast.makeText(AdminSendOrder.this, "Error while loading!", Toast.LENGTH_SHORT).show();
                    Log.d("AdminSendOrder", e.toString());
                    return;
                }

                if(documentSnapshot.exists()){
                    AdminOrderItem adminOrderItem = documentSnapshot.toObject(AdminOrderItem.class);
                    String category = adminOrderItem.getCategory();
                    String proName = adminOrderItem.getProductName();
                    String proCode = adminOrderItem.getProductCode();
                    String shopName = adminOrderItem.getShopName();

                    adminSendOrderImageItems.clear();

                    for(String imgUrl : adminOrderItem.getImgLink()){
                        adminSendOrderImageItems.add(new AdminSendOrderImageItem(imgUrl));
                    }

                    String mrp = adminOrderItem.getMrp();
                    Map<String,String> orderData = adminOrderItem.getOrderData();
                    String full36 = orderData.get(FULL36);
                    String half36 = orderData.get(HALF36);
                    String slim36 = orderData.get(SLIM36);
                    String full38 = orderData.get(FULl38);
                    String half38 = orderData.get(HALF38);
                    String slim38 = orderData.get(SLIM38);
                    String full40 = orderData.get(FULL40);
                    String half40 = orderData.get(HALF40);
                    String slim40 = orderData.get(SLIM40);
                    String full42 = orderData.get(FULL42);
                    String half42 = orderData.get(HALF42);
                    String slim42 = orderData.get(SLIM42);
                    String full44 = orderData.get(FULL44);
                    String half44 = orderData.get(HALF44);
                    String slim44 = orderData.get(SLIM44);
                    String full46 = orderData.get(FULL46);
                    String half46 = orderData.get(HALF46);
                    String slim46 = orderData.get(SLIM46);
                    String full48 = orderData.get(FULL48);
                    String half48 = orderData.get(HALF48);
                    String slim48 = orderData.get(SLIM48);
                    String full50 = orderData.get(FULL50);
                    String half50 = orderData.get(HALF50);
                    String slim50 = orderData.get(SLIM50);
                    String full54 = orderData.get(FULL54);
                    String half54 = orderData.get(HALF54);
                    String slim54 = orderData.get(SLIM54);
                    String full55 = orderData.get(FULL55);
                    String half55 = orderData.get(HALF55);
                    String slim55 = orderData.get(SLIM55);

                    imgRecyclerView.setHasFixedSize(true);
                    layoutManager = new LinearLayoutManager(AdminSendOrder.this,LinearLayoutManager.HORIZONTAL,false);
                    adminSendOrderImgAdapter = new AdminSendImageAdapter(adminSendOrderImageItems);
                    imgRecyclerView.setLayoutManager(layoutManager);
                    imgRecyclerView.setAdapter(adminSendOrderImgAdapter);

                    select_category_spinner.setText(category);
                    productName.setText(proName);
                    productCode.setText(proCode);
                    select_shop_spinner.setText(shopName);
                    productMrp.setText(mrp);
                    fs36.setText(full36);
                    hs36.setText(half36);
                    sf36.setText(slim36);
                    fs38.setText(full38);
                    hs38.setText(half38);
                    sf38.setText(slim38);
                    fs40.setText(full40);
                    hs40.setText(half40);
                    sf40.setText(slim40);
                    fs42.setText(full42);
                    hs42.setText(half42);
                    sf42.setText(slim42);
                    fs44.setText(full44);
                    hs44.setText(half44);
                    sf44.setText(slim44);
                    fs46.setText(full46);
                    hs46.setText(half46);
                    sf46.setText(slim46);
                    fs48.setText(full48);
                    hs48.setText(half48);
                    sf48.setText(slim48);
                    fs50.setText(full50);
                    hs50.setText(half50);
                    sf50.setText(slim50);
                    fs54.setText(full54);
                    hs54.setText(half54);
                    sf54.setText(slim54);
                    fs55.setText(full55);
                    hs55.setText(half55);
                    sf55.setText(slim55);
                }
            }
        });
    }


}
