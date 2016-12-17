package com.zwj.Operators.Observable_Utility_Operators;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;

/**
 * Timeout操作符给Observable加上超时时间，每发射一个数据后就重置计时器，当超过预定的时间还没有发射下一个数据，就抛出一个超时的异常。
 * Rxjava将Timeout实现为很多不同功能的操作符，比如说超时后用一个备用的Observable继续发射数据等。
 * 
 * @ClassName Timeout
 * @Description
 * @author zhangwj@yiche.com
 * @date Dec 15, 2016 7:44:11 PM
 */
public class Timeout {

  public static void main(String[] args) {
//    createObserver().timeout(200, TimeUnit.MILLISECONDS).subscribe(integer -> {
//      System.out.println("onNext " + integer);
//    });

    createObserver().timeout(200, TimeUnit.MILLISECONDS, Observable.just(6, 6, 6))
        .subscribe(integer -> {
          System.out.println("onNext " + integer);
        });
  }

  private static Observable<Integer> createObserver() {
    // java.util.concurrent.TimeoutException
    return Observable.create(new Observable.OnSubscribe<Integer>() {
      @Override
      public void call(Subscriber<? super Integer> subscriber) {
        for (int i = 0; i < 10; i++) {
          try {
            Thread.sleep(i * 100);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          subscriber.onNext(i);
        }
        subscriber.onCompleted();
      }
    });
  }

}
