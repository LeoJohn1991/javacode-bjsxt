package com.mingjalee.others;

import java.io.Serializable;

/**
 * @author jerry
 * @create 16/9/12 00:55
 */
public class Employ implements Serializable {
    private transient String name;
    private double salary;

    public Employ() {
    }

    public Employ(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
