package Graph;

import java.util.Arrays;
import java.util.Scanner;

public class Floyed_warshal {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int m=sc.nextInt();
        double [][]graph= new double[n][n];
        for (double[] row : graph)
            Arrays.fill(row, Double.POSITIVE_INFINITY);

        for(int p=0;p<n;p++){
            graph[p][p]=0;
        }
        double u,v,w;
        for(int i=0;i<m;i++){
            u= sc.nextDouble();
            v=sc.nextDouble();
            w=sc.nextDouble();
            graph[(int) u-1][(int) v-1]=w;
        }

        printsolution(floyed_warshal(graph));
        printsolution(faster_matrix_version(graph));
    }

    static void printsolution(double[][] graph){
        System.out.println();
        for(int p=0;p<graph.length;p++){
            for(int q=0;q<graph.length;q++){
                if(graph[p][q]==Double.POSITIVE_INFINITY) System.out.print("INF ");

                else System.out.print(graph[p][q]+" ");
            }
            System.out.println();
        }
    }

    static double[][] floyed_warshal(double[][] graph) {
        int n= graph.length;
        double [][]matrix=new double[n][n];
        for(int p=0;p<n;p++){
            for(int q=0;q<n;q++){
                matrix[p][q]=graph[p][q];
            }
        }
        for(int k=0;k<n;k++){
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(matrix[i][j]>matrix[i][k]+matrix[k][j])
                        matrix[i][j]=matrix[i][k]+matrix[k][j];
                }
            }
        }
        return matrix;
    }

    static double[][] matrix_multiplication(double[][] graph){
        int n=graph.length;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                Double value=Double.POSITIVE_INFINITY;
                for(int k=0;k<n;k++){
                    if(value>graph[i][k]+graph[k][j])
                        value=graph[i][k]+graph[k][j];
                }
                graph[i][j]=value;
            }
        }
        return graph;
    }
    static double[][] faster_matrix_version(double[][] graph){
        int n=graph.length;
        int m=1;
        while(m<n){
            graph=matrix_multiplication(graph);
            m=m*2;
        }
        return graph;
    }
}
