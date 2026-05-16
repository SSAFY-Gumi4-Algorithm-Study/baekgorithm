import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class jol_1082_화염에서탈출 {
    
    static char[][] map;
    static int R,C;
    static int [][] visited; 
    static class Point{
        int r,c;
        Point(int r, int c){
            this.r=r;
            this.c=c;
        }
    }
    static Queue<Point> jaewoo= new ArrayDeque<>();
    static Queue<Point> fire = new ArrayDeque<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visited = new int[R][C];
        int D_r=0,D_c=0;
        for(int i=0; i<R; i++){
            String line = br.readLine().trim();
            for(int j=0; j<C; j++){
                map[i][j]= line.charAt(j);
                if(map[i][j]=='S'){
                    jaewoo.offer(new Point(i, j));
                }else if(map[i][j]=='*'){
                    fire.offer(new Point(i, j));
                }else if(map[i][j]=='D'){
                    D_r = i;
                    D_c = j;
                }
            }
        }
        bfs();
        if(visited[D_r][D_c]==0){
            System.out.println("impossible");
        }else{
            System.out.println(visited[D_r][D_c]);
        }
    }
    static int[] dr= {-1,1,0,0};
    static int[] dc= {0,0,-1,1};

    static void bfs(){
        
        while( !jaewoo.isEmpty()){
            //불을 전파
            int fire_size = fire.size();
            while(fire_size-->0){
                Point cur = fire.poll();
                int r = cur.r;
                int c = cur.c;
                for(int i=0; i<4; i++){
                    int nr = r+dr[i];
                    int nc = c+dc[i];
                    if(nr<0 || nc<0 || nr>=R || nc>=C || 
                        map[nr][nc]=='*'||map[nr][nc]=='D'||map[nr][nc]=='X'
                    ) continue;
                    map[nr][nc] ='*';
                    fire.offer(new Point(nr, nc));
                }
            }

            //재우 이동
            int jae_size = jaewoo.size();
            while(jae_size-->0){
                Point cur = jaewoo.poll();
                int r = cur.r;
                int c = cur.c;
                for(int i=0; i<4; i++){
                    int nr = r+dr[i];
                    int nc = c+dc[i];
                    if(nr<0 || nc<0 || nr>=R || nc>=C || 
                        map[nr][nc]=='*' || map[nr][nc]=='X' || visited[nr][nc]!=0
                    ) continue;
                    visited[nr][nc]=visited[r][c]+1;
                    jaewoo.offer(new Point(nr, nc));
                }
            }
        }

        
    }
}
