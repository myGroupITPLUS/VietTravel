package com.viettravelapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import com.viettravelapplication.Model.Promotion;
import com.viettravelapplication.Model.Tour;
import com.viettravelapplication.R;
import com.viettravelapplication.Util.StringUtil;

import java.text.DecimalFormat;
import java.util.List;

public class TourAdapter extends RecyclerView.Adapter<TourAdapter.ViewHolder> {
    Context context;
    int layout;
    List<Tour> list;

    public TourAdapter(Context context, int layout, List<Tour> list){
        this.context = context;
        this.layout = layout;
        this.list = list;
    }


    @Override
    public long getItemId(int position) {
        return list.get(position).getId();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //View view = inflater.inflate(layout, null);
        View view = LayoutInflater.from(context).inflate(layout, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tour tour = list.get(position);
        Picasso.get().load(StringUtil.LOAD_IMAGES+tour.getImages())
                .placeholder(R.drawable.loading)
                .error(R.drawable.noimageicon)
                .into(holder.imageTour);
        holder.tvNameTour.setText(tour.getNametour());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.tvPrice.setText("Giá: "+decimalFormat.format(tour.getPrice())+" VNĐ");
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageTour;
        TextView tvNameTour;
        TextView tvPrice;
        public ViewHolder(View itemView) {
            super(itemView);
            imageTour = itemView.findViewById(R.id.imageTour);
            tvNameTour = itemView.findViewById(R.id.tvNameTour);
            tvPrice = itemView.findViewById(R.id.tvGia);
        }
    }
}