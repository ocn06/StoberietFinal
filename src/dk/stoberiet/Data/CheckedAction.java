package dk.stoberiet.Data;

@FunctionalInterface
public interface CheckedAction<T, E extends Throwable> {
    void apply(T t) throws E;
}
