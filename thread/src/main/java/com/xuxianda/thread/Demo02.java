package com.xuxianda.thread;

/**
 * Created by Xianda Xu on 2017/08/18 15:29.
 */
class RunnableDemo implements Runnable{

    private String name;

    public RunnableDemo(String name){
        this.name = name;
    }

    public void run() {
        for(int i = 0 ;i<100;i++){
            if(i == 10){
                System.out.println(name+"礼让"+i);
                Thread.yield();
            }else{
                System.out.println(name+i);
            }
        }
    }
}
public class Demo02 {

    public static void main(String[] args) throws Exception{
        RunnableDemo r1 = new RunnableDemo("A");
        RunnableDemo r2 = new RunnableDemo("B");
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();
    }

    public static void test1() throws Exception{
        RunnableDemo r1 = new RunnableDemo("A");
        Thread t1 = new Thread(r1);
        t1.start();
        for(int i = 0 ; i<100;i++){
            if(i>50){
                t1.join();
            }
            System.out.println("main:"+Thread.currentThread().getName());
        }
    }
}
