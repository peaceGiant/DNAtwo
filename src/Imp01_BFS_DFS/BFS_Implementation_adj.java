package Imp01_BFS_DFS;
import java.util.*;

public class BFS_Implementation_adj {

    static class Graph {

        private int V;
        private LinkedList<Integer>[] adj;

        Graph(int v) {
            V = v;
            adj = new LinkedList[v];
            for (int i = 0; i < v; ++i) {
                adj[i] = new LinkedList<>();
            }
        }

        void addEdge(int v, int w) {
            adj[v].add(w);
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
                for (Integer neighbor : adj[source]) {
                    int n = neighbor;
                    if (!visited[n]) {
                        visited[n] = true;
                        queue.add(n);
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
