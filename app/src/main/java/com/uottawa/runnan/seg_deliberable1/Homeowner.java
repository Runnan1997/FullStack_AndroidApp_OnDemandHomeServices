package com.uottawa.runnan.seg_deliberable1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Homeowner extends AppCompatActivity {
    TextView hoName;
    Button searchsp;
    Button rate;
    public static final String EXTRA_TEXT = "homeownernameinHomeOwner";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeowner);

        hoName = (TextView)findViewById(R.id.tvhoname);
        Intent intent = getIntent();
        String honame = intent.getStringExtra(Home.EXTRA_TEXT1);
        hoName.setText(honame);

        searchsp = (Button)findViewById(R.id.btnserachsp);
        rate = (Button)findViewById(R.id.btnrate);

        searchsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = hoName.getText().toString();
                Intent intent = new Intent(getApplicationContext(),SearchSP.class);
                intent.putExtra(EXTRA_TEXT,name);
                startActivity(intent);
            }
        });

        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = hoName.getText().toString();
                Intent intent = new Intent(getApplicationContext(),RateServiceProvider.class);
                intent.putExtra(EXTRA_TEXT,name);
                startActivity(intent);
            }
        });


    }
}
