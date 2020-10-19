package com.barry.designpartern.abstractfactory;

public class FactoryProducer {
    public static AbstractFactory getFactory(String choice) {
        if (choice == null) {
            return null;
        }
        if (choice.equals("SHAPE")) {
            return new ShapeFactory();
        }
        if (choice.equals("COLOR")) {
            return new ColorFactory();
        }
        return null;
    }
}
