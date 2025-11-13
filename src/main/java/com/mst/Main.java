package com.mst;

import java.util.List;

/**
 * Main class demonstrating MST construction, edge removal, and replacement.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("  MST Edge Removal and Replacement Demonstration");
        System.out.println("═══════════════════════════════════════════════════════════");

        // Create a graph with 6 vertices
        // Example graph:
        //       1
        //   0------1
        //   |   4  |\
        //  5|     / | \2
        //   |  7 /  |  \
        //   |   /   |3  2
        //   |  /    |  / \
        //   | /     | /6  \8
        //   3-------4------5
        //       9
        
        Graph graph = new Graph(6);
        
        // Add edges (source, destination, weight)
        graph.addEdge(0, 1, 1);
        graph.addEdge(0, 3, 5);
        graph.addEdge(1, 2, 2);
        graph.addEdge(1, 3, 4);
        graph.addEdge(1, 4, 7);
        graph.addEdge(2, 4, 6);
        graph.addEdge(2, 5, 8);
        graph.addEdge(3, 4, 9);
        graph.addEdge(4, 5, 3);

        System.out.println("\nGraph Information:");
        System.out.println("- Vertices: " + graph.getVertices());
        System.out.println("- Edges: " + graph.getEdges().size());

        // Step 1: Build the MST
        System.out.println("\n\n" + "─".repeat(60));
        System.out.println("STEP 1: Building Minimum Spanning Tree (MST)");
        System.out.println("─".repeat(60));
        List<Edge> mst = graph.buildMST();
        graph.displayEdges(mst, "Original MST Edges:");

        if (mst.isEmpty()) {
            System.out.println("Error: Could not build MST. Graph might be disconnected.");
            return;
        }

        // Step 2: Select and remove an edge from the MST
        System.out.println("\n\n" + "─".repeat(60));
        System.out.println("STEP 2: Removing an Edge from MST");
        System.out.println("─".repeat(60));
        
        // Remove the edge with highest weight or middle edge for demonstration
        Edge edgeToRemove = mst.get(mst.size() / 2);
        System.out.println("\nRemoving edge: " + edgeToRemove);

        // Step 3: Find components after removal
        System.out.println("\n\n" + "─".repeat(60));
        System.out.println("STEP 3: Analyzing Components After Removal");
        System.out.println("─".repeat(60));
        UnionFind componentsUF = graph.findComponentsAfterRemoval(mst, edgeToRemove);
        graph.displayComponents(componentsUF);

        // Step 4: Find replacement edge
        System.out.println("\n\n" + "─".repeat(60));
        System.out.println("STEP 4: Finding Replacement Edge");
        System.out.println("─".repeat(60));
        Edge replacementEdge = graph.findReplacementEdge(componentsUF, edgeToRemove);

        if (replacementEdge != null) {
            System.out.println("\nReplacement edge found: " + replacementEdge);
            System.out.println("This edge reconnects the two components.");

            // Step 5: Display the new MST
            System.out.println("\n\n" + "─".repeat(60));
            System.out.println("STEP 5: New MST After Replacement");
            System.out.println("─".repeat(60));
            
            // Create new MST with replacement edge
            List<Edge> newMST = new java.util.ArrayList<>(mst);
            newMST.remove(edgeToRemove);
            newMST.add(replacementEdge);
            
            graph.displayEdges(newMST, "New MST Edges:");

            // Show comparison
            System.out.println("\n\n" + "─".repeat(60));
            System.out.println("COMPARISON");
            System.out.println("─".repeat(60));
            System.out.println("Original MST weight: " + graph.calculateMSTWeight(mst));
            System.out.println("New MST weight:      " + graph.calculateMSTWeight(newMST));
            System.out.println("Edge removed:        " + edgeToRemove);
            System.out.println("Edge added:          " + replacementEdge);
            
        } else {
            System.out.println("\nNo replacement edge found!");
            System.out.println("The graph becomes disconnected after removing this edge.");
        }

        System.out.println("\n\n═══════════════════════════════════════════════════════════");
        System.out.println("  Demonstration Complete");
        System.out.println("═══════════════════════════════════════════════════════════\n");
    }
}
