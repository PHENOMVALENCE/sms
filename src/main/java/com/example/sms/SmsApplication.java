package com.example.sms;

import com.example.sms.model.Student;
import com.example.sms.service.StudentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SmsApplication implements CommandLineRunner {

    private final StudentService studentService;

    public SmsApplication(StudentService studentService) {
        this.studentService = studentService;
    }

    public static void main(String[] args) {
        SpringApplication.run(SmsApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (choice) {
                case 1 -> {
                    Student student = new Student();
                    System.out.print("Enter name: ");
                    student.setName(scanner.nextLine());
                    System.out.print("Enter email: ");
                    student.setEmail(scanner.nextLine());
                    System.out.print("Enter course: ");
                    student.setCourse(scanner.nextLine());
                    studentService.saveStudent(student);
                    System.out.println("Student added.");
                }
                case 2 -> {
                    System.out.println("\n--- All Students ---");
                    studentService.getAllStudents().forEach(s ->
                            System.out.println(s.getId() + ": " + s.getName() + ", " + s.getEmail() + ", " + s.getCourse()));
                }
                case 3 -> {
                    System.out.print("Enter student ID to update: ");
                    Long id = scanner.nextLong(); scanner.nextLine();
                    studentService.getStudentById(id).ifPresentOrElse(student -> {
                        System.out.print("Enter new name: ");
                        student.setName(scanner.nextLine());
                        System.out.print("Enter new email: ");
                        student.setEmail(scanner.nextLine());
                        System.out.print("Enter new course: ");
                        student.setCourse(scanner.nextLine());
                        studentService.saveStudent(student);
                        System.out.println("Updated.");
                    }, () -> System.out.println("Student not found."));
                }
                case 4 -> {
                    System.out.print("Enter student ID to delete: ");
                    Long id = scanner.nextLong();
                    studentService.deleteStudent(id);
                    System.out.println("Deleted if existed.");
                }
                case 5 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }
}
