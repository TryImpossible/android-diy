package com.barry.java.Thread.c3;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

public class C5_Atomic {
    public static void main(String[] args) {
//        ai();
//        aia();
//        ar();
//        au();
        ia();
    }

    /// AtomicInteger
    private static void ai() {
        AtomicInteger ai = new AtomicInteger(1);
        // 获取值
        System.out.println("ai.get() = " + ai.get());

        // 增加指定的值并获取
        System.out.println("ai.addAndGet(5) = " + ai.addAndGet(5));
        System.out.println("ai.get() = " + ai.get());

        // 比较并设置，1.预期值 2.新值  会将预期值跟当前比较，如果相同就设置新值，返回值：是否成功
        System.out.println("ai.compareAndSet(ai.get(), 10) = " + ai.compareAndSet(ai.get(), 10));
        System.out.println("ai.get() = " + ai.get());

        // 获取并且递增，先获取没递增的值，再去递增
        System.out.println("ai.getAndIncrement() = " + ai.getAndIncrement()); // ai.getAndDecrement()
        System.out.println("ai.get() = " + ai.get());
        // 递增并且获取，先递增，再获取
        System.out.println("ai.incrementAndGet() = " + ai.incrementAndGet());
        System.out.println("ai.get() = " + ai.get());

        // 懒设置 不会保证可见性
        ai.lazySet(8);
        // volatile保证了可见性
        // ai.set();
        System.out.println("ai.lazySet(8)" + ai.get());
        System.out.println("ai.get() = " + ai.get());

        System.out.println("ai.getAndSet(5) = " + ai.getAndSet(5));
        System.out.println("ai.get() = " + ai.get());
    }

    /// AtomicIntegerArray
    private static void aia() {
        int[] value = new int[]{1, 2};
        AtomicIntegerArray aia = new AtomicIntegerArray(value);

        System.out.println("aia.getAndAdd(0, 3)");
        aia.getAndAdd(0, 3);
        System.out.println("aia.get(0) = " + aia.get(0));
        System.out.println("value[0] = " + value[0]);

        aia.compareAndSet(1, 2, 5);
        System.out.println("aia.compareAndSet(1,2,5)");
        System.out.println("aia.get(1) = " + aia.get(1));
    }

    /// AtomicReference
    private static void ar() {
        User user = new User("张三", 20);
        AtomicReference<User> atomicUserRef = new AtomicReference<>(user);
//        atomicUserRef.set(user);
        System.out.println("atomicUserRef.get() = " + atomicUserRef.get().toString());

        User updateUser = new User("李四", 22);
        atomicUserRef.compareAndSet(user, updateUser);
        System.out.println("atomicUserRef.compareAndSet(user, updateUser)");
        System.out.println("atomicUserRef.get() = " + atomicUserRef.get().toString());
    }

    // AtomicIntegerFieldUpdater
    private static void au() {
        // 创建原子更新器，并设置需要更新的对象类和对象属性
        AtomicIntegerFieldUpdater<User> a = AtomicIntegerFieldUpdater.newUpdater(User.class, "age");
        // 设置张三的年龄是20岁
        User zhangsan = new User("张三", 20);
        // zhangsan长了一岁，但是仍然会输出旧的年龄
        System.out.println(a.getAndIncrement(zhangsan));
        // 输入zhangsan现在的年龄
        System.out.println(a.get(zhangsan));
    }

    /// LongAdder、LongAccumulator
    private static void ia() {
        // 无参构造函数，从0开始
        LongAdder longAdder = new LongAdder();

        longAdder.increment();
        longAdder.increment();
        longAdder.increment();

        System.out.println(longAdder.longValue()); //3

        LongAccumulator longAccumulator = new LongAccumulator((x, y) -> x +y, 1);
        longAccumulator.accumulate(1); //1
        longAccumulator.accumulate(3); //4
        longAccumulator.accumulate(3); //7

        System.out.println(longAccumulator.get());
        System.out.println(longAccumulator.get());

    }

    private static class User {
        public String name;
        public volatile int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
