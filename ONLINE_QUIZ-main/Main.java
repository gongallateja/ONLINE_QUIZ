package quizapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<User> users = new ArrayList<>();
    private static User loggedInUser = null;

    public static void main(String[] args) {
        List<Question> questions = new ArrayList<>();
        Quiz quiz = new Quiz(questions); // Initialize with an empty quiz

        // Default admin pin for quiz creation
        String quizPin = "12345";
        Scanner scanner = new Scanner(System.in);

        boolean exit = false;
        while (!exit) {
            if (loggedInUser == null) {
                // Display login or registration options if no user is logged in
                System.out.println("Welcome to Online Quiz Application!");
                System.out.println("1. Login");
                System.out.println("2. Register");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");

                int choice = getValidNumber(scanner);
                switch (choice) {
                    case 1:
                        login(scanner);
                        break;
                    case 2:
                        register(scanner);
                        break;
                    case 3:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice!");
                }
            } else {
                // Display quiz options if a user is logged in
                System.out.println("Welcome " + loggedInUser.getUsername() + "!");
                System.out.println("1. Create Quiz");
                System.out.println("2. Play Quiz");
                System.out.println("3. Logout");
                System.out.print("Enter your choice: ");

                int choice = getValidNumber(scanner);
                switch (choice) {
                    case 1:
                        quiz = createQuiz(scanner, quizPin);
                        break;
                    case 2:
                        playQuiz(scanner, quiz);
                        break;
                    case 3:
                        loggedInUser = null; // Logout
                        System.out.println("Logged out successfully.");
                        break;
                    default:
                        System.out.println("Invalid choice!");
                }
            }
        }

        scanner.close();
    }

    private static void login(Scanner scanner) {
        System.out.print("Enter Username: ");
        String username = scanner.next();
        System.out.print("Enter Password: ");
        String password = scanner.next();

        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                loggedInUser = user;
                System.out.println("Login successful!");
                return;
            }
        }
        System.out.println("Invalid credentials!");
    }

    private static void register(Scanner scanner) {
        System.out.print("Enter a new username: ");
        String username = scanner.next();
        System.out.print("Enter a new password: ");
        String password = scanner.next();

        for (User user : users) {
            if (user.getUsername().equals(username)) {
                System.out.println("Username already exists!");
                return;
            }
        }

        users.add(new User(username, password));
        System.out.println("User registered successfully!");
    }

    private static Quiz createQuiz(Scanner scanner, String quizPin) {
        System.out.print("Enter your Quiz creator pin: ");
        scanner.nextLine(); // Consume the newline
        String pin = scanner.nextLine();

        if (pin.equals(quizPin)) {
            System.out.println("Pin verified. Creating a new quiz...");

            // Create and return a new Quiz with questions
            return new Quiz(); // This calls the constructor that initializes questions
        } else {
            System.out.println("Invalid Pin");
            return new Quiz(new ArrayList<>()); // Return an empty quiz
        }
    }

    private static void playQuiz(Scanner scanner, Quiz quiz) {
        List<Question> questions = quiz.getQuestions();
        if (questions.isEmpty()) {
            System.out.println("No quiz questions available. Please create a quiz first.");
            return;
        }

        UserInterface ui = new UserInterface();
        ui.startQuiz(quiz, questions);
        System.out.println("Quiz completed! Your score is: " + quiz.getScore() + "/" + questions.size());
    }

    private static int getValidNumber(Scanner scanner) {
        int number;
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();
        }
        number = scanner.nextInt();
        return number;
    }
}
