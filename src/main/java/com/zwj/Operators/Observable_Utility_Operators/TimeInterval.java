package com.zwj.Operators.Observable_Utility_Operators;

import rx.Observable;
import rx.Subscriber;

/**
 * TimeInterval会拦截发射出来的数据，取代为前后两个发射两个数据的间隔时间。对于第一个发射的数据，其时间间隔为订阅后到首次发射的间隔。
 * 
 * @ClassName TimeInterval
 * @Description
 * @author zhangwj@yiche.com
 * @date Dec 15, 2016 8:27:51 PM
 */
public class TimeInterval {

  public static void main(String[] args) {
    createObserver().timeInterval().subscribe(i -> System.out
        .println("timeInterval: Value: " + i.getValue() + ", IntervalInMilliseconds: " + i.getIntervalInMilliseconds()));
  }

  private static Observable<Integer> createObserver() {
    return Observable.create(new Observable.OnSubscribe<Integer>() {
      @Override
      public void call(Subscriber<? super Integer> subscriber) {
        for (int i = 0; i <= 3; i++) {
          try {
            Thread.sleep(i * 1000);
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
