package dev.punchcafe.commons.dependency.graph;

import jdk.jfr.Experimental;

@Experimental
public class Edge<T> {

    public static <T> void createBetween(final Vertex<T> dependant, final Vertex<T> dependee){
        final Edge<T> edge = new Edge<>(dependant, dependee);
        dependant.addEdge(edge);
        dependee.addEdge(edge);
    }

    private final Vertex<T> dependant;
    private final Vertex<T> dependee;

    private Edge(final Vertex<T> dependant, final Vertex<T> dependee){
        this.dependant = dependant;
        this.dependee = dependee;
    }

    public Vertex<T> getDependant(){
        return this.dependant;
    }

    public Vertex<T> getDependee() {
        return dependee;
    }
}
