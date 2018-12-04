package com.uottawa.runnan.seg_deliberable1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.uottawa.runnan.seg_deliberable1.Model.HomeOwner;
import com.uottawa.runnan.seg_deliberable1.Model.Rate;
import com.uottawa.runnan.seg_deliberable1.Model.ServiceProvider;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class RateServices extends AppCompatActivity {
    DatabaseReference databasebookedservices;
    DatabaseReference databaseRate;
    ListView listofbookedservices;
    List<HomeOwner> bookedservices;
    Button submit;
    TextView service_name;
    TextView desnotmatter;
    TextView honame;
    EditText comments;
    EditText rating;

    @Override
    protected void onStart(){
        super.onStart();
        databasebookedservices.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                bookedservices.clear();
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    HomeOwner h = postSnapshot.getValue(HomeOwner.class);
                    bookedservices.add(h);
                    for(int i = 0;i < bookedservices.size();i++){
                        if(!bookedservices.get(i).getHoName().equals(honame.getText().toString())){
                            bookedservices.remove(i);
                        }
                    }
                }
                RateServiceList adapter = new RateServiceList(RateServices.this,bookedservices);
                listofbookedservices.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_services);

        databasebookedservices = FirebaseDatabase.getInstance().getReference("bookedServices");
        databaseRate = FirebaseDatabase.getInstance().getReference("ratedservice");
        bookedservices = new ArrayList<>();
        honame = (TextView)findViewById(R.id.tvHO_name);
        Intent intent = getIntent();
        String text = intent.getStringExtra(Homeowner.EXTRA_TEXT);
        honame.setText(text);
        listofbookedservices = (ListView)findViewById(R.id.lv_bookedservices);

        listofbookedservices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final HomeOwner h_o =bookedservices.get(position);
                LayoutInflater inflater = getLayoutInflater();

                service_name.setText(h_o.getService());
            }
        });

        desnotmatter = (TextView)findViewById(R.id.tvdoesnotmatter);
        service_name = (TextView)findViewById(R.id.tv_service);
        rating = (EditText)findViewById(R.id.et_rating);
        comments = (EditText)findViewById(R.id.et_comments);
        submit = (Button)findViewById(R.id.btn_rate_submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rate =  Integer.parseInt(rating.getText().toString()) ;
                String id = databaseRate.push().getKey();
                final Rate r = new Rate(id,service_name.getText().toString(),rate, comments.getText().toString());
                databaseRate.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(rating.getText().toString().isEmpty()){
                            Toast.makeText(RateServices.this, "Please enter a rate", Toast.LENGTH_SHORT).show();
                        }
                        else if(comments.getText().toString().isEmpty()){
                            Toast.makeText(RateServices.this, "Please enter a comment", Toast.LENGTH_SHORT).show();
                        }
                        else if(service_name.getText().toString().isEmpty()){
                            Toast.makeText(RateServices.this, "Please choose a service", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            databaseRate.child(r.getId()).setValue(r);
                            Toast.makeText(RateServices.this, "Thank you for rating!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }
}
