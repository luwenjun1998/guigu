package com.example.lwj.guiguapp.home.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lwj.guiguapp.R;
import com.example.lwj.guiguapp.home.bean.HomeBean;
import com.example.lwj.guiguapp.utils.Conatants;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;
import com.zhy.magicviewpager.transformer.RotateDownPageTransformer;
import com.zhy.magicviewpager.transformer.RotateYTransformer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter {
    //轮播图类型
    public static final int BANNER=0;
    //频道类型
    public static final int CHANNEL=1;
    //活动类型
    public static final int ATC=2;
    //秒杀类型
    public static final int SCKILL=3;
    //推荐类型
    public static final int RECOMMEND=4;
    //热卖类型
    public static final int HOT=5;

    private int currentType=BANNER;

    private Context context;
    private HomeBean.ResultBean resultBean;
    private LayoutInflater inflater;

    public HomeAdapter(Context context, HomeBean.ResultBean resultBean) {
        this.context = context;
        this.resultBean = resultBean;
        inflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if(i==BANNER){
            View view=inflater.inflate(R.layout.item_banner,viewGroup,false);
            return new BannerViewHolder(view);
        }
        else if(i==CHANNEL){
            View view=inflater.inflate(R.layout.item_grild,viewGroup,false);
            return new ChannelViewHolder(view);
        }
        else if(i==ATC){
            View view=inflater.inflate(R.layout.act_item,viewGroup,false);
            return new ActViewHolder(view);
        }
        else if(i==SCKILL){
            View view=inflater.inflate(R.layout.seckill_item,viewGroup,false);
            return new SeckillViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Log.e("asd",getItemViewType(i)+"");
        if(getItemViewType(i)==BANNER){
            BannerViewHolder bannerViewHolder= (BannerViewHolder) viewHolder;
            bannerViewHolder.banner.setDelayTime(1000);
            bannerViewHolder.banner.isAutoPlay(true);
            bannerViewHolder.banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            bannerViewHolder.banner.setIndicatorGravity(BannerConfig.CENTER);
            List<String> imgsUrl=new ArrayList<>();
            for(int j=0;j<resultBean.getBanner_info().size();j++){
                imgsUrl.add(resultBean.getBanner_info().get(j).getImage());
            }
            bannerViewHolder.banner.setImages(imgsUrl);
            bannerViewHolder.banner.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    Glide.with(context).load(Conatants.IMGURL+path).into(imageView);
                }
            });
            bannerViewHolder.banner.start();

        }
        else if(getItemViewType(i)==CHANNEL){
            ChannelViewHolder channelViewHolder= (ChannelViewHolder) viewHolder;
            List<HomeBean.ResultBean.ChannelInfoBean> channelInfoBeanList=resultBean.getChannel_info();
            ChannelAdapter adapter=new ChannelAdapter(context,channelInfoBeanList);
            channelViewHolder.gv.setAdapter(adapter);

        }
        else if(getItemViewType(i)==ATC){
             ActViewHolder actViewHolder= (ActViewHolder) viewHolder;
             actViewHolder.vp.setPageMargin(30);
             actViewHolder.vp.setPageTransformer(true,new RotateYTransformer());
             actViewHolder.vp.setAdapter(new PagerAdapter() {
                 @Override
                 public int getCount() {
                     return resultBean.getAct_info().size();
                 }

                 @Override
                 public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
                     return view==o;
                 }

                 @NonNull
                 @Override
                 public Object instantiateItem(@NonNull ViewGroup container, int position) {
                     ImageView imageView=new ImageView(context);
                     imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                     container.addView(imageView);
                     Glide.with(context).load(Conatants.IMGURL+resultBean.getAct_info().get(position).getIcon_url()).into(imageView);
                     return imageView;
                 }

                 @Override
                 public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                     container.removeView((View) object);
                 }
             });
        }
        else if(getItemViewType(i)==SCKILL){
             SeckillViewHolder seckillViewHolder= (SeckillViewHolder) viewHolder;
             SeckillAdapter adapter=new SeckillAdapter(context,resultBean.getSeckill_info().getList());
             LinearLayoutManager manager=new LinearLayoutManager(context);
             manager.setOrientation(LinearLayoutManager.HORIZONTAL);
             seckillViewHolder.rv.setLayoutManager(manager);
             seckillViewHolder.rv.setAdapter(adapter);

             handler.sendEmptyMessageDelayed(0,1000);
        }
    }

    private boolean isFirst = true;
    private static TextView tex1;
    private int dt;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                if (isFirst) {
                    dt=Integer.valueOf(resultBean.getSeckill_info().getEnd_time())-Integer.valueOf(resultBean.getSeckill_info().getStart_time());
                    isFirst = false;
                }
                dt = dt - 1000;
                SimpleDateFormat sd = new SimpleDateFormat("HH:mm:ss");
                tex1.setText(sd.format(new Date(dt)));

                handler.removeMessages(0);
                handler.sendEmptyMessageDelayed(0, 1000);
                if (dt == 0) {
                    handler.removeCallbacksAndMessages(null);
                }
            }

        }
    };


    /**
     * 轮播ViewHolder
     */
    class BannerViewHolder extends RecyclerView.ViewHolder{
        private Banner banner;
        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            banner=itemView.findViewById(R.id.banner);
        }
    }

    /**
     *频道ViewHolder
     */
    class ChannelViewHolder extends RecyclerView.ViewHolder{
        GridView gv;
        public ChannelViewHolder(@NonNull View itemView) {
            super(itemView);
            gv=itemView.findViewById(R.id.gv);
        }
    }
    /**
     *活动ViewHolder
     */
    class ActViewHolder extends RecyclerView.ViewHolder{
        ViewPager vp;
        public ActViewHolder(@NonNull View itemView) {
            super(itemView);
            vp=itemView.findViewById(R.id.vp);
        }
    }
    /**
     *秒杀ViewHolder
     */
    static class SeckillViewHolder extends RecyclerView.ViewHolder{
        TextView tex2;
        RecyclerView rv;
        public SeckillViewHolder(@NonNull View itemView) {
            super(itemView);
            tex1=itemView.findViewById(R.id.tex1);
            tex2=itemView.findViewById(R.id.tex2);
            rv=itemView.findViewById(R.id.rv);
        }
    }

    @Override
    public int getItemViewType(int position) {
        switch (position){
            case BANNER:
                 currentType=BANNER;
                break;
            case CHANNEL:
                currentType=CHANNEL;
                break;
            case ATC:
                currentType=ATC;
                break;
            case SCKILL:
                currentType=SCKILL;
                break;
            case RECOMMEND:
                currentType=RECOMMEND;
                break;
            case HOT:
                currentType=HOT;
                break;
        }
        return currentType;
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
