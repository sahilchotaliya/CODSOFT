package Task4;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class Question {
    String question;
    String[] options;
    int correctAnswer;

    Question(String question, String[] options, int correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }
}

public class Quiz {
    static Scanner scanner = new Scanner(System.in);
    static int score = 0;
    static int questionIndex = 0;
    static int timeLimit = 10;
    static boolean answered = false;
    static String[] userAnswers;
    static Timer timer;
    static Thread timerThread;

    public static void main(String[] args) {
        Question[] questions = {
                new Question("What is the capital of France?",
                        new String[] { "1. Berlin", "2. Madrid", "3. Paris", "4. Rome" }, 3),
                new Question("Who wrote 'To Kill a Mockingbird'?",
                        new String[] { "1. Harper Lee", "2. Jane Austen", "3. Mark Twain", "4. J.K. Rowling" }, 1),
                new Question("What is the largest planet in our Solar System?",
                        new String[] { "1. Earth", "2. Mars", "3. Jupiter", "4. Saturn" }, 3),
        };

        userAnswers = new String[questions.length];

        for (Question question : questions) {
            displayQuestion(question);
            startTimer(question);

            synchronized (Quiz.class) {
                while (!answered) {
                    try {
                        Quiz.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            questionIndex++;
            answered = false;
        }

        displayResults(questions);
    }

    static void displayQuestion(Question question) {
        System.out.println("\nQuestion " + (questionIndex + 1) + ": " + question.question);
        for (String option : question.options) {
            System.out.println(option);
        }
    }

    static void startTimer(Question question) {
        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                synchronized (Quiz.class) {
                    if (!answered) {
                        System.out.println("\nTime's up!");
                        answered = true;
                        userAnswers[questionIndex] = "No answer";
                        Quiz.class.notifyAll();
                    }
                }
                timer.cancel();
                if (timerThread != null && timerThread.isAlive()) {
                    timerThread.interrupt();
                }
            }
        };
        timer.schedule(task, timeLimit * 1000);

        timerThread = new Thread(() -> {
            int timeLeft = timeLimit;
            while (timeLeft > 0 && !answered) {
                System.out.print("\rTime left: " + timeLeft + " seconds" + " =>");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    break;
                }
                timeLeft--;
            }
            System.out.println("\r");
        });
        timerThread.start();

        new Thread(() -> {
            int userAnswer = -1;
            try {
                System.out.println("\nEnter your answer (1-4): ");
                userAnswer = scanner.nextInt();
            } catch (Exception e) {
                // Handle input exception
            }
            synchronized (Quiz.class) {
                if (!answered && userAnswer != -1 && userAnswer >= 1 && userAnswer <= 4) {
                    submitAnswer(question, userAnswer);
                    userAnswers[questionIndex] = question.options[userAnswer - 1];
                    answered = true;
                    Quiz.class.notifyAll();
                }
            }
            timer.cancel();
            if (timerThread != null && timerThread.isAlive()) {
                timerThread.interrupt();
            }
        }).start();
    }

    static void submitAnswer(Question question, int userAnswer) {
        if (userAnswer == question.correctAnswer) {
            score++;
        }
    }

    static void displayResults(Question[] questions) {
        System.out.println("\nQuiz Over!");
        System.out.println("\nSummary:");
        for (int i = 0; i < questions.length; i++) {
            System.out.println("Question " + (i + 1) + ": " + questions[i].question);
            System.out.println("Your answer: " + userAnswers[i]);
            System.out.println("Correct answer: " + questions[i].options[questions[i].correctAnswer - 1] + "\n");
        }
        System.out.println("Your final score is: " + score + "/" + questions.length);
    }
}
