package com.rk.rxjavasamples.operators;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rk.rxjavasamples.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;


public class JoinActivity extends AppCompatActivity {
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
                doTheOperation();
            }
        });
    }

    public void doTheOperation() {
        Observable<Long> left = Observable
                .interval(100, TimeUnit.MILLISECONDS);

        Observable<Long> right = Observable
                .interval(100, TimeUnit.MILLISECONDS);

        left.join(right,
                aLong -> {
                    return Observable.timer(0, TimeUnit.SECONDS);
                },
                aLong -> Observable.timer(0, TimeUnit.SECONDS),
                (l, r) -> {
                    System.out.println("Left result: " + l + " Right Result: " + r);
                    return l + r;
                })
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        System.out.println("onNext: " + aLong);
                        mResultText.append(AppConstant.LINE_SEPARATOR);
                        mResultText.append(" value : " + aLong);
                        mResultText.append(AppConstant.LINE_SEPARATOR);                    }

                    @Override
                    public void onError(Throwable e) {
                        mResultText.append(" onError : " + e.getMessage());
                        mResultText.append(AppConstant.LINE_SEPARATOR);
                    }

                    @Override
                    public void onComplete() {
                        mResultText.append(" onComplete");
                        mResultText.append(AppConstant.LINE_SEPARATOR);
                    }
                });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }






}
