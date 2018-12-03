package com.uottawa.runnan.seg_deliberable1;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.uottawa.runnan.seg_deliberable1.Model.Product;

import java.util.List;

public class MyServiceList extends ArrayAdapter<Product>{
    private Activity context;
    List<Product> myproducts;

    public MyServiceList(Activity context, List<Product> myproducts){
        super(context, R.layout.activity_my_service_list,myproducts);
        this.context = context;
        this.myproducts = myproducts;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.activity_my_service_list,null,true);

        TextView tvmyservice = (TextView)listViewItem.findViewById(R.id.tvmyservice);
        Product product = myproducts.get(position);
        tvmyservice.setText(product.getProductName());
        return listViewItem;
    }
}
