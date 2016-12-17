package com.zwj.Operators.Conditional_and_Boolean_Operators;

import java.util.concurrent.TimeUnit;

import rx.Observable;

/**
 * TakeUntil和TakeWhile操作符可以说和SkipUnitl和SkipWhile操作符是完全相反的功能。
 * TakeUntil 使用一个标志Observable是否发射数据来判断，
 * 当标志Observable没有发射数据时，正常发射数据，
 * 而一旦标志Observable发射过了数据则后面的数据都会被丢弃。
 * 
 * @ClassName TakeUntil
 * @Description
 * @author zhangwj@yiche.com
 * @date Dec 16, 2016 10:29:27 AM
 */
public class TakeUntil {

  public static void main(String[] args) {
    takeUntilObserver().subscribe(b -> {
      System.out.println("" + b);
    });
  }

  private static Observable<Long> takeUntilObserver() {
    return Observable.interval(1, TimeUnit.SECONDS)
        .takeUntil(Observable.timer(3, TimeUnit.SECONDS));
  }

}
