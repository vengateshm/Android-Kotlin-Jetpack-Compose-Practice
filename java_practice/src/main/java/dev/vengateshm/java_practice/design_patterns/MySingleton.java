package dev.vengateshm.java_practice.design_patterns;

import java.io.Serializable;

public class MySingleton implements Serializable, Cloneable {
    private static MySingleton instance;

    private MySingleton() {
        // Protect against reflection attacks
        if (instance != null) {
            throw new RuntimeException("Singleton already created.");
        }
    }

    public static MySingleton getInstance() {
        if (instance == null) {
            synchronized (MySingleton.class) {
                if (instance == null) {
                    instance = new MySingleton();
                }
            }
        }
        return instance;
    }

    public void showMessage() {
        System.out.println("Singleton instance: " + this.hashCode());
    }

    // Protect from serialization
    private Object readResolve() {
        return instance;
    }

    // Protect against cloning
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Singleton cannot be cloned.");
    }
}
