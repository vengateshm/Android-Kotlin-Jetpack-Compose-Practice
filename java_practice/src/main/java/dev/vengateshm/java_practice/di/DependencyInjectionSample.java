package dev.vengateshm.java_practice.di;

public class DependencyInjectionSample {
    public static void main(String[] args) {
        ComponentA componentA = DaggerComponentA.create();
        componentA.getServiceA().doSomething();
        componentA.getClientA().doSomething();
        componentA.getClientA().doSomething();
        System.out.println("[END]");
        ComponentB componentB = DaggerComponentB.create();
        componentB.getClientB().doSomething();
        componentB.getClientB().doSomething();
        System.out.println("[END]");
        ComponentC componentC = DaggerComponentC.create();
        componentC.getClientC().doSomething();
        componentC.getClientC().doSomething();
    }
}
