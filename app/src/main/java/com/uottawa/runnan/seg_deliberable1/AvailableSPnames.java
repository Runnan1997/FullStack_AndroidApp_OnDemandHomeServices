package com.uottawa.runnan.seg_deliberable1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.uottawa.runnan.seg_deliberable1.Model.Availability;

import java.util.ArrayList;
import java.util.List;

public class AvailableSPnames extends AppCompatActivity {
    TextView HOname;
    TextView Day;
    TextView Time;
    TextView Doesnotmatter;

    public static final String EXTRA_TEXT = "selectedspnamebytime";
    public static final String EXTRA_TEXT1 = "selectspbytime";

    DatabaseReference availability;
    ListView listofspname;
    List<Availability> serviceprovidernames;

    DatabaseReference bookedServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_spnames);

        HOname = (TextView)findViewById(R.id.tvhoname);
        Day = (TextView)findViewById(R.id.day);
        Time = (TextView)findViewById(R.id.time);
        Doesnotmatter = (TextView)findViewById(R.id.doesnotmatter);
        Intent intent = getIntent();
        String honamefrombytime = intent.getStringExtra(ByTime.EXTRA_TEXT4);
        String dayfrombytime = intent.getStringExtra(ByTime.EXTRA_TEXT2);
        String timefrombytime = intent.getStringExtra(ByTime.EXTRA_TEXT3);

        HOname.setText(honamefrombytime);
        Day.setText(dayfrombytime);
        Time.setText(timefrombytime );


        availability = FirebaseDatabase.getInstance().getReference("availability");
        serviceprovidernames = new ArrayList<>();

        bookedServices = FirebaseDatabase.getInstance().getReference("bookedServices");

        listofspname = (ListView)findViewById(R.id.spnamelist);
        listofspname.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Availability a = serviceprovidernames.get(position);
                String selectedsp = a.getName();
                String honamebytime = HOname.getText().toString();
                Intent intent = new Intent(getApplicationContext(), SPServiceByTime.class);
                intent.putExtra(EXTRA_TEXT, selectedsp);
                intent.putExtra(EXTRA_TEXT1, honamebytime);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        availability.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                serviceprovidernames.clear();
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    Availability avai = postSnapshot.getValue(Availability.class);
                    if(avai.getDates().equals(Day.getText().toString())){
                        if(avai.getTime().equals(Time.getText().toString())){
                            serviceprovidernames.add(avai);
                            }
                        }

                }
                ServiceProviderList splAdapter = new ServiceProviderList(AvailableSPnames.this, serviceprovidernames);
                listofspname.setAdapter(splAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
