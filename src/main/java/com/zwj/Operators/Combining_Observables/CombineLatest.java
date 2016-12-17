package com.zwj.Operators.Combining_Observables;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * when an item is emitted by either of two Observables, combine the latest item emitted by each
 * Observable via a specified function and emit items based on the results of this function
 * 
 * 两个Observable的数据项合并 取每个Observeble当前最后发射的数据项进行合并
 * 再把合并后的结果给消费者
 * 
 * @ClassName CombineLatest
 * @Description
 * @author zhangwj@yiche.com
 * @date Dec 14, 2016 3:55:37 PM
 */
public class CombineLatest {

  public static void main(String[] args) {
    TestCombineLatest();
  }

  private static void TestCombineLatest() {
    // observable1 emit 0, 5, 10, 15, 20
    Observable<Long> observable1 =
        Observable.timer(0, 1000, TimeUnit.MILLISECONDS).map(new Func1<Long, Long>() {
          @Override
          public Long call(Long aLong) {

            System.out.println("observable1 " + aLong * 5);
            return aLong * 5;
          }
        }).take(5);

    // observable1 emit 0, 10, 20, 30, 40
    Observable<Long> observable2 =
        Observable.timer(500, 1000, TimeUnit.MILLISECONDS).map(new Func1<Long, Long>() {
          @Override
          public Long call(Long aLong) {
            System.out.println("observable2 " + aLong * 10);
            return aLong * 10;
          }
        }).take(5);

    Observable.combineLatest(observable1, observable2, new Func2<Long, Long, Long>() {
      @Override
      public Long call(Long aLong1, Long aLong2) {
        System.out.println("combineLatest " + "aLong1 " + aLong1 + ", aLong2 " + aLong2);
        return aLong1 + aLong2;
      }
    }).subscribe(new Subscriber<Long>() {
      @Override
      public void onCompleted() {
        System.out.println("Sequence complete.");
      }

      @Override
      public void onError(Throwable e) {
        System.err.println("Error: " + e.getMessage());
      }

      @Override
      public void onNext(Long aLong) {
        System.out.println("onNext: " + aLong);
        System.out.println("---------");
      }
    });
  }

  /*
  observable1 0
  observable2 0
  combineLatest aLong1 0, aLong2 0
  onNext: 0
  ----------
  observable1 5
  combineLatest aLong1 5, aLong2 0
  onNext: 5
  ----------
  observable2 10
  combineLatest aLong1 5, aLong2 10
  onNext: 15
  ----------
  observable1 10
  combineLatest aLong1 10, aLong2 10
  onNext: 20
  ----------
  observable2 20
  combineLatest aLong1 10, aLong2 20
  onNext: 30
  ----------
  observable1 15
  combineLatest aLong1 15, aLong2 20
  onNext: 35
  ----------
  observable2 30
  combineLatest aLong1 15, aLong2 30
  onNext: 45
  ----------
  observable1 20
  combineLatest aLong1 20, aLong2 30
  onNext: 50
  System.out: ----------
  observable2 40
  combineLatest aLong1 20, aLong2 40
  onNext: 60
  ----------
  Sequence complete.
 */

}
