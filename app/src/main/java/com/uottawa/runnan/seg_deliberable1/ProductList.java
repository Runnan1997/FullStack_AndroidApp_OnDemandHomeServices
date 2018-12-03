package com.uottawa.runnan.seg_deliberable1;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.uottawa.runnan.seg_deliberable1.Model.Product;

import java.util.List;

public class ProductList extends ArrayAdapter<Product> {
    private Activity context;
    List<Product> products;

    public ProductList(Activity context, List<Product> products) {
        super(context, R.layout.layout_product_list, products);
        this.context = context;
        this.products = products;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_product_list, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.tvName);
        TextView textViewPrice = (TextView) listViewItem.findViewById(R.id.tvPrice);

        Product product = products.get(position);
        textViewName.setText(product.getProductName());
        textViewPrice.setText(String.valueOf(product.getPrice()));
        return listViewItem;
    }
}