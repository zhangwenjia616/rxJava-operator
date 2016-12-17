package com.zwj.Operators.Error_Handling_Operators;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * 
 * 当发生错误的时候，由另外一个Observable来代替当前的Observable并继续发射数据
 * 
 * @ClassName OnErrorResume
 * @Description
 * @author zhangwj@yiche.com
 * @date Dec 14, 2016 4:14:18 PM
 */
public class OnErrorResume {

  public static void main(String[] args) {
    createObserver().onErrorResumeNext(Observable.just("7", "8", "9"))
        .subscribe(new Action1<String>() {
          @Override
          public void call(String t1) {
            System.out.println(t1);
          }
        });
  }

  private static Observable<String> createObserver() {
    return Observable.create(new Observable.OnSubscribe<String>() {
      @Override
      public void call(Subscriber<? super String> subscriber) {
        for (int i = 1; i <= 6; i++) {
          if (i < 3) {
            subscriber.onNext("onNext:" + i);
          } else {
            subscriber.onError(new Throwable("Throw error"));
          }
        }
      }
    });
  }

}
