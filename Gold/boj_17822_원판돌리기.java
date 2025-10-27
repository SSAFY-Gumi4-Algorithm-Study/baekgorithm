import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_17822_원판돌리기 {
    
    static int N, M, T;
    static int [][] circle;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // N: 원판 개수, M: 숫자 개수, T: 회전 횟수
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        circle = new int[N][M];

        // 원판 초기 숫자 입력
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                circle[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 총 T회 회전 및 BFS 제거/평균 조정 수행
        for(int t=0; t<T; t++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()); // 회전할 배수 원판
            int d = Integer.parseInt(st.nextToken()); // 방향 (0: 시계, 1: 반시계)
            int k = Integer.parseInt(st.nextToken()); // 회전 칸 수

            // x의 배수인 원판들 회전
            for(int row = x-1; row < N; row += x){
                rotate(row, d, k);
            }

            boolean erase = false;
            visited = new boolean[N][M];

            // 같은 숫자 BFS 탐색하여 제거 여부 확인
            for(int i=0; i<N; i++){
                for(int j=0; j<M; j++){
                    if(!visited[i][j] && circle[i][j] != 0){
                        if(bfs(i, j)) erase = true;
                    }
                }
            }

            // 제거 없으면 평균에 따른 숫자 조정
            if(!erase){
                double sum = 0;
                double cnt = 0;

                for(int i=0; i<N; i++){
                    for(int j=0; j<M; j++){
                        if(circle[i][j] != 0){
                            sum += circle[i][j];
                            cnt++;
                        }
                    }
                }

                // 모두 제거된 경우 패스
                if(cnt == 0) continue;

                double avg = sum / cnt;

                // 평균보다 크면 -1, 작으면 +1 조정
                for(int i=0; i<N; i++){
                    for(int j=0; j<M; j++){
                        if(circle[i][j] == 0) continue;
                        if(circle[i][j] > avg) circle[i][j]--;
                        else if(circle[i][j] < avg) circle[i][j]++;
                    }
                }
            }
        }

        // 남은 숫자 총합 계산
        int res = 0;
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                res += circle[i][j];
            }
        }

        // 정답 출력
        System.out.println(res);
    }

    // 탐색 방향 (상, 하, 좌, 우)
    static int dr[] = {-1, 1, 0, 0};
    static int dc[] = {0, 0, -1, 1};

    /**
     * BFS로 같은 숫자가 인접한지 확인하고
     * 2개 이상 연결된 경우 0으로 제거 처리
     */
    static boolean bfs(int r, int c){
        Queue<int[]> q = new ArrayDeque<>();
        visited[r][c] = true;
        q.offer(new int[]{r, c});

        int num = circle[r][c];
        ArrayList<int[]> erase_list = new ArrayList<>();
        erase_list.add(new int[]{r, c});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cr = cur[0];
            int cc = cur[1];

            for(int d=0; d<4; d++){
                int nr = cr + dr[d];
                int nc = cc + dc[d];

                // 세로 방향은 범위 벗어나면 무시
                if(nr < 0 || nr >= N) continue;

                // 가로 방향은 원형 연결
                if(nc < 0) nc = M - 1;
                else if(nc >= M) nc = 0;

                // 이미 방문했거나 다르면 무시
                if(visited[nr][nc] || circle[nr][nc] != num) continue;

                // 같은 값이면 큐에 삽입
                visited[nr][nc] = true;
                erase_list.add(new int[]{nr, nc});
                q.offer(new int[]{nr, nc});
            }
        }

        // 연결된 칸이 2개 이상이면 0으로 변경
        if(erase_list.size() > 1){
            for(int[] e : erase_list){
                circle[e[0]][e[1]] = 0;
            }
            return true;
        }
        return false;
    }

    /**
     * row 원판을 방향 d, k번만큼 회전
     * d=0 : 시계 (오른쪽)
     * d=1 : 반시계 (왼쪽)
     */
    static void rotate(int row, int d, int k){
        // k번 반복 회전
        for(int i=0; i<k; i++){
            if(d == 0){ // 오른쪽 회전
                int temp = circle[row][M-1];
                for(int j=M-1; j>0; j--){
                    circle[row][j] = circle[row][j-1];
                }
                circle[row][0] = temp;
            } else { // 왼쪽 회전
                int temp = circle[row][0];
                for(int j=0; j<M-1; j++){
                    circle[row][j] = circle[row][j+1];
                }
                circle[row][M-1] = temp;
            }
        }
    }
}
