package Prep01_BFS_DFS;

import java.util.*;


public class Surrounded_Regions {

    static class Solution {

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

            ArrayList<Integer> BFS(int source) {
                boolean[] visited = new boolean[V];
                Arrays.fill(visited, false);

                LinkedList<Integer> queue = new LinkedList<Integer>();
                ArrayList<Integer> connected = new ArrayList<Integer>();

                visited[source] = true;
                queue.add(source);
                connected.add(source);

                while (queue.size() > 0) {
                    source = queue.poll();
                    // System.out.println(source);
                    for (int i = 0; i < V; ++i) {
                        if (!visited[i] && adj[source][i] == 1) {
                            visited[i] = true;
                            queue.add(i);
                            connected.add(i);
                        }
                    }
                }

                return connected;
            }

        }

        public void solve(char[][] board) {

            Graph graph = new Graph(board.length * board[0].length);

            for (int i = 0; i < board.length; ++i) {
                for (int j = 0; j < board[0].length; ++j) {
                    if (board[i][j] == 'X') continue;
                    // Current indices: i, j
                    // Above: i-1, j    |   i-1 >= 0
                    // Below: i+1, j    |   i+1 <= board.length
                    // Left: i, j-1     |   j-1 >= 0
                    // Right: i, j+1    |   j+1 <= board[0].length
                    if (i - 1 >= 0 && board[i - 1][j] == 'O') {
                        graph.addEdge(j + board[0].length * i, j + board[0].length * (i - 1));
                        graph.addEdge(j + board[0].length * (i - 1), j + board[0].length * i);
                    }
                    if (i + 1 < board.length && board[i + 1][j] == 'O') {
                        graph.addEdge(j + board[0].length * i, j + board[0].length * (i + 1));
                        graph.addEdge(j + board[0].length * (i + 1), j + board[0].length * i);
                    }
                    if (j - 1 >= 0 && board[i][j - 1] == 'O') {
                        graph.addEdge(j + board[0].length * i, j + board[0].length * i - 1);
                        graph.addEdge(j + board[0].length * i - 1, j + board[0].length * i);
                    }
                    if (j + 1 < board[0].length && board[i][j + 1] == 'O') {
                        graph.addEdge(j + board[0].length * i, j + board[0].length * i + 1);
                        graph.addEdge(j + board[0].length * i + 1, j + board[0].length * i);
                    }
                }
            }

            int[][] tiles_passed_by_bfs = new int[board.length][board[0].length];
            for (int i = 0; i < board.length; ++i) {
                for (int j = 0; j < board[0].length; ++j) {
                    tiles_passed_by_bfs[i][j] = 0;
                }
            }

            for (int i = 0; i < board.length; ++i) {
                for (int j = 0; j < board[0].length; ++j) {
                    if (tiles_passed_by_bfs[i][j] == 1) continue;
                    ArrayList<Integer> bfs_values = graph.BFS(j + i * board[0].length);
                    boolean should_flip = true;
                    for (int x : bfs_values) {
                        if (x / board[0].length == 0 || x / board[0].length == board.length - 1) {
                            should_flip = false;
                        }
                        if (x % board[0].length == 0 || x % board[0].length == board[0].length - 1) {
                            should_flip = false;
                        }
                        tiles_passed_by_bfs[x / board[0].length][x % board[0].length] = 1;
                    }
                    if (!should_flip) continue;
                    for (int x : bfs_values) {
                        board[x / board[0].length][x % board[0].length] = 'X';
                    }
                }
            }

        }

    }


    public static void main(String[] args) {
        char[][] board = new char[][]{{'X', 'X', 'X'}, {'X', 'O', 'O'}, {'X', 'X', 'X'}};

        Solution solution = new Solution();
        solution.solve(board);
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
