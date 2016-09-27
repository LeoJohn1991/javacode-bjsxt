package com.mingjalee.thread.create;

/**
 * @author jerry
 * @create 16/9/12 10:24
 */
public class RabbitApp {
    public static void main(String[] args) {
        Rabbit rab = new Rabbit();
        Tortoise tor = new Tortoise();

        rab.start();
        tor.start();

        for (int i = 0; i < 100; i++) {
            System.out.println("mian------>" + i);
        }
    }
}
