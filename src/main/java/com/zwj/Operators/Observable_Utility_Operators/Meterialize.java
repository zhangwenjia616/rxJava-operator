package com.zwj.Operators.Observable_Utility_Operators;


import rx.Notification;
import rx.Observable;
import rx.Subscriber;

/**
 * Meterialize操作符将OnNext/OnError/OnComplete都转化为一个Notification对象并按照原来的顺序发射出来
 * 
 * @ClassName Meterialize
 * @Description
 * @author zhangwj@yiche.com
 * @date Dec 15, 2016 7:30:08 PM
 */
public class Meterialize {

  public static void main(String[] args) {
    meterializeObserver().subscribe(new Subscriber<Notification<Integer>>() {
      @Override
      public void onCompleted() {
        System.out.println("subscribe --- onCompleted");
      }

      @Override
      public void onError(Throwable e) {
        System.out.println("subscribe --- onError");
      }

      @Override
      public void onNext(Notification<Integer> notification) {
        System.out.println("subscribe --- onNext " + "notification.getKind: "
            + notification.getKind() + ", notification.getValue: " + notification.getValue());
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
