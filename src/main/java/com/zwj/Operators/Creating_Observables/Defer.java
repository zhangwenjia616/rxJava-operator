package com.zwj.Operators.Creating_Observables;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func0;

/**
 * do not create the Observable until the observer subscribes, and create a fresh Observable for each observer
 * @ClassName Defer 
 * @Description
 * @author zhangwj@yiche.com 
 * @date Dec 12, 2016 3:18:35 PM
 */
public class Defer {

  public static void main(String[] args) {
    // test defer and just for comparison
    // use just the current times are always same
    // use defer the current times are different
    testDefer();
    testJust();
  }

  private static void testDefer() {
    // Observable<Long> d = Observable.defer(() -> Observable.just(System.currentTimeMillis()));
    Observable<Long> d = Observable.defer(new Func0<Observable<Long>>() {
      @Override
      public Observable<Long> call() {
        return Observable.just(System.currentTimeMillis());
      }
    });

    d.subscribe(new Subscriber<Long>() {
      @Override
      public void onCompleted() {}

      @Override
      public void onError(Throwable e) {}

      @Override
      public void onNext(Long t) {
        System.out.println("defer " +t);
      }
    });
    
    d.subscribe(new Subscriber<Long>() {
      @Override
      public void onCompleted() {}

      @Override
      public void onError(Throwable e) {}

      @Override
      public void onNext(Long t) {
        System.out.println("defer " + t);
      }
    });
    
    d.subscribe(new Subscriber<Long>() {
      @Override
      public void onCompleted() {}

      @Override
      public void onError(Throwable e) {}

      @Override
      public void onNext(Long t) {
        System.out.println("defer " + t);
      }
    });

  }
  
  private static void testJust() {
    Observable<Long> j = Observable.just(System.currentTimeMillis());

    j.subscribe(new Subscriber<Long>() {
      @Override
      public void onCompleted() {}

      @Override
      public void onError(Throwable e) {}

      @Override
      public void onNext(Long t) {
        System.out.println("just " + t);
      }
    });
    
    j.subscribe(new Subscriber<Long>() {
      @Override
      public void onCompleted() {}

      @Override
      public void onError(Throwable e) {}

      @Override
      public void onNext(Long t) {
        System.out.println("just " + t);
      }
    });
    
    j.subscribe(new Subscriber<Long>() {
      @Override
      public void onCompleted() {}

      @Override
      public void onError(Throwable e) {}

      @Override
      public void onNext(Long t) {
        System.out.println("just " + t);
      }
    });

  }

}
