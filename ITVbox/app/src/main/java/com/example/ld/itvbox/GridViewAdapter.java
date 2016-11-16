package com.example.ld.itvbox;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class GridViewAdapter extends BaseAdapter {

    private Context context;
    private int layoutResourceId;
    private List<ApplicationInfo> data;
    PackageManager manager;
    private SparseBooleanArray mSelectedItemsIds;

    public GridViewAdapter(Context context, int layoutResourceId, PackageManager manager, List<ApplicationInfo> data) {
        super();
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
        this.manager = manager;
        mSelectedItemsIds = new SparseBooleanArray();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ApplicationInfo task = data.get(position);
        View row = convertView;
        ViewHolder holder;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, null);
            holder = new ViewHolder();
            holder.imageTitle = (TextView) row.findViewById(R.id.text);
            holder.image = (ImageView) row.findViewById(R.id.image);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        try {

            String nameApp = (String) manager.getApplicationLabel(manager
                    .getApplicationInfo(task.processName,
                            PackageManager.GET_META_DATA));
            if (!nameApp.equals((String) context.getPackageManager()
                    .getApplicationLabel(
                            context.getPackageManager().getApplicationInfo(
                                  context.getPackageName(),
                                    PackageManager.GET_META_DATA)))) {
                holder.imageTitle.setText(nameApp);
            }

            holder.image.setImageDrawable(manager.getApplicationIcon(manager
                    .getApplicationInfo(task.processName,
                            PackageManager.GET_META_DATA)));

        } catch (Exception e) {
            e.printStackTrace();
        }

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return row;
    }



    public void toggleSelection(int position) {
        selectView(position, !mSelectedItemsIds.get(position));
    }

    public void removeSelection() {
        mSelectedItemsIds = new SparseBooleanArray();
        notifyDataSetChanged();
    }

    public void selectView(int position, boolean value) {
        if (value)
            mSelectedItemsIds.put(position, value);
        else
            mSelectedItemsIds.delete(position);
        notifyDataSetChanged();
    }
    public SparseBooleanArray getSelectedIds() {
        return mSelectedItemsIds;
    }

    static class ViewHolder {
        TextView imageTitle;
        ImageView image;
    }
}