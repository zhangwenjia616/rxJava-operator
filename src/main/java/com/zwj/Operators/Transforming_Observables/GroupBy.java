package com.zwj.Operators.Transforming_Observables;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.observables.GroupedObservable;

/**
 * divide an Observable into a set of Observables that each emit a different group of items from the
 * original Observable, organized by key
 * 
 * @ClassName GroupBy
 * @Description
 * @author zhangwj@yiche.com
 * @date Dec 12, 2016 8:49:46 PM
 */
public class GroupBy {

  public static void main(String[] args) {
    testGroupBy();
    testGroupBy2();
  }

  private static void testGroupBy() {
    // lambda style
    // Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9).groupBy(integer -> integer % 2);

    Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9).groupBy(new Func1<Integer, Integer>() {
      @Override
      public Integer call(Integer value) {
        // 按照key为0,1,2分为3组
        return value % 3; // return key
        // different result for a different key
      }
    }).subscribe(new Action1<GroupedObservable<Integer, Integer>>() {
      @Override
      public void call(GroupedObservable<Integer, Integer> result) {
        result
            // .subscribe(new Action1<Integer>() {
            // @Override
            // public void call(Integer value) {
            // System.out.println("key: " + result.getKey() + ", value: " + value);
            // }
            // });
            .count().subscribe(new Action1<Integer>() {
              @Override
              public void call(Integer count) {
                System.out.println("key: " + result.getKey() + ", has count " + count + " value");
              }
            });
      }
    });
  }

  private static void testGroupBy2() {
    Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9).groupBy(new Func1<Integer, Integer>() {
      @Override
      public Integer call(Integer value) {
        // 按照key为0,1,2分为3组
        return value % 3; // return key
        // different results for different keys
      }
    }, new Func1<Integer, String>() {
      @Override
      public String call(Integer value) {
        return "groupByKeyValue:" + value;
      }
    }).subscribe(new Subscriber<GroupedObservable<Integer, String>>() {
      @Override
      public void onCompleted() {}

      @Override
      public void onError(Throwable e) {}

      @Override
      public void onNext(GroupedObservable<Integer, String> integerIntegerGroupedObservable) {
        if (integerIntegerGroupedObservable.getKey() == 0) { // get the values when key == 0
          integerIntegerGroupedObservable.subscribe(integer -> {
            System.out.println(integer);
          });
        }
      }
    });
  }

}
