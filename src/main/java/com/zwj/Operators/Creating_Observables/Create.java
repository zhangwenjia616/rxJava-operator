package com.zwj.Operators.Creating_Observables;

import rx.Observable;
import rx.Subscriber;

/**
 * create an Observable from scratch by calling observer methods programmatically
 * 
 * @ClassName Create
 * @Description
 * @author zhangwj@yiche.com
 * @date Dec 11, 2016 9:02:45 PM
 */
public class Create {

  public static void main(String[] args) {
    testCreate();
  }

  public static void testCreate() {
    Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
      @Override
      public void call(Subscriber<? super String> subscriber) {
        subscriber.onNext("Hello: ");
        // subscriber.onCompleted();
        subscriber.onNext("My friend,");
        subscriber.onNext("this is rxjava create method test.");
        subscriber.onCompleted();
      }
    });

    Subscriber<String> subscriber = new Subscriber<String>() {
      @Override
      public void onNext(String s) {
        System.out.println("onNext" + s);
      }

      @Override
      public void onCompleted() {
        System.out.println("onCompleted");
      }

      @Override
      public void onError(Throwable e) {
        System.out.println("onError");
        e.printStackTrace();
      }
    };

    observable.subscribe(subscriber);
  }


}
