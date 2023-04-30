package Imp02_FlowGraph;

import java.util.Arrays;
import java.util.LinkedList;


public class FordFulkerson_MaxFlow {

    static class MaxFlow {

        private int V;
        private int[][] graph;
        private int[][] residual;

        MaxFlow(int v, int[][] g) {
            V = v;
            graph = new int[v][v];
            residual = new int[v][v];
            for (int i = 0; i < v; ++i) {
                for (int j = 0; j < v; ++j) {
                    graph[i][j] = residual[i][j] = g[i][j];
                }
            }
        }

        boolean BFS(int source, int sink, int[] parent) {
            boolean[] visited = new boolean[V];
            Arrays.fill(visited, false);

            LinkedList<Integer> queue = new LinkedList<Integer>();

            visited[source] = true;
            parent[source] = -1;
            queue.add(source);

            while (queue.size() > 0) {
                source = queue.poll();

                for (int i = 0; i < V; ++i) {
                    if (!visited[i] && residual[source][i] > 0) {
                        if (i == sink) {
                            parent[i] = source;
                            return true;
                        }
                        visited[i] = true;
                        parent[i] = source;
                        queue.add(i);
                    }
                }
            }

            return false;
        }

        int fordFulkerson(int source, int sink) {
            int[] parent = new int[V];
            int maxFlow = 0;

            while (BFS(source, sink, parent)) {
                int pathFlow = Integer.MAX_VALUE;

                for (int i = sink; i != source; i = parent[i]) {
                    pathFlow = Math.min(pathFlow, residual[parent[i]][i]);
                }

                for (int i = sink; i != source; i = parent[i]) {
                    residual[parent[i]][i] -= pathFlow;
                    residual[i][parent[i]] += pathFlow;
                }

                maxFlow += pathFlow;
            }

            return maxFlow;
        }
    }

    public static void main(String[] args) {

        int v = 6;
        int[][] graph = new int[][] {
                { 0, 16, 13, 0, 0, 0 }, {0, 0, 10, 12, 0, 0 },
                { 0, 4, 0, 0, 14, 0 },  { 0, 0, 9, 0, 0, 20 },
                { 0, 0, 0, 7, 0, 4 },   { 0, 0, 0, 0, 0, 0 }
        };

        MaxFlow env = new MaxFlow(v, graph);

        System.out.println(env.fordFulkerson(0, 5));
    }

}
