package com.zwj.Operators.Observable_Utility_Operators;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * 延时注册Subscriber
 * 
 * @ClassName DelaySubscription
 * @Description
 * @author zhangwj@yiche.com
 * @date Dec 15, 2016 2:17:12 PM
 */
public class DelaySubscription {

  public static void main(String[] args) {
    createObserver(2).delaySubscription(15000, TimeUnit.MILLISECONDS).subscribe(i -> {
      System.out.println("Subscriber:" + i);
      System.out.println("Subscriber difference value:" + (getCurrentTime() - i));
      System.out.println("----------------");
    });
  }

  private static Observable<Long> createObserver(int index) {
    return Observable.create(new Observable.OnSubscribe<Long>() {
      @Override
      public void call(Subscriber<? super Long> subscriber) {
        for (int i = 1; i <= index; i++) {
          long observableTime = getCurrentTime();
          System.out.println("Observable:" + observableTime);
          subscriber.onNext(observableTime);
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    }).subscribeOn(Schedulers.newThread());
  }

  private static long getCurrentTime() {
    return System.currentTimeMillis() / 1000;
  }

  /**
   * OnSubscribe的时候才产生数据源 注册延时 emit数据的时间和接收到数据的时间一样
12-15 14:20:28.704 20638-30041/com.rengwuxian.rxjavasamples I/System.out: Observable:1481782828
12-15 14:20:28.705 20638-30041/com.rengwuxian.rxjavasamples I/System.out: Subscriber:1481782828
12-15 14:20:28.705 20638-30041/com.rengwuxian.rxjavasamples I/System.out: Subscriber difference value:0
12-15 14:20:28.705 20638-30041/com.rengwuxian.rxjavasamples I/System.out: ----------------
12-15 14:20:29.705 20638-30041/com.rengwuxian.rxjavasamples I/System.out: Observable:1481782829
12-15 14:20:29.705 20638-30041/com.rengwuxian.rxjavasamples I/System.out: Subscriber:1481782829
12-15 14:20:29.705 20638-30041/com.rengwuxian.rxjavasamples I/System.out: Subscriber difference value:0
12-15 14:20:29.706 20638-30041/com.rengwuxian.rxjavasamples I/System.out: ----------------
12-15 14:20:30.706 20638-30041/com.rengwuxian.rxjavasamples I/System.out: Observable:1481782830
12-15 14:20:30.706 20638-30041/com.rengwuxian.rxjavasamples I/System.out: Subscriber:1481782830
12-15 14:20:30.706 20638-30041/com.rengwuxian.rxjavasamples I/System.out: Subscriber difference value:0
12-15 14:20:30.706 20638-30041/com.rengwuxian.rxjavasamples I/System.out: ----------------
12-15 14:20:31.710 20638-30041/com.rengwuxian.rxjavasamples I/System.out: Observable:1481782831
12-15 14:20:31.711 20638-30041/com.rengwuxian.rxjavasamples I/System.out: Subscriber:1481782831
12-15 14:20:31.711 20638-30041/com.rengwuxian.rxjavasamples I/System.out: Subscriber difference value:0
12-15 14:20:31.711 20638-30041/com.rengwuxian.rxjavasamples I/System.out: ----------------
12-15 14:20:32.711 20638-30041/com.rengwuxian.rxjavasamples I/System.out: Observable:1481782832
12-15 14:20:32.711 20638-30041/com.rengwuxian.rxjavasamples I/System.out: Subscriber:1481782832
12-15 14:20:32.711 20638-30041/com.rengwuxian.rxjavasamples I/System.out: Subscriber difference value:0
12-15 14:20:32.711 20638-30041/com.rengwuxian.rxjavasamples I/System.out: ----------------
12-15 14:20:33.711 20638-30041/com.rengwuxian.rxjavasamples I/System.out: Observable:1481782833
12-15 14:20:33.711 20638-30041/com.rengwuxian.rxjavasamples I/System.out: Subscriber:1481782833
12-15 14:20:33.711 20638-30041/com.rengwuxian.rxjavasamples I/System.out: Subscriber difference value:0
12-15 14:20:33.711 20638-30041/com.rengwuxian.rxjavasamples I/System.out: ----------------
12-15 14:20:34.712 20638-30041/com.rengwuxian.rxjavasamples I/System.out: Observable:1481782834
12-15 14:20:34.712 20638-30041/com.rengwuxian.rxjavasamples I/System.out: Subscriber:1481782834
12-15 14:20:34.713 20638-30041/com.rengwuxian.rxjavasamples I/System.out: Subscriber difference value:0
12-15 14:20:34.713 20638-30041/com.rengwuxian.rxjavasamples I/System.out: ----------------
12-15 14:20:35.713 20638-30041/com.rengwuxian.rxjavasamples I/System.out: Observable:1481782835
12-15 14:20:35.713 20638-30041/com.rengwuxian.rxjavasamples I/System.out: Subscriber:1481782835
12-15 14:20:35.713 20638-30041/com.rengwuxian.rxjavasamples I/System.out: Subscriber difference value:0
12-15 14:20:35.713 20638-30041/com.rengwuxian.rxjavasamples I/System.out: ----------------
12-15 14:20:36.714 20638-30041/com.rengwuxian.rxjavasamples I/System.out: Observable:1481782836
12-15 14:20:36.714 20638-30041/com.rengwuxian.rxjavasamples I/System.out: Subscriber:1481782836
12-15 14:20:36.714 20638-30041/com.rengwuxian.rxjavasamples I/System.out: Subscriber difference value:0
12-15 14:20:36.714 20638-30041/com.rengwuxian.rxjavasamples I/System.out: ----------------
12-15 14:20:37.714 20638-30041/com.rengwuxian.rxjavasamples I/System.out: Observable:1481782837
12-15 14:20:37.715 20638-30041/com.rengwuxian.rxjavasamples I/System.out: Subscriber:1481782837
12-15 14:20:37.715 20638-30041/com.rengwuxian.rxjavasamples I/System.out: Subscriber difference value:0
12-15 14:20:37.715 20638-30041/com.rengwuxian.rxjavasamples I/System.out: ----------------
   */


}
