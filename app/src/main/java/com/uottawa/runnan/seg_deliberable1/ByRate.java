package com.uottawa.runnan.seg_deliberable1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ByRate extends AppCompatActivity {

    EditText enteredRate;
    Button searchByRate;
    public static final String EXTRA_TEXT0 = "enteredRate";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_by_rate);

        enteredRate = (EditText)findViewById(R.id.enterARate);

        searchByRate = (Button)findViewById(R.id.asdf);
        searchByRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = enteredRate.getText().toString();
                String pattern ="^\\d{0,8}(\\.\\d{1,4})?$";
                if(enteredRate.getText().toString().isEmpty()){
                    Toast.makeText(ByRate.this, "Please enter a day", Toast.LENGTH_SHORT).show();
                }
                else if(!s.matches(pattern)){
                    Toast.makeText(ByRate.this, "Please enter a valid rate", Toast.LENGTH_SHORT).show();
                }
                else{
                    String enteredRateByRate = enteredRate.getText().toString();
                    Intent passratemarks = new Intent(getApplicationContext(), SPnameByRate.class);
                    passratemarks.putExtra(EXTRA_TEXT0, enteredRateByRate);
                    startActivity(passratemarks);
                }
            }
        });
    }
}
