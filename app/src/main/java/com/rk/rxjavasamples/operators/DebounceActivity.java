package com.rk.rxjavasamples.operators;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.rk.rxjavasamples.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DebounceActivity extends AppCompatActivity {
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

    private void doTheOperation() {
        getObservable()
                .debounce(500, TimeUnit.MILLISECONDS)
                // Run on a background thread
                .subscribeOn(Schedulers.io())
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver());
    }

    private Observable<Integer> getObservable() {
        return Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                // send events with simulated time wait
                emitter.onNext(1); // skip
                Thread.sleep(400);
                emitter.onNext(2); // deliver
                Thread.sleep(505);
                emitter.onNext(3); // skip
                Thread.sleep(100);
                emitter.onNext(4); // deliver
                Thread.sleep(605);
                emitter.onNext(5); // deliver
                Thread.sleep(510);
                emitter.onComplete();
            }
        });
    }

    private Observer<Integer> getObserver() {
        return new Observer<Integer>() {

            @Override
            public void onSubscribe(Disposable d) {
                Log.d("", " onSubscribe : " + d.isDisposed());
            }

            @Override
            public void onNext(Integer value) {
                mResultText.append(" onNext : ");
                mResultText.append(AppConstant.LINE_SEPARATOR);
                mResultText.append(" value : " + value);
                mResultText.append(AppConstant.LINE_SEPARATOR);
                Log.d("", " onNext ");
                Log.d("", " value : " + value);
            }

            @Override
            public void onError(Throwable e) {
                mResultText.append(" onError : " + e.getMessage());
                mResultText.append(AppConstant.LINE_SEPARATOR);
                Log.d("", " onError : " + e.getMessage());
            }

            @Override
            public void onComplete() {
                mResultText.append(" onComplete");
                mResultText.append(AppConstant.LINE_SEPARATOR);
                Log.d("", " onComplete");
            }
        };
    }

}
