
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_18405_경쟁적전염 {
    // 지도 정보 저장 배열
    static int[][] graph;
    static int N; // 지도 크기 (N x N)

    // 방향벡터 (상, 하, 좌, 우)
    static int dr[] = { -1, 1, 0, 0 };
    static int dc[] = { 0, 0, -1, 1 };

    /**
     * 바이러스 정보를 저장하는 클래스
     * → v_num이 작은 바이러스부터 전염되므로 Comparable 구현
     */
    static class Virus implements Comparable<Virus> {
        int r, c; // 바이러스 위치
        int v_num; // 바이러스 번호

        public Virus(int r, int c, int v_num) {
            this.r = r;
            this.c = c;
            this.v_num = v_num;
        }

        // 바이러스 번호 오름차순 정렬 (작은 번호부터 전염되도록)
        @Override
        public int compareTo(Virus o) {
            return this.v_num - o.v_num;
        }
    }

    // BFS를 위한 큐
    static Queue<Virus> viruses;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // N = 지도 크기 / K = 바이러스 종류 수
        N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        graph = new int[N + 1][N + 1];
        viruses = new ArrayDeque<>();
        ArrayList<Virus> init = new ArrayList<>();

        /**
         * 초기 지도 입력 및 바이러스 위치 저장
         * → 나중에 번호 기준 정렬 후 큐에 넣음
         */
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int num = Integer.parseInt(st.nextToken());
                graph[i][j] = num;
                if (num != 0) { // 바이러스가 존재하는 경우
                    init.add(new Virus(i, j, num));
                }
            }
        }

        // 바이러스 번호 기준으로 정렬 → 동시에 전염되더라도 번호가 작은 바이러스가 우선
        Collections.sort(init);
        for (Virus v : init)
            viruses.offer(v);

        // S 초 후 graph[row][col] 의 바이러스 번호 확인
        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());

        // 전염 시간만큼 반복 (초 단위)
        while (S-- > 0) {
            bfs();
        }

        // 최종 결과 출력
        System.out.println(graph[row][col]);
    }

    /**
     * 한 '초' 동안 전염되는 과정
     * → 현재 큐에 들어있는 모든 바이러스가 동시에 퍼져야 하므로
     * size만큼만 poll 수행 (동일 시간대 전염 처리)
     */
    static void bfs() {
        int size = viruses.size(); // 현재 시점(초)의 바이러스 개수

        while (size-- > 0) {
            Virus cur = viruses.poll();
            int r = cur.r;
            int c = cur.c;
            int v_num = cur.v_num;

            // 상하좌우로 전염 시도
            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                // 범위 벗어나거나 이미 감염된 경우 제외
                if (nr <= 0 || nc <= 0 || nr > N || nc > N || graph[nr][nc] != 0)
                    continue;

                // 전염 진행
                graph[nr][nc] = v_num;
                viruses.offer(new Virus(nr, nc, v_num));
            }
        }
    }
}
