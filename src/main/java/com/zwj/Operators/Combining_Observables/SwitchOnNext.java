package com.zwj.Operators.Combining_Observables;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class SwitchOnNext {

  public static void main(String[] args) {
    // switchObserver().subscribe(i -> {
    // System.out.println("switch:" + i);
    // });

    // 每隔500毫秒产生一个observable
    Observable<Observable<Long>> observable =
        Observable.timer(0, 500, TimeUnit.MILLISECONDS).map(new Func1<Long, Observable<Long>>() {
          @Override
          public Observable<Long> call(Long aLong) {
            // 每隔200毫秒产生一组数据（0,10,20,30,40)
            return Observable.timer(0, 200, TimeUnit.MILLISECONDS).map(new Func1<Long, Long>() {
              @Override
              public Long call(Long aLong) {
                return aLong * 10;
              }
            }).take(5);
          }
        }).take(2);

    Observable.switchOnNext(observable).subscribe(new Subscriber<Long>() {
      @Override
      public void onCompleted() {
        System.out.println("Sequence complete.");
      }

      @Override
      public void onError(Throwable e) {
        System.err.println("Error: " + e.getMessage());
      }

      @Override
      public void onNext(Long aLong) {
        System.out.println("Next:" + aLong);
      }
    });
  }

  private static Observable<String> switchObserver() {
    return Observable
        .switchOnNext(Observable.create(new Observable.OnSubscribe<Observable<String>>() {
          @Override
          public void call(Subscriber<? super Observable<String>> subscriber) {
            for (int i = 1; i < 3; i++) {
              subscriber.onNext(createObserver(i));
              try {
                Thread.sleep(2000);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
            }
          }
        }));
  }

  private static Observable<String> createObserver(int index) {
    return Observable.create(new Observable.OnSubscribe<String>() {
      @Override
      public void call(Subscriber<? super String> subscriber) {
        for (int i = 1; i < 5; i++) {
          subscriber.onNext(index + "-" + i);
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    }).subscribeOn(Schedulers.newThread());
  }

}
