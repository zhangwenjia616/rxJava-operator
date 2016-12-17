package com.zwj.Operators.Connectable_Observable_Operators;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.observables.ConnectableObservable;
import rx.schedulers.Schedulers;

/**
 * RefCount操作符就是将一个Connectable Observable 对象再重新转化为一个普通的Observable对象，这时候如果由订阅者进行订阅将会触发数据的发射。 
 * 不用再调用connect才能发射数据了
 * RefCount — make a Connectable Observable behave like an ordinary Observable
 * 
 * @ClassName RefCount
 * @Description
 * @author zhangwj@yiche.com
 * @date Dec 16, 2016 9:43:34 AM
 */
public class RefCount {

  public static void main(String[] args) {
    ConnectableObservable<Long> obs = publishObserver();
    Subscription myubscription = obs.refCount().subscribe(aLong -> {
      System.out.println("refCount:" + aLong);
    });

    // button clicked
    Action1 action2 = o -> System.out.println("action2:" + o);
    Action1 action1 = o -> {
      System.out.println("action1:" + o);
      // action2 比 action1 晚一会儿 所以只能接受到后半部分数据
      if ((long) o > 10) obs.subscribe(action2);
    };
    obs.subscribe(action1);

    // cancel button clicked
    if (myubscription != null) {
      myubscription.unsubscribe();
    }
  }

  private static ConnectableObservable<Long> publishObserver() {
    Observable<Long> obser = Observable.interval(1, TimeUnit.SECONDS);
    obser.observeOn(Schedulers.newThread());
    return obser.publish();
  }

}
