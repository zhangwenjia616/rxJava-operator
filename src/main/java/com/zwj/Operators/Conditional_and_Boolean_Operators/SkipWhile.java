package com.zwj.Operators.Conditional_and_Boolean_Operators;

import java.util.concurrent.TimeUnit;

import rx.Observable;

/**
 * SkipWhile 根据一个函数来判断是否跳过数据，当函数返回值为true的时候则一直跳过源Observable发射的数据；当函数返回false的时候则开始正常发射数据。
 * 
 * @ClassName SkipWhile
 * @Description
 * @author zhangwj@yiche.com
 * @date Dec 16, 2016 10:25:19 AM
 */
public class SkipWhile {

  public static void main(String[] args) {
    skipWhileObserver().subscribe(i -> {
      System.out.println("" + i);
    });
  }

  private static Observable<Long> skipWhileObserver() {
    return Observable.interval(1, TimeUnit.SECONDS).skipWhile(aLong -> aLong < 5);
  }

}
