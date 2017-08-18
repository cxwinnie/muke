package com.xuxianda.thread;

import org.junit.Test;

/**
 * Created by Xianda Xu on 2017/08/17 11:27.
 */
public class Demo01 {

    public static void main(String[] args) {
        //test1();
        test2();
    }

    public static void test1(){
        MyThread myThread1 = new MyThread("A");
        MyThread myThread2 = new MyThread("B");
        /*myThread1.run();
        myThread2.run();*/
        //线程的启用使用start,而非run
        myThread1.start();
        myThread2.start();
    }

    public static void test2(){
        MyRunnable r1 = new MyRunnable("A");
        MyRunnable r2 = new MyRunnable("B");
        Thread thread1 = new Thread(r1);
        Thread thread2 = new Thread(r2);
        thread1.start();
        thread2.start();
    }

}
