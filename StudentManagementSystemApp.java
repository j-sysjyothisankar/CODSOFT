import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private int rollNumber;
    private String grade;

    public Student(String name, int rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", rollNumber=" + rollNumber +
                ", grade='" + grade + '\'' +
                '}';
    }
}

class StudentManagementSystem {
    private ArrayList<Student> students;

    public StudentManagementSystem() {
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(int rollNumber) {
        students.removeIf(student -> student.getRollNumber() == rollNumber);
    }

    public Student searchStudent(int rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                return student;
            }
        }
        return null;
    }

    public void displayAllStudents() {
        for (Student student : students) {
            System.out.println(student);
        }
    }

    public void saveDataToFile(String fileName) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(students);
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadDataFromFile(String fileName) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            students = (ArrayList<Student>) inputStream.readObject();
            System.out.println("Data loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

public class StudentManagementSystemApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManagementSystem system = new StudentManagementSystem();

        while (true) {
            System.out.println("\nStudent Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Save Data to File");
            System.out.println("6. Load Data from File");
            System.out.println("7. Exit");

            System.out.print("Choose an option (1-7): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter student roll number: ");
                    int rollNumber = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    System.out.print("Enter student grade: ");
                    String grade = scanner.nextLine();

                    Student newStudent = new Student(name, rollNumber, grade);
                    system.addStudent(newStudent);
                    System.out.println("Student added successfully.");
                    break;

                case 2:
                    System.out.print("Enter student roll number to remove: ");
                    int rollToRemove = scanner.nextInt();
                    system.removeStudent(rollToRemove);
                    System.out.println("Student removed successfully.");
                    break;

                case 3:
                    System.out.print("Enter student roll number to search: ");
                    int rollToSearch = scanner.nextInt();
                    Student foundStudent = system.searchStudent(rollToSearch);
                    if (foundStudent != null) {
                        System.out.println("Student found: " + foundStudent);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 4:
                    System.out.println("All Students:");
                    system.displayAllStudents();
                    break;

                case 5:
                    System.out.print("Enter file name to save data: ");
                    String saveFileName = scanner.next();
                    system.saveDataToFile(saveFileName);
                    break;

                case 6:
                    System.out.print("Enter file name to load data: ");
                    String loadFileName = scanner.next();
                    system.loadDataFromFile(loadFileName);
                    break;

                case 7:
                    System.out.println("Exiting Student Management System. Goodbye!");
                    System.exit(0);

                default:
                    System.out.println("Invalid option. Please choose a valid option.");
            }
        }
    }
}
