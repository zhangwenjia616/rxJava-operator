package com.zwj.Operators.Filtering_Observables;

import rx.Observable;
import rx.functions.Action1;

/**
 * suppress the first n items emitted by an Observable
 * @ClassName Skip 
 * @Description
 * @author zhangwj@yiche.com 
 * @date Dec 13, 2016 2:34:27 PM
 */
public class Skip {

  public static void main(String[] args) {
    Observable.just(0, 1, 2, 3, 4, 5).skip(2).subscribe(new Action1<Integer>() {
      @Override
      public void call(Integer t1) {
        System.out.println(t1);
      }
    });
  }

}
