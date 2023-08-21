package dev.vengateshm.java_practice.core_java;

public class PassBySample {
    public static void main(String[] args) {
        int a = 5;
        System.out.println(a);
        modify(a);
        System.out.println(a);

        Fishy fishy = new Fishy("Fishy");
        System.out.println(fishy.getData());
        modify(fishy);
        System.out.println(fishy.getData());
    }

    public static void modify(int a) {
        a = 7;
    }

    public static void modify(Fishy fishy) {
        fishy.setData("Not Fishy");
        fishy = new Fishy("New fishy");
    }

    static class Fishy {
        private String data;

        public Fishy(String data) {
            this.data = data;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
    }
}