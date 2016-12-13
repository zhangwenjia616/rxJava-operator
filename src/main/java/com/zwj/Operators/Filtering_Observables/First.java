package com.zwj.Operators.Filtering_Observables;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.observables.BlockingObservable;

/**
 * emit only the first item, or the first item that meets a condition, from an Observable
 * @ClassName First 
 * @Description
 * @author zhangwj@yiche.com 
 * @date Dec 13, 2016 2:38:04 PM
 */
public class First {

  public static void main(String[] args) {

    // testFirst();
    testBlockingObservable();

  }

  private static void testFirst() {
    Observable.just(0, 1, 2, 3, 4, 5, 6, 7, 8, 9).first(i -> i > 4)
        .subscribe(new Action1<Integer>() {
          @Override
          public void call(Integer t1) {
            System.out.println(t1);
          }
        });
  }

  private static void testBlockingObservable() {
    Integer first = FilterObserver().first(i -> i > 4);
    System.out.println("first is:" + first);
  }

  private static BlockingObservable<Integer> FilterObserver() {
    return Observable.create(new Observable.OnSubscribe<Integer>() {
      @Override
      public void call(Subscriber<? super Integer> subscriber) {
        for (int i = 0; i < 10; i++) {
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          if (!subscriber.isUnsubscribed()) {
            System.out.println("onNext:" + i);
            subscriber.onNext(i);
          }
        }
        if (!subscriber.isUnsubscribed()) {
          subscriber.onCompleted();
        }
      }
    }).toBlocking();
  }

}
