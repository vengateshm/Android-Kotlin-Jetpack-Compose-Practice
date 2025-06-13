package dev.vengateshm.java_practice.null_handling;

import java.util.List;
import java.util.Optional;

public class NullHandling {
    public static void main(String[] args) {
        User user = new User("Vengatesh", 33, "vengatesh@gmail.com", new Address("Chennai"));
        System.out.println(getCityOld(user));
        System.out.println(getCityNew(user));
        System.out.println(getCityOld(null));
        System.out.println(getCityNew(null));

        Optional<User> user1 = findUserById(1L);
        user1.ifPresent(u -> System.out.println(u.getName()));

        String name = user1.map(User::getName).orElse("Anonymous");
        System.out.println(name);

        user1.filter(u -> u.getAge() > 30).map(User::getEmail).ifPresent(NullHandling::sendWelcomeEmail);

        UserService userService = new UserService();
        userService.processUser(1L);

        OrderService orderService = new OrderService();
        Customer customer = new Customer("John Doe");
        OrderItem item1 = new OrderItem(2, new Product("Laptop", 100.99));
        OrderItem item2 = new OrderItem(2, new Product("Mouse", 19.99));
        Order order = new Order(customer, List.of(item1, item2));
        System.out.println(orderService.processOrder(order));
        System.out.println(orderService.processOrderWithOptional(order));
    }

    public static void sendWelcomeEmail(String email) {
        System.out.println("Welcome");
    }

    public static String getCityOld(User user) {
        if (user != null) {
            if (user.getAddress() != null) {
                if (user.getAddress().getCity() != null) {
                    return user.getAddress().getCity();
                }
            }
        }
        return "Unknown";
    }

    public static String getCityNew(User user) {
        return Optional.ofNullable(user).map(User::getAddress).map(Address::getCity).orElse("Unknown");
    }

    public static Optional<User> findUserById(Long id) {
//        return Optional.empty();
        return Optional.of(new User("Vengatesh", 33, "vengatesh@gmail.com", new Address("Chennai")));
    }
}
