package com.example.lwj.guiguapp.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lwj.guiguapp.R;
import com.example.lwj.guiguapp.home.bean.HomeBean;
import com.example.lwj.guiguapp.utils.Conatants;

import java.util.ArrayList;
import java.util.List;

public class ChannelAdapter extends BaseAdapter {
    private Context context;
    private List<HomeBean.ResultBean.ChannelInfoBean> list;

    public ChannelAdapter(Context context, List<HomeBean.ResultBean.ChannelInfoBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
             convertView=View.inflate(context,R.layout.item_channel,null);
             viewHolder=new ViewHolder();
             viewHolder.tex=convertView.findViewById(R.id.tex);
             viewHolder.img=convertView.findViewById(R.id.img);
             convertView.setTag(viewHolder);
        }
        else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        Glide.with(context).load(Conatants.IMGURL+list.get(position).getImage()).into(viewHolder.img);
        viewHolder.tex.setText(list.get(position).getChannel_name());
        return convertView;
    }

    class ViewHolder {
        ImageView img;
        TextView tex;
    }
}
