package com.viettravelapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.viettravelapplication.Activity.TourDetailActivity;
import com.viettravelapplication.Interface.ItemClickListener;
import com.viettravelapplication.Model.Tour;
import com.viettravelapplication.R;
import com.viettravelapplication.Util.StringUtil;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.List;

public class LineListTourAdapter extends RecyclerView.Adapter<LineListTourAdapter.ViewHolder>{
    Context context;
    int layout;
    List<Tour> list;

    public LineListTourAdapter(Context context, int layout, List<Tour> list){
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
    public LineListTourAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //View view = inflater.inflate(layout, null);
        View view = LayoutInflater.from(context).inflate(layout, null);
        LineListTourAdapter.ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tour tour = list.get(position);
        Picasso.get().load(StringUtil.LOAD_IMAGES+tour.getImages())
                .placeholder(R.drawable.loading)
                .error(R.drawable.noimageicon)
                .into(holder.imgTour);
        holder.txtvNameTour.setText("Tên Tour: "+tour.getNametour());
        DecimalFormat decimalFormat = new DecimalFormat("#");
        holder.txtvMaTour.setText("Mã tour: "+decimalFormat.format(tour.getId()));
        holder.txtvThoiGianDi.setText(tour.getTimedi());
        holder.txtvThoiGianVe.setText(tour.getTimeve());
        holder.txtvDiemDi.setText(tour.getDiemdi());
        holder.txtvDiemDen.setText(tour.getDiemden());
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
        ImageView imgTour;
        TextView txtvNameTour;
        TextView txtvMaTour;
        TextView txtvThoiGianDi;
        TextView txtvThoiGianVe;
        TextView txtvDiemDi;
        TextView txtvDiemDen;
        private ItemClickListener itemClickListener;
        public ViewHolder(View itemView) {
            super(itemView);
            imgTour = itemView.findViewById(R.id.imgTOUR);
            txtvNameTour = itemView.findViewById(R.id.ttvNAMETOUR);
            txtvMaTour = itemView.findViewById(R.id.ttvMATOUR);
            txtvThoiGianDi = itemView.findViewById(R.id.ttvTIMEDI);
            txtvThoiGianVe = itemView.findViewById(R.id.ttvTIMEVE);
            txtvDiemDi = itemView.findViewById(R.id.ttvDIEMDI);
            txtvDiemDen = itemView.findViewById(R.id.ttvDIEMDEN);
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
