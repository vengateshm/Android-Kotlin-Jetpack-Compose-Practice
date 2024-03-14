package dev.vengateshm.java_practice.collections;


import java.util.HashSet;

class Book {
    String title;

    public Book(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int hashCode() {
        return this.title.hashCode();
    }
}
public class HashSetIssue {
    public static void main(String[] args) {
        Book book1 = new Book("Java Basics");
        HashSet<Book> books = new HashSet<>();
        books.add(book1);
        System.out.println(book1.hashCode());
        book1.setTitle("Advanced Java");
        System.out.println(book1.hashCode());
        System.out.println(books.contains(book1));
    }
}
