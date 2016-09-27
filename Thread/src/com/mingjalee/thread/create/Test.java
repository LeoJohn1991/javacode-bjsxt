package com.mingjalee.thread.create;

/**
 * @author jerry
 * @create 16/9/14 10:19
 */
public class Test {
    public int a;
    public int b;

    public Test(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public void swap(int num1, int num2) {
        int tmp = num1;
        num1 = num2;
        num2 = tmp;
    }
    public void swap(String s1, String s2) {
        String tmp = s1;
        s1 = s2;
        s2 = tmp;
    }
    public void swap(Object obj1, Object obj2) {
        Object tmp = obj1;
        obj1 = obj2;
        obj2 = tmp;
    }

    public void show() {
        System.out.println("[a, b] = [" + this.a + ", " + this.b + "]" );
    }

    public static void main(String[] args) {
        System.out.println("max int:" + Integer.MAX_VALUE + "\nmin int:" + Integer.MIN_VALUE);
    }

}


class Student {
    String name;
    int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Stu: " + this.name + "\tage: " + this.age;
    }
}
