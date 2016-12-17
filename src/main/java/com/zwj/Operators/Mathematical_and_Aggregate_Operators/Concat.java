package com.zwj.Operators.Mathematical_and_Aggregate_Operators;

import rx.Observable;
import rx.Subscriber;

/**
 * Concat操作符将多个Observable结合成一个Observable并发射数据，并且严格按照先后顺序发射数据，前一个Observable的数据没有发射完，
 * 是不能发射后面Observable的数据的。 有两个操作符跟它类似，但是有区别， 分别是 
 * 1.startWith：仅仅是在前面插上一个数据。 
 * 2.merge:其发射的数据是无序的。
 * 
 * @ClassName Concat
 * @Description
 * @author zhangwj@yiche.com
 * @date Dec 14, 2016 4:46:48 PM
 */
public class Concat {

  public static void main(String[] args) {

    createObservable().subscribe(new Subscriber<Integer>() {

      @Override
      public void onCompleted() {
        System.out.println("onCompleted");
      }

      @Override
      public void onError(Throwable e) {
        System.out.println("onError");
      }

      @Override
      public void onNext(Integer t) {
        System.out.println("onNext " + t);
      }
    });

  }

  public static Observable<Integer> createObservable() {
    Observable<Integer> obser1 = Observable.just(1, 2, 3);
    Observable<Integer> obser2 = Observable.just(4, 5, 6);
    Observable<Integer> obser3 = Observable.just(7, 8, 9);
    return Observable.concat(obser1, obser2, obser3);
  }

}
