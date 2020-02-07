package com.example.nimish.yesboss;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddUserActivity extends AppCompatActivity {

    TextView addShop;
    TextView addUserId;
    TextView addPwd;
    TextView addAddr;
    Button addUserDetails;
    EditText addShopText;
    EditText addUserIdText;
    EditText addPwdText;
    EditText addAddressText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_clear_black_24dp);// set drawable icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        addShop = findViewById(R.id.add_shop_name);
        addUserId = findViewById(R.id.add_user_id);
        addPwd = findViewById(R.id.add_password);
        addAddr = findViewById(R.id.add_address);
        addShopText = findViewById(R.id.add_shop_name_text);
        addUserIdText = findViewById(R.id.add_user_id_text);
        addPwdText = findViewById(R.id.add_pwd_text);
        addAddressText = findViewById(R.id.add_address_text);
        addUserDetails = findViewById(R.id.add_user_button);

    }
}
