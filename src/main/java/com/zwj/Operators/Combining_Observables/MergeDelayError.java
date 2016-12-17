package com.zwj.Operators.Combining_Observables;

import rx.Observable;
import rx.Subscriber;

/**
 * Merge操作符将多个Observable发射的数据整合起来发射，就如同是一个Observable发射的数据一样。 
 * 但是其发射的数据有可能是交错的，如果想要没有交错，可以使用concat操作符。
 * 当某一个Observable发出onError的时候，merge的过程会被停止并将错误分发给Subscriber，如果不想让错误终止merge的过程，
 * 可以使用MeregeDelayError操作符，会将错误在merge结束后再分发。
 * 
 * @ClassName MergeDelayError
 * @Description
 * @author zhangwj@yiche.com
 * @date Dec 13, 2016 4:16:21 PM
 */
public class MergeDelayError {

  public static void main(String[] args) {
    mergeDelayErrorObserver().subscribe(new Subscriber<Integer>() {
      @Override
      public void onCompleted() {
        System.out.println("onCompleted");
      }

      @Override
      public void onError(Throwable e) {
        System.out.println("onError");
        e.printStackTrace();
      }

      @Override
      public void onNext(Integer t) {
        System.out.println("onNext " + t);
      }
    });
  }

  private static Observable<Integer> mergeDelayErrorObserver() {
    return Observable.mergeDelayError(Observable.create(new Observable.OnSubscribe<Integer>() {
      @Override
      public void call(Subscriber<? super Integer> subscriber) {
        for (int i = 0; i < 5; i++) {
          if (i == 3) {
            subscriber.onError(new Throwable("error"));
          }
          subscriber.onNext(i);
        }
      }
    }), Observable.create(new Observable.OnSubscribe<Integer>() {
      @Override
      public void call(Subscriber<? super Integer> subscriber) {
        for (int i = 0; i < 5; i++) {
          subscriber.onNext(5 + i);
        }
        subscriber.onCompleted();
      }
    }));
  }

}
