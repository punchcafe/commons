package dev.punchcafe.commons.dependency.graph;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Vertex<T> {

    private final T element;
    private final List<Edge<T>> edges;

    public Vertex(final T element){
        this.element = element;
        this.edges = new ArrayList<>();
    }

    public List<Vertex<T>> getDependants(){
        return edges.stream()
                .filter(edge -> edge.getDependee() == this)
                .map(Edge::getDependant)
                .collect(toList());
    }

    public T getElement() {
        return element;
    }

    public void addEdge(final Edge<T> edge){
        this.edges.add(edge);
    }

}
