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
import com.viettravelapplication.Model.Category;
import com.viettravelapplication.R;
import com.viettravelapplication.Util.StringUtil;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    Context context;
    int layout;
    List<Category> list;

    public CategoryAdapter(Context context, int layout, List<Category> list){
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
        Category category = list.get(position);
        Picasso.get().load(StringUtil.LOAD_IMAGES+category.getImages())
                .placeholder(R.drawable.loading)
                .error(R.drawable.noimageicon)
                .into(holder.imgCategory);
        holder.tvCateName.setText(category.getCategoryname());
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgCategory;
        TextView tvCateName;
        public ViewHolder(View itemView) {
            super(itemView);
            imgCategory = itemView.findViewById(R.id.imgCategory);
            tvCateName = itemView.findViewById(R.id.tvCateName);
        }
    }
}