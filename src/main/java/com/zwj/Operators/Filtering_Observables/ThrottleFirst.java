package com.zwj.Operators.Filtering_Observables;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;

/**
 * Returns an Observable that emits only the first item emitted by the source Observable during
 * sequential time windows of a specified duration.
 * ThrottleFirst操作符则会定期发射这个时间段里源Observable发射的第一个数据
 * 抽样 过滤
 * 
 * @ClassName ThrottleFirst
 * @Description
 * @author zhangwj@yiche.com
 * @date Dec 13, 2016 2:41:17 PM
 */
public class ThrottleFirst {

  public static void main(String[] args) {
    createObserver().throttleFirst(1000, TimeUnit.MILLISECONDS).subscribe(i -> {
      System.out.println("throttleFirst:" + i);
    });
    // 0
    // 5
    // 10
    // 15
  }

  private static Observable<Integer> createObserver() {
    // 0..1..2..3..4.. 5..6..7..8..9.. 10..11..12..13..14.. 15..16..17..18..19..
    return Observable.create(new Observable.OnSubscribe<Integer>() {
      @Override
      public void call(Subscriber<? super Integer> subscriber) {
        for (int i = 0; i < 20; i++) {
          subscriber.onNext(i);
          try {
            Thread.sleep(200);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
        subscriber.onCompleted();
      }
    });
  }

}
