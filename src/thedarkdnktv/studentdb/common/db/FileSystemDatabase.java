package thedarkdnktv.studentdb.common.db;

import thedarkdnktv.studentdb.api.IDataModel;
import thedarkdnktv.studentdb.api.IDatabase;
import thedarkdnktv.studentdb.common.model.Student;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static java.nio.file.StandardOpenOption.*;

public class FileSystemDatabase implements IDatabase<Student> {

    protected final static int MAGIC = 0xFA1EC54D;
    protected final static int FILE_VERSION = 1;
    /**
     * We are writing always two magic and size = 12 bytes (int = 4 bytes)
     */
    protected final static int MIN_SIZE = 16;

    protected final Path file;
    protected final Set<Student> students;

    public FileSystemDatabase(Path file) {
        Objects.requireNonNull(file);
        if (!file.isAbsolute()) {
            file = file.toAbsolutePath();
        }

        this.file = file;
        this.students = new HashSet<>();
    }

    @Override
    public boolean persist(Student obj) {
        if (obj != null && !students.contains(obj)) {
            students.add(obj);
            return true;
        }

        return false;
    }

    @Override
    public boolean remove(Student obj) {
        return students.remove(obj);
    }

    @Override
    public Collection<Student> getAllStored() {
        return Collections.unmodifiableSet(students);
    }

    @Override
    public void save() throws IOException {
        {
            Path parent = file.getParent();
            if (Files.notExists(parent)) {
                Files.createDirectories(parent);
            }
        }

        try (DataOutputStream out = new DataOutputStream(Files.newOutputStream(file, WRITE, CREATE, TRUNCATE_EXISTING))) {
            out.writeInt(MAGIC);
            out.writeInt(FILE_VERSION);
            out.writeInt(students.size());
            for (IDataModel model : students) {
                model.store(out);
            }

            out.writeInt(MAGIC);
        } catch (IOException e) {
            throw new IOException("Unable to save database content to file", e);
        }
    }

    @Override
    public void load() throws IOException {
        students.clear();

        {
            Path parent = file.getParent();
            if (Files.notExists(parent)) {
                Files.createDirectories(parent);
            }
        }

        if (Files.notExists(file)) {
            return;
        }

        try (DataInputStream in = new DataInputStream(new BufferedInputStream(Files.newInputStream(file, READ)))) {
            if (in.available() < MIN_SIZE) { // check if we have header written
                Files.deleteIfExists(file);
                return;
            }

            int magic = in.readInt();
            if (magic != MAGIC) {
                throw new IOException("Invalid file format");
            }

            int version = in.readInt();
            if (version > FILE_VERSION) {
                throw new IOException("Unsupported file version: " + version);
            }

            int size = in.readInt();
            for (int i = 0; i < size; i ++) {
                Student student = new Student();
                student.read(in);
                students.add(student);
            }

            magic = in.readInt();
            if (magic != MAGIC) {
                throw new IOException("Invalid file format");
            }
        } catch (IOException e) {
            students.clear();
            if (e instanceof EOFException) {
                Files.deleteIfExists(file);
                return;
            }

            throw new IOException("Unable to load content of database from file", e);
        }
    }
}
