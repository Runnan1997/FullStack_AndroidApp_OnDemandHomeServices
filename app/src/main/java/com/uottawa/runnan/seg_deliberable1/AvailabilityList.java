package com.uottawa.runnan.seg_deliberable1;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.uottawa.runnan.seg_deliberable1.Model.Availability;

import java.util.List;

public class AvailabilityList extends ArrayAdapter<Availability> {
    private Activity context;
    List<Availability> availability;

    public AvailabilityList(Activity context,List<Availability> availability ) {
        super(context, R.layout.activity_availability_list,availability);
        this.context = context;
        this.availability = availability;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.activity_availability_list, null, true);

        TextView textViewDate = (TextView) listViewItem.findViewById(R.id.tvDate);
        TextView textViewTime = (TextView) listViewItem.findViewById(R.id.tvTime);

        Availability availabilities = availability.get(position);
        textViewDate.setText(availabilities.getDates());
        textViewTime.setText(availabilities.getTime());
        return listViewItem;
    }
}
