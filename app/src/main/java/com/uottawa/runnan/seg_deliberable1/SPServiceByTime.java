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
import com.uottawa.runnan.seg_deliberable1.Model.BookedService;
import com.uottawa.runnan.seg_deliberable1.Model.Product;

import java.util.ArrayList;
import java.util.List;

public class SPServiceByTime extends AppCompatActivity {
    TextView hoName;
    TextView spName;
    TextView sentence;

    ListView selectedspServices;
    List<Product> services;

    DatabaseReference databaseMyservices;
    DatabaseReference databaseBooking;

    Button bookthisService;
    TextView service;

    private void book(){
        if(!service.getText().toString().isEmpty()){
            String honame = hoName.getText().toString();
            String spname = spName.getText().toString();
            String id = databaseBooking.push().getKey();
            BookedService bs = new BookedService(honame,spname,id);
            databaseBooking.child(id).setValue(bs);
            Toast.makeText(this,"Service booked",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this,"Please choose a service",Toast.LENGTH_LONG).show();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spservice_by_time);

        bookthisService = (Button)findViewById(R.id.btnaddservice);
        bookthisService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                book();
            }
        });



        service = (TextView)findViewById(R.id.etservices);

        selectedspServices = (ListView)findViewById(R.id.selectedservices);
        services = new ArrayList<>();

        databaseMyservices = FirebaseDatabase.getInstance().getReference("myServices");
        databaseBooking = FirebaseDatabase.getInstance().getReference("bookedServices");

        sentence = (TextView)findViewById(R.id.tvdoesnotmatter);
        hoName = (TextView)findViewById(R.id.tvhoname);
        spName = (TextView)findViewById(R.id.tvselectedsp);
        Intent intent = getIntent();
        String text = intent.getStringExtra(AvailableSPnames.EXTRA_TEXT1);
        String text1 = intent.getStringExtra(AvailableSPnames.EXTRA_TEXT);
        hoName.setText(text);
        spName.setText(text1);

        selectedspServices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Product product = services.get(position);
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(SPServiceByTime.this);
                LayoutInflater inflater = getLayoutInflater();
                final View dialogView = inflater.inflate(R.layout.activity_book_service,null);
                dialogBuilder.setView(dialogView);
                final Button choose = (Button) dialogView.findViewById(R.id.btnbook);

                final AlertDialog b = dialogBuilder.create();
                b.show();

                choose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Product selectedservice = new Product(service.getText().toString());
                        service.setText(product.getProductName());
                        b.dismiss();
                    }
                });
            }
        });




    }

    @Override
    protected void onStart(){
        super.onStart();
        databaseMyservices.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                services.clear();
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    Product pro = postSnapshot.getValue(Product.class);
                    services.add(pro);
                    for(int i = 0; i < services.size();i++){
                        if(!services.get(i).get_name().equals(spName.getText().toString())){
                            services.remove(i);
                        }
                    }
                }
                ProductList adpter = new ProductList(SPServiceByTime.this, services);
                selectedspServices.setAdapter(adpter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
