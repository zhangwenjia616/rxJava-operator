package com.zwj.Operators.Filtering_Observables;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;

/**
 * emit the most recent item emitted by an Observable within periodic time intervals
 * Sample操作符会定时地发射源Observable最近发射的数据，其他的都会被过滤掉，等效于ThrottleLast操作符 抽样
 * 
 * @ClassName Sample
 * @Description
 * @author zhangwj@yiche.com
 * @date Dec 13, 2016 2:39:16 PM
 */
public class Sample {

  public static void main(String[] args) {
    createObserver().sample(1000, TimeUnit.MILLISECONDS).subscribe(i -> {
      System.out.println("throttleFirst:" + i);
    });
    // 4 9 14 19
  }

  private static Observable<Integer> createObserver() {
    // 0..1..2..3..4..  5..6..7..8..9..  10..11..12..13..14..  15..16..17..18..19..
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
