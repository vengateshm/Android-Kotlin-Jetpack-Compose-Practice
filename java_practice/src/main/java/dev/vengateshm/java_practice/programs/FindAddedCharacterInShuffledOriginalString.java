package dev.vengateshm.java_practice.programs;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FindAddedCharacterInShuffledOriginalString {
    public static void main(String[] args) {
        String s1 = "abcde";
        String s2 = "cedbaf";
        String s3 = "acdef";
        String s4 = "cedafb";
        System.out.println(findDifferentCharacter(s1, s2));
        System.out.println(findDifferentCharacter(s3, s4));
        System.out.println(findDifferentCharacter1(s1, s2));
        System.out.println(findDifferentCharacter1(s3, s4));
        System.out.println(findDifferentCharacter2(s1, s2));
        System.out.println(findDifferentCharacter2(s3, s4));

        //XOR
        System.out.println(10 ^ 7);
        System.out.println(10 ^ 10 ^ 7);
    }

    public static char findDifferentCharacter(String s1, String s2) {
        char[] s1CharArray = s1.toCharArray();
        char[] s2CharArray = s2.toCharArray();
        Arrays.sort(s1CharArray);
        Arrays.sort(s2CharArray);

        for (int i = 0; i < s1CharArray.length; i++) {
            if (s1CharArray[i] != s2CharArray[i]) {
                return s2CharArray[i];
            }
        }
        return s2CharArray[s2CharArray.length - 1];
    }

    public static char findDifferentCharacter1(String s1, String s2) {
        Map<Character, Integer> map = new HashMap<>();
        char[] s1CharArray = s1.toCharArray();
        char[] s2CharArray = s2.toCharArray();

        for (Character c : s1CharArray) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        for (Character c : s2CharArray) {
            map.put(c, map.getOrDefault(c, 0) - 1);
        }

        return map.entrySet().stream()
                .filter(e -> e.getValue() == -1)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse('-');
    }

    public static char findDifferentCharacter2(String s1, String s2) {
        char[] s1CharArray = s1.toCharArray();
        char[] s2CharArray = s2.toCharArray();

        char c = 0;
        for (Character c1 : s1CharArray) {
            c ^= c1;
            System.out.print(c);
        }
        System.out.println();
        for (Character c2 : s2CharArray) {
            c ^= c2;
            System.out.print(c);
        }

        return c;
    }
}
