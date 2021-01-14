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


import com.viettravelapplication.Activity.PromotionDetailActivity;
import com.viettravelapplication.Activity.TipDetailActivity;
import com.viettravelapplication.Interface.ItemClickListener;
import com.viettravelapplication.Model.Tip;
import com.viettravelapplication.R;

import java.io.Serializable;
import java.util.List;

public class TipAdapter extends RecyclerView.Adapter<TipAdapter.ViewHolder> {
    Context context;
    int layout;
    List<Tip> list;

    public TipAdapter(Context context, int layout, List<Tip> list){
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
        View view = LayoutInflater.from(context).inflate(layout, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tip tip = list.get(position);
        holder.tvTitle.setText(tip.getTitle());
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (isLongClick){
                    Toast.makeText(context, "Long Click: "+tip, Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(context, TipDetailActivity.class);
                    intent.putExtra("tipDetail", (Serializable) tip);
                    context.startActivity(intent);
                }
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        TextView tvTitle;
        ImageView xemthem_click;
        private ItemClickListener itemClickListener;
        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            xemthem_click = itemView.findViewById(R.id.xemthem_click);
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
