package com.example.nimish.yesboss;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class AdminOrderReqActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner select_category_spinner;
    Spinner select_shop_spinner;
    String category_name;
    String shop_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_order_req);
        select_category_spinner = findViewById(R.id.select_category);
        select_shop_spinner = findViewById(R.id.add_pwd_text);

        ArrayAdapter<CharSequence> productCategorySpinnerAdapter = ArrayAdapter.createFromResource(this,R.array.product_category_name,android.R.layout.simple_spinner_item);
        productCategorySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        select_category_spinner.setAdapter(productCategorySpinnerAdapter);
        select_category_spinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> shopNameSpinnerAdapter = ArrayAdapter.createFromResource(this,R.array.shop,android.R.layout.simple_spinner_item);
        shopNameSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        select_shop_spinner.setAdapter(shopNameSpinnerAdapter);
        select_shop_spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spin = (Spinner)parent;
        if(spin.getId() == R.id.select_category)
        {
            category_name=spin.getItemAtPosition(position).toString();
            Toast.makeText(this,category_name,Toast.LENGTH_SHORT).show();
        }
        else if(spin.getId() == R.id.add_pwd_text)
        {
            shop_name=spin.getItemAtPosition(position).toString();
            Toast.makeText(this,shop_name,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
