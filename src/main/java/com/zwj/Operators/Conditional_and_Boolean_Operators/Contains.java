package com.zwj.Operators.Conditional_and_Boolean_Operators;

import rx.Observable;

/**
 * Contains操作符用来判断源Observable所发射的数据是否包含某一个数据，如果包含会返回true，如果源Observable已经结束了却还没有发射这个数据则返回false
 * 
 * @ClassName Contains
 * @Description
 * @author zhangwj@yiche.com
 * @date Dec 16, 2016 10:10:12 AM
 */
public class Contains {

  public static void main(String[] args) {
    containsObserver().subscribe(b -> {
      System.out.print("contains: " + b);
    });
  }

  private static Observable<Boolean> containsObserver() {
    boolean tag = false;
    if (tag) {
      return Observable.just(1, 2, 3).contains(3);
    } else {
      return Observable.just(1, 2, 3).contains(4);
    }
  }
}
