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

public class SPNameList extends ArrayAdapter<Product> {
    private Activity context;
    List<Product> products;

    public SPNameList(Activity context, List<Product> products){
        super(context, R.layout.activity_spname_list, products);
        this.context =context;
        this.products = products;
    }

    @Override
    public View getView(int postion, View convertView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.activity_spname_list,null,true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.tvspNamebyService);

        Product p = products.get(postion);
        textViewName.setText(p.get_name().toString());
        return listViewItem;
    }
}
