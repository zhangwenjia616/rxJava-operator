package com.zwj.Operators.Creating_Observables;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;

/**
 * create an Observable that emits a sequence of integers spaced by a particular time interval
 * run in computation Scheduler
 * @ClassName Interval 
 * @Description
 * @author zhangwj@yiche.com 
 * @date Dec 12, 2016 3:55:15 PM
 */
public class Interval {

  public static void main(String[] args) {

    Observable.interval(1, TimeUnit.SECONDS).subscribe(new Observer<Long>() {
      @Override
      public void onCompleted() {
        System.out.println("Observable completed");
      }

      @Override
      public void onError(Throwable e) {
        System.out.println("Oh no! Something wrong happened!");
      }

      @Override
      public void onNext(Long message) {
        System.out.println("onNext " + message);
      }
    });
  }

}
