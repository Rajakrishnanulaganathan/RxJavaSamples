package com.rk.rxjavasamples.operators;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rk.rxjavasamples.R;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.observables.GroupedObservable;
import io.reactivex.schedulers.Schedulers;


public class GroupByActivity extends AppCompatActivity {
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
        getObservable().groupBy(new Function<Integer, Boolean>() {
            @Override
            public Boolean apply(Integer integer) {
                return (integer % 2 == 0) ? true : false;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<GroupedObservable<Boolean, Integer>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(GroupedObservable<Boolean, Integer> booleanIntegerGroupedObservable) {
                if (booleanIntegerGroupedObservable.getKey()) {
                    booleanIntegerGroupedObservable.subscribe(new Observer<Integer>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(Integer integer) {
                            mResultText.append(" onNext : value : " + integer);
                            mResultText.append(AppConstant.LINE_SEPARATOR);
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {
                            mResultText.append(" onComplete");
                            mResultText.append(AppConstant.LINE_SEPARATOR);
                        }
                    });
                }
            }

            @Override
            public void onError(Throwable e) {
                mResultText.append(" Error : value : " + e);
                mResultText.append(AppConstant.LINE_SEPARATOR);

            }

            @Override
            public void onComplete() {

            }
        });
    }

    Observable<Integer> getObservable() {
        return Observable.range(1, 10);
    }


}
