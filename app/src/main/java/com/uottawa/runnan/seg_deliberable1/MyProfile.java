package com.uottawa.runnan.seg_deliberable1;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.uottawa.runnan.seg_deliberable1.Model.Availability;
import com.uottawa.runnan.seg_deliberable1.Model.Product;
import com.uottawa.runnan.seg_deliberable1.Model.ServiceProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyProfile extends AppCompatActivity {
    TextView service;
    TextView availability;
    TextView myName;
    ListView servicelist;
    ListView availabilitylist;
    List<Product> services;
    List<Availability> av;

    DatabaseReference dataavailability;
    DatabaseReference datamyservices;

    @Override
    protected void onStart(){
        super.onStart();
        datamyservices.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                services.clear();
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    Product p = postSnapshot.getValue(Product.class);
                    services.add(p);
                    for(int i = 0; i < services.size(); i++){
                        if(!services.get(i).get_name().equals(myName.getText().toString())){
                            services.remove(i);
                        }
                    }
                }
                ProductList adapter = new ProductList(MyProfile.this, services);
                servicelist.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        dataavailability.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                av.clear();
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    Availability a = postSnapshot.getValue(Availability.class);
                    av.add(a);
                    for(int j = 0; j < av.size();j++){
                        if(!av.get(j).getName().equals(myName.getText().toString())){
                            av.remove(j);
                        }
                    }

                }

                AvailabilityList adapter = new AvailabilityList(MyProfile.this,av);
                availabilitylist.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        service = (TextView)findViewById(R.id.myservices);
        availability = (TextView)findViewById(R.id.myavailability);
        myName = (TextView)findViewById(R.id.tvmyname) ;
        Intent intent = getIntent();
        String text = intent.getStringExtra(SP_profile.EXTRA_TEXT3);
        myName.setText(text);
        servicelist = (ListView)findViewById(R.id.servicelist);
        availabilitylist = (ListView)findViewById(R.id.availabilitylist);

        dataavailability = FirebaseDatabase.getInstance().getReference("availability");
        datamyservices = FirebaseDatabase.getInstance().getReference("myServices");
        services = new ArrayList<>();
        av = new ArrayList<>();

        servicelist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Product myproduct = services.get(position);
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MyProfile.this);
                LayoutInflater inflater = getLayoutInflater();
                final View dialogView = inflater.inflate(R.layout.dialog_delete, null);
                dialogBuilder.setView(dialogView);
                final Button d = (Button)findViewById(R.id.btndelete);

                final AlertDialog x = dialogBuilder.create();
                x.show();
                final Button delete = (Button) dialogView.findViewById(R.id.btndelete);
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        datamyservices.child(myproduct.getProductName()).removeValue();
                        Toast.makeText(getApplicationContext(), "Service Deleted!", Toast.LENGTH_LONG).show();
                        x.dismiss();
                    }
                });
            }
        });

        availabilitylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Availability mya = av.get(position);
                showUpdateDeleteDialog(mya.getTime());
            }
        });
    }
    private void showUpdateDeleteDialog(final String mytime){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MyProfile.this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.enter_time_dialog, null);
        dialogBuilder.setView(dialogView);
        final Button upate = (Button)findViewById(R.id.btnupdatetime);
        final Button delete = (Button)findViewById(R.id.btndeletetime);
        final EditText dates = (EditText)dialogView.findViewById(R.id.etadate);
        final EditText time = (EditText)dialogView.findViewById(R.id.etatime);

        final AlertDialog b = dialogBuilder.create();
        b.show();

        upate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d = dates.getText().toString();
                String t = time.getText().toString();
                final String[] weekdays = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
                final String[] righttime = {"10:00to12:00","12:00to14:00","14:00to16:00","16:00to18:00","18:00to20:00"};
                if(d.isEmpty()){
                    Toast.makeText(MyProfile.this,"Please enter a date",Toast.LENGTH_LONG).show();
                }
                else if(t.isEmpty()){
                    Toast.makeText(MyProfile.this,"Please enter a time",Toast.LENGTH_LONG).show();
                }
                else if(!Arrays.asList(weekdays).contains(d)){
                    Toast.makeText(MyProfile.this,"Please enter a valid date with capial letters",Toast.LENGTH_LONG).show();
                }
                else if(!Arrays.asList(righttime).contains(t)){
                    Toast.makeText(MyProfile.this,"Please enter a valid time",Toast.LENGTH_LONG).show();
                }
                else{
                    updateA(d,mytime);
                    b.dismiss();
                }

            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteA(mytime);
                b.dismiss();
            }
        });
    }

    public void updateA(String da, String ti){
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("availability").child(ti);
        Availability a = new Availability(da, ti);
        dR.setValue(a);
        Toast.makeText(getApplicationContext(),"date Updated!", Toast.LENGTH_LONG).show();
    }

    private boolean deleteA(String t){
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("availability").child(t);
        dR.removeValue();
        Toast.makeText(getApplicationContext(), "Service Deleted!", Toast.LENGTH_LONG).show();
        return true;

    }



}
