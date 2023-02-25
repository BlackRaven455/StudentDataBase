package thedarkdnktv.studentdb.api;

import java.io.IOException;
import java.util.*;

public interface IDatabase<T> {

    boolean persist(T obj);

    boolean remove(T obj);

    Collection<T> getAllStored();

    default List<T> getStudentsSorted(Comparator<T> comparator) {
        List<T> students = new ArrayList<>(getAllStored());
        students.sort(comparator);
        return students;
    }

    void save() throws IOException;

    void load() throws IOException;
}
