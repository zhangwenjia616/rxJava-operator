package com.zwj.operators.Filtering_Observables;

import java.util.Arrays;
import java.util.List;

import rx.Observable;

public class TestDistinct {

  public static void testDistinct() {
    List<String> words = Arrays.asList("a", "a", "a", "a", "b", "c", "d", "e", "f");
    Observable.from(words).distinct().subscribe(System.out::println);
  }

  public static void main(String[] args) {
    testDistinct();
  }
 
  // test 对象 TODO
  
  
}
