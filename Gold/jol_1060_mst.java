import java.io.*;
import java.util.*;

public class jol_1060_mst {

    static int N;
    static int[][] cost;
    static boolean[] visited;
    static int[] minDist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        cost = new int[N][N];
        visited = new boolean[N];
        minDist = new int[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Arrays.fill(minDist, Integer.MAX_VALUE);

        // 0번 학원부터 시작
        minDist[0] = 0;

        int answer = 0;

        for (int i = 0; i < N; i++) {
            int min = Integer.MAX_VALUE;
            int current = -1;

            // 아직 방문하지 않은 정점 중 가장 싸게 연결할 수 있는 정점 선택
            for (int j = 0; j < N; j++) {
                if (!visited[j] && minDist[j] < min) {
                    min = minDist[j];
                    current = j;
                }
            }

            visited[current] = true;
            answer += min;

            // current를 통해 다른 정점으로 가는 비용 갱신
            for (int next = 0; next < N; next++) {
                if (!visited[next] && cost[current][next] < minDist[next]) {
                    minDist[next] = cost[current][next];
                }
            }
        }

        System.out.println(answer);
    }
}