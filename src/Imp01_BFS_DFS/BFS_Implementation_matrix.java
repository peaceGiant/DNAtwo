package Imp01_BFS_DFS;

import java.util.*;


public class BFS_Implementation_matrix {

    static class Graph {

        private int V;
        private int[][] adj;

        Graph(int v) {
            V = v;
            adj = new int[v][v];
            for (int[] rows : adj) {
                Arrays.fill(rows, 0);
            }
        }

        void addEdge(int v, int w) {
            adj[v][w] = 1;
        }

        void BFS(int source) {
            boolean[] visited = new boolean[V];
            Arrays.fill(visited, false);

            LinkedList<Integer> queue = new LinkedList<Integer>();

            visited[source] = true;
            queue.add(source);

            while (queue.size() > 0) {
                source = queue.poll();
                System.out.println(source);
                for (int i = 0; i < V; ++i) {
                    if (!visited[i] && adj[source][i] == 1) {
                        visited[i] = true;
                        queue.add(i);
                    }
                }
            }
        }

    }


    public static void main(String[] args) {

        Graph env = new Graph(4);
        env.addEdge(0, 1);
        env.addEdge(0, 2);
        env.addEdge(1, 2);
        env.addEdge(2, 0);
        env.addEdge(2, 3);
        env.addEdge(3, 3);

        env.BFS(2);
    }

}
