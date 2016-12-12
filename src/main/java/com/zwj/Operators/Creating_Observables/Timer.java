package com.zwj.Operators.Creating_Observables;

import java.util.concurrent.TimeUnit;

import rx.Observable;

public class Timer {

  public static void main(String[] args) {
    testTimer();
  }

  private static void testTimer() {
    Observable.timer(1, TimeUnit.SECONDS).subscribe(number -> {
      System.out.println(number);
    });

  }
}
