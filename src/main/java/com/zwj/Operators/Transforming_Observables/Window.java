package com.zwj.Operators.Transforming_Observables;

import rx.Observable;
import rx.Observer;
import rx.functions.Action1;

/**
 * periodically subdivide items from an Observable into Observable windows and emit these windows
 * rather than emitting the items one at a time
 * 
 * similar to buffer
 * 
 * @ClassName Window
 * @Description
 * @author zhangwj@yiche.com
 * @date Dec 12, 2016 9:47:05 PM
 */
public class Window {

  public static void main(String[] args) {

    // lambda
    // Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9).window(3).subscribe(i -> {
    // System.out.println("windowObservable:" + windowObservable);
    //
    // i.subscribe((j -> {
    // System.out.println("Value " + s);
    // }));
    // });

    Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9).window(3)
        .subscribe(new Action1<Observable<Integer>>() {
          @Override
          public void call(Observable<Integer> windowObservable) {
            System.out.println("windowObservable:" + windowObservable);

            windowObservable.subscribe(new Observer<Integer>() {
              @Override
              public void onCompleted() {}

              @Override
              public void onError(Throwable e) {}

              @Override
              public void onNext(Integer s) {
                System.out.println("Value " + s);
              }
            });

          }
        });
  }

}
