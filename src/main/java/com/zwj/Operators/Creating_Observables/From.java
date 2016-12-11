package com.zwj.Operators.Creating_Observables;

import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;

/**
 * convert some other object or data structure into an Observable
 * @ClassName From 
 * @Description
 * @author zhangwj@yiche.com 
 * @date Dec 11, 2016 9:02:34 PM
 */
public class From {

  public static void main(String[] args) {
    testArray();
    testList();
  }

  private static void testArray() {
    String[] letters = {"a", "b", "c", "d"};
    Observable.from(letters).subscribe(new Action1<String>() {
      @Override
      public void call(String letter) {
        System.out.println("array letter is: " + letter);
      }
    });
  }

  private static void testList() {
    String[] letter = {"a", "b", "c", "d"};
    List<String> list = Arrays.asList(letter);

    Observable.from(list).subscribe(new Action1<String>() {
      @Override
      public void call(String letter) {
        System.out.println("list letter is: " + letter);
      }
    });
  }



}
