package com.zwj.Operators.Observable_Utility_Operators;


import rx.Notification;
import rx.Observable;
import rx.Subscriber;

/**
 * DeMeterialize 是 Meterialize 执行相反的过程
 * Meterialize 操作符将OnNext/OnError/OnComplete都转化为一个Notification对象并按照原来的顺序发射出来
 * 
 * @ClassName DeMeterialize 
 * @Description
 * @author zhangwj@yiche.com 
 * @date Dec 15, 2016 7:30:30 PM
 */
public class DeMeterialize {

  public static void main(String[] args) {
    deMeterializeObserver().subscribe(new Subscriber<Integer>() {
      @Override
      public void onCompleted() {
        System.out.println("subscribe --- onCompleted");
      }

      @Override
      public void onError(Throwable e) {
        System.out.println("subscribe --- onError");
      }

      @Override
      public void onNext(Integer integer) {
        System.out.println("subscribe --- onNext " + integer);
      }
    });
  }

  private static Observable<Notification<Integer>> meterializeObserver() {
    return Observable.just(1, 2, 3).materialize();
  }

  private static Observable<Integer> deMeterializeObserver() {
    return meterializeObserver().dematerialize();
  }

}
