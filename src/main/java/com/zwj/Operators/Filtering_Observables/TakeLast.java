package com.zwj.Operators.Filtering_Observables;

import rx.Observable;
import rx.functions.Action1;

/**
 * emit only the last n items emitted by an Observable
 * @ClassName TakeLast 
 * @Description
 * @author zhangwj@yiche.com 
 * @date Dec 13, 2016 2:37:02 PM
 */
public class TakeLast {

  public static void main(String[] args) {
    Observable.just(0, 1, 2, 3, 4, 5).takeLast(2).subscribe(new Action1<Integer>() {
      @Override
      public void call(Integer t1) {
        System.out.println(t1);
      }
    });
  }

}
