package com.zwj.Operators.Filtering_Observables;

import java.util.Arrays;
import java.util.List;

import rx.Observable;

public class DistinctUtilChanged {

  public static void main(String[] args) {
    testDistinctUtilChanged();
  }

  public static void testDistinctUtilChanged() {
    List<String> words = Arrays.asList("a", "b", "b", "a", "b", "c", "d", "b", "e", "f");
    Observable.from(words).distinctUntilChanged().subscribe(System.out::println);
  }

}
