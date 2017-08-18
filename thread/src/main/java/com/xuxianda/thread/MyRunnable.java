package com.xuxianda.thread;

/**
 * Created by Xianda Xu on 2017/08/17 13:38.
 */
public class MyRunnable implements Runnable {

    private String name;

    public MyRunnable(String name){
        this.name = name;
    }

    public void run() {
        for(int i = 0 ; i <100000000;i++){
            if(i%10000 == 0){
                System.out.println(name+":"+i/10000);
            }
        }
    }
}
