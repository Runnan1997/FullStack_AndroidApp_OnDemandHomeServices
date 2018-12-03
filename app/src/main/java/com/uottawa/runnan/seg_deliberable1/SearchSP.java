package com.uottawa.runnan.seg_deliberable1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SearchSP extends AppCompatActivity {
    public static final String EXTRA_TEXT = "homeownernameToByTime";
    public static final String EXTRA_TEXT1 = "homeownernameToByServices";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_sp);

        final TextView hoName = (TextView)findViewById(R.id.tvhoname);
        Intent intent = getIntent();
        String honame = intent.getStringExtra(Homeowner.EXTRA_TEXT);
        hoName.setText(honame);

        TextView no = (TextView)findViewById(R.id.tvno);
        Button byTime = (Button)findViewById(R.id.btnbyTime);
        Button byService = (Button)findViewById(R.id.btnbyService);
        Button byRate = (Button)findViewById(R.id.btnbyRate);


        byTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = hoName.getText().toString();
                Intent intent = new Intent(getApplicationContext(),ByTime.class);
                intent.putExtra(EXTRA_TEXT,name);
                startActivity(intent);
            }
        });

        byService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = hoName.getText().toString();
                Intent intent = new Intent(getApplicationContext(),ByService.class);
                intent.putExtra(EXTRA_TEXT1,name);
                startActivity(intent);
            }
        });

        byRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ByRate.class);
                startActivity(intent);
            }
        });

    }
}
