package com.example.lwj.guiguapp.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lwj.guiguapp.R;
import com.example.lwj.guiguapp.home.bean.HomeBean;
import com.example.lwj.guiguapp.utils.Conatants;

import java.util.ArrayList;
import java.util.List;

public class SeckillAdapter extends RecyclerView.Adapter<SeckillAdapter.SeckillViewHolder>{

    private Context context;
    private List<HomeBean.ResultBean.SeckillInfoBean.ListBean> list;

    public SeckillAdapter(Context context, List<HomeBean.ResultBean.SeckillInfoBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SeckillViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        View view=inflater.inflate(R.layout.item_seckill,viewGroup,false);
        return new SeckillViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SeckillViewHolder seckillViewHolder, int i) {
        seckillViewHolder.tex1.setText(list.get(i).getOrigin_price());
        seckillViewHolder.tex2.setText(list.get(i).getCover_price());
        Glide.with(context).load(Conatants.IMGURL+list.get(i).getFigure()).into(seckillViewHolder.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class SeckillViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView tex1,tex2;
        public SeckillViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img);
            tex1=itemView.findViewById(R.id.tex1);
            tex2=itemView.findViewById(R.id.tex2);
        }
    }
}
