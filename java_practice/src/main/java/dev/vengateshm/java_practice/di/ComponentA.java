package dev.vengateshm.java_practice.di;

import javax.inject.Singleton;

import dagger.Component;

@Singleton // Creates one object
@Component
public interface ComponentA {
    ServiceA getServiceA();

    ClientA getClientA();
}
