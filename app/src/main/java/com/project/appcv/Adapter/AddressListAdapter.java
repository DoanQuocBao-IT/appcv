package com.project.appcv.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.project.appcv.DTO.AddressWorkDto;
import com.project.appcv.R;

import java.util.List;
public class AddressListAdapter extends ArrayAdapter<AddressWorkDto> {
    private Context mContext;
    private int mResource;

    public AddressListAdapter(Context context, int resource, List<AddressWorkDto> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(mResource, parent, false);
        }

        AddressWorkDto address = getItem(position);
        TextView nameTextView = (TextView) view.findViewById(R.id.tvACity);
        nameTextView.setText(address.getCity());

        TextView addressTextView = (TextView) view.findViewById(R.id.tvAAddress);
        addressTextView.setText(address.getAddress());

        return view;
    }
}