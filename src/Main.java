import controller.FileControl;
import controller.Student;

import static java.lang.System.out;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

public class Main {
    private static final Set<Student> students = new HashSet<>();

    public static void main(String[] args) throws IOException {
        FileControl file;
        Scanner scanner = new Scanner(System.in);
        int menuControl = 0;
        while (true) {
            while (true) {
                out.print("Choose database file:\n");
                for (int i = 0; i < DatabaseFiles.values().length - 1; i++) {
                    out.printf("\t%d. %s\n", i + 1, DatabaseFiles.getFileName(i).label);
                }
                try {
                    int j = scanner.nextInt() - 1;
                    if (j < 0 || j >= 1) { // Files from 2 to ... not existed yet
                        out.println("File with this number doesn't exist");
                        continue;
                    }
                    Path nameFile = Paths.get(DatabaseFiles.getFileName(j).label);
                    file = new FileControl(nameFile);
                    break;
                } catch (InputMismatchException e) {
                    out.println("Invalid value, use numbers\n");
                    scanner.next();
                }
            }
            while (true) {
                out.println("1 - Add student\n2 - Delete student\n3 - List of students\n4 - Exit\n");
                menuControl = scanner.nextInt();
                switch (menuControl) {
                    case 1: {
                        Student student = new Student();
                        addStudent(student);
                        file.write(student);
                        break;
                    }
                    case 2: {
                        showStudent();
                        break;
                    }
                    case 3: {
                        deleteStudent();
                        break;
                    }
                    case 4: {
                        file.exitFile();
                        out.println("Thank you for using this shity app");
                        System.exit(0);
                    }
                    default:
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
                byte groupNumber = scanner.nextByte();
                student.setGroupNumber(groupNumber);
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
            students.add(student);
        }


    }
    public static void showStudent(){
        out.println("show");
    }
    public static void deleteStudent(){
        out.println("delete");
    }
}
