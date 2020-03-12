package com.barry.java.DefaultMethod;

/**
 * Java 8 新增了接口的默认方法。
 * <p>
 * 简单说，默认方法就是接口可以有实现方法，而且不需要实现类去实现其方法。
 * <p>
 * 我们只需在方法名前面加个 default 关键字即可实现默认方法。
 */
public class Tester1 {
    interface Vehicle {
        default void print() {
            System.out.println("我是一辆车");
        }

        static void blowHorn() {
            System.out.println("按喇叭");
        }
    }

    interface FourWheeler {
        default void print() {
            System.out.println("我是一辆四轮车");
        }
    }

    static class Car implements Vehicle, FourWheeler {

        @Override
        public void print() {
            Vehicle.super.print();
            FourWheeler.super.print();
            Vehicle.blowHorn();
            System.out.println("我是一辆汽车");
        }
    }

    public static void main(String args[]) {
        Vehicle vehicle = new Car();
        vehicle.print();
    }
}
