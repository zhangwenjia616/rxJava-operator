package com.zwj.Operators.Creating_Observables;

import rx.Observable;
import rx.Observer;

public class TestJust {

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
