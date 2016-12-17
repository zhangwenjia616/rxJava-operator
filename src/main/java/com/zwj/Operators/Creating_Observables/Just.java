package com.zwj.Operators.Creating_Observables;

import rx.Observable;
import rx.Observer;

/**
 * convert an object or a set of objects into an Observable that emits that or those objects
 * 
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
    Observable<String> observable1 = Observable.just("a", "b", "c");
    Observable<String> observable2 = Observable.just("d", "e", "f");
    // Observable.just(helloWorld());

    // emit a null data and the observer will call onNext onCompleted
    // Observable<String> observableString = Observable.just(null);

    Observer<String> observer = new Observer<String>() {
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
        System.out.println("onNext " + message);
      }
    };

    observable1.subscribe(observer); // observable1
    new Thread(() -> {
      try {
        Thread.sleep(2000);
        observable2.subscribe(observer); // observable2
      } catch (Exception e) {}
    }).start();
  }

}
