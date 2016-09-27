package com.mingjalee.thread.create;

/**
 * 模拟龟兔赛跑
 * 1.创建线程(继承Thread),重写run方法
 * @author jerry
 * @create 16/9/12 10:14
 */
public class Rabbit extends Thread {
    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 100; i++) {
            System.out.println("兔子再了" + i + "步");
        }
    }
}

class Tortoise extends Thread {
    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 100; i++) {
            System.out.println("乌龟跑了" + i + "步");
        }
    }
}
