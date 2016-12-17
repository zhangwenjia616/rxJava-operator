package com.zwj.Operators.Conditional_and_Boolean_Operators;

import java.util.concurrent.TimeUnit;

import rx.Observable;

/**
 * Amb操作符可以将至多9个Observable结合起来，让他们竞争。哪个Observable首先发射了数据（包括onError和onComplete)就会继续发射这个Observable的数据，
 * 其他的Observable所发射的数据都会别丢弃
 * 
 * @ClassName Amb
 * @Description
 * @author zhangwj@yiche.com
 * @date Dec 16, 2016 10:03:18 AM
 */
public class Amb {

  public static void main(String[] args) {
    ambObserver().subscribe(i -> System.out.print("amb:" + i));
  }

  private static Observable<Integer> ambObserver() {
    Observable<Integer> delay3 = Observable.just(1, 2, 3).delay(3000, TimeUnit.MILLISECONDS);
    Observable<Integer> delay2 = Observable.just(4, 5, 6).delay(2000, TimeUnit.MILLISECONDS);
    Observable<Integer> delay1 = Observable.just(7, 8, 9).delay(1000, TimeUnit.MILLISECONDS);
    return Observable.amb(delay1, delay2, delay3);
  }

}
