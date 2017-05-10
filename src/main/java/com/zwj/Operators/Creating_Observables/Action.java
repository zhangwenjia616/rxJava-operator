package com.zwj.Operators.Creating_Observables;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Created by apple on 5/2/17.
 */
public class Action {


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

        // subscribe() 还支持不完整定义的回调，RxJava 会自动根据定义创建出 Subscriber
        // 最终都会包装成Subscriber
        Action1<String> onNextAction = new Action1<String>() {
            // onNext()
            @Override
            public void call(String s) {
                System.out.println("Action1 Next " + s);
            }
        };

        Action1<Throwable> onErrorAction = new Action1<Throwable>() {
            // onError()
            @Override
            public void call(Throwable throwable) {
                // Error handling
                System.out.println("throwable ");
            }
        };

        Action0 onCompletedAction = new Action0() {
            // onCompleted()
            @Override
            public void call() {
                System.out.println("completed ");
            }
        };

        // 三个观察者被注册了

        // 自动创建 Subscriber ，并使用 onNextAction 来定义 onNext()
        observable.subscribe(onNextAction);

        // 自动创建 Subscriber ，并使用 onNextAction 和 onErrorAction 来定义 onNext() 和 onError()
        observable.subscribe(onNextAction, onErrorAction);

        // 自动创建 Subscriber ，并使用 onNextAction、 onErrorAction 和 onCompletedAction 来定义 onNext()、 onError() 和 onCompleted()
        observable.subscribe(onNextAction, onErrorAction, onCompletedAction);
    }

}
