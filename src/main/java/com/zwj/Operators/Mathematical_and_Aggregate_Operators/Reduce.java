package com.zwj.Operators.Mathematical_and_Aggregate_Operators;

import rx.Observable;
import rx.Subscriber;

/**
 * Reduce操作符应用一个函数接收Observable发射的数据和函数的计算结果作为下次计算的参数，输出最后的结果。跟前面我们了解过的scan操作符很类似，只是scan会输出每次计算的结果，
 * 而reduce只会输出最后的结果。
 * 
 * @ClassName Reduce
 * @Description
 * @author zhangwj@yiche.com
 * @date Dec 14, 2016 4:52:41 PM
 */
public class Reduce {

  public static void main(String[] args) {
    Observable.just(1, 2, 3, 4, 5).reduce((x, y) -> x * y).subscribe(new Subscriber<Integer>() {

      @Override
      public void onCompleted() {
        System.out.println("onCompleted");
      }

      @Override
      public void onError(Throwable e) {
        System.out.println("onError");
      }

      @Override
      public void onNext(Integer t) {
        System.out.println("onNext " + t);
      }
    });
  }

}
