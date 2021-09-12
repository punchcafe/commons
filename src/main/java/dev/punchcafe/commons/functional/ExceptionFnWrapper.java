package dev.punchcafe.commons.functional;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class ExceptionFnWrapper {

    /**
     * Wraps a functional interface which throws a checked exception in a try catch block, allowing
     * checked exception functions to be cleanly wrapped in Stream::map and Optional::map operations.
     *
     * @param fn the original, checked exception throwing function
     * @param <T> the type of input of the function
     * @param <R> the return type of the function
     * @return the wrapped function.
     */
    public static <T, R> Function<T, R> wrapEx(final ExceptionThrowingFn<T, R> fn) {
        return (T obj) -> {
            try {
                return fn.invokeThrowingFn(obj);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        };
    }

    public static <T> Consumer<T> wrapEx(final ExceptionThrowingVoidFn<T> fn) {
        return (T obj) -> {
            try {
                fn.invokeThrowingFn(obj);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        };
    }

    public static <T> Predicate<T> wrapEx(final ExceptionThrowingPredicateFn<T> fn) {
        return (T obj) -> {
            try {
                return fn.invokeThrowingFn(obj);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        };
    }
}
