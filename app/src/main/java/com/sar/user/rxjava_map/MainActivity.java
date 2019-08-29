package com.sar.user.rxjava_map;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.reactivestreams.Subscriber;

import java.util.Observable;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView textView=findViewById(R.id.text);
         io.reactivex.Observable.just(1,2,3,4).map(new Function<Integer,Integer>() {
             @Override
             public Integer apply(Integer integer) throws Exception {
                 return integer*10;
             }
         }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Integer>() {


             /**
              * Provides the Observer with the means of cancelling (disposing) the
              * connection (channel) with the Observable in both
              * synchronous (from within {@link #onNext(Object)}) and asynchronous manner.
              *
              * @param d the Disposable instance whose {@link Disposable#dispose()} can
              *          be called anytime to cancel the connection
              * @since 2.0
              */
             @Override
             public void onSubscribe(Disposable d) {

             }

             /**
              * Provides the Observer with a new item to observe.
              * <p>
              * The {@link Observable} may call this method 0 or more times.
              * <p>
              * The {@code Observable} will not call this method again after it calls either {@link #onComplete} or
              * {@link #onError}.
              *
              * @param integer the item emitted by the Observable
              */
             @Override
             public void onNext(Integer integer) {
                 textView.append(integer.toString());

             }

             /**
              * Notifies the Observer that the {@link Observable} has experienced an error condition.
              * <p>
              * If the {@link Observable} calls this method, it will not thereafter call {@link #onNext} or
              * {@link #onComplete}.
              *
              * @param e the exception encountered by the Observable
              */
             @Override
             public void onError(Throwable e) {

             }

             /**
              * Notifies the Observer that the {@link Observable} has finished sending push-based notifications.
              * <p>
              * The {@link Observable} will not call this method if it calls {@link #onError}.
              */
             @Override
             public void onComplete() {

             }
         });
    }
}
