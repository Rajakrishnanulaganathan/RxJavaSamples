package com.rk.rxjavasamples;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.rk.rxjavasamples.cache.CacheActivity;
import com.rk.rxjavasamples.operators.OperatorsActivity;
import com.rk.rxjavasamples.rxbus.RxBusActivity;
import com.rk.rxjavasamples.search.SearchActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startOperatorsActivity(View view){
        startActivity(new Intent(this, OperatorsActivity.class));
    }

    public void startCacheActivity(View view) {
        startActivity(new Intent(this, CacheActivity.class));
    }

    public void startRxBusActivity(View view) {
        ((MyApplication) getApplication()).sendAutoEvent();
        startActivity(new Intent(this, RxBusActivity.class));
    }

    public void searchActivity(View view) {
        startActivity(new Intent(this, SearchActivity.class));
    }
}
