package Graph;

import GenericsAndCOllection.Array;

import java.util.Arrays;

class Graph2 {
    int matrix[][];

    public Graph2(int n) {       //number of vertices=n
        matrix = new int[n][n];
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                result += matrix[i][j] + " ";
            }
            result += "\n";
        }
        return result;
    }

    void addedge(Edge a) {
        matrix[a.v][a.w] = 1;
        matrix[a.w][a.v] = 1;       //for undirected
    }

    void add_weighted_edge(Weighted_edge a){
        matrix[a.v][a.w] = a.value;
        //matrix[a.w][a.v] = a.value;       //for undirected
    }

    void remove_edge(Edge a){
        matrix[a.v][a.w] = 0;
        matrix[a.w][a.v] = 0;       //for undirected
    }

    boolean isConnected(int u, int v) {
        if (matrix[u][v] !=0) return true;
        return false;
    }

    int get_weight(int u, int v){
        return matrix[u][v];
    }

    boolean isCyclefordirected() {
        int visited[] = new int[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if(matrix[i][j]==1){
                    if(visited[j]==1) return true;
                    visited[j]=1;
                }
            }
        }
        return false;
    }
    boolean isCycleforUndirected(){
        int visited[] = new int[matrix.length];
        int parent[] =new int[matrix.length];
        Arrays.fill(parent,-1);

        for (int i = 0; i < matrix.length; i++) {
            visited[i]=1;
            for (int j = 0; j < matrix[i].length; j++) {
                if(isConnected(i,j)){
                    if(parent[j]==-1) parent[j]=i;
                    else if(visited[j]==1 && parent[i]!=j) return true;
                }
            }
        }
        return false;
    }

}
public class Adj_Matrix {
    public static void main(String[] args) {
        Graph2 G= new Graph2(5);
        Edge a=new Edge(0,1);
        Edge b=new Edge(0,2);
        Edge c=new Edge(1,3);
        Edge d=new Edge(1,4);
        Edge e=new Edge(2,3);

        G.addedge(a);
        G.addedge(b);
        G.addedge(c);
        G.addedge(d);
        //G.addedge(e);

        System.out.println(G);
        //System.out.println(G.isConnected(3,4));
        System.out.println(G.isCycleforUndirected());

    }
}
