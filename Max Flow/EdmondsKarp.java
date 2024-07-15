package Graph;

import java.util.ArrayDeque;
import java.util.Queue;

public class EdmondsKarp {

    private long[][] flow; //max flow beetween i and j verticles
    private long[][] capacity; // edge capacity
    private int[] parent; //parent
    private boolean[] visited; //just for checking if visited
    private int n, m;

    public EdmondsKarp(int numOfVerticles, int numOfEdges) {
        this.n = numOfVerticles;
        this.m = numOfEdges;
        this.flow = new long[n][n];
        this.capacity = new long[n][n];
        this.parent = new int[n];
        this.visited = new boolean[n];
    }

    public void addEdge(int from, int to, long capacity) {
        this.capacity[from][to] += capacity;
    }


    public long getMaxFlow(int s, int t) {
        while (true) {
            final Queue<Integer> Q = new ArrayDeque<>();
            Q.add(s);

            for (int i = 0; i < this.n; ++i)
                visited[i] = false;
            visited[s] = true;

            boolean check = false;
            int current;
            while (!Q.isEmpty()) {
                current = Q.peek();
                if (current == t) {
                    check = true;               //checks if the path reaches to sink
                    break;
                }
                Q.remove();
                for (int i = 0; i < n; ++i) {
                    if (!visited[i] && capacity[current][i] > flow[current][i]) {       //after 1 bfs flow value changes, so in next loop another path appears
                        visited[i] = true;
                        Q.add(i);
                        parent[i] = current;
                    }
                }
            }
            if (check == false)
                break;

            long temp = capacity[parent[t]][t] - flow[parent[t]][t];
            for (int i = t; i != s; i = parent[i]){
                temp = Math.min(temp, (capacity[parent[i]][i] - flow[parent[i]][i]));   //Bottleneck value
            }
            //System.out.println("bottleneck value "+temp);
            for (int i = t; i != s; i = parent[i]) {
                flow[parent[i]][i] += temp;                         //adding bottleneck value to forward edge
                //System.out.println("from "+parent[i]+" "+i+" "+flow[parent[i]][i]);
                flow[i][parent[i]] -= temp;                         //subtracting bottleneck from  backward edge
                //System.out.println("from "+i+" "+parent[i]+" "+flow[i][parent[i]]);
            }
        }

        long result = 0;
        for (int i = 0; i < n; ++i)
            result += flow[s][i];                   //sums how much flow is getting out from source
        return result;
    }

    public static void main(String[] args) {
        EdmondsKarp g=new EdmondsKarp(8,9);
//        ed.addEdge(0,1,7);
//        ed.addEdge(0,2,10);
//        ed.addEdge(1,2,5);
//        ed.addEdge(1,3,11);
//        ed.addEdge(2,3,3);
//        ed.addEdge(0,1,7);
//        ed.addEdge(0,2,10);
//        ed.addEdge(1,2,1);
//        ed.addEdge(1,3,3);
//        ed.addEdge(2,3,2);
//        ed.addEdge(1,4,5);
//        ed.addEdge(3,4,3);
//        ed.addEdge(3,5,2);
//        ed.addEdge(4,6,10);
//        ed.addEdge(5,6,4);
        g.addEdge(0,1,1);
        g.addEdge(1,2,1);
        g.addEdge(2,4,1);
        g.addEdge(0,3,1);
        g.addEdge(3,4,1);
        g.addEdge(4,7,1);
        g.addEdge(3,5,1);
        g.addEdge(5,6,1);
        g.addEdge(6,7,1);

        System.out.println(g.getMaxFlow(0,7));
    }
}

