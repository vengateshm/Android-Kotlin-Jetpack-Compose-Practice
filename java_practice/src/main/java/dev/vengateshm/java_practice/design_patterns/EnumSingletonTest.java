package dev.vengateshm.java_practice.design_patterns;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;

public class EnumSingletonTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        EnumBasedSingleton singleton1 = EnumBasedSingleton.INSTANCE;
        singleton1.perform();
        EnumBasedSingleton singleton2 = EnumBasedSingleton.INSTANCE;
        singleton2.perform();

        // Reflection test
        EnumBasedSingleton singleton3 = EnumBasedSingleton.INSTANCE;

        try {
            Constructor<?>[] declaredConstructors = EnumBasedSingleton.class.getDeclaredConstructors();
            for (Constructor<?> constructor : declaredConstructors) {
                constructor.setAccessible(true);
                EnumBasedSingleton singleton4 = (EnumBasedSingleton) constructor.newInstance();
                singleton4.perform();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        singleton3.perform();

        // Serialization
        EnumBasedSingleton singleton5 = EnumBasedSingleton.INSTANCE;
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("singleton.ser"))) {
            oos.writeObject(singleton5);
        }
        EnumBasedSingleton singleton6;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("singleton.ser"))) {
            singleton6 = (EnumBasedSingleton) ois.readObject();
        }
        singleton6.perform();
        if (singleton5 == singleton6) {
            System.out.println("singleton5 and singleton6 are the same instance");
        }

        // Clone
        //singleton6.clone();

        // Thread safety
        Runnable task = () -> {
            EnumBasedSingleton singleton = EnumBasedSingleton.INSTANCE;
            singleton.perform();
        };
        Thread thread1 = new Thread(task, "thread1");
        Thread thread2 = new Thread(task, "thread2");
        Thread thread3 = new Thread(task, "thread3");
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
