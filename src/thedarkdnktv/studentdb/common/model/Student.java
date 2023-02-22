package thedarkdnktv.studentdb.common.model;

import thedarkdnktv.studentdb.api.IDataModel;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Objects;

public class Student implements IDataModel {

    private String name;
    private String surname;
    private String faculty;
    private float gradePointAverage;
    private byte course;
    private byte age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public float getGradePointAverage() {
        return gradePointAverage;
    }

    public void setGradePointAverage(float gradePointAverage) {
        this.gradePointAverage = gradePointAverage;
    }

    public byte getCourse() {
        return course;
    }

    public void setCourse(byte course) {
        this.course = course;
    }

    public byte getAge() {
        return age;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    @Override
    public void store(DataOutput out) throws IOException {
        out.writeUTF(name);
        out.writeUTF(surname);
        out.writeByte(age);

        out.writeUTF(faculty);
        out.writeFloat(gradePointAverage);
        out.writeByte(course);
    }

    @Override
    public void read(DataInput in) throws IOException {
        this.name       = in.readUTF();
        this.surname    = in.readUTF();
        this.age        = in.readByte();

        this.faculty    = in.readUTF();
        this.gradePointAverage = in.readFloat();
        this.course     = in.readByte();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Float.compare(student.gradePointAverage, gradePointAverage) == 0 && course == student.course && age == student.age && name.equals(student.name) && surname.equals(student.surname) && faculty.equals(student.faculty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, faculty, gradePointAverage, course, age);
    }
}
