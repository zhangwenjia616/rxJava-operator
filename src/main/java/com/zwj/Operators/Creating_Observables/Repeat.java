package com.zwj.Operators.Creating_Observables;

import rx.Observable;
import rx.Observer;

/**
 * create an Observable that emits a particular item multiple times
 * @ClassName Repeat 
 * @Description
 * @author zhangwj@yiche.com 
 * @date Dec 12, 2016 12:30:32 AM
 */
public class Repeat {

  public static void main(String[] args) {
    testRepeat();
  }

  private static void testRepeat() {
    Observable<String> observableString = Observable.just("repeat");
    observableString.repeat(3).subscribe(new Observer<String>() {
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
