package com.zwj.Operators.Conditional_and_Boolean_Operators;

import rx.Observable;

/**
 * SequenceEqual操作符用来判断两个Observable发射的数据序列是否相同
 * 1.发射的数据相同
 * 2.数据的序列相同，
 * 3.结束的状态相同，
 * 如果相同返回true，否则返回false
 * 
 * @ClassName SequenceEqual
 * @Description
 * @author zhangwj@yiche.com
 * @date Dec 16, 2016 10:16:07 AM
 */
public class SequenceEqual {

  public static void main(String[] args) {
    equalObserver().subscribe(b -> {
      System.out.println("" + b);
    });

    notEqualObserver().subscribe(b -> {
      System.out.println("" + b);
    });
  }


  private static Observable<Boolean> equalObserver() {
    return Observable.sequenceEqual(Observable.just(1, 2, 3), Observable.just(1, 2, 3));
  }

  private static Observable<Boolean> notEqualObserver() {
    return Observable.sequenceEqual(Observable.just(1, 2, 3), Observable.just(1, 2));
  }

}
