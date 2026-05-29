import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class jol_1006_로봇 {
    //bfs
    static int R,C;
    static int [] dr = {0,0,1,-1};
    static int [] dc = {1,-1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int [][] arr = new int[R][C];
        for(int i=0; i<R; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<C; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        int s_r = Integer.parseInt(st.nextToken())-1;
        int s_c = Integer.parseInt(st.nextToken())-1;
        int s_d = Integer.parseInt(st.nextToken())-1;
        st = new StringTokenizer(br.readLine());
        int d_r = Integer.parseInt(st.nextToken())-1;
        int d_c = Integer.parseInt(st.nextToken())-1;
        int d_d = Integer.parseInt(st.nextToken())-1;
        System.out.println( bfs(s_r, s_c, s_d, d_r, d_c, d_d, arr));
    }
    static int bfs(int s_r,int s_c,int s_d, int d_r,int d_c, int d_d, int[][]arr){
        //0 1 2 3 동 서 남 북 
        int [][][] dist = new int[R][C][4];
        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                Arrays.fill(dist[i][j], -1);
            }
        }

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{s_r,s_c,s_d});
        dist[s_r][s_c][s_d]=0;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];
            int d = cur[2];

            if(r==d_r && c==d_c && d==d_d){
                return dist[r][c][d];
            }
            
            for(int i=1; i<=3; i++){
                int nr = r + dr[d]*i;
                int nc = c + dc[d]*i;
                if(nr <0 || nc<0 || nr>=R || nc>=C ||arr[nr][nc]==1){
                    break;
                }
                if(dist[nr][nc][d] == -1){
                    dist[nr][nc][d] = dist[r][c][d]+1;
                    q.offer(new int[]{nr,nc,d});
                }
            }

            for(int nd : getDirection(d)){
                if(dist[r][c][nd]==-1){
                    dist[r][c][nd] = dist[r][c][d]+1;
                    q.offer(new int[]{r,c,nd});
                }
            }
            
            
        }
        return -1;
    }
    static int[] getDirection(int d){
        if(d==0){
            return new int[]{2,3};
        }else if (d==1){
            return new int[]{2,3};
        }else if (d==2){
            return new int[]{0,1};
        }else {
            return new int[]{0,1};
        }
    }
}
