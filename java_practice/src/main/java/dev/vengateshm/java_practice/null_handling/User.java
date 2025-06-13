package dev.vengateshm.java_practice.null_handling;

public class User {
    private String name;
    private int age;
    public String email;
    private Address address;

    public User(String name, int age, String email, Address address) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void sendNotification(String message) {
        System.out.println("Sending notification to " + this.email + ": " + message);
    }
}
