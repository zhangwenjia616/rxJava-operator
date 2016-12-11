package com.zwj.Operators.Filtering_Observables;

import java.util.Arrays;
import java.util.List;

import rx.Observable;

/**
 * suppress duplicate items emitted by an Observable
 * @ClassName Distinct 
 * @Description
 * @author zhangwj@yiche.com 
 * @date Dec 11, 2016 7:43:23 PM
 */
public class Distinct {

  public static void testDistinct() {
    List<String> words = Arrays.asList("a", "a", "a", "a", "b", "c", "d", "e", "f");
    Observable.from(words).distinct().subscribe(System.out::println);
  }

  public static void main(String[] args) {
    testDistinct();
  }
 
}
