package com.barry.java.Lambda;

public class Tester2 {
     static String salutation = "Hello";

    interface GreetingService {
        void sayMessage(String message);
    }

    public interface Converter<T1, T2> {
        void convert(int i);
    }

    public static void main(String args[]) {
        GreetingService greetingService = message -> System.out.println(salutation + message);
        greetingService.sayMessage("Barry");

        final int num = 1;
        Converter<Integer, String> s =(param) -> System.out.println(String.valueOf(param + num));
        s.convert(2);
    }
}
