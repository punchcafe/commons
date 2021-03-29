package dev.punchcafe.commons.bus;

public interface Subscriber<T> {

    void accept(T message);
}
