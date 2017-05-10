package com.zwj.Subject;

import rx.Observer;
import rx.subjects.BehaviorSubject;

/**
 * Observer 会接收到 BehaviorSubject 被订阅之前的最后一个数据，再接收其他发射过来的数据，
 * 如果 BehaviorSubject 被订阅之前没有发送任何数据，则会发送一个默认数据。
 */
public class TestBehaviorSubject {


    public static void main(String[] args) {
        testHasEmit();
        testNoItemEmitBeforeSubscribe();
    }

    private static void testHasEmit() {
        /*
        会接收
        behaviorSubject:behaviorSubject 2
        behaviorSubject:behaviorSubject 3
        behaviorSubject:behaviorSubject 4
         */

        BehaviorSubject<String> behaviorSubject = BehaviorSubject.create("default");

        behaviorSubject.onNext("behaviorSubject 1");
        behaviorSubject.onNext("behaviorSubject 2");

        behaviorSubject.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("behaviorSubject:complete");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("behaviorSubject:error");
            }

            @Override
            public void onNext(String s) {
                System.out.println("behaviorSubject:" + s);
            }
        });

        behaviorSubject.onNext("behaviorSubject 3");
        behaviorSubject.onNext("behaviorSubject 4");
    }

    private static void testNoItemEmitBeforeSubscribe() {
        /*
        会接收
        behaviorSubject:default
        behaviorSubject:behaviorSubject 3
        behaviorSubject:behaviorSubject 4
         */

        BehaviorSubject<String> behaviorSubject = BehaviorSubject.create("default");

        behaviorSubject.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("behaviorSubject:complete");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("behaviorSubject:error");
            }

            @Override
            public void onNext(String s) {
                System.out.println("behaviorSubject:" + s);
            }
        });

        behaviorSubject.onNext("behaviorSubject 3");
        behaviorSubject.onNext("behaviorSubject 4");
    }
}
