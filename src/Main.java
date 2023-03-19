import controller.FileControl;
import controller.Student;

import static java.lang.System.out;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    private static final Set<Student> students = new HashSet<>();

    public static void main(String[] args) throws IOException {
        FileControl file;
        Scanner scanner = new Scanner(System.in);
        int menuControl = 0;
        Path nameFile = Paths.get("db.bin");
        file = new FileControl(nameFile);
        file.read(students);
        while (true) {
            out.println("1 - Add student\n2 - List of students\n3 - Delete student\n4 - Exit\n");
            menuControl = scanner.nextInt();
            switch (menuControl) {
                case 1: {
                    Student student = new Student();
                    addStudent(student);
                    break;
                }
                case 2: {
                    showStudent();
                    break;
                }
                case 3: {
                    out.println("Enter Family  name of the student\n");
                    String familyName = scanner.next();

                    out.println("Enter  First name of the student");
                    String firstName = scanner.next();
                    deleteStudent(familyName, firstName);
                    break;
                }
                case 4: {
                    file.write(students);
//                    file.exitFile();
                    out.println("Thank you for using this shity app");
                    System.exit(0);
                }
                default: {
                    out.println("No options");
                }
            }
        }
    }

    public static void addStudent(Student student) {
        Scanner scanner = new Scanner(System.in);
        out.println("Enter family name:");
        student.setFamilyName(scanner.next());
        out.println("Enter first name:");
        student.setFirstName(scanner.next());
        while (true) {
            try {
                out.println("Enter age:");
                int age = scanner.nextInt();
                student.setAge(age);
                break;
            } catch (InputMismatchException e) {
                out.println("Input mismatch. Need number.\n");
                scanner.next();
            }
        }
        out.println("Enter specialization:");
        student.setSpecialization(scanner.next());
        while (true) {
            try {
                out.println("Enter group number:");
                student.setGroupNumber(scanner.nextByte());
                break;
            } catch (InputMismatchException e) {
                out.println("Input mismatch. Need byte from 0 to 127\n");
                scanner.next();
            }
        }

        while (true) {
            try {
                out.println("Enter year:");
                student.setYear(scanner.nextByte());
                break;
            } catch (InputMismatchException e) {
                out.println("Input mismatch. Need byte from 0 to 127\n");
                scanner.next();
            }
        }
        while (true) {
            try {
                out.println("Enter average score:");
                float score = scanner.nextFloat();
                student.setAverageScore(score);
                break;
            } catch (InputMismatchException e) {
                out.println("Input mismatch. Need number with . (ex: 0.0)\n");
                scanner.next();
            }
        }
        students.add(student);
        out.println("Student added");

    }

    public static void showStudent() {
        if (students.isEmpty()) {
            out.println("Nothing to show");
            return;
        }
        out.println("Std. No.| Family name | First name | Age | Specialization | Group number | Year | Average score||");
        int index = 0;
        for (Student student : students) {
            out.println(index + " | " +
                    student.getFamilyName() + " | " +
                    student.getFirstName() + " | " +
                    student.getAge() + " | " +
                    student.getSpecialization() + " | " +
                    student.getGroupNumber() + " | " +
                    student.getYear() + " | " +
                    student.getAverageScore() + " ||");
            index++;
        }

    }


    public static void deleteStudent(String familyName, String firstName) {
        if (students.isEmpty()) {
            out.println("Nothing to delete");
            return;
        }
        students.removeIf(student -> student.getFamilyName().equalsIgnoreCase(familyName) && student.getFirstName().equalsIgnoreCase(firstName));
        out.println("deleted");
    }
}
