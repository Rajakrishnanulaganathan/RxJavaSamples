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


public class ConcatActivity extends AppCompatActivity {
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

        Observable.concat(getOneObservable(), getTwoObservable())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("onNext: " + s);
                        mResultText.append(AppConstant.LINE_SEPARATOR);
                        mResultText.append(" value : " + s);
                        mResultText.append(AppConstant.LINE_SEPARATOR);
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
                });



        Thread.sleep(5000);

    }


    Observable<String> getOneObservable(){
        return Observable.fromArray(new String[]{"1","2","3"});
    }
    Observable<String> getTwoObservable(){
        return Observable.fromArray(new String[]{"3","4","5"});
    }



}
