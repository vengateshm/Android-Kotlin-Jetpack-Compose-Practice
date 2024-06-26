package dev.vengateshm.java_practice.programs;

public class AnagramsUsingStream {

    public static boolean areAnagrams(String s1, String s2) {
        if (s1.length() != s2.length()) return false;

        var ss1 = s1.toLowerCase().chars().sorted().collect(StringBuilder::new,
                StringBuilder::appendCodePoint, StringBuilder::append).toString();
        var ss2 = s2.toLowerCase().chars().sorted().collect(StringBuilder::new,
                StringBuilder::appendCodePoint, StringBuilder::append).toString();

        return ss1.equals(ss2);
    }

    public static void main(String[] args) {
        System.out.println(areAnagrams("Listen", "Silent"));
        System.out.println(areAnagrams("Listena", "Silentg"));
    }
}
