package com.barry.designpartern.observer;

public abstract class Observer {
    protected Subject subject;
    public abstract void update();
}
