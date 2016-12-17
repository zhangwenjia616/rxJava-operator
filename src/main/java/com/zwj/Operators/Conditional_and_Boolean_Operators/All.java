package com.zwj.Operators.Conditional_and_Boolean_Operators;

import rx.Observable;

/**
 * All操作符根据一个函数对源Observable发射的所有数据进行判断，最终返回的结果就是这个判断结果。这个函数使用发射的数据作为参数，内部判断所有的数据是否满足我们定义好的判断条件，
 * 如果全部都满足则返回true，否则就返回false
 * 
 * @ClassName All
 * @Description
 * @author zhangwj@yiche.com
 * @date Dec 16, 2016 9:56:01 AM
 */
public class All {

  public static void main(String[] args) {
    allObserver().subscribe(b -> {
      System.out.println("" + b);
    });
  }

  private static Observable<Boolean> allObserver() {
    Observable<Integer> just;
    boolean tag = true;
    if (tag) {
      just = Observable.just(1, 2, 3, 4, 5);
    } else {
      just = Observable.just(1, 2, 3, 4, 5, 6);
    }
    tag = true;
    return just.all(integer -> integer < 6);
  }


}
