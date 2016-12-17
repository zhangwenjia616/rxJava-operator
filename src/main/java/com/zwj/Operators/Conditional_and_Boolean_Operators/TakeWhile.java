package com.zwj.Operators.Conditional_and_Boolean_Operators;

import java.util.concurrent.TimeUnit;

import rx.Observable;

/**
 * TakeWhile 根据一个函数来判断是否发射数据，当函数返回值为true的时候正常发射数据；当函数返回false的时候丢弃所有后面的数据。
 * 
 * @ClassName TakeWhile
 * @Description
 * @author zhangwj@yiche.com
 * @date Dec 16, 2016 10:29:38 AM
 */
public class TakeWhile {

  public static void main(String[] args) {
    takeWhileObserver().subscribe(b -> {
      System.out.println("" + b);
    });
  }

  private static Observable<Long> takeWhileObserver() {
    return Observable.interval(1, TimeUnit.SECONDS).takeWhile(aLong -> aLong < 5);
  }

}
