package com.barry.java.Thread.c4;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

public class C3_CAS_ABA {

    public static void main(String[] args) {
        ABA();
        fixABA();
    }

    private static void ABA() {
        // 张三余额
        AtomicInteger balance = new AtomicInteger(1000);
        System.out.println("（A）张三余额：" + balance.get());

        // 财务发工资
        Integer newValue = balance.addAndGet(3000);
        balance.compareAndSet(balance.get(), newValue);
        System.out.println("（B）财务发3000工资：" + balance.get());

        // 家人取3000工资
        newValue = balance.addAndGet(-3000);
        balance.compareAndSet(balance.get(), newValue);
        System.out.println("（A）家人取3000工资：" + balance.get());

        // 张三查工资
        if (balance.get() > 3000) {
            System.out.println("张三美滋滋：" + balance.get());
        } else {
            System.out.println("张三找财务麻烦：" + balance.get());
        }
    }

    private static void fixABA() {
        // 张三余额
        AtomicStampedReference<Integer> balance = new AtomicStampedReference<>(1000, 1);
        System.out.println("（A）张三余额：" + balance.getReference());

        // 财务发工资
        balance.compareAndSet(balance.getReference(), 1000 + 3000, balance.getStamp(), balance.getStamp() + 1);
        System.out.println("（B）财务发3000工资：" + balance.getReference());

        // 家人取3000工资
        balance.compareAndSet(balance.getReference(), 4000 - 3000, balance.getStamp(), balance.getStamp() + 1);
        System.out.println("（A）李四取3000工资：" + balance.getReference());

        // 张三查工资
        if (balance.getReference() > 3000) {
            System.out.println("张三美滋滋：" + balance.getReference());
        } else {
            if (balance.getStamp() == 1) {
                System.out.println("张三找财务麻烦");
            } else {
                System.out.println("张三找李四麻烦");
            }
        }
    }
}
