package com.zwj.Subject;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.subjects.PublishSubject;

public class TestPublishSubject {

  public static void testPublishProject() {
    // PublishSubject 同时是生产者和消费者
    PublishSubject<String> stringPublishSubject = PublishSubject.create();

    Subscription subscriptionPrint = stringPublishSubject.subscribe(new Observer<String>() {
      @Override
      public void onCompleted() {
        System.out.println("Observable completed");
      }

      @Override
      public void onError(Throwable e) {
        System.out.println("Oh no! Something wrong happened!");
      }

      @Override
      public void onNext(String message) {
        System.out.println(message);
      }
    });

    stringPublishSubject.onNext("Hello World");
  }

  public static void test() {
    final PublishSubject<Boolean> subject = PublishSubject.create();
    subject.subscribe(new Observer<Boolean>() {
      @Override
      public void onCompleted() {}

      @Override
      public void onError(Throwable e) {}

      @Override
      public void onNext(Boolean completed) {
        System.out.println("Observable completed!");
      }
    });

    Observable.create(new Observable.OnSubscribe<Integer>() {
      @Override
      public void call(Subscriber<? super Integer> subscriber) {
        for (int i = 0; i < 5; i++) {
          subscriber.onNext(i);
        }
        subscriber.onCompleted();
      }
    }).doOnCompleted(new Action0() {
      @Override
      public void call() {
        subject.onNext(true);
      }
    }).subscribe();
  }

  public static void main(String[] args) {
    testPublishProject();
    test();
  }

  public static String potentialException(String s) throws Exception {
    return "...";
  }

}


