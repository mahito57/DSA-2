package Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class bellman_ford {
    static void bellmanford(ArrayList<Weighted_edge> edge_list,int n,int src,int dest){
        int[] dist=new int[n];
        int[] previous=new int[n];

        for(int i=0;i<n;i++){
            dist[i]=Integer.MAX_VALUE;
        }

        dist[src]=0;      //Vertex 0 is the source
        previous[src]=src;
        for(int i=0;i<n;i++){
            for(Weighted_edge x:edge_list){
                if(dist[x.v]!=Integer.MAX_VALUE && dist[x.v]+x.value<dist[x.w]){        //edge from v to w
                    dist[x.w]=dist[x.v]+x.value;
                    previous[x.w]=x.v;
                }
            }
        }

        for(Weighted_edge x:edge_list){
            if(dist[x.v]!=Integer.MAX_VALUE && dist[x.v]+x.value<dist[x.w]){        //edge from v to w
                System.out.println("Negative cycle detected");
                return;
            }
        }
        System.out.println("The graph does not contain a negative cycle");
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
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int e=sc.nextInt();

        int u,v,w;
        Weighted_edge p;
        ArrayList<Weighted_edge> edge_list=new ArrayList<>();
        for(int i=0;i<e;i++){
            u=sc.nextInt();
            v=sc.nextInt();
            w=sc.nextInt();
            p=new Weighted_edge(w,u,v);
            edge_list.add(p);
        }
        int src=sc.nextInt();
        int dest=sc.nextInt();
        bellmanford(edge_list,n,src,dest);


    }

}
