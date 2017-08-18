package com.xuxianda.thread;

/**
 * Created by Xianda Xu on 2017/08/18 17:04.
 */

class MyRun implements Runnable{

    private String name;

    public MyRun(String name){
        this.name = name;
    }

    public void run() {
        for(int i = 0 ;i<10;i++){
            try {
                Thread.sleep(1000);
                System.out.println(name+":"+i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

public class Demo03 {
    public static void main(String[] args) {
        Thread t1 = new Thread(new MyRun("A"));
        Thread t2 = new Thread(new MyRun("B"));
        Thread t3 = new Thread(new MyRun("C"));
        t1.setPriority(Thread.MIN_PRIORITY);
        t2.setPriority(Thread.NORM_PRIORITY);
        t3.setPriority(Thread.MAX_PRIORITY);
        t1.start();
        t2.start();
        t3.start();
    }
}
