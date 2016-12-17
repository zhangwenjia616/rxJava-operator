package com.zwj.Operators.Conditional_and_Boolean_Operators;

import java.util.concurrent.TimeUnit;

import rx.Observable;

/**
 * 这两个操作符都是根据条件来跳过一些数据，不同之处在于SkipUnitl是根据一个标志Observable来判断的，当这个标志Observable没有发射数据的时候，
 * 所有源Observable发射的数据都会被跳过；当标志Observable发射了一个数据，则开始正常地发射数据。
 * 
 * @ClassName SkipUntil
 * @Description
 * @author zhangwj@yiche.com
 * @date Dec 16, 2016 10:25:13 AM
 */
public class SkipUntil {

  public static void main(String[] args) {
    skipUntilObserver().subscribe(i -> {
      System.out.println("" + i);
    });
  }

  private static Observable<Long> skipUntilObserver() {
    return Observable.interval(1, TimeUnit.SECONDS)
        .skipUntil(Observable.timer(3, TimeUnit.SECONDS));
  }

}
