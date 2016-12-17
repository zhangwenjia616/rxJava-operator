package com.zwj.Operators.Combining_Observables;

import rx.Observable;
import rx.functions.Action1;

public class StartWith {

  public static void main(String[] args) {
    Observable.just(1, 2, 3).startWith(-1, 0).subscribe(new Action1<Integer>() {
      @Override
      public void call(Integer t1) {
        System.out.println(t1);
      }
    });
  }

}
