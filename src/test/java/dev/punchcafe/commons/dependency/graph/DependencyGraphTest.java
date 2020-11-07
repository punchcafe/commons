package dev.punchcafe.commons.dependency.graph;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

public class DependencyGraphTest {

    public static class ExampleClass {
        public ExampleClass() {
        }

        ;
    }

    public static class ExampleParentClass {
        public ExampleParentClass(ExampleClass exampleClass) {
        }

        ;
    }

    public static class ExampleGrandParentClass {
        public ExampleGrandParentClass(ExampleParentClass exampleParentClass) {
        }
    }

    @Test
    void canThreadBasedOnConstructorDependencies() {
        // TODO: fix so dependant/dependee is consistent
        final Function<Class, Collection<Class>> dependencyRetriever = (Class clazz) -> List.of(clazz.getConstructors()[0].getParameterTypes());
        final var dependencyGraph = DependencyGraph.buildFrom(dependencyRetriever, List.of(ExampleClass.class, ExampleParentClass.class, ExampleGrandParentClass.class));
        System.out.println("lol");
    }

    @Test
    void throwsUnsupportedOperationExceptionIfDependenciesWhichDontExist() {
        final Function<Class, Collection<Class>> dependencyRetriever = (Class clazz) -> List.of(clazz.getConstructors()[0].getParameterTypes());
        final var dependencyGraph = DependencyGraph.buildFrom(dependencyRetriever, List.of(ExampleClass.class, ExampleParentClass.class, ExampleGrandParentClass.class));
    }
}
