package dev.vengateshm.java_practice.null_handling;

public class UserService {
    UserDatabase database = new UserDatabase();

    public User findUser(Long id) {
        User user = database.findUser(id);
        return user != null ? user : NullUser.INSTANCE;
    }

    public void processUser(Long id) {
        User user = findUser(id);
        System.out.println("Processing: " + user.getName());
        user.sendNotification("Welcome!");
    }
}
