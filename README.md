# MST Edge Removal and Replacement

![Java](https://img.shields.io/badge/Java-11%2B-blue)
![Maven](https://img.shields.io/badge/Maven-3.6%2B-red)

## Project Description

This project demonstrates an efficient algorithm for handling edge removal from a Minimum Spanning Tree (MST) and finding a replacement edge to maintain the MST property. The implementation uses **Kruskal's algorithm** with **Union-Find** data structure for optimal performance.

### Key Features:
-  Build MST using Kruskal's algorithm
-  Remove an edge from the MST
-  Identify resulting components after edge removal
-  Find the minimum weight replacement edge to reconnect components
-  Display the complete process with clear output

## Algorithm Overview

### 1. MST Construction
- Uses **Kruskal's algorithm** with Union-Find (Disjoint Set Union)
- Time complexity: O(E log E) where E is the number of edges
- Sorts edges by weight and greedily adds edges that don't form cycles

### 2. Edge Removal
- Removes a specified edge from the MST
- Creates two disconnected components

### 3. Component Detection
- Uses Union-Find to identify vertices in each component
- Time complexity: O(V) where V is the number of vertices

### 4. Replacement Edge Finding
- Scans all edges to find the minimum weight edge connecting the two components
- Time complexity: O(E)
- Ensures the graph remains a valid MST

## Prerequisites

Before running this project, ensure you have:

- **Java Development Kit (JDK)** 11 or higher
- **Apache Maven** 3.6 or higher

### Check if installed:
```bash
java -version
mvn -version
```

### Installation (if needed):

**macOS:**
```bash
# Install using Homebrew
brew install openjdk@11
brew install maven
```

**Linux (Ubuntu/Debian):**
```bash
sudo apt update
sudo apt install openjdk-11-jdk maven
```

**Windows:**
- Download JDK from [Oracle](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://adoptium.net/)
- Download Maven from [Apache Maven](https://maven.apache.org/download.cgi)

## How to Run

### Method 1: Clone and Run (Recommended)

```bash
# Clone the repository
git clone <your-repository-url>
cd bonus\ task

# Compile the project
mvn clean compile

# Run the program
mvn exec:java
```

### Method 2: Build and Run JAR

```bash
# Build the project
mvn clean package

# Run the JAR file
java -cp target/mst-edge-removal-1.0.0.jar com.mst.Main
```

### Method 3: Direct Java Execution

```bash
# Compile manually
javac -d target/classes src/main/java/com/mst/*.java

# Run the main class
java -cp target/classes com.mst.Main
```

## Project Structure

```
bonus task/
├── pom.xml                          # Maven configuration file
├── README.md                        # This file
├── .gitignore                       # Git ignore file
└── src/
    └── main/
        └── java/
            └── com/
                └── mst/
                    ├── Main.java        # Main program demonstrating MST operations
                    ├── Graph.java       # Graph class with MST methods
                    ├── Edge.java        # Edge representation
                    └── UnionFind.java   # Union-Find data structure
```

## Example Output

When you run the program, you'll see:

```
═══════════════════════════════════════════════════════════
  MST Edge Removal and Replacement Demonstration
═══════════════════════════════════════════════════════════

Graph Information:
- Vertices: 6
- Edges: 9

────────────────────────────────────────────────────────────
STEP 1: Building Minimum Spanning Tree (MST)
────────────────────────────────────────────────────────────

Original MST Edges:
==================================================
(0-1, weight: 1)
(1-2, weight: 2)
(4-5, weight: 3)
(1-3, weight: 4)
(0-3, weight: 5)
Total weight: 15

────────────────────────────────────────────────────────────
STEP 2: Removing an Edge from MST
────────────────────────────────────────────────────────────

Removing edge: (4-5, weight: 3)

────────────────────────────────────────────────────────────
STEP 3: Analyzing Components After Removal
────────────────────────────────────────────────────────────

Components after edge removal:
Component 1: [0, 1, 2, 3, 4]
Component 2: [5]

────────────────────────────────────────────────────────────
STEP 4: Finding Replacement Edge
────────────────────────────────────────────────────────────

Replacement edge found: (2-4, weight: 6)
This edge reconnects the two components.

────────────────────────────────────────────────────────────
STEP 5: New MST After Replacement
────────────────────────────────────────────────────────────

New MST Edges:
==================================================
(0-1, weight: 1)
(1-2, weight: 2)
(1-3, weight: 4)
(0-3, weight: 5)
(2-4, weight: 6)
Total weight: 18

────────────────────────────────────────────────────────────
COMPARISON
────────────────────────────────────────────────────────────
Original MST weight: 15
New MST weight:      18
Edge removed:        (4-5, weight: 3)
Edge added:          (2-4, weight: 6)

═══════════════════════════════════════════════════════════
  Demonstration Complete
═══════════════════════════════════════════════════════════
```

## Implementation Details

### Classes Description

#### `Edge.java`
- Represents an edge with source, destination, and weight
- Implements `Comparable` for sorting by weight
- Provides equality checking for edge comparison

#### `UnionFind.java`
- Implements Disjoint Set Union (DSU) data structure
- Uses **path compression** for efficient find operations
- Uses **union by rank** for balanced tree structure
- Time complexity: nearly O(1) amortized for both union and find

#### `Graph.java`
- Main graph class containing all MST operations
- **`buildMST()`**: Constructs MST using Kruskal's algorithm
- **`findComponentsAfterRemoval()`**: Identifies components after edge removal
- **`findReplacementEdge()`**: Finds minimum weight edge to reconnect components
- **`displayComponents()`**: Visualizes components
- **`displayEdges()`**: Pretty prints edges with total weight

#### `Main.java`
- Demonstrates the complete workflow
- Creates a sample graph with 6 vertices and 9 edges
- Shows all steps with clear formatting

## Testing with Different Graphs

You can modify the graph in `Main.java` to test different scenarios:

```java
Graph graph = new Graph(numberOfVertices);
graph.addEdge(source, destination, weight);
```

## Time Complexity Analysis

- **MST Construction**: O(E log E) - sorting edges
- **Edge Removal**: O(1) - constant time
- **Component Detection**: O(V) - iterate through vertices
- **Replacement Edge Finding**: O(E) - scan all edges
- **Overall**: O(E log E) - dominated by MST construction

## Space Complexity

- O(V + E) - storing graph structure
- O(V) - Union-Find data structure

## Author

Bonus Task Implementation - MST Edge Removal

## License

This project is created for educational purposes.

---

**Note**: This project is designed to be easily cloned and run. Make sure to have Java 11+ and Maven installed before running.
