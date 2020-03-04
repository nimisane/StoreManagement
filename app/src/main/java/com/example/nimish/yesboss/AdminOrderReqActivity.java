package com.example.nimish.yesboss;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminOrderReqActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final int RESULT_LOAD_IMAGE = 1;
    Spinner select_category_spinner;
    Spinner select_shop_spinner;
    String category_name;
    String shop_name;
    Button submit;
    EditText c;
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
    Button addPhotoButton;
    RecyclerView photoRecyclerView;
    ProgressBar progressBar,circularProgress;;
    AdminProductPhotoAdapter adminProductPhotoAdapter;
    ArrayList<AdminProductPhotoItems> adminProductPhotoItems;
    private RecyclerView.LayoutManager layoutManager;
    private Uri imageUri;
    int totalItemsSelected;
    ImageView noImage;
    LinearLayout container;
    List<AddCategoryItem> categoryList;
    List<ShopNames> shopNamesList;
    String fileName;
    private StorageReference mStorageRef;
    List<String> imgDownload;
    private StorageTask mUploadTask;
    public static final String FULL36 ="full_36";
    public static final String HALF36 ="half_36";
    public static final String SLIM36 ="slim_36";
    public static final String FULl38 ="full_38";
    public static final String HALF38 ="half_38";
    public static final String SLIM38 ="slim_38";
    public static final String FULL40 ="full_40";
    public static final String HALF40 ="half_40";
    public static final String SLIM40 ="slim_40";
    public static final String FULL42 ="full_42";
    public static final String HALF42 ="half_42";
    public static final String SLIM42 ="slim_42";
    public static final String FULL44 ="full_44";
    public static final String HALF44 ="half_44";
    public static final String SLIM44 ="slim_44";
    public static final String FULL46 ="full_46";
    public static final String HALF46 ="half_46";
    public static final String SLIM46 ="slim_46";
    public static final String FULL48 ="full_48";
    public static final String HALF48 ="half_48";
    public static final String SLIM48 ="slim_48";
    public static final String FULL50 ="full_50";
    public static final String HALF50 ="half_50";
    public static final String SLIM50 ="slim_50";
    public static final String FULL54 ="full_54";
    public static final String HALF54 ="half_54";
    public static final String SLIM54 ="slim_54";
    public static final String FULL55 ="full_55";
    public static final String HALF55 ="half_55";
    public static final String SLIM55 ="slim_55";

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference categoryRef = db.collection("Shirt Category");
    private CollectionReference shopRef = db.collection("Shops");
    private CollectionReference reqRef = db.collection("AdminOrders");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_order_req);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_clear_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressBar = findViewById(R.id.refresh);
        circularProgress = findViewById(R.id.process_bar);
        circularProgress.setVisibility(View.GONE);
       // progressBar.setVisibility(View.GONE);
        mStorageRef = FirebaseStorage.getInstance().getReference("uploads");
        categoryList = new ArrayList<>();
        shopNamesList = new ArrayList<>();
        loadCategory();
        //container = (LinearLayout)findViewById(R.id.container);
        loadShops();
        adminProductPhotoItems = new ArrayList<>();
        imgDownload = new ArrayList<>();
        noImage = findViewById(R.id.no_image);
        addPhotoButton = findViewById(R.id.add_photo_button);
        //c = findViewById(R.id.add_shop_name_text);
        productName = findViewById(R.id.add_shop_name_text);
        productCode = findViewById(R.id.add_user_id_text);
        productMrp = findViewById(R.id.mrp_edit);

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


        adminProductPhotoAdapter = new AdminProductPhotoAdapter(adminProductPhotoItems);
        photoRecyclerView = findViewById(R.id.add_product_image);
        photoRecyclerView.setHasFixedSize(true);


        layoutManager = new LinearLayoutManager(getApplicationContext());
        photoRecyclerView.setLayoutManager(layoutManager);
        photoRecyclerView.setAdapter(adminProductPhotoAdapter);


//        ArrayAdapter<CharSequence> productCategorySpinnerAdapter = ArrayAdapter.createFromResource(this,R.array.product_category_name,android.R.layout.simple_spinner_item);
//        productCategorySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


//        ArrayAdapter<CharSequence> shopNameSpinnerAdapter = ArrayAdapter.createFromResource(this,R.array.shop,android.R.layout.simple_spinner_item);
//        shopNameSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        select_shop_spinner.setAdapter(shopNameSpinnerAdapter);
//        select_shop_spinner.setOnItemSelectedListener(this);


        addPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), RESULT_LOAD_IMAGE);
            }
        });

        adminProductPhotoAdapter.setOnItemClickListener(new AdminProductPhotoAdapter.OnItemClickListener() {
            @Override
            public void onDelete(int position) {
                removeItem(position);
                if (adminProductPhotoItems.isEmpty()) {
                    noImage.setVisibility(View.VISIBLE);
                }
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUploadTask != null && mUploadTask.isInProgress()) {
                    Toast.makeText(AdminOrderReqActivity.this, "Upload in progress", Toast.LENGTH_SHORT).show();
                } else {
                    sendOrder();
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

    public void removeItem(int position) {
        adminProductPhotoItems.remove(position);
        adminProductPhotoAdapter.notifyItemRemoved(position);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spin = (Spinner) parent;
        if (spin.getId() == R.id.select_category) {
            category_name = spin.getItemAtPosition(position).toString();
        } else if (spin.getId() == R.id.add_pwd_text) {
            shop_name = spin.getItemAtPosition(position).toString();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK) {

            if (data.getClipData() != null) {

                totalItemsSelected = data.getClipData().getItemCount();

                for (int i = 0; i < totalItemsSelected; i++) {

                    imageUri = data.getClipData().getItemAt(i).getUri();

                    fileName = getFileName(imageUri);

                    adminProductPhotoItems.add(new AdminProductPhotoItems("uploading", fileName, imageUri));
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

            } else if (data.getData() != null) {

                imageUri = data.getData();
                String fileName = getFileName(imageUri);

                adminProductPhotoItems.add(new AdminProductPhotoItems("uploading", fileName, imageUri));
                noImage.setVisibility(View.GONE);
                adminProductPhotoAdapter.notifyDataSetChanged();
                //Toast.makeText(AdminOrderReqActivity.this, "Selected Single File", Toast.LENGTH_SHORT).show();
            }

        }

    }

    private String getFileExtension(Uri uri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    private String getFileName(Uri uri){
        String result = "image_"+System.currentTimeMillis()+"."+getFileExtension(uri);
        return result;
    }


    public void loadCategory() {
        categoryRef.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            AddCategoryItem addCategoryItem = documentSnapshot.toObject(AddCategoryItem.class);
                            String catName = addCategoryItem.getCategory_name();
                            categoryList.add(new AddCategoryItem(catName));

                        }
                        ArrayAdapter<AddCategoryItem> adapter = new ArrayAdapter<AddCategoryItem>(getApplicationContext(), android.R.layout.simple_spinner_item, categoryList);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        select_category_spinner.setAdapter(adapter);
                        select_category_spinner.setOnItemSelectedListener(AdminOrderReqActivity.this);

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }

    public void loadShops() {
        shopRef.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            ShopNames shopNames = documentSnapshot.toObject(ShopNames.class);
                            String shopName = shopNames.getShop_name();
                            shopNamesList.add(new ShopNames(shopName));

                        }
                        ArrayAdapter<ShopNames> sadapter = new ArrayAdapter<ShopNames>(getApplicationContext(), android.R.layout.simple_spinner_item, shopNamesList);
                        sadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        select_shop_spinner.setAdapter(sadapter);
                        select_shop_spinner.setOnItemSelectedListener(AdminOrderReqActivity.this);

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }

    public void sendOrder() {

        final String proName = productName.getText().toString();
        final String proCode = productCode.getText().toString();
        final String proMrp = productMrp.getText().toString();

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

        final Map< String,Integer> orderData = new HashMap< String,Integer>();
        //final Map< String,Integer> orderData = new HashMap<String,Integer>();
        orderData.put(FULL36,fs_36);
        orderData.put(HALF36,hs_36);
        orderData.put(SLIM36,sf_36);
        orderData.put(FULl38,fs_38);
        orderData.put(HALF38,hs_38);
        orderData.put(SLIM38,sf_38);
        orderData.put(FULL40,fs_40);
        orderData.put(HALF40,hs_40);
        orderData.put(SLIM40,sf_40);
        orderData.put(FULL42,fs_42);
        orderData.put(HALF42,hs_42);
        orderData.put(SLIM42,sf_42);
        orderData.put(FULL44,fs_44);
        orderData.put(HALF44,hs_44);
        orderData.put(SLIM44,sf_44);
        orderData.put(FULL46,fs_46);
        orderData.put(HALF46,hs_46);
        orderData.put(SLIM46,sf_46);
        orderData.put(FULL48,fs_48);
        orderData.put(HALF48,hs_48);
        orderData.put(SLIM48,sf_48);
        orderData.put(FULL50,fs_50);
        orderData.put(HALF50,hs_50);
        orderData.put(SLIM50,sf_50);
        orderData.put(FULL54,fs_54);
        orderData.put(HALF54,hs_54);
        orderData.put(SLIM54,sf_54);
        orderData.put(FULL55,fs_55);
        orderData.put(HALF55,hs_55);
        orderData.put(SLIM55,sf_55);

        if(proName.trim().isEmpty() || proCode.trim().isEmpty() || proMrp.isEmpty()){
            Toast.makeText(this, "Enter all the Fields", Toast.LENGTH_LONG).show();
            return;
        }
        if(adminProductPhotoItems.isEmpty()){
            Toast.makeText(AdminOrderReqActivity.this,"Please select the prouct image",Toast.LENGTH_LONG).show();
            return;
        }

        circularProgress.setVisibility(View.VISIBLE);

        for (int i = 0; i < adminProductPhotoItems.size(); i++) {

            final StorageReference fileReference = mStorageRef.child(adminProductPhotoItems.get(i).toString());
            final int finalI = i;

            final int finalI1 = i+1;
            mUploadTask = fileReference.putFile(adminProductPhotoItems.get(i).getImg())
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            circularProgress.setVisibility(View.VISIBLE);
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    progressBar.setProgress(0);
                                }
                            }, 6000);

                            Toast.makeText(AdminOrderReqActivity.this, "Image "+finalI1+" Uploaded", Toast.LENGTH_SHORT).show();

                            fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    Uri downloadUrl = uri;
                                    imgDownload.add(downloadUrl.toString());
                                    int s = adminProductPhotoItems.size()-1;

                                    if(finalI == s) {
                                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh.mm aa");
                                        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
                                        SimpleDateFormat dateOnlyFormat = new SimpleDateFormat("yyyy-MM-dd");
                                        String dateOnly = dateOnlyFormat.format(new Date());
                                        String sortDate = dateFormat2.format(new Date());
                                        String currentDate = dateFormat.format(new Date());
                                        reqRef.add(new AdminOrderItem(proName,proCode,shop_name,category_name,imgDownload,orderData,proMrp,currentDate,sortDate,dateOnly))
                                                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                    @Override
                                                    public void onSuccess(DocumentReference documentReference) {
                                                        PatternItems patternItems = new PatternItems(proCode,imgDownload.get(0),category_name);
                                                        db.collection("CategoryPattern").document(proCode)
                                                                .set(patternItems)
                                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                    @Override
                                                                    public void onSuccess(Void aVoid) {
                                                                        Log.d("Pattern","Pattern Added");
                                                                    }
                                                                })
                                                                .addOnFailureListener(new OnFailureListener() {
                                                                    @Override
                                                                    public void onFailure(@NonNull Exception e) {
                                                                        Log.d("Pattern",e.getMessage());
                                                                    }
                                                                });
                                                        Toast.makeText(AdminOrderReqActivity.this,"Request Sent",Toast.LENGTH_LONG).show();
                                                        imgDownload.removeAll(imgDownload);
                                                        Intent i = new Intent(AdminOrderReqActivity.this,MainActivity.class);
                                                        startActivity(i);
                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(AdminOrderReqActivity.this,"Request Failed. Try Again!!",Toast.LENGTH_LONG).show();
                                                    }
                                                });

                                    }
                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AdminOrderReqActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                           // progressBar.setVisibility(View.VISIBLE);
                            progressBar.setProgress((int) progress);
                        }
                    });
        }

    }
}
