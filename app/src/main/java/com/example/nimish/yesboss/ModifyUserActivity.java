package com.example.nimish.yesboss;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ModifyUserActivity extends AppCompatActivity {

    TextView editShop;
    TextView editUserId;
    TextView editPwd;
    TextView editAddr;
    Button editUserDetails;
    EditText editShopText;
    EditText editUserIdText;
    EditText editPwdText;
    EditText editAddressText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_user);

        editShop = findViewById(R.id.edit_shop_name);
        editUserId = findViewById(R.id.edit_user_id);
        editPwd = findViewById(R.id.edit_password);
        editAddr = findViewById(R.id.edit_address);
        editShopText = findViewById(R.id.edit_shop_name_text);
        editUserIdText = findViewById(R.id.edit_user_id_text);
        editPwdText = findViewById(R.id.edit_pwd_text);
        editAddressText = findViewById(R.id.edit_address_text);
        editUserDetails = findViewById(R.id.update);


    }
}
