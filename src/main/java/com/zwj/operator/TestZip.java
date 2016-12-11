package com.zwj.operator;

import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func2;

/**
 * 测试zip zipWith操作
 */
public class TestZip {

  /**
   * 两个数据源的数据 按照Func规定的方式进行合并 最后消费者收到的事件 是合并后的数据源结果
   * 生成的新的Observable对象的数据个数是 前面两个Observable最少的
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

  /**
   * zips a provided stream with the existing stream
   */
  public static void testZipWith() {
    List<String> words = Arrays.asList("the", "quick", "brown", "fox", "jumped", "over", "the", "lazy", "dog");

    Observable.from(words) // existing stream
        .zipWith(Observable.range(1, Integer.MAX_VALUE), // provided stream
            (string, count) -> String.format("%2d. %s", count, string))
        .subscribe(System.out::println);
  }

  public static void main(String[] args) {
    // testZip();
    testZipWith();
  }



}
