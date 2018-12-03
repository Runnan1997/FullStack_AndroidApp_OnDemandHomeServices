package com.uottawa.runnan.seg_deliberable1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.uottawa.runnan.seg_deliberable1.Model.Product;
import com.uottawa.runnan.seg_deliberable1.Model.Rating;

import java.util.ArrayList;
import java.util.List;

public class SPnameByRate extends AppCompatActivity {

    TextView markinSPnameByRate;
    TextView sentence;

    ListView SpnamesByRating;
    List<Rating> sps;

    DatabaseReference rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spname_by_rate);

        markinSPnameByRate = (TextView)findViewById(R.id.tvspNameRate);
        Intent intent0 = getIntent();
        String rateByRate = intent0.getStringExtra(ByRate.EXTRA_TEXT0);
        markinSPnameByRate.setText(rateByRate);

        rating = FirebaseDatabase.getInstance().getReference("rating");

        sps = new ArrayList<>();
        SpnamesByRating = (ListView)findViewById(R.id.liatOfspnameByRate);
    }

    @Override
    protected void onStart(){
        super.onStart();
        rating.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                sps.clear();
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    Rating choosedRating = postSnapshot.getValue(Rating.class);
                    sps.add(choosedRating);
                    for(int i = 0; i < sps.size(); i++){
                        if(sps.get(i).getAvgRating() != Double.parseDouble(markinSPnameByRate.getText().toString())){
                            sps.remove(i);
                        }
                    }
                }
                aveRateNameList adapter = new aveRateNameList(SPnameByRate.this,sps);
                SpnamesByRating.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
