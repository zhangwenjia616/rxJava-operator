package com.zwj.Operators.Transforming_Observables;

import rx.Observable;

/**
 * apply a function to each item emitted by an Observable, sequentially, and emit each successive
 * value
 * 
 * @ClassName Scan
 * @Description
 * @author zhangwj@yiche.com
 * @date Dec 12, 2016 9:10:47 PM
 */
public class Scan {

  public static void main(String[] args) {
    Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9).scan((x, y) -> x + y).subscribe(number -> {
      System.out.println(number);
    });
  }


}
