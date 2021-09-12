package dev.punchcafe.commons.functional;

/**
 * A function which throws an exception.
 *
 * @param <T> The function arg type
 * @param <R> The function return type
 */
@FunctionalInterface
public interface ExceptionThrowingVoidFn<T> {
    void invokeThrowingFn(T arg) throws Exception;
}
