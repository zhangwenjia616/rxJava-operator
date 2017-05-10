package com.zwj.Operators.Transforming_Observables;

import rx.Observable;
import rx.Observer;
import rx.functions.Func1;

/**
 * transform the items emitted by an Observable by applying a function to each item
 * 
 * @ClassName Map
 * @Description
 * @author zhangwj@yiche.com
 * @date Dec 11, 2016 7:37:05 PM
 */
public class Map {

  public static void main(String[] args) {
    testMap();
    testMapLambda();
  }

  public static void testMap() {
    Observable.just("Hello, world!").map(new Func1<String, String>() {
      @Override
      public String call(String s) { // 参数s是Observable emit出来的
        return s + " -Zhang Wen Jia"; // 返回的是转变后的item 不产生新的被观察对象 只是里面的数据发生了变化
      } // flatmap返回值是Observable2 又生成了一个新的被观察对象
    }).subscribe(new Observer<String>() {
      @Override
      public void onCompleted() {}

      @Override
      public void onError(Throwable e) {}

      @Override
      public void onNext(String s) {
        System.out.println(s);
      }
    });
  }

  public static void testMapLambda() {
    Observable.just("Hello, world!").map(s -> s + " -Zhang Wen Jia")
        .subscribe(s -> System.out.println(s));




  }


}
