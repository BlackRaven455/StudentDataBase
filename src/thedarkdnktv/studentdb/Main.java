package thedarkdnktv.studentdb;

import thedarkdnktv.studentdb.api.IDatabase;
import thedarkdnktv.studentdb.api.IRenderer;
import thedarkdnktv.studentdb.common.Operation;
import thedarkdnktv.studentdb.common.db.FileSystemDatabase;
import thedarkdnktv.studentdb.common.model.Student;
import thedarkdnktv.studentdb.common.render.RenderProvider;
import thedarkdnktv.studentdb.common.render.StudentRenderer;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static java.lang.System.out;

public class Main {

    public static final String VERSION = "0.1";

    static Scanner scan;
    static IDatabase<Student> database;
    static RenderProvider renderProvider;

    public static void main(String[] args) {
        println("Запуск базы данных версии v" + VERSION);

        try {
            scan = new Scanner(System.in);
            database = new FileSystemDatabase(Paths.get("db.bin"));
            database.load();
            renderProvider = new RenderProvider();
            initRenderers();
        } catch (Exception e) {
            println("Ошибка инициализации");
            e.printStackTrace();
            System.exit(-1);
            return;
        }

        showMenuPage();
    }

    static void showMenuPage() {
        boolean isRunning = true;
        while (isRunning) {
            println("Пожалуйста выберите операцию:");
            for (int i = 0; i < Operation.VALID_VALUES.length; i++) {
                out.printf("\t%d. %s\n", i, Operation.VALID_VALUES[i].label);
            }

            Operation option;
            while (true) {
                try {
                    option = Operation.getById(scan.nextInt());
                    if (option.isInvalid()) {
                        println("Выбранный ответ не существует, выберете из списка");
                        continue;
                    }

                    break;
                } catch (InputMismatchException e) {
                    println("Введённое значение не является цифрой, пожалуйста повторите ввод");
                }

                scan.next();
            }

            switch (option) {
                case ADD: {
                    addStudent();
                    break;
                }
                case REMOVE: {
                    removeStudent();
                    break;
                }
                case VIEW: {
                    showStudents();
                    break;
                }
                case EXIT: {
                    isRunning = false;
                    exit();
                }
            }
        }
    }

    static void addStudent() {
        Student student = new Student();

        {
            out.print("Введите имя: ");
            student.setName(scan.next());

            out.print("Введите фамилию: ");
            student.setSurname(scan.next());

            while (true) {
                out.print("Введите возраст: ");
                try {
                    int age = scan.nextInt();
                    println();

                    if (age < 0 || age > Byte.MAX_VALUE) {
                        println("Возраст не может быть меньше 0 или больше 127");
                        continue;
                    }

                    student.setAge((byte) age);
                    break;
                } catch (NoSuchElementException e) {
                    scan.next();
                    println("Введите число");
                }
            }

            out.print("Введите факультет: ");
            student.setFaculty(scan.next());

            while (true) {
                out.print("Введите курс: ");
                try {
                    int course = scan.nextInt();
                    println();

                    if (course <= 0 || course > Byte.MAX_VALUE) {
                        println("Курс не может быть меньше или равен 0 или больше 127");
                        continue;
                    }

                    student.setCourse((byte) course);
                    break;
                } catch (NoSuchElementException e) {
                    scan.next();
                    println("Введите число");
                }
            }

            while (true) {
                out.print("Введите средний бал (десятичная дробь): ");
                try {
                    float gpa = scan.nextFloat();
                    println();

                    if (gpa < 0) {
                        println("Средний бал не может быть меньше 0");
                        continue;
                    }

                    student.setGradePointAverage(gpa);
                    break;
                } catch (NoSuchElementException e) {
                    scan.next();
                    println("Введите дробное число");
                }
            }
        }

        if (database.persist(student)) {
            println("Студент успешно сохранён");
        } else {
            println("Не удалось сохранить студента, данный студент уже существует");
        }
    }

    static void removeStudent() {
        // TODO
    }

    static void showStudents() {
        IRenderer<Student> renderer = renderProvider.getRenderer(Student.class);

        println("Студенты в базе:");
        for (Student student : database.getAllStored()) {
            println(renderer.renderAsString(student));
        }
    }

    static void exit() {
        println("Выход из базы данных");

        try {
            database.save();
        } catch (IOException e) {
            println("Ошибка сохранения базы данных");
            e.printStackTrace();
            System.exit(-2);
        }
    }

    private static void initRenderers() {
        renderProvider.register(Student.class, new StudentRenderer());
    }

    static synchronized void println(Object... obj) {
        for (Object o : obj) {
            System.out.print(o);
            System.out.print(' ');
        }

        System.out.print('\n');
    }
}
