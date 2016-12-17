package com.zwj.Operators.Observable_Utility_Operators;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Using操作符创建一个在Observable生命周期内存活的资源，也可以这样理解：我们创建一个资源并使用它，用一个Observable来限制这个资源的使用时间，
 * 当这个Observable终止的时候，这个资源就会被销毁。
 * 
 * Using需要使用三个参数，分别是：
 * 
 * 1.创建这个一次性资源的函数 2.创建Observable的函数 3.释放资源的函数
 * 
 * @ClassName Using
 * @Description
 * @author zhangwj@yiche.com
 * @date Dec 15, 2016 9:11:01 PM
 */
public class Using {

  public static void main(String[] args) {

    Observable<Long> observable = usingObserver();
    Subscriber<Long> subscriber = new Subscriber<Long>() {
      @Override
      public void onCompleted() {
        System.out.println("onCompleted");
      }

      @Override
      public void onError(Throwable e) {
        System.out.println("onError");
      }

      @Override
      public void onNext(Long o) {
        System.out.println("onNext " + o);
      }
    };

    observable.subscribe(subscriber);
    
    new Thread(() -> {
      try {
        Thread.sleep(1000000000);
        // subscriber.unsubscribe();
      } catch (Exception e) {

      }
    }) {}.start();

  }


  private static Observable<Long> usingObserver() {
    // 
    return Observable.using(() -> new Animal(), i -> Observable.timer(10000, TimeUnit.MILLISECONDS),
        animal -> animal.relase());
  }

  /**
   * 定义了一个Animal类，并使用Using来控制其创建和释放
   * 
   * @ClassName Animal
   * @Description
   * @author zhangwj@yiche.com
   * @date Dec 15, 2016 9:08:43 PM
   */
  private static class Animal {
    Subscriber<Long> subscriber = new Subscriber<Long> () {
      @Override
      public void onCompleted() {
        System.out.println("animal onCompleted");
      }

      @Override
      public void onError(Throwable e) {
        System.out.println("animal onError");
      }

      @Override
      public void onNext(Long o) {
        System.out.println("animal onNext " + o);
      }
    };

    public Animal() {
      System.out.println("create animal");
      Observable.interval(1000, TimeUnit.MILLISECONDS).subscribe(subscriber);
    }

    public void relase() {
      System.out.println("release animal");
      subscriber.unsubscribe();
    }
  }



}
