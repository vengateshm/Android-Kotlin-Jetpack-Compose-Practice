package dev.vengateshm.java_practice.programs;

import java.util.Arrays;
import java.util.Comparator;

public class ComparatorTest {
    public static void main(String[] args) {
        InnerClass in = new InnerClass();
        String[] ar = {"c", "d", "b", "a", "e"};
        System.out.println(Arrays.toString(ar));
        //Arrays.parallelSort(ar, in);
        //Arrays.sort(ar, in);
//        Arrays.parallelSort(ar, (s1, s2) -> s1.compareTo(s2));
//        Arrays.parallelSort(ar, Comparator.naturalOrder());
        Arrays.parallelSort(ar, Comparator.reverseOrder());
        //Arrays.sort(ar);
        System.out.println(Arrays.toString(ar));

        for (String s : ar) {
            System.out.println(s + "");
            System.out.println(Arrays.binarySearch(ar, "b"));
        }
    }

    static class InnerClass implements Comparator<String> {

        @Override
        public int compare(String s1, String s2) {
            return s2.compareTo(s1);
        }
    }
}
