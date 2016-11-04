package com.example.yjn.trytakephoto;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by yjn on 2016/11/4.
 */

public class SimpleFragmentActivity extends FragmentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_fragment_layout);
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
//        transaction.add(R.id.fragment1,new SimpleFragment(),"fragment拍照"); //直接继承
        transaction.add(R.id.fragment1,new SimpleFragmentIpl(),"fragmentIpl拍照"); //实现接口, 重写oncreate,onActivityResult等
        transaction.commit();
    }
}
