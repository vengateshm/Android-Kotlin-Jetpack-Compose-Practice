package dev.vengateshm.java_practice.null_handling;

public class UserDatabase {
    public User findUser(Long id) {
//        return null;
        return new User("Vengatesh", 33, "vengatesh@gmail.com", new Address("Chennai"));
    }
}
