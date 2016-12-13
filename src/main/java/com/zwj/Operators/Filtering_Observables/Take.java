package com.zwj.Operators.Filtering_Observables;

import rx.Observable;
import rx.functions.Action1;

/**
 * emit only the first n items emitted by an Observable
 * @ClassName Take 
 * @Description
 * @author zhangwj@yiche.com 
 * @date Dec 13, 2016 2:34:18 PM
 */
public class Take {

  public static void main(String[] args) {
    Observable.just(0, 1, 2, 3, 4, 5).take(2).subscribe(new Action1<Integer>() {
      @Override
      public void call(Integer t1) {
        System.out.println(t1);
      }
    });

  }

}
