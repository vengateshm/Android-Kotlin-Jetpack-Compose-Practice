package dev.vengateshm.java_practice.di;

import javax.inject.Inject;

public class ServiceCImpl implements ServiceC {
    @Inject
    public ServiceCImpl() {
        System.out.println("ServiceC constructor called : " + this);
    }

    @Override
    public void doSomething() {
        System.out.println("ServiceC doSomething called");
    }
}
