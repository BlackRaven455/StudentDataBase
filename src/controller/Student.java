package controller;

import java.util.ArrayList;

public class Student extends Human{
    public static ArrayList<String> listOfStudents;
    private String specialization = new String();
    private int groupNumber = 1;
    private int year = 1;
    private int avarageScore = 0;


    public Student(String firstName, String familyName) {
        super(firstName, familyName);
        listOfStudents.add(familyName + '_' + firstName);
    }

    public Student(String firstName, String familyName, int age) {
        super(firstName, familyName, age);
        listOfStudents.add(familyName + '_' + firstName);
    }




}
