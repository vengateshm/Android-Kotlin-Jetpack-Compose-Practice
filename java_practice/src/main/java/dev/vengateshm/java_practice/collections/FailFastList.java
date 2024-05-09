package dev.vengateshm.java_practice.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class FailFastList {
    public static void main(String[] args) {
//        List<String> list = new ArrayList<>();
        List<String> list = new CopyOnWriteArrayList<>();
        list.add("a");
        list.add("b");

        Iterator iterator = list.iterator();

        while (iterator.hasNext()){
            String next = (String) iterator.next();
            System.out.println(next);
            list.add("c");
        }
    }
}
