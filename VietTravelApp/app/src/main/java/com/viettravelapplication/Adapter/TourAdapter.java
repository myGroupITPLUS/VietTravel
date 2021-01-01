package com.viettravelapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import com.viettravelapplication.Activity.PromotionDetailActivity;
import com.viettravelapplication.Activity.TourDetailActivity;
import com.viettravelapplication.Interface.ItemClickListener;
import com.viettravelapplication.Model.Promotion;
import com.viettravelapplication.Model.Tour;
import com.viettravelapplication.R;
import com.viettravelapplication.Util.StringUtil;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.List;

public class TourAdapter extends RecyclerView.Adapter<TourAdapter.ViewHolder> implements Serializable{
    Context context;
    int layout;
    List<Tour> list;

    public TourAdapter(Context context, int layout, List<Tour> list){
        this.context = context;
        this.layout = layout;
        this.list = list;
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
        holder.tvNameTour.setText("Tên Tour: "+tour.getNametour());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.tvPrice.setText("Giá: "+decimalFormat.format(tour.getPrice())+" VNĐ");
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (isLongClick){
                    Toast.makeText(context, "Long Click: "+tour, Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(context, TourDetailActivity.class);
                    intent.putExtra("tourDetail", (Serializable) tour);
                    context.startActivity(intent);
                }
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        ImageView imageTour;
        TextView tvNameTour;
        TextView tvPrice;
        private ItemClickListener itemClickListener;
        public ViewHolder(View itemView) {
            super(itemView);
            imageTour = itemView.findViewById(R.id.imageTour);
            tvNameTour = itemView.findViewById(R.id.tvNameTour);
            tvPrice = itemView.findViewById(R.id.tvGia);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }
        public void setItemClickListener(ItemClickListener itemClickListener){
            this.itemClickListener = itemClickListener;
        }
        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition(), false);

        }

        @Override
        public boolean onLongClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition(), true);
            return true;
        }
    }
}