//Цель: создание консольного приложения, которое позволяет пользователю добавлять, удалять и просматривать информацию о студентах.
//
//        Функциональные требования:
//
//        При запуске приложения, пользователь должен увидеть меню с вариантами действий: "Добавить студента", "Удалить студента", "Просмотреть список студентов", "Выход из приложения".
//
//        При выборе опции "Добавить студента", пользователь должен иметь возможность ввести следующие данные о студенте: имя, фамилию, возраст, группу, успеваемость и т.д.
//        При выборе опции "Удалить студента", пользоват
//        При выборе опции "Просмотреть список студентов", пользователель должен иметь возможность выбрать студента из списка и удалить его из базы данных.ь должен иметь возможность просмотреть список всех студентов в базе данных, отсортированных по фамилии или группе.
//        При выборе опции "Выход из приложения", приложение должно быть закрыто.
//        Нефункциональные требования:
//
//        - Данные о студентах должны храниться в файле с бинарном виде.
//        - Приложение должно обрабатывать ошибки ввода и вывода, а также ошибки, связанные с базой данных.
//        - Код приложения должен быть написан с использованием объектно-ориентированного подхода и разбит на классы, отражающие объекты системы (например, класс "Студент").
//        - Нужно использовать git для контролля версий

import controller.FileControl;
import controller.Student;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Student> studentArrayList = new ArrayList<>();
        int studentCount = 0;
        int menuControl = 0;
        FileControl file = new FileControl();
        while (menuControl != 4) {
            System.out.println("1 - Add student\n2- Delete student\n3 - List of students\n4 - Exit\n");
            menuControl = scanner.nextInt();
            switch (menuControl) {
                case 1: {
                    System.out.println("Enter first and family name, specialization, age, year, group number, avaage score:\n ");
                    studentArrayList.add(new Student(scanner.next(), scanner.next(), scanner.next(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextFloat()));
                    System.out.print("Choose file to write: 1 - Studentdb.bin, 2 - \n\n");
                    String fileName = scanner.next();
                    try {
                        if (fileName.equals("Studentdb.bin")) {
                            file.fileWrite(studentArrayList.get(studentArrayList.size()-1).getInfo(studentArrayList.size()-1), fileName);
                        } else {
                            System.out.print("Error in the name of file\n");
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }


                }
                case 2: {
                    System.out.print("Choose file to read: 1 - Studentdb.bin, 2 - not available\n");
                    String fileName = scanner.next();
                    if (fileName.equals("Studentdb.bin")) {
                        file.fileRead(fileName);
                    } else {
                        System.out.print("Error in the name of file\n");
                    }

                }
                case 3: {
                    System.out.print("Choose student to delete: 1 - Studentdb.bin, 2 - not available\n");
                    studentCount = scanner.nextInt();
                    try {
                        studentArrayList.get(studentCount).deleteInfo(studentCount);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.print("Out of bound. No data.\n");
                        continue;
                    }
                    studentArrayList.remove(studentCount);
                    System.out.print("Choose file to delete: 1 - Studentdb.bin, 2 - not available\n");
                    String fileName = scanner.next();
                    if (fileName.equals("Studentdb.bin")) {
                        file.fileDelete(fileName, studentArrayList);
                    } else {
                        System.out.print("Error in the name of file\n");
                    }
                }
                default:
                    System.out.println("No options");

            }
        }
        System.out.println("Thank you for using this shity app");
    }
}
