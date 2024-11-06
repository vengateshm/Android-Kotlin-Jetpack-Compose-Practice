package dev.vengateshm.java_practice.di;

import dagger.Component;

@Component(modules = ServiceCModule.class)
public interface ComponentC {
    ClientC getClientC();
}
