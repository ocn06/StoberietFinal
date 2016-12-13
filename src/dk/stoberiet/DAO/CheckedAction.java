package dk.stoberiet.DAO;

@FunctionalInterface
public interface CheckedAction<T, E extends Throwable> {
    void apply(T t) throws E;
}
