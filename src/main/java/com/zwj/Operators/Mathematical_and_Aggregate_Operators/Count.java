package com.zwj.Operators.Mathematical_and_Aggregate_Operators;

import rx.Observable;
import rx.Subscriber;

/**
 * Count操作符用来统计源Observable发射了多少个数据，最后将数目给发射出来；
 * 如果源Observable发射错误，则会将错误直接报出来；在源Observable没有终止前，
 * count是不会发射统计数据的。
 * 
 * @ClassName Count
 * @Description
 * @author zhangwj@yiche.com
 * @date Dec 14, 2016 4:49:35 PM
 */
public class Count {

  public static void main(String[] args) {
    Observable.just(1, 2, 3).count().subscribe(new Subscriber<Integer>() {

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
        System.out.println("onNext count result " + t);
      }
    });
  }

}
