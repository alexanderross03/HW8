/******************************************************************
 *
 *   Alexander Ross / 001
 *
 *   This java file contains solutions for canFinish and numGroups methods.
 *
 ********************************************************************/

import java.util.*;

public class ProblemSolutions {

    /**
     * Returns true if you can finish all certification exams (i.e., the directed
     * prerequisite graph has no cycle); otherwise, false.
     */
    public boolean canFinish(int numExams, int[][] prerequisites) {
        // Build directed adjacency list: edge ai -> bi
        List<Integer>[] adj = new ArrayList[numExams];
        for (int i = 0; i < numExams; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] p : prerequisites) {
            int ai = p[0], bi = p[1];
            adj[ai].add(bi);
        }

        // 0 = unvisited, 1 = visiting, 2 = visited
        int[] state = new int[numExams];
        for (int i = 0; i < numExams; i++) {
            if (state[i] == 0 && hasCycle(i, adj, state)) {
                return false;
            }
        }
        return true;
    }

    // DFS-based cycle detection
    private boolean hasCycle(int u, List<Integer>[] adj, int[] state) {
        state[u] = 1; // mark visiting
        for (int v : adj[u]) {
            if (state[v] == 1 || (state[v] == 0 && hasCycle(v, adj, state))) {
                return true;
            }
        }
        state[u] = 2; // mark visited
        return false;
    }

    /**
     * Returns the number of connected groups (components) in an undirected
     * graph given by its adjacency matrix.
     */
    public int numGroups(int[][] adjMatrix) {
        int n = adjMatrix.length;
        boolean[] visited = new boolean[n];
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i, adjMatrix, visited);
                count++;
            }
        }
        return count;
    }

    // DFS on the adjacency matrix, treating edges as undirected
    private void dfs(int u, int[][] adjMatrix, boolean[] visited) {
        visited[u] = true;
        for (int v = 0; v < adjMatrix.length; v++) {
            if ((adjMatrix[u][v] == 1 || adjMatrix[v][u] == 1) && !visited[v]) {
                dfs(v, adjMatrix, visited);
            }
        }
    }
}
