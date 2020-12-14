package com.viettravelapplication.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.viettravelapplication.Model.GioiThieu;

import java.util.List;

public class IntroAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<GioiThieu> gioiThieuList;
    @Override
    public int getCount() {
        return gioiThieuList.getSize();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
