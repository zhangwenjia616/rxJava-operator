package com.zwj.Operators.Filtering_Observables;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * 为了过滤
 * debounce操作符也可以使用时间来进行过滤，这时它跟throttleWithTimeOut使用起来是一样， 但是deounce操作符还可以根据一个函数来进行限流。
 * 这个函数的返回值是一个临时Observable，如果源Observable在发射一个新的数据的时候，上一个数据根据函数所生成的临时Observable还没有结束，那么上一个数据就会被过滤掉。
 * 
 * @ClassName Debounce
 * @Description
 * @author zhangwj@yiche.com
 * @date Dec 13, 2016 11:48:35 AM
 */
public class Debounce {

  public static void main(String[] args) {
    testDebounce();
  }

  /**
   * 生成一个Observable并使用debounce对其进行过滤，只有发射来的数据为偶数的时候才会调用onCompleted方法来表示这个临时的Observable已经终止。
   * 最后一个发射的是9 后面没有元素了 所以不需要过滤 保留了下来
   * @Description
   * @author zhangwj@yiche.com
   * @date Dec 13, 2016 11:49:43 AM
   */
  private static void testDebounce() {
    Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9).debounce(new Func1<Integer, Observable<Integer>>() {
      @Override
      public Observable<Integer> call(Integer integer) {
        System.out.println(integer);

        return Observable.create(new Observable.OnSubscribe<Integer>() {
          @Override
          public void call(Subscriber<? super Integer> subscriber) {
            if (integer % 2 == 0 && !subscriber.isUnsubscribed()) {
              System.out.println("complete:" + integer);
              subscriber.onNext(integer);
              subscriber.onCompleted();
            }
          }

        });
      }
    }).subscribe(new Action1<Integer>() {
      @Override
      public void call(Integer t1) {
        System.out.println("debounce result: " + t1);
      }
    });

    // lambda Style
    // Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9).debounce(integer -> {
    // System.out.println(integer);
    // return Observable.create(new Observable.OnSubscribe<Integer>() {
    // @Override
    // public void call(Subscriber<? super Integer> subscriber) {
    // if (integer % 2 == 0 && !subscriber.isUnsubscribed()) {
    // System.out.println("complete:" + integer);
    // subscriber.onNext(integer);
    // subscriber.onCompleted();
    // }
    // }
    // });
    // }).subscribe(new Action1<Integer>() {
    // @Override
    // public void call(Integer t1) {
    // System.out.println("debounce result: " + t1);
    // }
    // });
  }

}
