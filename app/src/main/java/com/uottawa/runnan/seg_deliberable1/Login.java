package com.uottawa.runnan.seg_deliberable1;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.uottawa.runnan.seg_deliberable1.Model.User;

public class Login extends AppCompatActivity {
    //Firebase
    FirebaseDatabase database;
    DatabaseReference users;

    EditText Name;
    EditText Password;
    Button SignIn;
    Button CreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Name = (EditText)findViewById(R.id.name);
        Password = (EditText)findViewById(R.id.psw);
        SignIn = (Button)findViewById(R.id.btnlogin);
        CreateAccount = (Button)findViewById(R.id.btncreataccount);
        //FireBase
        database = FirebaseDatabase.getInstance();
        users = database.getReference("Users");

        CreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignUp();
            }
        });

        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn(Name.getText().toString(), Password.getText().toString());
            }
        });
    }
    public void openSignUp(){
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }
    private void signIn(final String username, final String psw){
        users.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(username).exists()){
                    if(!username.isEmpty()) {
                        User login = dataSnapshot.child(username).getValue(User.class);
                        if (login.getPassword().equals(psw)) {
                            Toast.makeText(Login.this, "Success login", Toast.LENGTH_SHORT).show();
                            Intent passname = new Intent(getApplicationContext(),Home.class);
                            passname.putExtra("username", username);
                            passname.putExtra("roletype", login.getRole());
                            startActivity(passname);
                        }
                        else {
                            Toast.makeText(Login.this, "Wrong password", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(Login.this, "Username cannot be empty", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(Login.this, "Username is not registered", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //custom code
            }
        });
    }
}
