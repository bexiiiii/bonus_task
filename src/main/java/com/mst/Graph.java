package com.mst;

import java.util.*;

/**
 * Represents a graph and provides MST operations including edge removal and replacement.
 */
public class Graph {
    private final int vertices;
    private final List<Edge> edges;

    public Graph(int vertices) {
        this.vertices = vertices;
        this.edges = new ArrayList<>();
    }

    /**
     * Add an edge to the graph.
     */
    public void addEdge(int source, int destination, int weight) {
        edges.add(new Edge(source, destination, weight));
    }

    /**
     * Build the Minimum Spanning Tree using Kruskal's algorithm.
     * @return List of edges in the MST
     */
    public List<Edge> buildMST() {
        List<Edge> mst = new ArrayList<>();
        Collections.sort(edges); // Sort edges by weight

        UnionFind uf = new UnionFind(vertices);

        for (Edge edge : edges) {
            if (uf.union(edge.getSource(), edge.getDestination())) {
                mst.add(edge);
                if (mst.size() == vertices - 1) {
                    break; // MST is complete
                }
            }
        }

        return mst;
    }

    /**
     * Find connected components after removing an edge from MST.
     * @param mst The current MST
     * @param removedEdge The edge to remove
     * @return UnionFind structure representing the two components
     */
    public UnionFind findComponentsAfterRemoval(List<Edge> mst, Edge removedEdge) {
        UnionFind uf = new UnionFind(vertices);

        for (Edge edge : mst) {
            if (!edge.equals(removedEdge)) {
                uf.union(edge.getSource(), edge.getDestination());
            }
        }

        return uf;
    }

    /**
     * Find the replacement edge with minimum weight that reconnects the two components.
     * @param uf UnionFind structure representing the components
     * @param removedEdge The edge that was removed
     * @return The best replacement edge, or null if none exists
     */
    public Edge findReplacementEdge(UnionFind uf, Edge removedEdge) {
        Edge replacementEdge = null;

        for (Edge edge : edges) {
            // Skip the removed edge
            if (edge.equals(removedEdge)) {
                continue;
            }

            // Check if this edge connects the two components
            if (!uf.connected(edge.getSource(), edge.getDestination())) {
                if (replacementEdge == null || edge.getWeight() < replacementEdge.getWeight()) {
                    replacementEdge = edge;
                }
            }
        }

        return replacementEdge;
    }

    /**
     * Calculate the total weight of edges in the MST.
     */
    public int calculateMSTWeight(List<Edge> mst) {
        int totalWeight = 0;
        for (Edge edge : mst) {
            totalWeight += edge.getWeight();
        }
        return totalWeight;
    }

    /**
     * Display the components after edge removal.
     */
    public void displayComponents(UnionFind uf) {
        Map<Integer, List<Integer>> components = new HashMap<>();

        for (int i = 0; i < vertices; i++) {
            int root = uf.find(i);
            components.computeIfAbsent(root, k -> new ArrayList<>()).add(i);
        }

        System.out.println("\nComponents after edge removal:");
        int componentNum = 1;
        for (List<Integer> component : components.values()) {
            System.out.print("Component " + componentNum + ": ");
            System.out.println(component);
            componentNum++;
        }
    }

    /**
     * Display a list of edges.
     */
    public void displayEdges(List<Edge> edges, String title) {
        System.out.println("\n" + title);
        System.out.println("=".repeat(50));
        for (Edge edge : edges) {
            System.out.println(edge);
        }
        System.out.println("Total weight: " + calculateMSTWeight(edges));
    }

    public int getVertices() {
        return vertices;
    }

    public List<Edge> getEdges() {
        return edges;
    }
}
