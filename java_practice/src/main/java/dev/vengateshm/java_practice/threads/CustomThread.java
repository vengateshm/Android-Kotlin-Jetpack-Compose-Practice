package dev.vengateshm.java_practice.threads;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CustomThread extends Thread {

    //static Map<Integer, String> map = new HashMap<>(); // Throws concurrent modification exception
    static Map<Integer, String> map = new ConcurrentHashMap<>(); // Does not concurrent modification exception

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            map.put(103, "D");
        } catch (InterruptedException e) {
            System.out.println("Child Thread going to add element");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        map.put(100, "A");
        map.put(101, "B");
        map.put(102, "C");

        System.out.println(map);

        CustomThread thread = new CustomThread();
        thread.start();

        for (Object o : map.entrySet()) {
            Object s = o;
            System.out.println(s);
            Thread.sleep(1000);
        }

        System.out.println(map);
    }
}
