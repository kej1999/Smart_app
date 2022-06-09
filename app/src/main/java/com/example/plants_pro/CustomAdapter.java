package com.example.plants_pro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    Context context;
    ArrayList<item> items;

    public CustomAdapter(Context context, ArrayList<item> items){
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.plant_list,null);
        }

        item it = (item) getItem(position);
        de.hdodenhof.circleimageview.CircleImageView circleImageView = convertView.findViewById(R.id.pltList);
        TextView pltName = convertView.findViewById(R.id.pltName);

        circleImageView.setImageBitmap(it.getBitmap());
        pltName.setText(it.getName());

        return convertView;
    }
}
