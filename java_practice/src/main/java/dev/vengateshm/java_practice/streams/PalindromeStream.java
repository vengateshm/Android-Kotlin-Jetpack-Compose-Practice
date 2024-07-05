package dev.vengateshm.java_practice.streams;

import java.util.Scanner;
import java.util.stream.IntStream;

public class PalindromeStream {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int num = scanner.nextInt();
        scanner.close();
        if (isPalindrome(num)) {
            System.out.println(num + " is a palindrome.");
        } else {
            System.out.println(num + " is not a palindrome.");
        }
    }

    public static boolean isPalindrome(int num) {
        String original = String.valueOf(num);
        String reversed = IntStream.rangeClosed(1, original.length())
                .mapToObj(i -> original.charAt(original.length() - i))
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
        return original.equals(reversed);
    }
}
