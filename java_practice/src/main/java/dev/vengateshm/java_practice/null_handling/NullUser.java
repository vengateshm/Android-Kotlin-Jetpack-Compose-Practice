package dev.vengateshm.java_practice.null_handling;

public class NullUser extends User {
    public static final NullUser INSTANCE = new NullUser("Guest", 0, "", null);

    private NullUser(String name, int age, String email, Address address) {
        super(name, age, email, address);
    }

    @Override
    public void sendNotification(String message) {
        
    }
}
