package com.example.nimish.yesboss;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AdminOrderReqActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final int RESULT_LOAD_IMAGE = 1;
    Spinner select_category_spinner;
    Spinner select_shop_spinner;
    String category_name;
    String shop_name;
    Button submit;
    EditText c;
    Button addPhotoButton;
    RecyclerView photoRecyclerView;
    AdminProductPhotoAdapter adminProductPhotoAdapter;
    ArrayList<AdminProductPhotoItems> adminProductPhotoItems;
    private RecyclerView.LayoutManager layoutManager;
    private Uri imageUri;
    ImageView noImage;
    LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_order_req);


        //container = (LinearLayout)findViewById(R.id.container);

        adminProductPhotoItems = new ArrayList<>();

        noImage = findViewById(R.id.no_image);
        addPhotoButton = findViewById(R.id.add_photo_button);
        c = findViewById(R.id.add_shop_name_text);
        submit = findViewById(R.id.submit);
        select_category_spinner = findViewById(R.id.select_category);
        select_shop_spinner = findViewById(R.id.add_pwd_text);


        adminProductPhotoAdapter = new AdminProductPhotoAdapter(adminProductPhotoItems);
        photoRecyclerView = findViewById(R.id.add_product_image);
        photoRecyclerView.setHasFixedSize(true);


        layoutManager = new LinearLayoutManager(getApplicationContext());
        photoRecyclerView.setLayoutManager(layoutManager);
        photoRecyclerView.setAdapter(adminProductPhotoAdapter);


        ArrayAdapter<CharSequence> productCategorySpinnerAdapter = ArrayAdapter.createFromResource(this,R.array.product_category_name,android.R.layout.simple_spinner_item);
        productCategorySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        select_category_spinner.setAdapter(productCategorySpinnerAdapter);
        select_category_spinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> shopNameSpinnerAdapter = ArrayAdapter.createFromResource(this,R.array.shop,android.R.layout.simple_spinner_item);
        shopNameSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        select_shop_spinner.setAdapter(shopNameSpinnerAdapter);
        select_shop_spinner.setOnItemSelectedListener(this);

        addPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Picture"), RESULT_LOAD_IMAGE);
            }
        });

        adminProductPhotoAdapter.setOnItemClickListener(new AdminProductPhotoAdapter.OnItemClickListener() {
            @Override
            public void onDelete(int position) {
                removeItem(position);
                if(adminProductPhotoItems.isEmpty()){
                    noImage.setVisibility(View.VISIBLE);
                }
            }
        });
//        submit.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                LayoutInflater layoutInflater =
////                        (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
////                final View addView = layoutInflater.inflate(R.layout.row, null);
////                EditText textOut = (EditText) addView.findViewById(R.id.textout);
////                textOut.setText(c.getText().toString());
////                String xy = textOut.getText().toString();
////                Button buttonRemove = (Button)addView.findViewById(R.id.remove);
////                buttonRemove.setOnClickListener(new View.OnClickListener(){
////
////                    @Override
////                    public void onClick(View v) {
////                        ((LinearLayout)addView.getParent()).removeView(addView);
////                    }});
////
////                container.addView(addView);
////            }
////        });

    }

    public void removeItem(int position){
        adminProductPhotoItems.remove(position);
        adminProductPhotoAdapter.notifyItemRemoved(position);
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK){

            if(data.getClipData() != null){

                int totalItemsSelected = data.getClipData().getItemCount();

                for(int i = 0; i < totalItemsSelected; i++){

                    Uri fileUri = data.getClipData().getItemAt(i).getUri();

                    String fileName = getFileName(fileUri);

                    adminProductPhotoItems.add(new AdminProductPhotoItems("uploading",fileName,fileUri));
                    noImage.setVisibility(View.GONE);
                    adminProductPhotoAdapter.notifyDataSetChanged();

//                    StorageReference fileToUpload = mStorage.child("Images").child(fileName);
//
//                    final int finalI = i;
//                    fileToUpload.putFile(fileUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//
//                            fileDoneList.remove(finalI);
//                            fileDoneList.add(finalI, "done");
//
//                            uploadListAdapter.notifyDataSetChanged();
//
//                        }
//                    });

                }

                //Toast.makeText(MainActivity.this, "Selected Multiple Files", Toast.LENGTH_SHORT).show();

            } else if (data.getData() != null){

                Uri fileUri = data.getData();
                String fileName = getFileName(fileUri);

                adminProductPhotoItems.add(new AdminProductPhotoItems("uploading",fileName,fileUri));
                noImage.setVisibility(View.GONE);
                adminProductPhotoAdapter.notifyDataSetChanged();
                Toast.makeText(AdminOrderReqActivity.this, "Selected Single File", Toast.LENGTH_SHORT).show();

            }

        }

    }


    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }
}
