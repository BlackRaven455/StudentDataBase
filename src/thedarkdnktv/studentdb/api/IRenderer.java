package thedarkdnktv.studentdb.api;

public interface IRenderer<T> {

    String renderAsString(T object);

    boolean isSupported(Class<?> type);
}
