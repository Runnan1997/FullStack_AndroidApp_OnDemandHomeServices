package com.uottawa.runnan.seg_deliberable1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.uottawa.runnan.seg_deliberable1.Model.Product;

import java.util.ArrayList;
import java.util.List;

public class ByService extends AppCompatActivity {

    ListView listofservices;
    List<Product> servicenames;
    DatabaseReference services;


    public static final String EXTRA_TEXT = "selectedproductNameByService";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_by_service);


        listofservices = (ListView)findViewById(R.id.alltheservices);
        servicenames = new ArrayList<>();
        services = FirebaseDatabase.getInstance().getReference("services");


        listofservices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product p = servicenames.get(position);
                String productname = p.getProductName();
                Intent passservicenameByService = new Intent(getApplicationContext(), SPnameByService.class);
                passservicenameByService.putExtra(EXTRA_TEXT, productname);
                startActivity(passservicenameByService);
            }
        });

    }

    @Override
    protected void onStart(){
        super.onStart();
        services.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                servicenames.clear();
                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    Product pbyservice = postSnapshot.getValue(Product.class);
                    servicenames.add(pbyservice);
                }
                ProductList plAdapter = new ProductList(ByService.this,servicenames);
                listofservices.setAdapter(plAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
