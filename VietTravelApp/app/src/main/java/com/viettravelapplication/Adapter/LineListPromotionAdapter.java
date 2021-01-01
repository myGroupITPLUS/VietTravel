package com.viettravelapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.viettravelapplication.Activity.PromotionDetailActivity;
import com.viettravelapplication.Interface.ItemClickListener;
import com.viettravelapplication.Model.Promotion;
import com.viettravelapplication.R;
import com.viettravelapplication.Util.StringUtil;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.List;

public class LineListPromotionAdapter extends RecyclerView.Adapter<LineListPromotionAdapter.ViewHolder> {
    Context context;
    int layout;
    List<Promotion> list;

    public LineListPromotionAdapter(Context context, int layout, List<Promotion> list){
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
    public LineListPromotionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //View view = inflater.inflate(layout, null);
        View view = LayoutInflater.from(context).inflate(layout, null);
        LineListPromotionAdapter.ViewHolder viewHolder = new LineListPromotionAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LineListPromotionAdapter.ViewHolder holder, int position) {
        Promotion promotion = list.get(position);
        Picasso.get().load(StringUtil.LOAD_IMAGES+promotion.getImage())
                .placeholder(R.drawable.loading)
                .error(R.drawable.noimageicon)
                .into(holder.imgPromotion);
        holder.ttvNameTour.setText("Tên Tour: "+promotion.getNametour());
//        holder.ttvMaTour.setText((promotion.getPromotionid());
        DecimalFormat decimalFormat = new DecimalFormat("#");
        holder.ttvMaTour.setText("Mã tour: "+decimalFormat.format(promotion.getId()));
        holder.ttvThoiGianDi.setText(promotion.getTimedi());
        holder.ttvThoiGianVe.setText(promotion.getTimeve());
        holder.ttvDiemDi.setText(promotion.getDiemdi());
        holder.ttvDiemDen.setText(promotion.getDiemden());
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (isLongClick){
                    Toast.makeText(context, "Long Click: "+promotion.toString(), Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(context, PromotionDetailActivity.class);
                    intent.putExtra("promotionDetail", (Serializable) promotion);
                    context.startActivity(intent);
                }
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        ImageView imgPromotion;
        TextView ttvNameTour;
        TextView ttvMaTour;
        TextView ttvThoiGianDi;
        TextView ttvThoiGianVe;
        TextView ttvDiemDi;
        TextView ttvDiemDen;
        private ItemClickListener itemClickListener;
        public ViewHolder(View itemView) {
            super(itemView);
            imgPromotion = itemView.findViewById(R.id.imgPromotion);
            ttvNameTour = itemView.findViewById(R.id.ttvNameTour);
            ttvMaTour = itemView.findViewById(R.id.ttvMaTour);
            ttvThoiGianDi = itemView.findViewById(R.id.ttvThoiGianDi);
            ttvThoiGianVe = itemView.findViewById(R.id.ttvThoiGianVe);
            ttvDiemDi = itemView.findViewById(R.id.ttvDiemDi);
            ttvDiemDen = itemView.findViewById(R.id.ttvDiemDen);
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
