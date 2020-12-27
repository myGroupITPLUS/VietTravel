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

import java.text.DecimalFormat;
import java.util.List;

public class PromotionAdapter extends RecyclerView.Adapter<PromotionAdapter.ViewHolder> {
    Context context;
    int layout;
    List<Promotion> list;

    public PromotionAdapter(Context context, int layout, List<Promotion> list){
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
       Promotion promotion = list.get(position);
        Picasso.get().load(promotion.getImage())
                .placeholder(R.drawable.loading)
                .error(R.drawable.noimageicon)
                .into(holder.imgTour);
        holder.txtvNameTour.setText(promotion.getNametour());
        holder.txtvUudai.setText("Mã giảm giá: "+promotion.getPromotionid());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtvGia.setText("Giá: "+decimalFormat.format(promotion.getPrice())+"Đ");
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgTour;
        TextView txtvNameTour;
        TextView txtvUudai;
        TextView txtvGia;
        public ViewHolder(View itemView) {
            super(itemView);
            imgTour = itemView.findViewById(R.id.imgTour);
            txtvNameTour = itemView.findViewById(R.id.txtvNameTour);
            txtvUudai = itemView.findViewById(R.id.txtvUuDai);
            txtvGia = itemView.findViewById(R.id.txtvGia);
        }
    }
}
