package quizapp;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private Scanner scanner;

    public UserInterface() {
        scanner = new Scanner(System.in);
    }

    public int getUserResponse() {
        int userInput;
        do {
            while (!scanner.hasNextInt()) {
                System.out.print("Please enter a valid number.: ");
                scanner.next();
            }
            userInput = scanner.nextInt();
            if(userInput < 1 || userInput > 4){
                System.out.print("Please enter number between 1 and 4: ");
            }
        } while (userInput < 1 || userInput > 4); // Assuming there are 4 options
        return userInput;
    }

    public void startQuiz(Quiz quiz,List<Question> questions){
        System.out.println("Welcome to the Online Quiz!");
        for (int i = 0; i < questions.size(); i++) {
            System.out.println("\nQuestion " + (i + 1) + ":");
            quiz.displayQuestion(i);
            int userAnswer = this.getUserResponse();
            quiz.evaluateAnswer(i, userAnswer);
        }

        int finalScore = quiz.getScore();
        System.out.println("\nQuiz completed! Your score is: " + finalScore + "/" + questions.size());
    }
}
