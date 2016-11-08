package com.example.ld.clienttoserver.deviceserver;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ld.clienttoserver.R;

import java.util.List;

/**
 * Created by LD on 7/17/2016.
 */
public class AdapterDevice extends BaseAdapter{
    List<Device> deviceList = null;
    Context context;

    public AdapterDevice(List<Device> deviceList, Context mContext){
        super();
        this.deviceList = deviceList;
        this.context = mContext;
    }

    @Override
    public int getCount() {
        return deviceList.size();
    }

    @Override
    public Object getItem(int position) {
        return deviceList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Device devices = deviceList.get(position);
        Holder holder = null;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list, null);
            holder = new Holder();
            holder.tv_status = (TextView) convertView.findViewById(R.id.status);
            holder.tv_detail = (TextView) convertView.findViewById(R.id.detail);
            convertView.setTag(holder);
        }else {
            holder = (Holder)convertView.getTag();
        }
        holder.tv_status.setText(devices.status);
        holder.tv_detail.setText(devices.detail);
        return convertView;
    }

    public class Holder{
        TextView tv_status, tv_detail;
    }
}
