package com.example.lwj.guiguapp;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.lwj.guiguapp.fragment.FindFragment;
import com.example.lwj.guiguapp.home.fragment.HomeFragment;
import com.example.lwj.guiguapp.fragment.PersonFragment;
import com.example.lwj.guiguapp.fragment.ShopFragment;
import com.example.lwj.guiguapp.fragment.TypeFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.rg)
    RadioGroup rg;
    @BindView(R.id.rb1)
    RadioButton rb1;
    @BindView(R.id.rb2)
    RadioButton rb2;
    @BindView(R.id.rb3)
    RadioButton rb3;
    @BindView(R.id.rb4)
    RadioButton rb4;
    @BindView(R.id.rb5)
    RadioButton rb5;
    HomeFragment homeFragment;
    TypeFragment typeFragment;
    FindFragment findFragment;
    PersonFragment personFragment;
    ShopFragment shopFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        rg.setOnCheckedChangeListener(this);
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        if(homeFragment==null){
            homeFragment=new HomeFragment();
            transaction.replace(R.id.ll,homeFragment);
        }
        else{
            transaction.replace(R.id.ll,homeFragment);
        }
        transaction.commit();
        rb1.setChecked(true);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        if(rg.getCheckedRadioButtonId()==R.id.rb1){
            if(homeFragment==null){
                homeFragment=new HomeFragment();
                transaction.replace(R.id.ll,homeFragment);
            }
            else{
                transaction.replace(R.id.ll,homeFragment);
            }
        }
        else if(rg.getCheckedRadioButtonId()==R.id.rb2){
            if(typeFragment==null){
                typeFragment=new TypeFragment();
                transaction.replace(R.id.ll,typeFragment);
            }
            else{
                transaction.replace(R.id.ll,typeFragment);
            }
        }
        else if(rg.getCheckedRadioButtonId()==R.id.rb3){
            if(findFragment==null){
                findFragment=new FindFragment();
                transaction.replace(R.id.ll,findFragment);
            }
            else{
                transaction.replace(R.id.ll,findFragment);
            }
        }
        else if(rg.getCheckedRadioButtonId()==R.id.rb4){
            if(shopFragment==null){
                shopFragment=new ShopFragment();
                transaction.replace(R.id.ll,shopFragment);
            }
            else{
                transaction.replace(R.id.ll,shopFragment);
            }
        }
        else if(rg.getCheckedRadioButtonId()==R.id.rb5){
            if(personFragment==null){
                personFragment=new PersonFragment();
                transaction.replace(R.id.ll,personFragment);
            }
            else{
                transaction.replace(R.id.ll,personFragment);
            }
        }
        transaction.commit();
    }
}
