package com.example.nimish.yesboss;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import static com.example.nimish.yesboss.AllOrdersFragment.CATEGORY;
import static com.example.nimish.yesboss.CategoryPatternGrid.PROCODE;

public class TotalOrderDetails extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {//implements AdapterView.OnItemSelectedListener{

    TextView catPattern;
    TextView totalOrder;
    TextView fs36,hs36,sf36;
    TextView fs38,hs38,sf38;
    TextView fs40,hs40,sf40;
    TextView fs42,hs42,sf42;
    TextView fs44,hs44,sf44;
    TextView fs46,hs46,sf46;
    TextView fs48,hs48,sf48;
    TextView fs50,hs50,sf50;
    TextView fs54,hs54,sf54;
    TextView fs55,hs55,sf55;
    TextView totalfs,totalhs,totalsf;
    TextView selectDate;
    String currentDate = "";



    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference allOrderRef = db.collection("ShopOrders");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_order_details);

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
        totalfs = findViewById(R.id.total_fs);
        totalhs = findViewById(R.id.total_hs);
        totalsf = findViewById(R.id.total_sf);
        totalOrder = findViewById(R.id.total_order);
        catPattern = findViewById(R.id.pattern_text);
        selectDate = findViewById(R.id.select_date);


        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        final String category = intent.getStringExtra(CATEGORY);
        final String proCode = intent.getStringExtra(PROCODE);
        SimpleDateFormat dateOnlyFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateOnly = dateOnlyFormat.format(new Date());
        selectDate.setText("Order Date: "+dateOnly);
        calculateTotal(proCode,category,dateOnly);
    }


    public void calculateTotal(final String proCode, final String category,String currentDate){
        allOrderRef.whereEqualTo("productCode",proCode).whereEqualTo("category",category).whereEqualTo("ordDate",currentDate)
                .addSnapshotListener(this,new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
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
                        fs36.setText(String.valueOf(fullTotal36));
                        hs36.setText(String.valueOf(halfTotal36));
                        sf36.setText(String.valueOf(slimTotal36));
                        fs38.setText(String.valueOf(fullTotal38));
                        hs38.setText(String.valueOf(halfTotal38));
                        sf38.setText(String.valueOf(slimTotal38));
                        fs40.setText(String.valueOf(fullTotal40));
                        hs40.setText(String.valueOf(halfTotal40));
                        sf40.setText(String.valueOf(slimTotal40));
                        fs42.setText(String.valueOf(fullTotal42));
                        hs42.setText(String.valueOf(halfTotal42));
                        sf42.setText(String.valueOf(slimTotal42));
                        fs44.setText(String.valueOf(fullTotal44));
                        hs44.setText(String.valueOf(halfTotal44));
                        sf44.setText(String.valueOf(slimTotal44));
                        fs46.setText(String.valueOf(fullTotal46));
                        hs46.setText(String.valueOf(halfTotal46));
                        sf46.setText(String.valueOf(slimTotal46));
                        fs48.setText(String.valueOf(fullTotal48));
                        hs48.setText(String.valueOf(halfTotal48));
                        sf48.setText(String.valueOf(slimTotal48));
                        fs50.setText(String.valueOf(fullTotal50));
                        hs50.setText(String.valueOf(halfTotal50));
                        sf50.setText(String.valueOf(slimTotal50));
                        fs54.setText(String.valueOf(fullTotal54));
                        hs54.setText(String.valueOf(halfTotal54));
                        sf54.setText(String.valueOf(slimTotal54));
                        fs55.setText(String.valueOf(fullTotal55));
                        hs55.setText(String.valueOf(halfTotal55));
                        sf55.setText(String.valueOf(slimTotal55));
                        totalfs.setText(String.valueOf(fullTotal));
                        totalhs.setText(String.valueOf(halfTotal));
                        totalsf.setText(String.valueOf(slimTotal));
                        totalOrder.setText("Total Order: "+totalSum);
                        catPattern.setText("Category: "+category+"\nProductCode: "+proCode);
                    }
                });
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        SimpleDateFormat dateOnlyFormat = new SimpleDateFormat("yyyy-MM-dd");
        currentDate = dateOnlyFormat.format(c.getTime());
        selectDate.setText("Order Date: "+currentDate);
        Intent intent = getIntent();
        final String category = intent.getStringExtra(CATEGORY);
        final String proCode = intent.getStringExtra(PROCODE);
        calculateTotal(proCode,category,currentDate);
    }
}
