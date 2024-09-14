# ONLINE_QUIZ


The Online Quiz Application is a Java-based system designed to facilitate quiz-taking. It supports user authentication, quiz management, and tracks user progress and performance. The application uses SQLite for data persistence and provides a simple user interface

Features Implemented

User Authentication
Users can create an account and log in to the system.

Quiz Management
Administrators can create quizzes and add multiple-choice questions.
Each question includes a title, options, and the correct answer.

Quiz Taking
Users can select and take quizzes.
Questions are displayed one at a time with options to select answers.
Immediate feedback is provided after each question.

Scoring and Progress Tracking
Users receive a score at the end of each quiz.
Users can view their past quiz attempts and scores.

Leaderboard (Optional)
User Interface Design
Error Handling and Validation (Optional)


Getting Started
Prerequisites
Java Development Kit (JDK): Ensure JDK 11 or later is installed.
SQLite: SQLite database should be set up.
JavaFX: Required for the user interface (if applicable).

Setup SQLite Database

Create the SQLite database file and tables. Use the following SQL schema to set up your database:
-- Create users table
CREATE TABLE users (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    username TEXT NOT NULL UNIQUE,
    password TEXT NOT NULL
);

-- Create quizzes table
CREATE TABLE quizzes (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    title TEXT NOT NULL
);

-- Create questions table
CREATE TABLE questions (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    quiz_id INTEGER,
    question_text TEXT NOT NULL,
    option1 TEXT NOT NULL,
    option2 TEXT NOT NULL,
    option3 TEXT NOT NULL,
    option4 TEXT NOT NULL,
    correct_option INTEGER NOT NULL,
    FOREIGN KEY (quiz_id) REFERENCES quizzes(id)
);

-- Create quiz_results table
CREATE TABLE quiz_results (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    user_id INTEGER,
    quiz_id INTEGER,
    score INTEGER NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (quiz_id) REFERENCES quizzes(id)
);


Configure the Application
Ensure the application’s database connection settings are correctly configured to match your SQLite database.


Usage-----
User Authentication
Register and log in to access the quiz features.

Quiz Management (Admin)
Use the admin interface to create and manage quizzes and questions.

Taking a Quiz
Select a quiz and answer questions. Receive immediate feedback on your answers.

Viewing Results
Check your score after completing a quiz and view past attempts.

Acknowledgements
SQLite for the database system.
JavaFX for the user interface framework.
