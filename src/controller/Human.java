package controller;

import java.io.DataOutputStream;
import java.io.IOException;

abstract class Human {
    protected String firstName = "";
    protected String familyName = "";
    protected int age = 0;

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

    public abstract void writeInfo(DataOutputStream out) throws IOException;
}
