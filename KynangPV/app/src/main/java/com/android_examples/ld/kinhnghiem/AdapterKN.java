package com.android_examples.ld.kinhnghiem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android_examples.ld.R;
import com.android_examples.ld.tracnghiem.ItemTracNghiem;

import java.util.List;

/**
 * Created by LD on 9/28/2016.
 */
public class AdapterKN extends BaseAdapter {
    List<ItemKinhNghiem> items;
    Context mContext;
    public AdapterKN(List<ItemKinhNghiem> items, Context mContext){
        super();
        this.items = items;
        this.mContext = mContext;
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
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemKinhNghiem item = items.get(position);
        ViewHolder holder = null;

        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_kinhnghiem, null);
            holder = new ViewHolder();
            holder.tittle = (TextView) convertView.findViewById(R.id.tvTitle);
            holder.content = (TextView) convertView.findViewById(R.id.tvContent);
            holder.img = (ImageView) convertView.findViewById(R.id.imgAva);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tittle.setText(item.tittle);
        holder.content.setText(item.content);
        holder.img.setImageResource(item.image);
        return convertView;
    }

    class ViewHolder{
        TextView tittle;
        TextView content;
        ImageView img;
    }
}
