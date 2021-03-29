package dev.punchcafe.commons.bus;

public interface Bus<T> {

    void publish(T message);
}
