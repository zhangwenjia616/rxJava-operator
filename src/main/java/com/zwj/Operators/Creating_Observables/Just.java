package com.zwj.Operators.Creating_Observables;

import rx.Observable;
import rx.Observer;

/**
 * convert an object or a set of objects into an Observable that emits that or those objects
 * @ClassName Just 
 * @Description
 * @author zhangwj@yiche.com 
 * @date Dec 11, 2016 9:02:08 PM
 */
public class Just {

  public static void main(String[] args) {
    testJust();
  }

  public static String helloWorld() {
    return "Hello World";
  }

  public static void testJust() {
    Observable<String> observableString = Observable.just(helloWorld());
    observableString.subscribe(new Observer<String>() {
      @Override
      public void onCompleted() {
        System.out.println("Observable completed");
      }

      @Override
      public void onError(Throwable e) {
        System.out.println("Oh no! Something wrong happened!");
      }

      @Override
      public void onNext(String message) {
        System.out.println(message);
      }
    });
  }

}
