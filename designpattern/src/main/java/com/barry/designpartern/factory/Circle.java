package com.barry.designpartern.factory;

public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Inisde Circle::draw() method");
    }
}
