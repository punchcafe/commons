package dev.punchcafe.commons.dependency.graph;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.*;

public class DependencyGraph<T> {

    private final Map<T, Vertex<T>> vertexMap;

    private DependencyGraph(final Map<T, Collection<T>> entityToDependants) {
        Map<T, Vertex<T>> typeToVertex = entityToDependants.keySet()
                .stream()
                .collect(toConcurrentMap(identity(), Vertex::new));
        // Populate entry maps
        for (Map.Entry<T, Vertex<T>> entry : typeToVertex.entrySet()) {
            entityToDependants.get(entry.getKey())
                    .stream()
                    .map(typeToVertex::get)
                    .forEach(vertex -> Edge.createBetween(entry.getValue(), vertex));
        }
        this.vertexMap = typeToVertex;
    }

    public static <T> DependencyGraph<T> buildFrom(final Function<T, Collection<T>> dependantsRetriever,
                                                   final Collection<T> entities) {
        final var arg = entities.stream()
                .collect(toUnmodifiableMap(identity(), dependantsRetriever));

        return new DependencyGraph<>(arg);

    }

}
