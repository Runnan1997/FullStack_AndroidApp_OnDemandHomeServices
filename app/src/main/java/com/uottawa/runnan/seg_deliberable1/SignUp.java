package com.uottawa.runnan.seg_deliberable1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.uottawa.runnan.seg_deliberable1.Model.User;

public class SignUp extends AppCompatActivity {

    EditText Name;
    EditText Password;
    EditText Email;
    Button Register;
    Button SignIn;
    //Firebase
    FirebaseDatabase database;
    DatabaseReference users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Firebase
        database = FirebaseDatabase.getInstance();
        users = database.getReference("Users");


        Name = (EditText)findViewById(R.id.etName);
        Password = (EditText)findViewById(R.id.etPsw);
        Email = (EditText)findViewById((R.id.etEmail));
        Register = (Button)findViewById(R.id.btnRegist);
        SignIn = (Button)findViewById(R.id.btnsignin);

        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
            }
        });
        Bundle bundle = getIntent().getExtras();
        final String roletype = bundle.getString("roletype");


        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = Email.getText().toString().trim();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if(email.matches(emailPattern)){
                    final User user = new User(Name.getText().toString(), Password.getText().toString(), Email.getText().toString(),roletype);
                    users.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange( DataSnapshot dataSnapshot) {
                            if(user.getRole().equals("Administrator")){
                                for(DataSnapshot data: dataSnapshot.getChildren()){
                                    String userType = data.child("role").getValue().toString();
                                    if(userType.equals("Administrator")){
                                        Toast.makeText(SignUp.this,"Admin already exsist", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                            else if(dataSnapshot.child(user.getUsername()).exists()) {
                                Toast.makeText(SignUp.this, "The username is already exsist!", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                users.child(user.getUsername()).setValue(user);
                                Toast.makeText(SignUp.this,"Success Register", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled( DatabaseError databaseError) {
                            //add custom code here
                        }
                    });
                }
                else{
                    Toast.makeText(SignUp.this,"Wrong email address", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
}
