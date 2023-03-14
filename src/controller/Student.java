package controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Objects;

public class Student extends Human {

    private String specialization = new String();
    private byte groupNumber = 1;
    private byte year = 1;
    private float averageScore = 0.0f;

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public byte getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(byte groupNumber) {
        this.groupNumber = groupNumber;
    }

    public byte getYear() {
        return year;
    }

    public void setYear(byte year) {
        this.year = year;
    }

    public float getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(float averageScore) {
        this.averageScore = averageScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return groupNumber == student.groupNumber && year == student.year && Float.compare(student.averageScore, averageScore) == 0 && Objects.equals(specialization, student.specialization);
    }


    @Override
    public int hashCode() {
        return Objects.hash(specialization, groupNumber, year, averageScore);
    }

    public void writeInfo(DataOutputStream out) throws IOException {
        out.writeUTF(getFamilyName());
        out.writeUTF(getFirstName());
        out.writeByte(getAge());
        out.writeUTF(specialization);
        out.writeByte(year);
        out.writeFloat(averageScore);
    }

    public void readInfo(DataInputStream in) throws IOException {
//        setFamilyName(in.readUTF());
//        setFirstName(in.readUTF());
//        setAge(in.readByte());
        this.familyName = in.readUTF();
        this.firstName = in.readUTF();
        this.age = in.readByte();
        this.specialization = in.readUTF();
        this.year = in.readByte();
        this.averageScore = in.readFloat();
    }

}
