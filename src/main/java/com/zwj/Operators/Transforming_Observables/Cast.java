package com.zwj.Operators.Transforming_Observables;

import rx.Observable;
import rx.Observer;

/**
 * Returns an Observable that emits the items emitted by the source Observable, converted to the
 * specified type.
 * 
 * @ClassName Cast
 * @Description
 * @author zhangwj@yiche.com
 * @date Dec 12, 2016 9:06:09 PM
 */
public class Cast {

  public static void main(String[] args) {
    testCast();
  }

  private static void testCast() {
    Observable.just(1.0, 2.0, 3.0, 4.0, 5.0).cast(Integer.class).subscribe(new Observer<Integer>() {
      @Override
      public void onCompleted() {}

      @Override
      public void onError(Throwable e) {}

      @Override
      public void onNext(Integer s) {
        System.out.println(s);
      }
    });
  }

}
