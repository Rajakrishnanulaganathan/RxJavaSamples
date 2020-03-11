package com.rk.rxjavasamples.operators;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.rk.rxjavasamples.R;

public class OperatorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operators);
    }

    public void simpleOperatorsClick(View view){
        startActivity(new Intent(this,SimpleActivity.class));
    }
    public void createOperatorsClick(View view){
        startActivity(new Intent(this,CreateActivity.class));
    }

    public void deferOperatorsClick(View view) {
        startActivity(new Intent(this,DeferActivity.class));
    }

    public void fromOperatorsClick(View view) {
        startActivity(new Intent(this,FromActivity.class));
    }

    public void intervalOperatorsClick(View view) {
        startActivity(new Intent(this,IntervalActivity.class));
    }

    public void rangeOperatorsClick(View view) {
        startActivity(new Intent(this,RangeActivity.class));
    }

    public void repearOperatorsClick(View view) {
        startActivity(new Intent(this,RepeatActivity.class));
    }

    public void timerOperatorsClick(View view) {
        startActivity(new Intent(this,TimerlActivity.class));
    }

    public void bufferOperatorsClick(View view) {
        startActivity(new Intent(this,BufferActivity.class));
    }

    public void mapOperatorsClick(View view) {
        startActivity(new Intent(this,MapActivity.class));

    }

    public void flatmapOperatorsClick(View view) {
        startActivity(new Intent(this,FlatMapActivity.class));

    }
    public void concatmapOperatorsClick(View view) {
        startActivity(new Intent(this,ConcatMapActivity.class));

    }

    public void switchmapOperatorsClick(View view) {
        startActivity(new Intent(this,SwitchMapActivity.class));

    }

    public void groupbyOperatorsClick(View view) {
        startActivity(new Intent(this,GroupByActivity.class));

    }

    public void scanOperatorsClick(View view) {
        startActivity(new Intent(this,ScanActivity.class));

    }

    public void debounceOperatorsClick(View view) {
        startActivity(new Intent(this,DebounceActivity.class));

    }

    public void distinctOperatorsClick(View view) {
        startActivity(new Intent(this,DistinctActivity.class));

    }

    public void elementsperatorsClick(View view) {
        startActivity(new Intent(this,ElementAtActivity.class));

    }

    public void filterOperatorsClick(View view) {
        startActivity(new Intent(this,FilterActivity.class));
    }

    public void ignoreOperatorsClick(View view) {
        startActivity(new Intent(this,IgnoreElementsActivity.class));

    }

    public void skipOperatorsClick(View view) {
        startActivity(new Intent(this,SkipActivity.class));

    }

    public void takeOperatorsClick(View view) {
        startActivity(new Intent(this,TakeActivity.class));
    }

    public void combineLatestperatorsClick(View view) {
        startActivity(new Intent(this,CombineLatestActivity.class));
    }

    public void joinOperatorsClick(View view) {
        startActivity(new Intent(this,JoinActivity.class));

    }

    public void mergeOperatorsClick(View view) {
        startActivity(new Intent(this,MergeActivity.class));

    }

    public void concatOperatorsClick(View view) {
        startActivity(new Intent(this,ConcatActivity.class));

    }

    public void zipOperatorsClick(View view) {
        startActivity(new Intent(this,ZipActivity.class));

    }

    public void publishSubjectOperatorsClick(View view) {
        startActivity(new Intent(this,PublishSubjectExampleActivity.class));

    }

    public void replaysubjectOperatorsClick(View view) {
        startActivity(new Intent(this,ReplaySubjectExampleActivity.class));

    }

    public void asyncsubjectOperatorsClick(View view) {
        startActivity(new Intent(this,AsyncSubjectExampleActivity.class));

    }
    public void behavioursubjectOperatorsClick(View view) {
        startActivity(new Intent(this,BehaviourSubjectExampleActivity.class));

    }
}
