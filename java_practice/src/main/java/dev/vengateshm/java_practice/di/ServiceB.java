package dev.vengateshm.java_practice.di;

import javax.inject.Inject;

public class ServiceB {
    @Inject
    public ServiceB() {
        System.out.println("ServiceB constructor called : " + this);
    }

    public void doSomething() {
        System.out.println("ServiceB doSomething called");
    }
}
