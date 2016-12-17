package com.zwj.Operators.Error_Handling_Operators;

import rx.Observable;
import rx.Subscriber;

/**
 * 类似于OnErrorResume,不同之处在于其会对onError抛出的数据类型做判断，如果是Exception，也会使用另外一个Observable代替原Observable继续发射数据，
 * 否则会将错误分发给Subscriber。
 * 
 * @ClassName OnExceptionResumeNext
 * @Description
 * @author zhangwj@yiche.com
 * @date Dec 14, 2016 4:21:01 PM
 */
public class OnExceptionResumeNext {

  public static void main(String[] args) {
    onExceptionResumeObserver(false).subscribe(new Subscriber<String>() {
      @Override
      public void onCompleted() {
        System.out.println("onCompleted");
      }

      @Override
      public void onError(Throwable e) {
        System.out.println("onError");
        e.printStackTrace();
      }

      @Override
      public void onNext(String t) {
        System.out.println("onNext " + t);
      }
    });
  }

  private static Observable<String> onExceptionResumeObserver(boolean isException) {
    return createObserver(isException).onExceptionResumeNext(Observable.just("7", "8", "9"));
  }

  private static Observable<String> createObserver(Boolean createExcetion) {
    return Observable.create(new Observable.OnSubscribe<String>() {
      @Override
      public void call(Subscriber<? super String> subscriber) {
        for (int i = 1; i <= 6; i++) {
          if (i < 3) {
            subscriber.onNext("onNext:" + i);
          } else if (createExcetion) {
            subscriber.onError(new Exception("Exception"));
          } else {
            subscriber.onError(new Throwable("Throw error"));
          }
        }
      }
    });
  }

}
