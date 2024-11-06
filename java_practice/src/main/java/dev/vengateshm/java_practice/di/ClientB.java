package dev.vengateshm.java_practice.di;

import javax.inject.Inject;

public class ClientB {
    private ServiceB serviceB;

    @Inject
    public ClientB() {
        System.out.println("ClientB constructor called : " + this);
    }

    @Inject
    public void initialize(ServiceB serviceB) {
        this.serviceB = serviceB;
    }

    void doSomething() {
        serviceB.doSomething();
        System.out.println("ClientB doSomething called");
    }
}
