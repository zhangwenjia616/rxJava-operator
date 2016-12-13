package com.zwj.Operators.Filtering_Observables;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;

/**
 * Returns an Observable that only emits those items emitted by the source Observable that are not
 * followed by another emitted item within a specified time window.
 * 
 * @ClassName ThrottleWithTimeout
 * @Description
 * @author zhangwj@yiche.com
 * @date Dec 12, 2016 11:51:41 PM
 */
public class ThrottleWithTimeout {

  public static void main(String[] args) {
    // 这个操作符通过时间来限流，源Observable每次发射出来一个数据后就会进行计时，
    // 如果在设定好的时间结束前源Observable有新的数据发射出来，这个数据就会被丢弃，同时重新开始计时。
    // 如果每次都是在计时结束前发射数据，那么这个限流就会走向极端：只会发射最后一个数据。
    createObserver().throttleWithTimeout(200, TimeUnit.MILLISECONDS).subscribe(i -> {
      System.out.println("throttleWithTimeout:" + i);
    });
  }

  private static Observable<Integer> createObserver() {
    // 0...1.2.3...4.5.6...7.8.9
    return Observable.create(new Observable.OnSubscribe<Integer>() {
      @Override
      public void call(Subscriber<? super Integer> subscriber) {
        for (int i = 0; i < 10; i++) {
          if (!subscriber.isUnsubscribed()) {
            subscriber.onNext(i);
          }
          int sleep = 100;
          if (i % 3 == 0) {
            sleep = 300;
          }
          try {
            Thread.sleep(sleep);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }

        subscriber.onCompleted();
      }
    });
  }

}
