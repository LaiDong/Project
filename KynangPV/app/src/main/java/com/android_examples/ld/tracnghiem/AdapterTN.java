package com.android_examples.ld.tracnghiem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android_examples.ld.R;

import java.util.List;

/**
 * Created by LD on 9/28/2016.
 */
public class AdapterTN extends BaseAdapter {
    List<ItemTracNghiem> items;
    Context mContext;
    public AdapterTN(List<ItemTracNghiem> items, Context mContext){
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
        ItemTracNghiem item = items.get(position);
        ViewHolder holder = null;

        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_tracnghiem, null);
            holder = new ViewHolder();
            holder.tittle = (TextView) convertView.findViewById(R.id.tvTitleTracNghiem);
            holder.time = (TextView) convertView.findViewById(R.id.tvTimeTracNghiem);
            holder.numberquestion = (TextView) convertView.findViewById(R.id.tvNumberQuestionTracNghiem);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tittle.setText(item.tittle);
        holder.time.setText(item.time);
        holder.numberquestion.setText(item.sentence);

        return convertView;
    }

    class ViewHolder{
        TextView tittle;
        TextView time;
        TextView numberquestion;
    }
}
