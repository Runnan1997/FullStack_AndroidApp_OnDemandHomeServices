package com.uottawa.runnan.seg_deliberable1;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.uottawa.runnan.seg_deliberable1.Model.HomeOwner;

import org.w3c.dom.Text;

import java.util.List;

public class RateServiceList extends ArrayAdapter<HomeOwner> {
    private Activity context;
    List<HomeOwner> ho;

    public RateServiceList(Activity context, List<HomeOwner> ho){
        super(context, R.layout.activity_rate_service_list,ho);
        this.context =context;
        this.ho =ho;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.activity_rate_service_list,null,true);

        TextView booked_services = (TextView)listViewItem.findViewById(R.id.tv_booked_services);
        HomeOwner h = ho.get(position);
        booked_services.setText(h.getService());
        return listViewItem;
    }
}
