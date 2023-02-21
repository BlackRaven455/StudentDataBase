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

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter file name:");
        String fileName = scanner.next();
        int menuControl = 0;
        FileControl file = new FileControl();
        while (menuControl != 4) {
            System.out.println("1 - Add student\n2- Delete student\n3 - List of students\n4 - Exit");
            menuControl = scanner.nextInt();
            switch (menuControl) {
                case 1: {
                    System.out.println("Enter first and family name, specialization, age, year, group number, avaage score:\n ");
                    Student student = new Student(scanner.next(), scanner.next(), scanner.next(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextFloat());
                    file.FileWrite(student, fileName);
                    break;
                }
                case 2: {
                    file.FileRead(fileName);
                    break;
                }
                case 3: {
                    break;
                }
                case 4: {
                    break;
                }
                default:
                    System.out.println("No options");

            }

        }
        System.out.println("Thank you for using this shity app");
    }
}
