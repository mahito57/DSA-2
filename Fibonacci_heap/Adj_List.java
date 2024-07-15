package Graph;


import java.util.LinkedList;
import java.util.List;

class Edge{
    int v,w;
    public Edge(int v,int w){
        this.v=v;
        this.w=w;
    }
    @Override
    public String toString(){
        return "("+v+","+w+")";
    }
}
class Weighted_edge{
    int value,v,w;
    public Weighted_edge(int value, int v, int w){
        this.v=v;
        this.w=w;
        this.value=value;
    }
    @Override
    public String toString(){
       // return v+"   "+w+"   "+value;
        return "("+v+","+w+")";
    }
}

class Graph1{
    List<Integer> G[];
    public Graph1(int n){        //Number of vertex=n
        G=new LinkedList[n];
        for(int i=0;i<n;i++){
            G[i]=new LinkedList<>();
        }
    }
    @Override
    public String toString(){
        String result="";
        for(int i=0;i<G.length;i++){
            result+=i+" ==> "+G[i]+"\n";
        }
        return result;
    }
    void addedge(Edge a){       //for directed only the 1st line
        G[a.v].add(a.w);
        G[a.w].add(a.v);
    }

    boolean isConnected(int u,int v){
        for(int i=0;i<G[u].size();i++){
            if(G[u].get(i)==v){
                return true;
            }
        }
        return false;
    }
}

public class Adj_List {
    public static void main(String[] args) {
        Graph1 G= new Graph1(10);
        Edge a=new Edge(1,5);
        Edge b=new Edge(3,5);
        Edge c=new Edge(6,2);
        Edge d=new Edge(2,7);

        G.addedge(a);
        G.addedge(b);
        G.addedge(c);
        G.addedge(d);

        System.out.println(G);

        System.out.println(G.isConnected(5,2));
    }
}
