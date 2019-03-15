package com.example.lwj.guiguapp.home.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.lwj.guiguapp.R;
import com.example.lwj.guiguapp.home.adapter.HomeAdapter;
import com.example.lwj.guiguapp.home.bean.HomeBean;
import com.example.lwj.guiguapp.home.server.HomeServer;
import com.example.lwj.guiguapp.utils.Conatants;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener{

    private EditText etx1;
    private LinearLayout msg;
    private RecyclerView rv;
    private ImageButton img;
    private HomeAdapter adapter;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        init(view);
        initdata();
        return view;
    }

    private void init(View view) {
        etx1=view.findViewById(R.id.etx1);
        msg=view.findViewById(R.id.mes);
        rv=view.findViewById(R.id.rv);
        img=view.findViewById(R.id.img);
        etx1.setOnClickListener(this);
        msg.setOnClickListener(this);
        img.setOnClickListener(this);
        GridLayoutManager manager=new GridLayoutManager(getActivity(),1);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(manager);

    }
    private void initdata(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Conatants.JSONURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        HomeServer menuInterface=retrofit.create(HomeServer.class);
        menuInterface.getData(Conatants.HOME_URL)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e("aaa","1");
                    }

                    @Override
                    public void onNext(HomeBean menu) {
                        Log.e("aaa",menu.getMsg());
                        adapter=new HomeAdapter(getActivity(),menu.getResult());
                        rv.setAdapter(adapter);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("aaa","2");
                    }

                    @Override
                    public void onComplete() {
                        Log.e("aaa","3");
                    }
                });
    }

    @Override
    public void onClick(View v) {
        if(R.id.etx1==v.getId()){

        }
        else if(R.id.mes==v.getId()){

        }
        else if(R.id.img==v.getId()){
          rv.smoothScrollToPosition(0);
        }
    }
}
