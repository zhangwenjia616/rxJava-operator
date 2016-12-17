package com.zwj.Operators.Connectable_Observable_Operators;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.observables.ConnectableObservable;
import rx.schedulers.Schedulers;

/**
 * 
 * Replay操作符返回一个Connectable Observable 对象并且可以缓存其发射过的数据，这样即使有订阅者在其发射数据之后进行订阅也能收到其之前发射过的数据。
 * 不过使用Replay操作符我们最好还是限定其缓存的大小， 否则缓存的数据太多了可会占用很大的一块内存。对缓存的控制可以从空间和时间两个方面来实现。
 * 
 * 可以控制Observable什么时候发射数据connect的时候 也可以让后来的消费者不会损失前面发射的数据
 * 
 * Replay — ensure that all observers see the same sequence of emitted items, even if they subscribe
 * after the Observable has begun emitting items
 * 
 * @ClassName Replay
 * @Description
 * @author zhangwj@yiche.com
 * @date Dec 16, 2016 9:43:16 AM
 */
public class Replay {

  public static void main(String[] args) {
    final ConnectableObservable<Long> obs = relayCountObserver();
    // final ConnectableObservable<Long> obs = relayTimeObserver();

    Action1<Long> action2 = o -> {
      System.out.println("action2:" + o);
    };
    Action1<Long> action1 = o -> {
      System.out.println("action1:" + o);
      if (o == 5) obs.subscribe(action2); // 在发射到第5个数字的时候 第2个订阅者
    };

    // relayCount 按数量缓存
    obs.subscribe(action1);
    System.out.println("relayCount");
    Subscription mSubscription = obs.connect();

    // relayTime 按时间缓存
    // obs.subscribe(action1);
    // System.out.println("relayTime");
    // mSubscription = obs.connect();
  }

  private static ConnectableObservable<Long> relayCountObserver() {
    Observable<Long> obser = Observable.interval(1, TimeUnit.SECONDS);
    obser.observeOn(Schedulers.newThread());
    return obser.replay(2);
  }

  private static ConnectableObservable<Long> relayTimeObserver() {
    Observable<Long> obser = Observable.interval(1, TimeUnit.SECONDS);
    obser.observeOn(Schedulers.newThread());
    return obser.replay(3, TimeUnit.SECONDS);
  }

}
