package common;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Set;

import static java.lang.System.in;
import static java.lang.System.out;
import static java.nio.file.StandardOpenOption.*;

public class FileControl {

    private final Path file;
    protected final static int CHECKSUM = 0xFA1DCCE7;
    protected final static byte VERSION = 1;

    public FileControl(Path file) {
        Objects.requireNonNull(file);
        if (!file.isAbsolute()) {
            file = file.toAbsolutePath();
        }
        this.file = file;

    }

    public <T extends Student> void write(Set<Student> students) throws IOException {
        try (DataOutputStream out = new DataOutputStream(Files.newOutputStream(file, WRITE, CREATE, TRUNCATE_EXISTING))) {
            out.writeInt(CHECKSUM);
            out.writeByte(VERSION);
            out.writeInt(students.size());
            for (Human persona : students) {
                persona.writeInfo(out);
            }
            out.writeInt(CHECKSUM);
        }
    }

    public <T extends Student> void read(Set<Student> students) {
        try (DataInputStream in = new DataInputStream(new BufferedInputStream(Files.newInputStream(file)))) {
            if (in.available() < 6) {
                System.out.println(in.available());
                Files.deleteIfExists(file);
                return;
            }
            int checkSum = in.readInt();
            if (checkSum != CHECKSUM) {
                Files.deleteIfExists(file);
                System.out.println("Chosen file not appropriate. Created new one");
                return;
            }
            int version = in.readByte();
            if (version != VERSION) {
                System.out.println("Version does not match ");
                return;
            }
            int size = in.readInt();
            if(size != 0 && size != CHECKSUM){
                for (int i = 0; i < size; i++) {
                    Student student = new Student();
                    student.readInfo(in);
                    students.add(student);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public void checkFile() {
        if (Files.notExists(file)) {
            try {
                Files.createFile(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try (DataOutputStream out = new DataOutputStream(Files.newOutputStream(file, WRITE, CREATE, TRUNCATE_EXISTING))) {
                out.writeInt(CHECKSUM);
                out.writeByte(VERSION);
                out.writeInt(CHECKSUM);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        out.println("Ready to read");
    }
}
