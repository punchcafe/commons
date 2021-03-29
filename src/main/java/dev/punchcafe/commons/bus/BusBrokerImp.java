package dev.punchcafe.commons.bus;

import java.util.Map;
import java.util.Optional;

class BusBrokerImp implements BusBroker {
    private Map<Class, Map<String, Bus<?>>> buses;

    @Override
    public <T> Optional<Bus<T>> getBus(Class<T> messageType, String topic) {
        final var classBusses = buses.get(messageType);
        if(classBusses == null){
            return Optional.empty();
        }
        final var bus = classBusses.get(topic);
        return Optional.of((Bus<T>) bus);
    }
}
