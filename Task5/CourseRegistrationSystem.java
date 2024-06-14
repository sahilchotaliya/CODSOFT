package Task5;

import java.sql.*;

class Course {
    private String courseCode;
    private String title;
    private String description;
    private int capacity;
    private String schedule;

    public Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getSchedule() {
        return schedule;
    }
}

class Student {
    private int studentID;
    private String name;

    public Student(int studentID, String name) {
        this.studentID = studentID;
        this.name = name;
    }

    public int getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }
}

public class CourseRegistrationSystem {
    private Connection connection;

    public CourseRegistrationSystem() {
        try {

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/courseregistration", "root",
                    "Root");
            System.out.println("Connected to the database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addCourse(Course course) {
        try {
            String insertSQL = "INSERT INTO courses(course_code, title, description, capacity, schedule) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setString(1, course.getCourseCode());
            preparedStatement.setString(2, course.getTitle());
            preparedStatement.setString(3, course.getDescription());
            preparedStatement.setInt(4, course.getCapacity());
            preparedStatement.setString(5, course.getSchedule());
            preparedStatement.executeUpdate();
            System.out.println("Course added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addStudent(Student student) {
        try {
            String insertSQL = "INSERT INTO students(student_id, name) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setInt(1, student.getStudentID());
            preparedStatement.setString(2, student.getName());
            preparedStatement.executeUpdate();
            System.out.println("Student added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CourseRegistrationSystem system = new CourseRegistrationSystem();

        Course c1 = new Course("CSCI101", "Introduction to Computer Science", "Basic concepts of programming", 50,
                "Mon/Wed 10:00 AM - 12:00 PM");
        Course c2 = new Course("MATH201", "Calculus I", "Introduction to calculus", 40, "Tue/Thu 9:00 AM - 11:00 AM");

        Student s1 = new Student(1, "John Doe");
        Student s2 = new Student(2, "Jane Smith");

        system.addCourse(c1);
        system.addCourse(c2);
        system.addStudent(s1);
        system.addStudent(s2);
    }
}
