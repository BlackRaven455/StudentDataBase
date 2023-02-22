package thedarkdnktv.studentdb.api;

import java.io.IOException;
import java.util.Collection;

public interface IDatabase<T> {

    boolean persist(T obj);

    boolean remove(T obj);

    Collection<T> getAllStored();

    void save() throws IOException;

    void load() throws IOException;
}
