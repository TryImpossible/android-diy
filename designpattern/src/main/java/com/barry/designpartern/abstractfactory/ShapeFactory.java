package com.barry.designpartern.abstractfactory;

public class ShapeFactory extends AbstractFactory {
    @Override
    public Shape getShape(String shape) {
        if (shape == null) {
            return null;
        }
        if (shape.equals("CIRCLE")) {
            return new Circle();
        }
        if (shape.equals("RECTANGLE")) {
            return new Rectangle();
        }
        if (shape.equals("SQUARE")) {
            return new Square();
        }
        return null;
    }

    @Override
    public Color getColor(String color) {
        return null;
    }
}
