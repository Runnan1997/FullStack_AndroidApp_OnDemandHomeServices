package com.uottawa.runnan.seg_deliberable1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText CreateAccount;
    private Button Administrator;
    private Button HomeOwner;
    private Button ServiceProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CreateAccount = (EditText) findViewById(R.id.createaccount);
        Administrator = (Button) findViewById(R.id.administrator);
        HomeOwner = (Button) findViewById(R.id.homeOwner);
        ServiceProvider = (Button) findViewById(R.id.serviceProvider);

        Administrator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent passdata = new Intent(getApplicationContext(),SignUp.class);
                passdata.putExtra("roletype", "Administrator");
                startActivity(passdata);
            }

        });


        HomeOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent passdata = new Intent(getApplicationContext(),SignUp.class);
                passdata.putExtra("roletype", "HomeOwner");
                startActivity(passdata);
            }
        });

        ServiceProvider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent passdata = new Intent(getApplicationContext(),SignUp.class);
                passdata.putExtra("roletype", "ServiceProvider");
                startActivity(passdata);
            }
        });
    }



}
