package com.zwj.Subject;

import rx.functions.Action1;
import rx.subjects.ReplaySubject;

/**
 * Created by apple on 5/10/17.
 */
public class TestReplaySubject {

    public static void main(String[] args) {
        // 创建默认初始缓存容量大小为16 的 ReplaySubject，当数据条目超过16会重新分配内存空间，使用这种方式，不论ReplaySubject何时被订阅，Observer都能接收到数据
        ReplaySubject<String> replaySubject = ReplaySubject.create();

        // 创建指定初始缓存容量大小为100 的 ReplaySubject
        // replaySubject = ReplaySubject.create(100);

        // 只缓存订阅前最后发送的2条数据
        // replaySubject = ReplaySubject.createWithSize(2);

        // replaySubject 被订阅前的前1秒内发送的数据才能被接收
        // replaySubject = ReplaySubject.createWithTime(1, TimeUnit.SECONDS,Schedulers.computation());

        replaySubject.onNext("replaySubject:pre 1");
        replaySubject.onNext("replaySubject:pre 2");
        replaySubject.onNext("replaySubject:pre 3");

        replaySubject.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("replaySubject:" + s);
            }
        });

        replaySubject.onNext("replaySubject:after 1");
        replaySubject.onNext("replaySubject:after 2");
        replaySubject.onNext("replaySubject:after 3");

    }
}
