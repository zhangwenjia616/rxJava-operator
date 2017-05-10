package com.zwj.Subject;

import rx.Observer;
import rx.subjects.AsyncSubject;

/**
 * Subject
 *
 * Subject继承了Observable，又实现了Observer接口，所以说它既是Observable又是Observer
 * 可以看成是一个桥梁或者代理，在某些ReactiveX实现中（如RxJava），
 * 它同时充当了Observer和Observable的角色。
 * 因为它是一个Observer，它可以订阅一个或多个Observable；
 * 又因为它是一个Observable，它可以转发它收到(Observe)的数据，也可以发射新的数据
 * <p>
 * Subject 是一个抽象类，不能通过 new 来实例化 Subject，
 * 所以 Subject 有四个实现类，
 * 分别为:
 * AsyncSubject
 * BehaviorSubject
 * PublishSubject
 * ReplaySubject
 */


/**
 * AsyncSubject
 * Observer 会接收 AsyncSubject 的 onComplete() 之前的最后一个数据，asyncSubject5
 * 如果不调用 onCompleted()，Subscriber 将不接收任何数据。
 * 如果因异常而终止，AsyncSubject 将不会释放任何数据，
 * 但是会向 Observer 传递一个异常通知。
 */
public class TestAsyncSubject {

    public static void main(String[] args) {

        AsyncSubject<String> asyncSubject = AsyncSubject.create(); // 这个像是Observable的特性

        asyncSubject.onNext("asyncSubject 1"); // 这个像是Subscriber的特性
        asyncSubject.onNext("asyncSubject 2");
        asyncSubject.onNext("asyncSubject 3");
        asyncSubject.onNext("asyncSubject 4");
        asyncSubject.onNext("asyncSubject 5");
        asyncSubject.onCompleted(); // 可以把这个注释掉看看效果

        asyncSubject.subscribe(new Observer<String>() { // 这个像是Observable的特性
            @Override
            public void onCompleted() {
                System.out.println("asyncSubject onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("asyncSubject onError");
            }

            @Override
            public void onNext(String s) {
                System.out.println("asyncSubject:" + s);
            }
        });
    }




}
