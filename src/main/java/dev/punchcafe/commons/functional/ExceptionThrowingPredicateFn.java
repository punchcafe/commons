package dev.punchcafe.commons.functional;

/**
 * A function which throws an exception.
 *
 * @param <T> The function arg type
 */
@FunctionalInterface
public interface ExceptionThrowingPredicateFn<T> {
    boolean invokeThrowingFn(T arg) throws Exception;
}
