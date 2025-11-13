package com.mst;

import java.util.Objects;

/**
 * Represents an edge in the graph with source, destination, and weight.
 */
public class Edge implements Comparable<Edge> {
    private final int source;
    private final int destination;
    private final int weight;

    public Edge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public int getSource() {
        return source;
    }

    public int getDestination() {
        return destination;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Edge other) {
        return Integer.compare(this.weight, other.weight);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return (source == edge.source && destination == edge.destination) ||
               (source == edge.destination && destination == edge.source);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Math.min(source, destination), Math.max(source, destination));
    }

    @Override
    public String toString() {
        return String.format("(%d-%d, weight: %d)", source, destination, weight);
    }
}
