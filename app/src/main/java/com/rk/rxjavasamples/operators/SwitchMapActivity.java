package com.rk.rxjavasamples.operators;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rk.rxjavasamples.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


public class SwitchMapActivity extends AppCompatActivity {
    TextView mResultText;
    Button mClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
        mResultText = findViewById(R.id.txt_result);
        mClick=findViewById(R.id.btn_dothework);
        mClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doTheOperation();
            }
        });
    }

    public void doTheOperation() {
       getObservable().switchMap(new Function<Integer, ObservableSource<Integer>>() {
           @Override
           public ObservableSource<Integer> apply(Integer integer) throws Exception {
               return Observable.just(integer).delay(1, TimeUnit.SECONDS);
           }
       })
               .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(gerObserver());
    }

    Observable<Integer> getObservable() {
        return Observable.fromArray(new Integer[]{1, 2, 3, 4, 5, 6});
    }

    Observer<Integer> gerObserver(){
        return new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(""," onSubscribe : " + d.isDisposed());
            }

            @Override
            public void onNext(Integer s) {
                mResultText.append(" onNext : value : " + s);
                mResultText.append(AppConstant.LINE_SEPARATOR);
            }

            @Override
            public void onError(Throwable e) {
                mResultText.append(" Error : value : " + e);
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
