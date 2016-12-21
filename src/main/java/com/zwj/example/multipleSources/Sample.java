package com.zwj.example.multipleSources;

import java.util.concurrent.TimeUnit;

import rx.Observable;

public class Sample {

  public static void main(String[] args) {
    Sources sources = new Sources();

    // Create our sequence for querying best available data
    Observable<Data> source = Observable
        .concat(sources.memory(), // 先从内存中取 看是否是符合first中的条件的
            sources.disk(), // 再从磁盘里取
            sources.network()) // 再从网络上获取
        // .first();
        .first(data -> data != null && data.isUpToDate());

    // "Request" latest data once a second
    // 跟输入的秒数无关 所以是__ 返回的是source
    // Observable 主要是返回source
    Observable.interval(1, TimeUnit.SECONDS).flatMap(__ -> source).subscribe(data -> {
      if (data != null) {
        System.out.println("##Received## " + data.value);
      } else {
        System.out.println("##Received##" + " but data is null");
      }
    }); // 在这里订阅

    // Occasionally clear memory (as if app restarted) so that we must go to disk
    // 每隔三秒钟 清理内存
    // 从disk里获取 或者从网络获取 如果disk中的数据过期的话
    Observable.interval(3, TimeUnit.SECONDS).subscribe(__ -> sources.clearMemory());

    // Java will quit unless we idle
    // 也就是15秒钟结束
    sleep(15 * 1000);
  }

  static void sleep(long millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {}
  }

}
