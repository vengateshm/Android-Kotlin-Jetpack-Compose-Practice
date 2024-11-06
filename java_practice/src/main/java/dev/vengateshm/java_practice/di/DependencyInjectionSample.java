package dev.vengateshm.java_practice.di;

public class DependencyInjectionSample {
    public static void main(String[] args) {
        ComponentA componentA = DaggerComponentA.create();
        componentA.getServiceA().doSomething();
        componentA.getClientA().doSomething();
        componentA.getClientA().doSomething();
    }
}
