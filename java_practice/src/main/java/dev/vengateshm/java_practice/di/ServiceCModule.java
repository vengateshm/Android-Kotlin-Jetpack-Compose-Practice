package dev.vengateshm.java_practice.di;

import dagger.Binds;
import dagger.Module;

@Module
public interface ServiceCModule {
    @Binds
    ServiceC bindServiceC(ServiceCImpl impl);
}
