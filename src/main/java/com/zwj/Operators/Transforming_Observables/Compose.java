package com.zwj.Operators.Transforming_Observables;

import rx.Observable;
import rx.Observable.Transformer;
import rx.schedulers.Schedulers;

/**
 * for reuse
 * @ClassName Compose 
 * @Description
 * @author zhangwj@yiche.com 
 * @date Dec 22, 2016 4:10:09 PM
 */
public class Compose {

  public static void main(String[] args) {
    Observable.just(1, 2, 3).map(data -> data + "").compose(applySchedulers()).subscribe(data -> {
      doSomething(data);
    });
  }

  private static void doSomething(String data) {

  }

  static <T> Transformer<T, T> applySchedulers() {
    return new Transformer<T, T>() {
      @Override
      public Observable<T> call(Observable<T> observable) {
        return observable.subscribeOn(Schedulers.io())
            // should be AndroidSchedulers.mainThread()
            .observeOn(Schedulers.immediate());
      }
    };
  }

}
