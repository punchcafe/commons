package dev.punchcafe.commons.functional;

/**
 * A function which throws an exception.
 *
 * @param <T> The function arg type
 */
@FunctionalInterface
public interface ExceptionThrowingLongFn<T> {
    long invokeThrowingFn(T arg) throws Exception;
}
