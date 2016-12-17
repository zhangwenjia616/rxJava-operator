package com.zwj.Operators.Mathematical_and_Aggregate_Operators;

import rx.Observable;
import rx.Subscriber;

public class Average {

  public static void main(String[] args) {
    Observable.just(1, 2, 3).count().subscribe(new Subscriber<Integer>() {

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
        System.out.println("onNext count result " + t);
      }
    });

  }

}
