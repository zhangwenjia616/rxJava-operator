package com.zwj.Operators.Creating_Observables;

import rx.Observable;
import rx.Subscriber;

/**
 * create an Observable from scratch by calling observer methods programmatically
 *
 * @author zhangwj@yiche.com
 * @ClassName Create
 * @Description
 * @date Dec 11, 2016 9:02:45 PM
 */
public class Create {

    public static void main(String[] args) {
        testCreate();
    }

    public static void testCreate() {

        // 被观察对象
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                // 调用注册进来的观察者的行为方法
                // 括号里面的是stream需要被观察的数据流
                subscriber.onNext("Hello: ");
                subscriber.onNext("My friend,");
                subscriber.onNext("this is rxjava create method test.");
                subscriber.onCompleted();
            }
        });

        // 观察者 String是被观察的类型
        Subscriber<String> subscriber = new Subscriber<String>() {
            // 观察者行为的实现
            @Override
            public void onNext(String s) {
                System.out.println("onNext" + s);
            }

            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError");
                e.printStackTrace();
            }
        };

        // 被观察对象 和  观察者的绑定
        // 一旦注册上了 就会调用OnSubscribe的call方法
        observable.subscribe(subscriber);
    }


}
