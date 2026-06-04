package com.example.donation_app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.ArrayList;

public class Addcloth extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase ;
    DatabaseReference mref;
    FirebaseStorage firebaseStorage;
    ImageButton imageButton ;
    EditText edfirst,edlast,edprice;
    Button btninsert;
    private static final int code=1;
    Uri imageurl = null;
    String lati,longi;

    EditText edname,edaddress,edcontact,editem1,editem4,editem2,editem3;




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcloth);

        imageButton = findViewById(R.id.imageButton2);
        edname = findViewById(R.id.edname);

        editem1 = findViewById(R.id.editem1);

        editem2 = findViewById(R.id.editem2);
        editem3 = findViewById(R.id.editem3);
        editem4 = findViewById(R.id.edaddress);


        btninsert = findViewById(R.id.btninsert);

        firebaseDatabase = FirebaseDatabase.getInstance();
        mref = firebaseDatabase.getReference().child("cloth");
        firebaseStorage = FirebaseStorage.getInstance();

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,code);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == code && resultCode == RESULT_OK)
        {

            imageurl = data.getData();
            imageButton.setImageURI(imageurl);
        }

        btninsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String item = edname.getText().toString().trim();

                String dname = editem1.getText().toString().trim();
                String number = editem2.getText().toString().trim();

                String qty = editem4.getText().toString().trim();
                String address = editem3.getText().toString().trim();


                Geocoder coder = new Geocoder(getApplicationContext());

                try {
                    ArrayList<Address> adresses = (ArrayList<Address>) coder.getFromLocationName(address, 50);


                    for(Address add : adresses){
                        double longitude = add.getLongitude();
                        double latitude = add.getLatitude();
                        lati = String.valueOf(latitude);
                        longi = String.valueOf(longitude);
                        Toast.makeText(getApplicationContext(),lati.toString(), Toast.LENGTH_LONG).show();


                    }



                } catch (IOException e) {
                    e.printStackTrace();
                }

                StorageReference filepath =firebaseStorage.getReference().child("imagepost").child(imageurl.getLastPathSegment());
                filepath.putFile(imageurl).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        Task<Uri> downloadurl =taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {

                                String t =task.getResult().toString();
                                DatabaseReference newpost =mref.push();

                                newpost.child("item").setValue(item);

                                newpost.child("name").setValue(dname);
                                newpost.child("number").setValue(number);

                                newpost.child("address").setValue(address);
                                newpost.child("qty").setValue(qty);
                                newpost.child("status").setValue("Activate");
                                newpost.child("lati").setValue(lati);
                                newpost.child("longi").setValue(longi);

                                newpost.child("imageurl").setValue(task.getResult().toString());
                                Toast.makeText(getApplicationContext(), "Uploaded", Toast.LENGTH_SHORT).show();



                            }
                        });
                    }
                });
            }
        });
    }
}