package com.uottawa.runnan.seg_deliberable1;

import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.uottawa.runnan.seg_deliberable1.Model.Product;
//Firebase
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//List
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServiceList extends AppCompatActivity{
        EditText etproduct;
        EditText etprice;
        Button btnadd;
        ListView listviewproducts;
        List<Product> products;

        //Firebase
        DatabaseReference databaseServices;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.chore_item_layout);

            etproduct = (EditText) findViewById(R.id.etproduct);
            etprice = (EditText) findViewById(R.id.etprice);
            listviewproducts = (ListView) findViewById(R.id.listviewproducts);
            btnadd = (Button) findViewById(R.id.btnadd);

            databaseServices = FirebaseDatabase.getInstance().getReference("services");

            products = new ArrayList<>();

            btnadd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String pattern ="^\\d{0,1000}(\\.\\d{1,1000})?$";
                    String input =  etprice.getText().toString();
                    Pattern r = Pattern.compile(pattern);
                    Matcher m = r.matcher(input);
                    if(etproduct.getText().toString().isEmpty()){
                        Toast.makeText(ServiceList.this,"Please enter a service",Toast.LENGTH_LONG).show();
                    }
                    else if(etprice.getText().toString().isEmpty()){
                        Toast.makeText(ServiceList.this,"Please enter a price",Toast.LENGTH_LONG).show();
                    }
                    else if(!m.matches()){
                        Toast.makeText(ServiceList.this,"Invalid Price",Toast.LENGTH_LONG).show();
                    }
                    else{
                        addProduct();
                    }
                }
            });

            listviewproducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Product product = products.get(i);
                    showUpdateDeleteDialog(product.getId(), product.getProductName());
                }
            });

        }


        @Override
        protected void  onStart(){
            super.onStart();
            databaseServices.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    products.clear();
                    for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                        Product product = postSnapshot.getValue(Product.class);
                        products.add(product);
                    }
                    ProductList productsAdapter = new ProductList(ServiceList.this,products);
                    listviewproducts.setAdapter(productsAdapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

        private void addProduct(){
            String name = etproduct.getText().toString().trim();
            double price = Double.parseDouble(String.valueOf(etprice.getText().toString()));

            if(!TextUtils.isEmpty(name)){
                String id =  databaseServices.push().getKey();
                Product product = new Product(id, name, price);
                databaseServices.child(id).setValue(product);
                etproduct.setText("");
                etprice.setText("");
                Toast.makeText(this,"Service added",Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(this,"Please enter a service",Toast.LENGTH_LONG).show();
            }

        }

        public void updateProduct(String id, String name, double price){
            DatabaseReference dR = FirebaseDatabase.getInstance().getReference("services").child(id);
            Product product = new Product(id,name,price);
            dR.setValue(product);
            Toast.makeText(getApplicationContext(),"Service Updated!", Toast.LENGTH_LONG).show();
        }

        private boolean deleteProduct(String id){
            DatabaseReference dR = FirebaseDatabase.getInstance().getReference("services").child(id);
            dR.removeValue();
            Toast.makeText(getApplicationContext(), "Service Deleted!", Toast.LENGTH_LONG).show();
            return true;
        }

        private void showUpdateDeleteDialog(final String productId, String productName){
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
            LayoutInflater inflater = getLayoutInflater();
            final View dialogView = inflater.inflate(R.layout.update_dialog, null);
            dialogBuilder.setView(dialogView);

            final EditText etproduct = (EditText)dialogView.findViewById(R.id.etproduct);
            final EditText etprice = (EditText)dialogView.findViewById(R.id.etprice);
            final Button btnupdate = (Button)dialogView.findViewById(R.id.btnupdate);
            final Button btndelete = (Button)dialogView.findViewById(R.id.btndelete);


            dialogBuilder.setTitle(productName);
            final AlertDialog b = dialogBuilder.create();
            b.show();

            btnupdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name = etproduct.getText().toString().trim();
                    double value = Double.parseDouble(String.valueOf(etprice.getText().toString()));
                    if(!TextUtils.isEmpty(name)){
                        updateProduct(productId, name, value);
                        b.dismiss();
                    }
                }
            });

            btndelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteProduct(productId);
                    b.dismiss();
                }
            });
        }
}
