package com.zwj.Operators.Connectable_Observable_Operators;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.observables.ConnectableObservable;
import rx.schedulers.Schedulers;

/**
 * Connectable Observable:
 * 就是一种特殊的Observable对象，并不是Subscrib的时候就发射数据，而是只有对其应用connect操作符的时候才开始发射数据，所以可以用来更灵活的控制数据发射的时机。
 * 而Publish操作符就是用来将一个普通的Observable对象转化为一个Connectable Observable。需要注意的是如果发射数据已经开始了再进行订阅只能接收以后发射的数据。
 * 
 * Connect操作符就是用来触发Connectable
 * Observable发射数据的。应用Connect操作符后会返回一个Subscription对象，通过这个Subscription对象，我们可以调用其unsubscribe方法来终止数据的发射。
 * 另外，如果还没有订阅者订阅的时候就应用Connect操作符也是可以使其开始发射数据的。
 * 
 * Connect — instruct a connectable Observable to begin emitting items to its subscribers
 * 
 * @ClassName Connect
 * @Description
 * @author zhangwj@yiche.com
 * @date Dec 16, 2016 9:43:53 AM
 */
public class Connect {

  public static void main(String[] args) {
    ConnectableObservable<Long> obs = publishObserver();
    Action1 action2 = o -> System.out.println("action2:" + o);
    Action1 action1 = o -> {
      System.out.println("action1:" + o);
      // action2 比 action1 晚一会儿 所以只能接受到后半部分数据
      if ((long) o == 3) obs.subscribe(action2);
    };
    obs.subscribe(action1);

    // 点击按钮开始注册
    // 在没有connect操作之前 即使有消费者订阅了 生产者也不发送数据
    Subscription subscription = obs.connect();

    // 点击按钮取消注册
    if (subscription != null) {
      subscription.unsubscribe();
    }
  }

  private static ConnectableObservable<Long> publishObserver() {
    Observable<Long> obser = Observable.interval(1, TimeUnit.SECONDS);
    obser.observeOn(Schedulers.newThread());
    return obser.publish(); // 把普通的observable转成ConnectableObservable
  }

}
