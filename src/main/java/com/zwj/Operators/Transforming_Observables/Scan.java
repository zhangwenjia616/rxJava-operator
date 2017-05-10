package com.zwj.Operators.Transforming_Observables;

import rx.Observable;

/**
 * apply a function to each item emitted by an Observable, sequentially, and emit each successive
 * value
 * scan操作符通过遍历源Observable产生的结果，依次对每一个结果项按照指定规则进行运算，计算后的结果作为下一个迭代项参数，每一次迭代项都会把计算结果输出给订阅者。
 *
 * @author zhangwj@yiche.com
 * @ClassName Scan
 * @Description
 * @date Dec 12, 2016 9:10:47 PM
 */
public class Scan {

    public static void main(String[] args) {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9).scan((x, y) -> x + y).subscribe(number -> {
            System.out.println(number);
        });
    }


}
