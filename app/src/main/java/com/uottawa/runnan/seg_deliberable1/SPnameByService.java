package com.uottawa.runnan.seg_deliberable1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.uottawa.runnan.seg_deliberable1.Model.Product;

import java.util.ArrayList;
import java.util.List;

public class SPnameByService extends AppCompatActivity {
    TextView servicename;
    TextView asdf;

    DatabaseReference myServices;
    List<Product> serviceprovidernames;
    ListView listofspname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spname_by_service);

        asdf = (TextView)findViewById(R.id.tvdoesnotmatter);

        servicename = (TextView)findViewById(R.id.tvservicename);
        Intent intent = getIntent();
        String text = intent.getStringExtra(ByService.EXTRA_TEXT);
        servicename.setText(text);

        serviceprovidernames = new ArrayList<>();
        listofspname = (ListView)findViewById(R.id.servicenamebyService);

        myServices = FirebaseDatabase.getInstance().getReference("myServices");
    }


    @Override
    protected void onStart(){
        super.onStart();
        myServices.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                serviceprovidernames.clear();
                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    Product frommyservice = postSnapshot.getValue(Product.class);
                    serviceprovidernames.add(frommyservice);
                    for(int i = 0; i < serviceprovidernames.size();i++){
                        if(!serviceprovidernames.get(i).getProductName().equals(servicename.getText().toString())){
                            serviceprovidernames.remove(i);
                        }
                    }
                }
                SPNameList adapter = new SPNameList(SPnameByService.this, serviceprovidernames);
                listofspname.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
