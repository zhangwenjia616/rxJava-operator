package com.zwj.Operators.Observable_Utility_Operators;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Delay操作符就是让发射数据的时机延后一段时间，这样所有的数据都会依次延后一段时间发射
 * @ClassName Delay 
 * @Description
 * @author zhangwj@yiche.com 
 * @date Dec 15, 2016 2:19:27 PM
 */
public class Delay {

  public static void main(String[] args) {
    createObserver(2).delay(15000, TimeUnit.MILLISECONDS).subscribe(i -> {
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
  
  /** 接收数据比发射数据晚了15秒
12-15 14:27:15.212 20638-3089/com.rengwuxian.rxjavasamples I/System.out: Observable:1481783235
12-15 14:27:16.213 20638-3089/com.rengwuxian.rxjavasamples I/System.out: Observable:1481783236
12-15 14:27:17.213 20638-3089/com.rengwuxian.rxjavasamples I/System.out: Observable:1481783237
12-15 14:27:18.214 20638-3089/com.rengwuxian.rxjavasamples I/System.out: Observable:1481783238
12-15 14:27:19.214 20638-3089/com.rengwuxian.rxjavasamples I/System.out: Observable:1481783239
12-15 14:27:20.214 20638-3089/com.rengwuxian.rxjavasamples I/System.out: Observable:1481783240
12-15 14:27:21.215 20638-3089/com.rengwuxian.rxjavasamples I/System.out: Observable:1481783241
12-15 14:27:22.216 20638-3089/com.rengwuxian.rxjavasamples I/System.out: Observable:1481783242
12-15 14:27:23.217 20638-3089/com.rengwuxian.rxjavasamples I/System.out: Observable:1481783243
12-15 14:27:24.217 20638-3089/com.rengwuxian.rxjavasamples I/System.out: Observable:1481783244

12-15 14:27:30.213 20638-22382/com.rengwuxian.rxjavasamples I/System.out: Subscriber:1481783235
12-15 14:27:30.213 20638-22382/com.rengwuxian.rxjavasamples I/System.out: Subscriber difference value:15
12-15 14:27:30.213 20638-22382/com.rengwuxian.rxjavasamples I/System.out: ----------------
12-15 14:27:31.213 20638-22382/com.rengwuxian.rxjavasamples I/System.out: Subscriber:1481783236
12-15 14:27:31.213 20638-22382/com.rengwuxian.rxjavasamples I/System.out: Subscriber difference value:15
12-15 14:27:31.213 20638-22382/com.rengwuxian.rxjavasamples I/System.out: ----------------
12-15 14:27:32.213 20638-22382/com.rengwuxian.rxjavasamples I/System.out: Subscriber:1481783237
12-15 14:27:32.213 20638-22382/com.rengwuxian.rxjavasamples I/System.out: Subscriber difference value:15
12-15 14:27:32.213 20638-22382/com.rengwuxian.rxjavasamples I/System.out: ----------------
12-15 14:27:33.214 20638-22382/com.rengwuxian.rxjavasamples I/System.out: Subscriber:1481783238
12-15 14:27:33.214 20638-22382/com.rengwuxian.rxjavasamples I/System.out: Subscriber difference value:15
12-15 14:27:33.214 20638-22382/com.rengwuxian.rxjavasamples I/System.out: ----------------
12-15 14:27:34.214 20638-22382/com.rengwuxian.rxjavasamples I/System.out: Subscriber:1481783239
12-15 14:27:34.214 20638-22382/com.rengwuxian.rxjavasamples I/System.out: Subscriber difference value:15
12-15 14:27:34.214 20638-22382/com.rengwuxian.rxjavasamples I/System.out: ----------------
12-15 14:27:35.214 20638-22382/com.rengwuxian.rxjavasamples I/System.out: Subscriber:1481783240
12-15 14:27:35.215 20638-22382/com.rengwuxian.rxjavasamples I/System.out: Subscriber difference value:15
12-15 14:27:35.215 20638-22382/com.rengwuxian.rxjavasamples I/System.out: ----------------
12-15 14:27:36.215 20638-22382/com.rengwuxian.rxjavasamples I/System.out: Subscriber:1481783241
12-15 14:27:36.215 20638-22382/com.rengwuxian.rxjavasamples I/System.out: Subscriber difference value:15
12-15 14:27:36.215 20638-22382/com.rengwuxian.rxjavasamples I/System.out: ----------------
12-15 14:27:37.218 20638-22382/com.rengwuxian.rxjavasamples I/System.out: Subscriber:1481783242
12-15 14:27:37.219 20638-22382/com.rengwuxian.rxjavasamples I/System.out: Subscriber difference value:15
12-15 14:27:37.219 20638-22382/com.rengwuxian.rxjavasamples I/System.out: ----------------
12-15 14:27:38.217 20638-22382/com.rengwuxian.rxjavasamples I/System.out: Subscriber:1481783243
12-15 14:27:38.218 20638-22382/com.rengwuxian.rxjavasamples I/System.out: Subscriber difference value:15
12-15 14:27:38.218 20638-22382/com.rengwuxian.rxjavasamples I/System.out: ----------------
12-15 14:27:39.218 20638-22382/com.rengwuxian.rxjavasamples I/System.out: Subscriber:1481783244
12-15 14:27:39.218 20638-22382/com.rengwuxian.rxjavasamples I/System.out: Subscriber difference value:15
12-15 14:27:39.218 20638-22382/com.rengwuxian.rxjavasamples I/System.out: ----------------
   */

}
