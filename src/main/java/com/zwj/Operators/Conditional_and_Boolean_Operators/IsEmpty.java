package com.zwj.Operators.Conditional_and_Boolean_Operators;

import rx.Observable;
import rx.Subscriber;

/**
 * IsEmpty操作符用来判断源Observable是否发射过数据，
 * 如果没发射过就会返回true
 * 
 * @ClassName IsEmpty
 * @Description
 * @author zhangwj@yiche.com
 * @date Dec 16, 2016 10:09:28 AM
 */
public class IsEmpty {

  public static void main(String[] args) {
    isEmptyObserver().subscribe(b -> {
      System.out.print("isEmpty: " + b);
    });
  }

  private static Observable<Boolean> isEmptyObserver() {
    return Observable.create(new Observable.OnSubscribe<Integer>() {
      @Override
      public void call(Subscriber<? super Integer> subscriber) {
        boolean tag = true;
        if (tag) {
          subscriber.onNext(1);
        }
        subscriber.onCompleted();
      }
    }).isEmpty();
  }

}
