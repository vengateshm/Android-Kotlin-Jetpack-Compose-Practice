package dev.vengateshm.java_practice.asynchronous;

public class EmployeeData {
    private String id;
    private String name;
    private int rating;
    private String gender;
    private String email;

    public EmployeeData() {
    }

    public EmployeeData(String id, String name, int rating, String gender, String email) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.gender = gender;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
