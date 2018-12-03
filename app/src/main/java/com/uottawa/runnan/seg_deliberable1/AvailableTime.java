package com.uottawa.runnan.seg_deliberable1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AvailableTime extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    DatabaseReference dR;
    EditText availabledate;
    TextView availabletime;
    Button addavailability;
    Button finishadding;
    TextView serviceprovidername;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_time);
        availabledate = (EditText)findViewById(R.id.date);
        availabletime = (TextView)findViewById(R.id.avaitime) ;
        Spinner spinnertime = findViewById(R.id.spinner2);
        addavailability = (Button)findViewById(R.id.add);
        finishadding=(Button)findViewById(R.id.finishadding);
        serviceprovidername = (TextView)findViewById(R.id.serviceprovidername);
        Intent intent = getIntent();
        String text = intent.getStringExtra(SP_profile.EXTRA_TEXT2);
        serviceprovidername.setText(text);

        dR = FirebaseDatabase.getInstance().getReference("availability");

        addavailability.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = dR.push().getKey();
                final Availability sp =new Availability(id,availabledate.getText().toString(),availabletime.getText().toString(),serviceprovidername.getText().toString());
                final String[] weekdays = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
                dR.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(availabledate.getText().toString().isEmpty()){
                            Toast.makeText(AvailableTime.this,"Please enter a date",Toast.LENGTH_LONG).show();
                        }
                        else if(!Arrays.asList(weekdays).contains(availabledate.getText().toString())){
                            Toast.makeText(AvailableTime.this,"Please enter a valid date with capial letters",Toast.LENGTH_LONG).show();
                        }
                        else{
                            dR.child(sp.getId()).setValue(sp);
                            Toast.makeText(AvailableTime.this,"Availability added!",Toast.LENGTH_LONG).show();
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


        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.time,android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnertime.setAdapter(adapter2);
        spinnertime.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        availabletime.setText(parent.getItemAtPosition(position).toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
