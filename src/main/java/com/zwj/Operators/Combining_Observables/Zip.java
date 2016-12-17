package com.zwj.Operators.Combining_Observables;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func2;

/**
 * combine the emissions of multiple Observables together via a specified function and emit single
 * items for each combination based on the results of this function
 * 
 * Zip操作符将多个Observable发射的数据按顺序组合起来，每个数据只能组合一次，而且都是有序的。最终组合的数据的数量由发射数据最少的Observable来决定
 */
public class Zip {

  /**
   * 两个数据源的数据 按照Func规定的方式进行合并 最后消费者收到的事件 是合并后的数据源结果 生成的新的Observable对象的数据个数是 前面两个Observable最少的
   */
  public static void testZip() {
    Observable<Integer> observable1 = Observable.just(1, 2, 3, 4, 5);
    Observable<String> observable2 = Observable.just("A", "B", "C", "D");

    Observable.zip(observable1, observable2, new Func2<Integer, String, String>() {
      @Override
      public String call(Integer o1, String o2) {
        return o1 + o2;
      }
    }).subscribe(new Subscriber<String>() {
      @Override
      public void onCompleted() {
        System.out.println("Sequence complete.");
      }

      @Override
      public void onError(Throwable e) {
        System.err.println("Error: " + e.getMessage());
      }

      @Override
      public void onNext(String value) {
        System.out.println("Next:" + value);
      }
    });
  }

  public static void main(String[] args) {
    testZip();
  }


}
