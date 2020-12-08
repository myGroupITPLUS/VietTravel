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

import java.util.List;

public class PromotionAdapter extends RecyclerView.Adapter<PromotionAdapter.ViewHolder> {
    Context context;
    int layout;
    List<Tour> list;

    public PromotionAdapter(Context context, int layout, List<Tour> list){
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
        Picasso.get().load(tour.getImages())
                .placeholder(R.drawable.loading)
                .error(R.drawable.noimageicon)
                .into(holder.imgTour);
        holder.tvNameTour.setText(tour.getNametour());
        holder.tvPromotion.setText("Mã giảm giá: "+tour.getPromotionid());
        holder.tvPrice.setText((int) tour.getPrice());
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgTour;
        TextView tvNameTour;
        TextView tvPromotion;
        TextView tvPrice;
        public ViewHolder(View itemView) {
            super(itemView);
            imgTour = itemView.findViewById(R.id.imgTour);
            tvNameTour = itemView.findViewById(R.id.txtvNameTour);
            tvPromotion = itemView.findViewById(R.id.txtvUuDai);
            tvPrice = itemView.findViewById(R.id.txtvGia);
        }
    }
}
