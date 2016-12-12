package com.zwj.Operators.Error_Handling_Operators;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;

/**
 * if a source Observable sends an onError notification, resubscribe to it in the hopes that it will
 * complete without error
 * 
 * @ClassName Retry
 * @Description
 * @author zhangwj@yiche.com
 * @date Dec 11, 2016 10:28:40 PM
 */
public class Retry {

  public static void main(String[] args) {
    testRetry();
  }

  private static void testRetry() {
    createObserver().retry(3).subscribe(new Observer<Integer>() {
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
      public void onNext(Integer i) {
        System.out.println(i);
      }
    });
  }

  private static Observable<Integer> createObserver() {
    return Observable.create(new Observable.OnSubscribe<Integer>() {
      @Override
      public void call(Subscriber<? super Integer> subscriber) {
        for (int i = 0; i < 3; i++) {
          if (i == 2) {
            subscriber.onError(new Exception("Exception-"));
          } else {
            subscriber.onNext(i);
          }
        }
      }
    });
  }

}
