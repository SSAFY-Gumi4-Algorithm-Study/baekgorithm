import java.io.*;
import java.util.*;

public class jol_1457_영역구하기 {

    static int M, N, K;
    static boolean[][] map;
    static boolean[][] visited;

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    static class Point {
        int y, x;

        Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken()); // 세로
        N = Integer.parseInt(st.nextToken()); // 가로
        K = Integer.parseInt(st.nextToken());

        map = new boolean[M][N];
        visited = new boolean[M][N];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());

            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            for (int y = y1; y < y2; y++) {
                for (int x = x1; x < x2; x++) {
                    map[y][x] = true; // 직사각형 내부 칠하기
                }
            }
        }

        ArrayList<Integer> areas = new ArrayList<>();

        for (int y = 0; y < M; y++) {
            for (int x = 0; x < N; x++) {
                if (!map[y][x] && !visited[y][x]) {
                    int area = bfs(y, x);
                    areas.add(area);
                }
            }
        }

        Collections.sort(areas);

        StringBuilder sb = new StringBuilder();
        sb.append(areas.size()).append('\n');

        for (int area : areas) {
            sb.append(area).append(' ');
        }

        System.out.println(sb);
    }

    static int bfs(int startY, int startX) {
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(startY, startX));
        visited[startY][startX] = true;

        int count = 1;

        while (!q.isEmpty()) {
            Point cur = q.poll();

            for (int d = 0; d < 4; d++) {
                int ny = cur.y + dy[d];
                int nx = cur.x + dx[d];

                if (ny < 0 || ny >= M || nx < 0 || nx >= N) {
                    continue;
                }

                if (map[ny][nx]) {
                    continue;
                }

                if (visited[ny][nx]) {
                    continue;
                }

                visited[ny][nx] = true;
                q.offer(new Point(ny, nx));
                count++;
            }
        }

        return count;
    }
}
