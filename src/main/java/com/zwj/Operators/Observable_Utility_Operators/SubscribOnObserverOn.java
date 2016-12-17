package com.zwj.Operators.Observable_Utility_Operators;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * 1. SubscribOn 用来指定Observable在哪个线程上运行，我们可以指定在IO线程上运行也可以让其新开一个线程运行，当然也可以在当前线程上运行。
 * 一般来讲会指定在各种后台线程而不是主线程上运行，就如同AsyncTask的doInBackground一样
 * 
 * 2. ObserverOn用来指定观察者所运行的线程，也就是发射出的数据在那个线程上使用。
 * 在android中，如果我们要修改UI界面，观察者就必须在主线程上运行，就如同AsyncTask的onPostExecute。
 * 
 * @ClassName SubscribOnObserverOn
 * @Description
 * @author zhangwj@yiche.com
 * @date Dec 15, 2016 7:36:24 PM
 */
public class SubscribOnObserverOn {

  public static void main(String[] args) {
    createObserver().observeOn(Schedulers.computation()).subscribeOn(Schedulers.newThread())
        .subscribe(new Action1<Integer>() {
          @Override
          public void call(Integer t1) {
            System.out.println("消费者:" + Thread.currentThread().getName());
          }
        });
  }

  private static Observable<Integer> createObserver() {
    return Observable.create(new Observable.OnSubscribe<Integer>() {
      @Override
      public void call(Subscriber<? super Integer> subscriber) {
        System.out.println("生产者:" + Thread.currentThread().getName());
        subscriber.onNext(1);
        subscriber.onCompleted();
      }
    });
  }

}
