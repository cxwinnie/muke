package com.xuxianda.thread;

/**
 * Created by Xianda Xu on 2017/08/17 11:27.
 */
public class MyThread extends Thread {

    private String name;

    public MyThread(String name){
        this.name = name;
    }

    @Override
    public void run() {
        for(int i = 0 ;i<10000;i++){
            System.out.println(name+":"+i);
        }
    }
}
