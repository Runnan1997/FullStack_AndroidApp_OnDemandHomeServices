package com.uottawa.runnan.seg_deliberable1;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.uottawa.runnan.seg_deliberable1.Model.ServiceProvider;

import java.util.List;


public class RateServiceProviderList extends ArrayAdapter<ServiceProvider> {

    private Activity context;
    List<ServiceProvider> providers;

    public RateServiceProviderList(Activity context,  List<ServiceProvider> providers ){
        super(context, R.layout.layout_product_list, providers);
        this.context = context;
        this.providers = providers;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_service_provider_list, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.tv_display);
        TextView textViewCompany = (TextView) listViewItem.findViewById(R.id.tv_company1);

        ServiceProvider provider = providers.get(position);
        textViewName.setText(provider.get_username());
        textViewCompany.setText(provider.get_company());
        return listViewItem;
    }

}
