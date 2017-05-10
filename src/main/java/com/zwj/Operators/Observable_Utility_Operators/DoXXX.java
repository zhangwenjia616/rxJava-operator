package com.zwj.Operators.Observable_Utility_Operators;

import rx.Observable;
import rx.Subscriber;

/**
 * Do操作符就是给Observable的生命周期的各个阶段加上一系列的回调监听， 当Observable执行到这个阶段的时候，这些回调就会被触发。在Rxjava实现了很多的doxxx操作符。
 * <p>
 * 1.DoOnEach Observable每发射一个数据的时候就会触发这个回调，不仅包括onNext还包括onError和onCompleted 可以得到类型
 * <p>
 * 2.DoOnNext 只有onNext的时候才会被触发。
 * <p>
 * 3.DoOnComplete 会在OnCompleted发生的时候触发回调
 * <p>
 * 4.DoOnError 会在OnError发生的时候触发回调，并将Throwable对象作为参数传进回调函数里。
 * <p>
 * 5.DoOnSubscribe 和 DoOnUnSubscribe则会在Subscriber进行订阅和反订阅的时候触发回调
 * <p>
 * 6.DoOnUnSubscribe 当一个Observable通过OnError或者OnCompleted结束的时候，会反订阅所有的Subscriber
 * <p>
 * 7.DoOnTerminate 会在Observable结束前触发回调，无论是正常还是异常终止。
 * <p>
 * 8.FinallyDo 会在Observable结束后触发回调，无论是正常还是异常终止
 *
 * @author zhangwj@yiche.com
 * @ClassName DoXXX
 * @Description
 * @date Dec 15, 2016 5:14:05 PM
 */
public class DoXXX {

    public static void main(String[] args) {
        testDo();
        //testDoWithError();
    }

    private static Observable<Integer> createObserver() {
        return Observable.just(1, 2, 3, 4, 5);
    }

    private static void testDo() {
        createObserver()
                .doOnEach(notification -> System.out.println("do onEach send " + notification.getValue() + " type: " + notification.getKind()))
                .doOnNext(aInteger -> System.out.println("do onNext send " + aInteger))
                .doOnSubscribe(() -> System.out.println("do onSubscribe"))
                .doOnUnsubscribe(() -> System.out.println("do onUnsubscribe\n"))
                .doOnCompleted(() -> System.out.println("do onCompleted"))
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("消费 subscribe --- onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("消费 subscribe --- onError");
                    }

                    @Override
                    public void onNext(Integer t) {
                        System.out.println("消费 subscribe --- onNext " + t);
                    }
                });
    }

    private static Observable<Integer> createObserverWithError() {
        return Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 1; i <= 5; i++) {
                    if (i <= 3) {
                        subscriber.onNext(i);
                    } else {
                        subscriber.onError(new Throwable("num>3"));
                    }
                }
            }
        });
    }

    private static void testDoWithError() {
        createObserverWithError()
                .doOnEach(notification -> System.out.println(
                        "do onEach send " + notification.getValue() + " type: " + notification.getKind()))
                .doOnError(throwable -> System.out.println("do onError:" + throwable.getMessage()))
                .doOnTerminate(() -> System.out.println("do onTerminate"))
                .finallyDo(() -> System.out.println("finallyDo ")).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("subscribe --- onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("subscribe --- onError");
            }

            @Override
            public void onNext(Integer t) {
                System.out.println("subscribe --- onNext " + t);
            }
        });
        ;
    }

}
