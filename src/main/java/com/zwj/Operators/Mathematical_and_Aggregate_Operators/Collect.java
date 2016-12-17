package com.zwj.Operators.Mathematical_and_Aggregate_Operators;

import java.util.ArrayList;

import rx.Observable;
import rx.Subscriber;

/**
 * collect用来将源Observable发射的数据给收集到一个数据结构里面，需要使用两个参数：
 * 
 * 一个产生收集数据结构的函数。 
 * 一个接收第一个函数产生的数据结构和源Observable发射的数据作为参数的函数。
 * 
 * @ClassName Collect
 * @Description
 * @author zhangwj@yiche.com
 * @date Dec 14, 2016 6:01:07 PM
 */
public class Collect {

  public static void main(String[] args) {
    collectObserver().subscribe(new Subscriber<ArrayList<Integer>>() {

      @Override
      public void onCompleted() {
        System.out.println("onCompleted");
      }

      @Override
      public void onError(Throwable e) {
        System.out.println("onError");
      }

      @Override
      public void onNext(ArrayList<Integer> t) {
        System.out.println("onNext " + t);
      }
    });
  }

  private static Observable<ArrayList<Integer>> collectObserver() {
    return Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9).collect(() -> new ArrayList<>(),
        (integers, integer) -> integers.add(integer));
  }

}
