package com.zwj.Operators.Filtering_Observables;

import rx.Observable;
import rx.functions.Action1;

public class ElementAt {

  public static void main(String[] args) {
    testElementAt();
  }

  public static void testElementAt() {
    Observable.just(0, 1, 2, 3, 4, 5).elementAt(2).subscribe(new Action1<Integer>() {
      @Override
      public void call(Integer t1) {
        System.out.println(t1);
      }
    });
  }

}
