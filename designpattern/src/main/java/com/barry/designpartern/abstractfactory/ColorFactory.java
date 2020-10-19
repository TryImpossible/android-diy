package com.barry.designpartern.abstractfactory;

public class ColorFactory extends AbstractFactory {
    @Override
    public Color getColor(String color) {
        if (color == null) {
            return null;
        }
        if (color.equals("RED")) {
            return new Red();
        }
        if (color.equals("GREEN")) {
            return new Green();
        }
        if (color.equals("BLUE")) {
            return new Blue();
        }
        return null;
    }

    @Override
    public Shape getShape(String shape) {
        return null;
    }
}
