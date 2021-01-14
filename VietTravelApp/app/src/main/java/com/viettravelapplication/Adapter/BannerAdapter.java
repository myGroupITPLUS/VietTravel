package com.viettravelapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.viettravelapplication.Model.Banner;
import com.viettravelapplication.R;
import com.viettravelapplication.Util.StringUtil;

import java.util.List;

public class BannerAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<Banner> list;

    public BannerAdapter(Context context, int layout, List<Banner> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
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
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(layout, null);
        ImageView imageView = convertView.findViewById(R.id.imgSlider);
        Banner banner = list.get(position);
        Picasso.get().load(StringUtil.LOAD_IMAGES+banner.getImages()).into(imageView);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return  convertView;
    }
}