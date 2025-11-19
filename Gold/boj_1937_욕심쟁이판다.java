import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1937_욕심쟁이판다 {
    static int[][] graph; // 대나무 숲 지도
    static int[][] dp; // 해당 위치에서 가장 많은 칸 저장 (메모이제이션)
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        graph = new int[N][N];
        dp = new int[N][N];

        // 대나무 양 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int max = 0;
        // 모든 칸을 시작점으로 탐색 (어디서 시작해야 가장 많은 칸?)
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                max = Math.max(max, dfs(i, j)); // 가장 많은 칸 갱신
            }
        }

        System.out.println(max);
    }

    // 이동 방향 (상, 하, 좌, 우)
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    static int dfs(int r, int c) {

        // 이미 해당 위치에 대한 결과를 계산했으면 그대로 반환
        if (dp[r][c] != 0)
            return dp[r][c];

        // 최소 한 칸 이동 가능 (해당 위치의 대나무 먹기)
        dp[r][c] = 1;

        // 현재 위치에서 상하좌우 탐색
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            // 범위를 벗어나거나, 이동할 곳의 대나무 양이 같거나 작으면 이동 불가

            if (nr < 0 || nc < 0 || nr >= N || nc >= N || graph[nr][nc] <= graph[r][c])
                continue;

            // 이동 가능한 곳 중 더 많이 이동할 수 있는 경로 갱신
            dp[r][c] = Math.max(dp[r][c], dfs(nr, nc) + 1);
        }

        return dp[r][c];
    }
}
