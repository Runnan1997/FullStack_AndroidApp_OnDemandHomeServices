package com.uottawa.runnan.seg_deliberable1;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.uottawa.runnan.seg_deliberable1.Model.Availability;

import java.util.List;

public class ServiceProviderList extends ArrayAdapter<Availability> {
    private Activity context;
    List<Availability> spnameinAvai;

    public ServiceProviderList(Activity context, List<Availability> spnameinAvai){
        super(context, R.layout.activity_service_provider_list, spnameinAvai);
        this.context = context;
        this.spnameinAvai = spnameinAvai;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.activity_service_provider_list,null,true);

        TextView TextViewspnames = (TextView)listViewItem.findViewById(R.id.tvspnamesbytime);
        Availability a = spnameinAvai.get(position);
        TextViewspnames.setText(a.getName());
        return listViewItem;
    }
}
