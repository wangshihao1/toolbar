package com.bawei.myapplication.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.bawei.myapplication.R;
import com.bawei.myapplication.data.bean.HotVideoBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class HotAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<HotVideoBean.ResultBean> mhot;

    public void setData(Context context,List<HotVideoBean.ResultBean> mhot) {
        this.context = context;
        this.mhot = mhot;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_hot,viewGroup,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
         MyHolder holder = (MyHolder) viewHolder;
         holder.info.setText(mhot.get(i).getSummary());
         holder.simple.setImageURI(Uri.parse(mhot.get(i).getImageUrl()));

    }

    @Override
    public int getItemCount() {
        return mhot.size();
    }


    class MyHolder extends RecyclerView.ViewHolder{

        private SimpleDraweeView simple;
        private TextView info;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            simple = itemView.findViewById(R.id.simple);
            info = itemView.findViewById(R.id.tv_info);
        }
    }
}
