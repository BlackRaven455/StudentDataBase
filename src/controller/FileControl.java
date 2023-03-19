package controller;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static java.nio.file.StandardOpenOption.*;

public class FileControl {

    private final Path file;
    private final int CHECKSUMM = 0xFA;
    private final byte VERSION = 0x01;

    public FileControl(Path file) {
        Objects.requireNonNull(file);
        if (!file.isAbsolute()) {
            file = file.toAbsolutePath();
        }
        this.file = file;

    }

    public <T extends Student> void write(Set<Student> students) throws IOException {
        try (DataOutputStream out = new DataOutputStream(Files.newOutputStream(file, WRITE, CREATE, TRUNCATE_EXISTING))) {
            out.writeByte(CHECKSUMM);
            out.writeByte(VERSION);
            out.write(students.size());
            for (Human persona : students) {
                persona.writeInfo(out);
            }

        }
    }

    public <T extends Student> Set<Student> read(Set<Student> students) {
        Student student = new Student();
        try (DataInputStream in = new DataInputStream(Files.newInputStream(file))) {
            int checkSum = in.readInt();
            int version = in.readByte();
            if(checkSum != CHECKSUMM){
                System.out.println("Chosen file not exist");
                return null;
            } else if(version != VERSION){
                System.out.println("Version does not match ");
                return null;
            }
            for(int size =  0;  size <= in.readInt(); size++){
                student.readInfo(in);
                students.add(student);
            }
            return students;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public void exitFile() throws IOException{
        try (DataOutputStream out = new DataOutputStream(Files.newOutputStream(file, WRITE, CREATE, TRUNCATE_EXISTING))) {
            out.writeByte(CHECKSUMM);
        }
    }
}
