package com.zwj.Operators.Filtering_Observables;

import rx.Observable;
import rx.functions.Action1;

/**
 * emit only the last item emitted by an Observable
 * @ClassName Last 
 * @Description
 * @author zhangwj@yiche.com 
 * @date Dec 13, 2016 2:37:48 PM
 */
public class Last {

  public static void main(String[] args) {
    Observable.just(0, 1, 2, 3, 4, 5, 6, 7, 8, 9).last(i -> i > 4)
        .subscribe(new Action1<Integer>() {
          @Override
          public void call(Integer t1) {
            System.out.println(t1);
          }
        });
  }

}
