package com.rk.rxjavasamples.cache;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rk.rxjavasamples.R;
import com.rk.rxjavasamples.cache.model.Data;
import com.rk.rxjavasamples.cache.source.DataSource;
import com.rk.rxjavasamples.cache.source.DiskDataSource;
import com.rk.rxjavasamples.cache.source.MemoryDataSource;
import com.rk.rxjavasamples.cache.source.NetworkDataSource;



import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CacheActivity extends AppCompatActivity {
    TextView mResultText;
    Button mClick;
    DataSource dataSource;
    private static final String TAG = CacheActivity.class.getSimpleName();

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
        dataSource = new DataSource(new MemoryDataSource(), new DiskDataSource(), new NetworkDataSource());
    }

    private void doTheOperation() {

        io.reactivex.Observable<Data> memory = dataSource.getDataFromMemory();
        io.reactivex.Observable<Data> disk = dataSource.getDataFromDisk();
        io.reactivex.Observable<Data> network = dataSource.getDataFromNetwork();

        Observable.concat(memory, disk, network)
                .firstElement()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .toObservable()
                .subscribe(getObserver());
    }

    private Observer<Data> getObserver() {
        return new Observer<Data>() {

            @Override
            public void onSubscribe(Disposable d) {
                Log.d("", " onSubscribe : " + d.isDisposed());
            }

            @Override
            public void onNext(Data data) {
                mResultText.append(" onNext : " + data.source);
                mResultText.append("\n");
                Log.d(TAG, " onNext : " + data.source);
            }

            @Override
            public void onError(Throwable e) {
                mResultText.append(" onError : " + e.getMessage());
                mResultText.append("\n");
                Log.d(TAG, " onError : " + e.getMessage());
            }

            @Override
            public void onComplete() {
                mResultText.append(" onComplete");
                mResultText.append("\n");
                Log.d(TAG, " onComplete");
            }
        };
    }

}
