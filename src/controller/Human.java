package controller;

abstract class Human {
    private String firstName = "";
    private String familyName = "";
    private int age = 0;

    public Human(String firstName, String familyName) {
        this.familyName = familyName;
        this.firstName = firstName;
    }

    public Human(String firstName, String familyName, int age) {
        this.familyName = familyName;
        this.firstName = firstName;
        this.age = age;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return familyName + '_' + firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public int getAge() {
        return age;
    }
}
