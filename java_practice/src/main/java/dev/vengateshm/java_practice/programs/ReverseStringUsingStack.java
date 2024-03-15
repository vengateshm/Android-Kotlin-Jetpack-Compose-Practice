package dev.vengateshm.java_practice.programs;

import java.util.Scanner;
import java.util.Stack;

public class ReverseStringUsingStack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a string");
        String input = scanner.nextLine();
        Stack<Character> characterStack = new Stack<>();

        for (int i = 0; i < input.length(); i++) {
            characterStack.push(input.charAt(i));
        }

        System.out.println("Reversed string");
        while (!characterStack.isEmpty()) {
            System.out.print(characterStack.pop());
        }
    }
}
