package dev.vengateshm.java_practice.di;

import javax.inject.Inject;

public class ClientC {
    private final ServiceC serviceC;

    @Inject
    public ClientC(ServiceC serviceC) {
        System.out.println("ClientC constructor called : " + this);
        this.serviceC = serviceC;
    }

    public void doSomething() {
        serviceC.doSomething();
        System.out.println("ClientC doSomething called");
    }
}
