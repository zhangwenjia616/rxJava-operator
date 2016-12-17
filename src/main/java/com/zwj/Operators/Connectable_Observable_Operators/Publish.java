package com.zwj.Operators.Connectable_Observable_Operators;

/**
 * Connectable Observable:
 * 就是一种特殊的Observable对象，并不是Subscrib的时候就发射数据，而是只有对其应用connect操作符的时候才开始发射数据，所以可以用来更灵活的控制数据发射的时机。
 * 而Publish操作符就是用来将一个普通的Observable对象转化为一个Connectable Observable。需要注意的是如果发射数据已经开始了再进行订阅只能接收以后发射的数据。
 * Publish — convert an ordinary Observable into a connectable Observable
 * 
 * @ClassName Publish
 * @Description
 * @author zhangwj@yiche.com
 * @date Dec 16, 2016 9:43:46 AM
 */
public class Publish {

  public static void main(String[] args) {
    // see Connect
  }

}
