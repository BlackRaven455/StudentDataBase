package controller;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static java.nio.file.StandardOpenOption.*;

public class FileControl {

    private final Path file;
    private final Set<Student> students;

    public FileControl(Path file) {
        Objects.requireNonNull(file);
        if (!file.isAbsolute()) {
            file = file.toAbsolutePath();
        }
        this.file = file;
        this.students = new HashSet<>();
    }

    public void write(String writtenInfo, String fileName) throws IOException {
        try (DataOutputStream out = new DataOutputStream(Files.newOutputStream(file, WRITE, CREATE, TRUNCATE_EXISTING))) {
            byte[] b = writtenInfo.getBytes(StandardCharsets.UTF_8);
            out.writeUTF(writtenInfo);
            for (Human persona : students) {
                persona.writeInfo(out);
            }
        }
    }

    public void read(String fileName) {
        try (DataInputStream in = new DataInputStream(Files.newInputStream(Paths.get(fileName)))) {
            System.out.println(in.readUTF());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> void fileDelete(String fileName, Object deletedInfo) {

    }
}
