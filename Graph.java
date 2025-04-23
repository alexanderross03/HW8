/******************************************************************
 *
 *   Alexander Ross / 001
 *
 *   Note, additional comments provided throughout this source code
 *   is for educational purposes
 *
 ********************************************************************/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *  Graph traversal exercise
 *
 *  The Graph class represents a directed graph of vertices (nodes)
 *  and edges, stored in an adjacency list.
 */
public class Graph {
  int numVertices;                  // number of vertices in graph
  LinkedList<Integer>[] adjListArr; // adjacency list
  List<Integer> vertexValues;       // vertex values

  // Constructor
  @SuppressWarnings("unchecked")
  public Graph(int numV) {
    numVertices = numV;
    adjListArr = new LinkedList[numVertices];
    vertexValues = new ArrayList<>(numVertices);
    for (int i = 0; i < numVertices; i++) {
      adjListArr[i] = new LinkedList<>();
      vertexValues.add(0);
    }
  }

  /**
   * Sets a vertex's value.
   */
  public void setValue(int vertexIndex, int value) {
    if (vertexIndex >= 0 && vertexIndex < numVertices) {
      vertexValues.set(vertexIndex, value);
    } else {
      throw new IllegalArgumentException("Invalid vertex index: " + vertexIndex);
    }
  }

  /**
   * Adds a directed edge from src to dest.
   */
  public void addEdge(int src, int dest) {
    adjListArr[src].add(dest);
  }

  /**
   * Prints the graph as an adjacency matrix.
   */
  public void printGraph() {
    System.out.println("\nAdjacency Matrix Representation:\n");
    int[][] matrix = new int[numVertices][numVertices];
    for (int i = 0; i < numVertices; i++) {
      for (Integer dest : adjListArr[i]) {
        matrix[i][dest] = 1;
      }
    }
    System.out.print("  ");
    for (int i = 0; i < numVertices; i++) System.out.print(i + " ");
    System.out.println();
    for (int i = 0; i < numVertices; i++) {
      System.out.print(i + " ");
      for (int j = 0; j < numVertices; j++) {
        System.out.print(matrix[i][j] == 1 ? "| " : ". ");
      }
      System.out.println();
    }
  }

  /**
   * Returns the value of the root vertex (node with no incoming edges).
   * If there is no unique root, returns -1.
   */
  public int findRoot() {
    // Compute in-degrees
    int[] inDegree = new int[numVertices];
    for (int src = 0; src < numVertices; src++) {
      for (int dest : adjListArr[src]) {
        inDegree[dest]++;
      }
    }
    // Identify vertices with in-degree == 0
    int rootIndex = -1;
    int count = 0;
    for (int i = 0; i < numVertices; i++) {
      if (inDegree[i] == 0) {
        rootIndex = i;
        count++;
      }
    }
    // Return value if exactly one root, otherwise -1
    return (count == 1) ? vertexValues.get(rootIndex) : -1;
  }
}
