package dev.vengateshm.java_practice.di;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton // Creates one object
public class ServiceA {
    @Inject
    public ServiceA() {
        System.out.println("ServiceA constructor called : " + this);
    }

    public void doSomething() {
        System.out.println("ServiceA doSomething called");
    }
}
