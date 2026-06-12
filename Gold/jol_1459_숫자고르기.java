import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class jol_1459_숫자고르기 {

    static int N;
    static int [] arr;
    static boolean[] visited;
    static boolean[] answer;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine().trim());
        arr = new int[N+1];
        answer = new boolean[N+1];

        for(int i=1; i<=N; i++){
            arr[i] = Integer.parseInt(br.readLine().trim());

        }

        for(int i=1; i<=N; i++){
            visited=new boolean[N+1];
            dfs(i,i);
        }
        int cnt=0;
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=N; i++){
            if(answer[i]){
                cnt++;
                sb.append(i);
                sb.append("\n");
            }
        }
        System.out.println(cnt);
        System.out.println(sb);

        
    }

    static void dfs(int start, int cur){
        if(visited[cur]){
            return;
        }

        visited[cur] =true;

        int next = arr[cur];
        if(next == start){
            answer[start]=true;
            answer[cur]=true;
            return;
        }

        dfs(start, next);

        if(answer[start]){
            answer[cur]=true;
        }
    }
}