package com.zwj.Operators.Transforming_Observables.TestFlatMap;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.functions.Func1;

public class TestFlatMap {

  public static void main(String[] args) {
    testFlatMap();
  }
  
  private static void testFlatMap() {
    // 打印所有学生的课程
    // 两个from 产生两个生产者
    Observable.from(builtData()) // 生产者1
    .flatMap(new Func1<Student, Observable<Course>>() {
      @Override
      public Observable<Course> call(Student student) { // student这个参数是生产者1  emit出来的 
        // student是生产者2的参数 
        // 生产者2的产生依赖于生产者1
        return Observable.from(student.courseList);     // 返回一个新的生产者2
      }
    })
    .subscribe(new Observer<Course>() { // 订阅的是生产者2 emit出来的item类型
      @Override
      public void onCompleted() {}

      @Override
      public void onError(Throwable e) {
      }

      @Override
      public void onNext(Course course) {
        System.out.println(course.courseId);
      }
    });
    // 每次的订阅都是一次完整的链路调用过程
    // 从第一个生产者开始emit 第一个item(TypeA) 被call方法接受 返回新的生产者 再emit(TypeB)每个元素
    // 回去到第一个生产者 第二个item(TypeA) 被call方法接受 返回新的生产者 再emit(TypeB) 每个元素
  }
  
  private static List<Student> builtData() {
    ArrayList<Student> studentList = new ArrayList<>();
    for (int i = 0; i < 400; i++) {
      Student s = new Student();
      s.stuentId = "s" + i;
      ArrayList<Course> clist = new ArrayList<>();
      s.courseList = clist;
      for (int j = 0; j < 300; j++) {
        Course c = new Course();
        c.courseId = s.stuentId + " 课" + j;
        clist.add(c);
      }
      studentList.add(s);
    }
    return studentList;
  }

}
