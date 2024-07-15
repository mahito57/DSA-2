package Graph;


import java.io.*;
import java.util.*;

public class Baseball {
    private String[] tNames;
    private int[] tWins;
    private int[] tLosses;
    private int[] tRemain;
    private int[][] tRemainOthers;
    private int cTeams;


    public Baseball(String filename) throws IOException {
        File file = new File(filename);
        BufferedReader in = new BufferedReader(new FileReader(file));
        cTeams= Integer.parseInt(in.readLine());
        tNames = new String[cTeams];
        tWins = new int[cTeams];
        tLosses = new int[cTeams];
        tRemain = new int[cTeams];
        tRemainOthers = new int[cTeams][cTeams];
        for (int iTeam = 0; iTeam < cTeams; iTeam++) {
            String line = in.readLine();
            Scanner lineScanner = new Scanner(line);
            tNames[iTeam] = lineScanner.next();
            tWins[iTeam] = lineScanner.nextInt();
            tLosses[iTeam] = lineScanner.nextInt();
            tRemain[iTeam] = lineScanner.nextInt();
            for (int iAgainst = 0; iAgainst < cTeams; iAgainst++) {
                tRemainOthers[iTeam][iAgainst] = lineScanner.nextInt();
            }
        }

    }

    public int numberOfTeams() {
        return cTeams;
    }

    public ArrayList<String> teams() {
        ArrayList<String> teams = new ArrayList<>();
        for (String s : tNames) {
            teams.add(s);
        }
        return teams;
    }

    public int numberWins(String team) {
        for (int i = 0; i < cTeams; i++) {
            if (team.equals(tNames[i])) {
                return tWins[i];
            }
        }
        throw new java.lang.IllegalArgumentException();
    }

//    public int numberLosses(String team) {
//        for (int i = 0; i < cTeams; i++) {
//            if (team.equals(tNames[i])) {
//                return tLosses[i];
//            }
//        }
//        throw new java.lang.IllegalArgumentException();
//    }
//
//    public int RemainingMatches(String team) {
//        for (int i = 0; i < cTeams; i++) {
//            if (team.equals(tNames[i])) {
//                return tRemain[i];
//            }
//        }
//        throw new java.lang.IllegalArgumentException();
//    }
//
//    public int MatchesBetween(String team1, String team2) {
//        int numLeft = 0;
//        int one = -1;
//        int two = -1;
//        for (int i = 0; i < cTeams; i++) {
//            if (team1.equals(tNames[i])) {
//                one = i;
//            }
//            if (team2.equals(tNames[i])) {
//                two = i;
//            }
//        }
//        if (one == -1 || two == -1) {
//            throw new java.lang.IllegalArgumentException();
//        }
//        numLeft = tRemainOthers[one][two];
//        return numLeft;
//    }

    private boolean trivialSolution(int team) {
        boolean elim = false;
        for (int i = 0; i < cTeams; i++) {
            if (team != i && tWins[team] + tRemain[team] < tWins[i]) {
                elim = true;
                break;
            }
        }
        return elim;
    }

     boolean checkteam(int k){
        int n=numberOfTeams();       //team-Nodes
        int games=(n-1)*(n-2)/2;        //Game-Nodes
        int src=0;
        int sink=games+n+1;
        int [][]matrix=new int[sink+1][sink+1];
        int gamenodeused=0;
        int count_edges=0;
        int nodes=sink+1;

        int x=numberWins(tNames[k-1])+tRemain[k-1];
        int rem_games=0;

        for(int i=1;i<=n;i++){
            if(i!=k){
                matrix[i][sink]=x-numberWins(tNames[i-1]);                  //connection to sink
            //    System.out.println(i+" "+sink+" "+matrix[i][sink]);
                count_edges++;
            }
            for(int j=i+1;j<=n;j++){
                if(i!=k && j!=k){
                    matrix[src][n+gamenodeused+1]=tRemainOthers[i-1][j-1];          //connection from source to gamenode
                    rem_games=rem_games+tRemainOthers[i-1][j-1];
                //    System.out.println(i+" "+j+" "+matrix[src][n+gamenodeused+1]);

                    matrix[n+gamenodeused+1][i] = Integer.MAX_VALUE;            //connection between gamenode to teamnode
                    matrix[n+gamenodeused+1][j] = Integer.MAX_VALUE;

                    gamenodeused++;
                    count_edges+=3;
                }
            }
        }
//         for(int i=0;i<nodes;i++){
//             for(int j=0;j<nodes;j++){
//                 System.out.print(matrix[i][j]+"    ");
//             }
//             System.out.println();
//         }

        EdmondsKarp ed=new EdmondsKarp(matrix.length,count_edges);
        for(int i=0;i<nodes;i++){
            for(int j=0;j<nodes;j++){
                if(matrix[i][j]!=0){
                    ed.addEdge(i,j,matrix[i][j]);
                }
            }
        }

        int flow= (int) ed.getMaxFlow(src,sink);
        if(flow!=rem_games) return true;
        return false;

    }

    void checkall(){
        int n=numberOfTeams();
        for(int i=1;i<=n;i++){
            if(checkteam(i) || trivialSolution(i-1)){
                System.out.println(teams().get(i-1)+" is eliminated");
            }
        }
    }



    public static void main(String[] args) throws IOException {
        Baseball bs=new Baseball("Baseball.txt");
        bs.checkall();
    }
}