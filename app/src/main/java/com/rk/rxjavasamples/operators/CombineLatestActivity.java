package com.rk.rxjavasamples.operators;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rk.rxjavasamples.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;


public class CombineLatestActivity extends AppCompatActivity {
    TextView mResultText;
    Button mClick;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
        mResultText = findViewById(R.id.txt_result);
        mClick = findViewById(R.id.btn_dothework);
        mClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    doTheOperation();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void doTheOperation() throws InterruptedException {

        Observable.combineLatest(getOneObservable(), getTwoObservable(),
                new BiFunction<String, String, String>() {
                    @Override
                    public String apply(String observable1Times, String observable2Times) throws Exception {
                        return "Observable1: " + observable1Times + "Observable2: " + observable2Times;
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String item) throws Exception {
                        mResultText.append(item);
                    }
                });


    }

    Observable<String> getOneObservable(){
        return Observable.fromArray(new String[]{"1","2","3"});
    }
    Observable<String> getTwoObservable(){
        return Observable.fromArray(new String[]{"3","4","5"});
    }




}
