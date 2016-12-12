package com.zwj.Operators.Transforming_Observables;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;

/**
 * periodically gather items from an Observable into bundles and emit these bundles rather than
 * emitting the items one at a time
 * 
 * @ClassName Buffer
 * @Description
 * @author zhangwj@yiche.com
 * @date Dec 12, 2016 6:30:34 PM
 */
public class Buffer {

  public static void main(String[] args) {
    testBuffer();
    testBufferSkip();
    testBufferTime();
  }

  private static void testBuffer() {
    // gather 3 items
    // receive a list gathered before
    Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9).buffer(3).subscribe(new Action1<List<Integer>>() {
      @Override
      public void call(List<Integer> list) {
        System.out.println(list);
      }
    });

    // lambda style
    // Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9).buffer(3).subscribe(list -> {
    // System.out.println(list);
    // });
  }

  private static void testBufferSkip() {
    // gather 2 items every 3 items. this will ignore 1 item after the second item.
    // buffer(3, 3) == buffer(3)
    Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9).buffer(2, 3).subscribe(i -> {
      System.out.println(i);
    });
  }

  private static void testBufferTime() {
    // gather items every 3 seconds
    Observable.interval(1, TimeUnit.SECONDS).buffer(3, TimeUnit.SECONDS).subscribe(i -> {
      System.out.println(i);
    });
  }



}
