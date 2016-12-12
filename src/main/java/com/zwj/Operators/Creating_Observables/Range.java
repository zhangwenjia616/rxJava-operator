package com.zwj.Operators.Creating_Observables;

import rx.Observable;
import rx.Subscriber;

/**
 * create an Observable that emits a range of sequential integers
 * 
 * @ClassName Range
 * @Description
 * @author zhangwj@yiche.com
 * @date Dec 12, 2016 2:57:03 PM
 */
public class Range {

  public static void main(String[] args) {
    Observable.range(10, 5).subscribe(new Subscriber<Integer>() {

      @Override
      public void onCompleted() {

      }

      @Override
      public void onError(Throwable e) {

      }

      @Override
      public void onNext(Integer i) {
        System.out.println(i);
      }
    });
  }

}
