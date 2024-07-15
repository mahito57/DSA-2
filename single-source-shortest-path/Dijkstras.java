package Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Dijkstras {
    void dikstra(Graph2 g, int src,int dest){
        int len=g.matrix.length;
        int dist[]=new int[len];
        Boolean spset[]= new Boolean[len];
        int previous[]= new int[len];

        for(int i=0;i<len;i++){
            dist[i]=Integer.MAX_VALUE;
            spset[i]=false;
        }

        dist[src]=0;
        previous[src]=src;

        for(int count=0; count<len-1;count++){
            int u=minDistance(dist,spset,len);
            spset[u]=true;
            for (int v=0;v<len;v++){
                if(!spset[v] && g.matrix[u][v]!=0 && dist[u]!=Integer.MAX_VALUE && dist[u]+g.matrix[u][v]<dist[v]){
                    dist[v]= dist[u]+ g.matrix[u][v];
                    previous[v]=u;
                }
            }
        }
        printsolution(dist,previous,src,dest);
    }

    private void printsolution(int[] dist,int[] previous, int src,int dest) {
//        System.out.println("Vertex \t Distance from Source");
//        for (int i = 0; i < dist.length; i++)
//            System.out.println(i + " \t\t " + dist[i]);
        System.out.println("Shortest path cost "+dist[dest]);
        int x=-1;
        ArrayList<Integer> result=new ArrayList<>();
        while (dest!=0){
            result.add(dest);
            x=previous[dest];
            dest=x;
        }
        result.add(src);
        Collections.reverse(result);
        for(int i=0;i<result.size()-1;i++){
            System.out.print(result.get(i)+"->");
        }
        System.out.print(result.get(result.size()-1));
    }

    private int minDistance(int[] dist, Boolean[] spset,int length) {
        int min=Integer.MAX_VALUE;
        int minindex=-1;

        for(int v=0;v<length;v++){
            if(spset[v]==false && dist[v]<=min){
                min=dist[v];
                minindex=v;
            }
        }
        return minindex;
    }

    public static void main(String[] args) {
//        Dijkstras sp=new Dijkstras();
//        Weighted_edge a=new Weighted_edge(4,0,1);
//        Weighted_edge b=new Weighted_edge(2,1,3);
//        Weighted_edge c=new Weighted_edge(7,0,4);
//        Weighted_edge d=new Weighted_edge(1,1,2);
//        Weighted_edge e=new Weighted_edge(6,2,3);
//
//        Graph2 g= new Graph2(5);
//
//        g.add_weighted_edge(a);
//        g.add_weighted_edge(b);
//        g.add_weighted_edge(c);
//        g.add_weighted_edge(d);
//        g.add_weighted_edge(e);
//
//        System.out.println(g);
//        sp.dikstra(g,0);
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int e=sc.nextInt();

        int u,v,w;
        Weighted_edge x;
        Graph2 G=new Graph2(n);
        for(int i=0;i<e;i++){
            u=sc.nextInt();
            v=sc.nextInt();
            w=sc.nextInt();
            x=new Weighted_edge(w,u,v);
            G.add_weighted_edge(x);
        }
        int src=sc.nextInt();
        int dest=sc.nextInt();
        Dijkstras sp=new Dijkstras();
        sp.dikstra(G,src,dest);
    }
}
