package com.zwj.Operators.Filtering_Observables;

import rx.Observable;
import rx.functions.Action1;

/**
 * emit only those items from an Observable that pass a predicate test
 * @ClassName Filter 
 * @Description
 * @author zhangwj@yiche.com 
 * @date Dec 13, 2016 1:46:23 PM
 */
public class Filter {

  public static void main(String[] args) {
    Observable.just(0, 1, 2, 3, 4, 5).filter(i -> i < 3).subscribe(new Action1<Integer>() {
      @Override
      public void call(Integer t1) {
        System.out.println(t1);
      }
    });
  }

}
