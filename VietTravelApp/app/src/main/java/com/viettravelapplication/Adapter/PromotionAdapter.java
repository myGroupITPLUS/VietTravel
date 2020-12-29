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
import com.viettravelapplication.Interface.ItemClickListener;
import com.viettravelapplication.Model.Promotion;
import com.viettravelapplication.Model.Tour;
import com.viettravelapplication.R;
import com.viettravelapplication.Util.StringUtil;

import java.io.Serializable;
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
        Picasso.get().load(StringUtil.LOAD_IMAGES+promotion.getImage())
                .placeholder(R.drawable.loading)
                .error(R.drawable.noimageicon)
                .into(holder.imagePromotion);
        holder.txtvNameTour.setText(promotion.getNametour());
        holder.txtvUudai.setText("Mã giảm giá: "+promotion.getPromotionid());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtvGia.setText("Giá: "+decimalFormat.format(promotion.getPrice())+"Đ");
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (isLongClick){
                    Toast.makeText(context, "Long Click: "+promotion, Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(context, PromotionDetailActivity.class);
                    intent.putExtra("promotionDetail", (Serializable) promotion);
                    context.startActivity(intent);
                }
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        ImageView imagePromotion;
        TextView txtvNameTour;
        TextView txtvUudai;
        TextView txtvGia;
        private ItemClickListener itemClickListener;
        public ViewHolder(View itemView) {
            super(itemView);
            imagePromotion = itemView.findViewById(R.id.imagePromotion);
            txtvNameTour = itemView.findViewById(R.id.txtvNameTour);
            txtvUudai = itemView.findViewById(R.id.txtvUuDai);
            txtvGia = itemView.findViewById(R.id.txtvGia);
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
