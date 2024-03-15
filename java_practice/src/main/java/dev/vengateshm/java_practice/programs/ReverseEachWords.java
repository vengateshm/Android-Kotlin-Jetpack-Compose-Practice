package dev.vengateshm.java_practice.programs;

public class ReverseEachWords {
    public static void main(String[] args) {
        String input = "Java Java";

        String[] split = input.split(" ");
        StringBuilder revString = new StringBuilder();

        for (int i = 0; i < split.length; i++) {
            String word = split[i];
            StringBuilder revWord = new StringBuilder();

            for (int j = word.length() - 1; j >= 0; j--) {
                revWord.append(word.charAt(j));
            }
            revString.append(revWord);
            if (i < split.length - 1) {
                revString.append(" ");
            }
        }

        System.out.println(revString);
        System.out.println(revString.length());
    }
}
