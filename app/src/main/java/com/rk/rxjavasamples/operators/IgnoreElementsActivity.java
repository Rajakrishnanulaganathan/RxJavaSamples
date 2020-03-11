package com.rk.rxjavasamples.operators;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rk.rxjavasamples.R;

import io.reactivex.CompletableObserver;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;


public class IgnoreElementsActivity extends AppCompatActivity {
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
        Observable.range(1, 10)
                .ignoreElements()
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mResultText.append(" onSubscribed");

                    }

                    @Override
                    public void onComplete() {
                        mResultText.append(" onComplete");
                        mResultText.append(AppConstant.LINE_SEPARATOR);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mResultText.append(" onError : " + e.getMessage());
                        mResultText.append(AppConstant.LINE_SEPARATOR);
                    }
                });    }






}
