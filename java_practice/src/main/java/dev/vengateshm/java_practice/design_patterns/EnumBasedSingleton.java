package dev.vengateshm.java_practice.design_patterns;

public enum EnumBasedSingleton {
    INSTANCE;

    public void perform() {
        System.out.println("doing something " + this.hashCode());
    }
}
