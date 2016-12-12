package com.zwj.Operators.Error_Handling_Operators;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * 
 * @ClassName RetryWhen
 * @Description
 * @author zhangwj@yiche.com
 * @date Dec 11, 2016 10:31:01 PM
 */
public class RetryWhen {

  public static class RetryWithDelayFunc1
      implements
        Func1<Observable<? extends Throwable>, Observable<?>> {

    private final int maxRetries;
    private final int retryDelayMillis;
    private int retryCount;

    public RetryWithDelayFunc1(int maxRetries, int retryDelayMillis) {
      this.maxRetries = maxRetries;
      this.retryDelayMillis = retryDelayMillis;
    }

    @Override
    public Observable<?> call(Observable<? extends Throwable> error) {
      return error.flatMap(new Func1<Throwable, Observable<?>>() {
        @Override
        public Observable<?> call(Throwable throwable) {
          // whether retry or not
          if (++retryCount <= maxRetries) {
            // When this Observable calls onNext, the original Observable will be retried
            // (i.e.re-subscribed).
            System.out.println("get error, it will try after " + retryDelayMillis
                + " millisecond, retry count " + retryCount + ", max is: " + maxRetries);
            return Observable.just(null); // do retry just = next
            // return Observable.timer(5, TimeUnit.SECONDS);
          }
          // Max retries hit. Just pass the error along.
          return Observable.error(throwable); // do on error
        }
      });
    }

  }

  public static void main(String[] args) {
    testRetryWhen();
  }

  private static void testRetryWhen() {
    createObserver().retryWhen(new RetryWithDelayFunc1(3, 3000)).subscribe(new Observer<Integer>() {
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
      public void onNext(Integer i) {
        System.out.println("onNext " + i);
      }
    });
  }

  private static Observable<Integer> createObserver() {
    return Observable.create(new Observable.OnSubscribe<Integer>() {
      @Override
      public void call(Subscriber<? super Integer> subscriber) {
        for (int i = 0; i < 3; i++) {
          if (i == 2) {
            subscriber.onError(new Exception("Exception-@@@"));
          } else {
            subscriber.onNext(i);
          }
        }
      }
    });
  }

}
