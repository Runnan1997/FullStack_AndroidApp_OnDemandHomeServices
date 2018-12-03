package com.uottawa.runnan.seg_deliberable1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.uottawa.runnan.seg_deliberable1.Model.Product;

import java.util.ArrayList;
import java.util.List;

public class MyService extends AppCompatActivity {
    DatabaseReference databasenewSer;
    DatabaseReference databaseServices;

    ListView listviewproducts;
    List<Product> products;
    //Button checkmytime;

    @Override
    protected void  onStart() {
        super.onStart();
        databaseServices.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                products.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Product product = postSnapshot.getValue(Product.class);
                    products.add(product);
                }
                ProductList productsAdapter = new ProductList(MyService.this, products);
                listviewproducts.setAdapter(productsAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_service);
        final TextView service = (TextView)findViewById(R.id.etservices);
        TextView a = (TextView)findViewById(R.id.tvchooseservice);
        final TextView b = (TextView)findViewById(R.id.tvspname);
        Intent intent = getIntent();
        String text = intent.getStringExtra(SP_profile.EXTRA_TEXT);
        b.setText(text);
        Button addservice = (Button)findViewById(R.id.btnaddservice);
        Button finishadding = (Button)findViewById(R.id.btnfinishadding);
     //   checkmytime = (Button)findViewById(R.id.btnchecktime);
        listviewproducts = (ListView) findViewById(R.id.servicesfromadmin);
        databaseServices = FirebaseDatabase.getInstance().getReference("services");
        databasenewSer = FirebaseDatabase.getInstance().getReference("myServices");
        products = new ArrayList<>();

        listviewproducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Product product = products.get(i);
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MyService.this);
                LayoutInflater inflater = getLayoutInflater();
                final View dialogView = inflater.inflate(R.layout.sp_dialog, null);
                dialogBuilder.setView(dialogView);

                final Button btnadd = (Button) dialogView.findViewById(R.id.btnaddservice);

                final AlertDialog b = dialogBuilder.create();
                b.show();

                btnadd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Product newservice = new Product(service.getText().toString());
                        service.setText(product.getProductName());
                        b.dismiss();
                    }
                });

            }
        });

        addservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Product newservice = new Product(service.getText().toString(), b.getText().toString());
                databasenewSer.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(service.getText().toString().isEmpty()){
                            Toast.makeText(MyService.this,"Please choose a service",Toast.LENGTH_LONG).show();
                        }
                        else{
                            databasenewSer.child(newservice.getProductName()).setValue(newservice);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        finishadding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SP_profile.class);
                startActivity(intent);
            }
        });


    }
}
