package Graph;

import java.io.*;
import java.util.*;

class Node2 {
    int vertex;
    int dist;
    public Node2(){
        vertex=0;
        dist=0;
    }
    public Node2(int a, int b){
        vertex=a;
        dist=b;
    }

    public int getDist() {
        return dist;
    }

    public int getVertex() {
        return vertex;
    }
}

public class Dijkstras {
    void dikstra(Graph2 g, int src,int dest,BufferedWriter bw) throws IOException {
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

        PriorityQueue<Node2> pq=new PriorityQueue<>(Comparator.comparing(Node2 -> Node2.dist));
        Node2 a=new Node2(src,dist[src]);
        pq.add(a);

//        fibHeap fh=new fibHeap();
//        node b=new node(src,dist[src]);
//        fh.insert(b);
        for(int count=0; count<len-1;count++){
            //int u=minDistance(dist,spset,len);
            int u=pq.remove().getVertex();
//            int u=fh.extract_min().getVertex();
            spset[u]=true;
            for (int v=0;v<len;v++){
                if(!spset[v] && g.matrix[u][v]!=0 && dist[u]!=Integer.MAX_VALUE && dist[u]+g.matrix[u][v]<dist[v]){
                    dist[v]= dist[u]+ g.matrix[u][v];
                    previous[v]=u;

                    a=new Node2(v,dist[v]);
                    pq.add(a);

        //            b=new node(v,dist[v]);
         //           fh.insert(b);
                }
            }
        }
        //printsolution(dist,previous,src,dest);
        //System.out.println();
        output_to_file(dist,previous,src,dest,bw);
    }

    void dijkstra_fibheap(Graph2 g, int src,int dest,BufferedWriter bw){
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


        fibHeap fh=new fibHeap();
        node b=new node(src,dist[src]);
        fh.insert(b);
        for(int count=0; count<len-1;count++){
            int u=fh.extract_min().getVertex();
            spset[u]=true;
            for (int v=0;v<len;v++){
                if(!spset[v] && g.matrix[u][v]!=0 && dist[u]!=Integer.MAX_VALUE && dist[u]+g.matrix[u][v]<dist[v]){
                    dist[v]= dist[u]+ g.matrix[u][v];
                    previous[v]=u;

                    //       a=new Node(v,dist[v]);
                    //       pq.add(a);

                    b=new node(v,dist[v]);
                    fh.insert(b);
                }
            }
        }
    }

    private void printsolution(int[] dist,int[] previous, int src,int dest) {
//        System.out.println("Vertex \t Distance from Source");
//        for (int i = 0; i < dist.length; i++)
//            System.out.println(i + " \t\t " + dist[i]);
        System.out.println("Shortest path cost "+dist[dest]);
        int x=-1;
        ArrayList<Integer> result=new ArrayList<>();
        while (dest!=src){
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

    private void output_to_file(int[] dist,int[] previous, int src,int dest,BufferedWriter bw) throws IOException {

        int x,count=0;
        int distance=dist[dest];
        Stack<Integer> result=new Stack<>();
        while (dest!=src){
            result.push(dest);
            x=previous[dest];
            dest=x;
            count++;
        }
        result.push(src);
//        while (result.size()>1){
//            System.out.print(result.pop()+"->");
//        }
//        System.out.println(result.pop());
        bw.write(count+"   "+distance);
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

    public static void main(String[] args) throws IOException {

        File file=new File("dijkstra_input.txt");
        BufferedReader br=new BufferedReader(new FileReader(file));
        String st;
        st=br.readLine();
        String[] values=st.split(" ");
        int n= Integer.parseInt(values[0]);
        int e=Integer.parseInt(values[1]);
        Graph2 G=new Graph2(n);

        while ((st=br.readLine())!=null){
            values=st.split(" ");
            Weighted_edge x=new Weighted_edge((int) Double.parseDouble(values[2]),Integer.parseInt(values[0]),Integer.parseInt(values[1]));
            G.add_weighted_edge(x);
        }

        file=new File("dijkstra_case.txt");
        br=new BufferedReader(new FileReader(file));
        st=br.readLine();
        int t=Integer.parseInt(st);

        FileWriter myfile=new FileWriter("dijkstra_output.txt");
        BufferedWriter bw=new BufferedWriter(myfile);

        while (t!=0){
            st=br.readLine();
            values=st.split(" ");
            Dijkstras sp=new Dijkstras();
            int src=Integer.parseInt(values[0]);
            int dest=Integer.parseInt(values[1]);
            long start=System.nanoTime();
            sp.dikstra(G,src,dest,bw);
            long end=System.nanoTime();
            long exc_time=end-start;

            long start2=System.nanoTime();
            sp.dijkstra_fibheap(G,src,dest,bw);
            long end2=System.nanoTime();
            long exc2=end2-start2;
            bw.write("   "+exc_time+"   "+exc2+"\n");
            System.out.println();
            t--;
        }
        bw.close();
    }
}
