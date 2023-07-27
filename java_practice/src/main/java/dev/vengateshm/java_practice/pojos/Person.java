package dev.vengateshm.java_practice.pojos;

public class Person {
    private String firstname;
    private String lastName;
    private Address address;

    public Person(String firstname, String lastName, Address address) {
        this.firstname = firstname;
        this.lastName = lastName;
        this.address = address;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
