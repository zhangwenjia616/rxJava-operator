package com.zwj.Subject;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.subjects.PublishSubject;

/**
 * PublishSubject
 * <p>
 * Observer 只会接收到 PublishSubject 被订阅之后发送的数据
 */
public class TestPublishSubject {

    public static void main(String[] args) {
        // testPublishProject();
        // test();
        // test1();
        test2();
    }

    public static void test1() {
        PublishSubject<String> publishSubject = PublishSubject.create();

        publishSubject.onNext("publishSubject 1");
        publishSubject.onNext("publishSubject 2");

        publishSubject.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("publishSubject onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("publishSubject onError");
            }

            @Override
            public void onNext(String s) {
                System.out.println("publishSubject onNext:" + s);
            }
        });

        publishSubject.onNext("publishSubject 3");
        publishSubject.onNext("publishSubject 4");
    }

    public static void testPublishProject() {

        // TestPublishSubject 同时是生产者和消费者
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
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

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


    public static String potentialException(String s) throws Exception {
        return "...";
    }

    public static void test2() {
        PublishSubject<String> publishSubject = PublishSubject.create();

        publishSubject.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError");
            }

            @Override
            public void onNext(String s) {
                System.out.println("subject:" + s);
            }
        });

        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("as Bridge");
                subscriber.onCompleted();
            }
        }).subscribe(publishSubject);
    }

}


