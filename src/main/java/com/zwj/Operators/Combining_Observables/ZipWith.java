package com.zwj.Operators.Combining_Observables;

import java.util.Arrays;
import java.util.List;

import rx.Observable;

public class ZipWith {

  public static void main(String[] args) {
    testZipWith();
  }

  /**
   * zips a provided stream with the existing stream
   */
  public static void testZipWith() {
    List<String> words =
        Arrays.asList("the", "quick", "brown", "fox", "jumped", "over", "the", "lazy", "dog");

    Observable.from(words) // existing stream
        .zipWith(Observable.range(1, Integer.MAX_VALUE), // provided stream
            (string, count) -> String.format("%2d. %s", count, string)) // zip func
        .subscribe(System.out::println);
  }

}
