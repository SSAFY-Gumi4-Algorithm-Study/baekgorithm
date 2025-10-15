package 백고리즘.baekgorithm.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_1967_트리의지름 {
    static class Edge{
        int to,cost;
        Edge(int to,int cost){
            this.to=to;
            this.cost=cost;
        }
    }
    static ArrayList<Edge>[] graph ; //인접리스트
    static boolean[] visited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N+1];
        visited = new boolean[N+1];
        for(int i=0; i<=N ; i++){
            graph[i]= new ArrayList<>();
        }

        for(int i=1; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[from].add(new Edge(to, cost));
            graph[to].add(new Edge(from, cost));    //양방향 리스트
        }
        visited[1]=true;       //1번 방문
        dfs(1, 0); //1번에서 출발하는 dfs
                            //1번에서 가장 먼 노드가 maxEdge에 저장

        Arrays.fill(visited, false); //visited 초기화

        visited[maxEdge.to]=true;   //가장 먼 node에서 출발 방문처리
        dfs(maxEdge.to, 0);

        System.out.println(maxEdge.cost); //1번에서 가장 먼 node에서 가장 먼 node까지의 거리

    }
    static Edge maxEdge = new Edge(0, 0); //초기화

    static void dfs(int start, int dist){
        for(Edge child : graph[start]){
            if(!visited[child.to]){//방문하지 않았다면 ㄱㄱ
                visited[child.to]=true;
                dfs(child.to, child.cost+dist);//현재 dist에 다음 node까지의 cost를 더함

            }
        }

        if(maxEdge.cost<dist){//지금까지의 dist가 maxEdge의 cost보다 작으면 갱신
            maxEdge.to=start;
            maxEdge.cost=dist;
        }
    }
}
