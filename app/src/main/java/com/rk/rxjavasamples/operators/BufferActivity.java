package com.rk.rxjavasamples.operators;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rk.rxjavasamples.R;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class BufferActivity extends AppCompatActivity {
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
        Observable<List<String>> buffered = getObservable().buffer(3, 1);
        buffered.subscribe(getObserver());
    }

    Observable<String> getObservable() {
        return Observable.fromArray(new String[]{"1","2","3","4","5"});

    }

    private Observer<List<String>> getObserver() {
        return new Observer<List<String>>() {

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<String> stringList) {
                mResultText.append(" onNext size : " + stringList.size());
                mResultText.append(AppConstant.LINE_SEPARATOR);
                for (String value : stringList) {
                    mResultText.append(" value : " + value);
                    mResultText.append(AppConstant.LINE_SEPARATOR);

                }

            }

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
        };
    }



}
