package quizapp;

import java.util.List;
import java.util.Scanner;

public class AuthenticationManager {
    private List<User> users;

    public AuthenticationManager() {
        this.users = FileManager.loadUsers(); // Load users from file
    }

    public boolean login(Scanner scanner) {
        System.out.println("Enter Username: ");
        String username = scanner.nextLine();
        System.out.println("Enter Password: ");
        String password = scanner.nextLine();

        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                System.out.println("Login Successful!");
                return true;
            }
        }
        System.out.println("Invalid credentials!");
        return false;
    }

    public void register(Scanner scanner) {
        System.out.println("Enter a new username: ");
        String username = scanner.nextLine();
        System.out.println("Enter a new password: ");
        String password = scanner.nextLine();

        users.add(new User(username, password));
        FileManager.saveUsers(users); // Save updated user list to file
        System.out.println("User registered successfully!");
    }
}

