package com.barry.java.Lambda;

/**
 * Lambda 表达式，也可称为闭包，它是推动 Java 8 发布的最重要新特性。
 * <p>
 * Lambda 允许把函数作为一个方法的参数（函数作为参数传递进方法中）。
 * <p>
 * 使用 Lambda 表达式可以使代码变的更加简洁紧凑。
 */
public class Tester1 {
    interface MathOperation {
        int operation(int a, int b);
    }

    interface GreetingService {
        void sayMessage(String message);
    }

    private int operate(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }

    public static void main(String args[]) {
        Tester1 tester1 = new Tester1();

        // 类型声明
        MathOperation addition = (int a, int b) -> a + b;

        // 不用类型声明
        MathOperation subtraction = (a, b) -> a - b;

        // 大括号中的返回语句
        MathOperation multiplication = (int a, int b) -> {
            return a * b;
        };

        // 没有大括号及返回语句
        MathOperation division = (int a, int b) -> a / b;

        System.out.println("10 + 5 = " + tester1.operate(10, 5, addition));
        System.out.println("10 - 5 = " + tester1.operate(10, 5, subtraction));
        System.out.println("10 * 5 = " + tester1.operate(10, 5, multiplication));
        System.out.println("10 / 5 = " + tester1.operate(10, 5, division));

        // 不用括号
        GreetingService greetingService1 = message -> System.out.println("Hello " + message);

        // 用括号
        GreetingService greetingService2 = (message) -> System.out.println("Hello " + message);

        greetingService1.sayMessage("Barry");
        greetingService2.sayMessage("Google");
    }
}


