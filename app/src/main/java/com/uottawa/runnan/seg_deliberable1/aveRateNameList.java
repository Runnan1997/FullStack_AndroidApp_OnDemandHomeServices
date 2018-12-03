package com.uottawa.runnan.seg_deliberable1;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.uottawa.runnan.seg_deliberable1.Model.Rating;

import java.util.List;

public class aveRateNameList extends ArrayAdapter<Rating> {
    private Activity context;
    List<Rating> SpnamesByRate;

    public aveRateNameList(Activity context, List<Rating> SpnamesByRate){
        super(context, R.layout.activity_ave_rate_name_list, SpnamesByRate);
        this.context = context;
        this.SpnamesByRate = SpnamesByRate;
    }

    @Override
    public View getView(int postion, View convertView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.activity_ave_rate_name_list,null,true);

        TextView TextViewspNameByrate = (TextView)listViewItem.findViewById(R.id.SPnamesbyRate);

        Rating r = SpnamesByRate.get(postion);
        TextViewspNameByrate.setText(r.getName());

        return listViewItem;

    }
}
