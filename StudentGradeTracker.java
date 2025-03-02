import java.util.ArrayList;
import java.util.Scanner;
class Student {
    private String name;
    private double grade;
    public Student(String name, double grade) {
        this.name = name;
        this.grade = grade;
    }
    public String getName() {
        return name;
    }
    public double getGrade() {
        return grade;
    }
}
public class StudentGradeTracker {
    private static ArrayList<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        while (true) {
            System.out.println("\nStudent Grade Tracker");
            System.out.println("1. Add Student Grade");
            System.out.println("2. Display All Students");
            System.out.println("3. Calculate Average Grade");
            System.out.println("4. Find Highest and Lowest Grade");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    displayStudents();
                    break;
                case 3:
                    calculateAverage();
                    break;
                case 4:
                    findHighestAndLowest();
                    break;
                case 5:
                    System.out.println("Exiting program...");
                    return;
                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
            }
        }
    }
    // Add a student and their grade
    private static void addStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter student grade: ");
        double grade = scanner.nextDouble();
        students.add(new Student(name, grade));
        System.out.println("Student added successfully!");
    }
    // Display all student records
    private static void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("No student records available.");
            return;
        }
        System.out.println("\nStudent Grades:");
        for (Student student : students) {
            System.out.println(student.getName() + " - " + student.getGrade());
        }
    }
    // Calculate and display the average grade
    private static void calculateAverage() {
        if (students.isEmpty()) {
            System.out.println("No grades available to calculate the average.");
            return;
        }
        double sum = 0;
        for (Student student : students) {
            sum += student.getGrade();
        }
        double average = sum / students.size();
        System.out.printf("Average Grade: %.2f\n", average);
    }
    // Find and display the highest and lowest grade
    private static void findHighestAndLowest() {
        if (students.isEmpty()) {
            System.out.println("No grades available to determine highest and lowest.");
            return;
        }
        double highest = students.get(0).getGrade();
        double lowest = students.get(0).getGrade();
        String highestStudent = students.get(0).getName();
        String lowestStudent = students.get(0).getName();
        for (Student student : students) {
            if (student.getGrade() > highest) {
                highest = student.getGrade();
                highestStudent = student.getName();
            }
            if (student.getGrade() < lowest) {
                lowest = student.getGrade();
                lowestStudent = student.getName();
            }
        }
        System.out.println("Highest Grade: " + highest + " (Student: " + highestStudent + ")");
        System.out.println("Lowest Grade: " + lowest + " (Student: " + lowestStudent + ")");
    }
}