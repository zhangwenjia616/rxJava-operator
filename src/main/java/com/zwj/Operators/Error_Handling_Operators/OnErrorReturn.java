package com.zwj.Operators.Error_Handling_Operators;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * 我们知道Rxjava对错误的处理很方便，就是当有错误出现的时候就会调用Subscriber的onError方法来处理错误。
 * 如果有100个Subscriber就要定义100遍，每个Subscriber都要来各自定义如何处理错误. 如何来统一地处理这些错误呢？ 这时我们就可以使用Error
 * handling相关的操作符来集中统一地处理错误。 把错误的处理放在Observeble上
 * 
 * 当发生错误的时候，让Observable发射一个预先定义好的数据并正常地终止
 * 
 * @ClassName OnErrorReturn
 * @Description
 * @author zhangwj@yiche.com
 * @date Dec 14, 2016 4:21:37 PM
 */
public class OnErrorReturn {

  public static void main(String[] args) {
    createObserver().onErrorReturn(new Func1<Throwable, String>() {
      @Override
      public String call(Throwable t1) {
        return "Something wrong";
      }
    }).subscribe(new Action1<String>() {
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
