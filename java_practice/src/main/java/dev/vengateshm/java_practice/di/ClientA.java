package dev.vengateshm.java_practice.di;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton // Creates one object
public class ClientA {
    private ServiceA serviceA;

    @Inject
    public ClientA(ServiceA serviceA) {
        this.serviceA = serviceA;
        System.out.println("ClientA constructor called : " + this);
    }

    void doSomething() {
        serviceA.doSomething();
        System.out.println("ClientA doSomething called");
    }
}
