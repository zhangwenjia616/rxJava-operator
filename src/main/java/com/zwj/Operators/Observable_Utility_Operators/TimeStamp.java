package com.zwj.Operators.Observable_Utility_Operators;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Timestamped;

/**
 * TimeStamp会将每个数据项给重新包装一下，加上了一个时间戳来标明每次发射的时间
 * @ClassName TimeStamp 
 * @Description
 * @author zhangwj@yiche.com 
 * @date Dec 15, 2016 8:22:04 PM
 */
public class TimeStamp {

  public static void main(String[] args) {
    createObserver().timestamp().subscribe(new Subscriber<Timestamped<Integer>>() {
      @Override
      public void onCompleted() {
        System.out.println("onCompleted ");
      }

      @Override
      public void onError(Throwable e) {
        System.out.println("onError ");
      }

      @Override
      public void onNext(Timestamped<Integer> t) {
        System.out.println("onNext " + "TimestampMillis: " + t.getTimestampMillis() + ", getValue: "
            + t.getValue());
      }
    });
  }

  private static Observable<Integer> createObserver() {
    return Observable.create(new Observable.OnSubscribe<Integer>() {
      @Override
      public void call(Subscriber<? super Integer> subscriber) {
        for (int i = 0; i <= 3; i++) {
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          subscriber.onNext(i);
        }
        subscriber.onCompleted();
      }
    });
  }

}
