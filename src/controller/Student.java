package controller;

import java.util.ArrayList;

public class Student extends Human {
    public static ArrayList<String> listOfStudents;
    private String specialization = new String();
    private int groupNumber = 1;
    private int year = 1;
    private float avarageScore = 0.0f;

    public Student(String firstName, String familyName,  String specialization, int age, int year, int groupNumber,float avarageScore) {
        super(firstName, familyName, age);
        this.avarageScore = avarageScore;
        this.groupNumber = groupNumber;
        this.year = year;
        this.specialization = specialization;
        listOfStudents.add(familyName + " | " + firstName + " | " + specialization + " | " + age + " | " + year + " | " + groupNumber + " | " + avarageScore+ " || ");
    }
    public void addStudent(String firstName, String familyName,  String specialization, int age, int year, int groupNumber,float avarageScore){
        listOfStudents.add(familyName + " | " + firstName + " | " + specialization + " | " + age + " | " + year + " | " + groupNumber + " | " + avarageScore+ " || ");
    }

    public String getInfo(int index){
        return listOfStudents.get(index);
    }

    public void deleteInfo(int index){
        listOfStudents.get(index);
        System.out.print("Deleted");
    }

//    static{
//
//    }
}
