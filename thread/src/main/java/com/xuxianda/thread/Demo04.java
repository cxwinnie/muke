package com.xuxianda.thread;

/**
 * Created by Xianda Xu on 2017/08/18 17:33.
 */
class MyThreadTest implements Runnable {

    private Integer ticket = 10;

    public void run() {
        for (int i = 0; i < 10; i++) {
            test();
        }
    }
    public synchronized void test(){
        if (ticket > 0) {
            System.out.println("票：" + ticket);
            ticket--;
        }
    }
}

public class Demo04 {
    public static void main(String[] args) {
        MyThreadTest myThreadTest = new MyThreadTest();
        Thread t1 = new Thread(myThreadTest);
        Thread t2 = new Thread(myThreadTest);
        Thread t3 = new Thread(myThreadTest);
        t1.start();
        t2.start();
        t3.start();
    }
}
