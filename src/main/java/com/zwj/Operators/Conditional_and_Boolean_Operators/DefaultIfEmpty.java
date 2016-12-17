package com.zwj.Operators.Conditional_and_Boolean_Operators;

import rx.Observable;
import rx.Subscriber;

/**
 * DefaultIfEmpty操作符会判断源Observable是否发射数据，如果源Observable发射了数据则正常发射这些数据，如果没有则发射一个默认的数据
 * 
 * @ClassName DefaultIfEmpty
 * @Description
 * @author zhangwj@yiche.com
 * @date Dec 16, 2016 10:11:48 AM
 */
public class DefaultIfEmpty {

  public static void main(String[] args) {
    emptyObserver().subscribe(b -> {
      System.out.print("" + b);
    });

    notEmptyObserver().subscribe(b -> {
      System.out.print("" + b);
    });
  }

  private static Observable<Integer> emptyObserver() {
    return Observable.create(new Observable.OnSubscribe<Integer>() {
      @Override
      public void call(Subscriber<? super Integer> subscriber) {
        subscriber.onCompleted();
      }
    }).defaultIfEmpty(10);
  }

  private static Observable<Integer> notEmptyObserver() {
    return Observable.create(new Observable.OnSubscribe<Integer>() {
      @Override
      public void call(Subscriber<? super Integer> subscriber) {
        subscriber.onNext(1);
        subscriber.onCompleted();
      }
    }).defaultIfEmpty(10);
  }

}
