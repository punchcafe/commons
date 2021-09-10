package dev.punchcafe.commons.functional;

/**
 * A function which throws an exception.
 *
 * @param <T> The function arg type
 * @param <R> The function return type
 */
@FunctionalInterface
public interface ExceptionThrowingFn<T, R> {
    R invokeThrowingFn(T arg) throws Exception;
}
