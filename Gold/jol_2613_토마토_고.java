import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class jol_2613_토마토_고 {
    static int R,C;
    static int[][] arr;
    static Queue<int[]> selected;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        arr = new int[R][C];
        selected = new ArrayDeque<>();
        int not_completed=0;
        for(int i=0; i<R; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<C; j++){
                int n =Integer.parseInt(st.nextToken());
                arr[i][j]=n;
                if(n==1){
                    selected.add(new int[]{i,j});
                }else if(n==0){
                    not_completed++;
                }

            }
        }
        int cnt=-1;
        if(not_completed==0){
            System.out.println(0);
            return;
        }
        if(selected.isEmpty()){
            System.out.println(-1);
        }else{
            int check_completed=0;
            while(!selected.isEmpty()){

                int size = selected.size();
                while(size-->0){
                    int[] cur = selected.poll();
                    int r = cur[0];
                    int c = cur[1];
                    for(int d=0; d<4; d++){
                        int nr = r + dr[d];
                        int nc = c + dc[d];
                        if(nr<0 || nc<0 || nr>=R || nc>=C || arr[nr][nc]==1||arr[nr][nc]==-1){
                            continue;
                        }
                        arr[nr][nc]=1;
                        check_completed++;

                        selected.offer(new int[]{nr,nc});
                    }
                }
                cnt++;
            }
            if(check_completed==not_completed)
            {
                System.out.println(cnt);
            }
            else{
                System.out.println(-1);
            }
        }
    }   
    static int []dr = {-1,1,0,0};
    static int []dc = {0,0,-1,1};
    
}
