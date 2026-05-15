import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class jol_1462_보물섬 {

    static int R, C;
    static char[][] map;

    static int max_cnt = 0;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static class Node {
        int r;
        int c;
        int dist;

        Node(int r, int c, int dist) {
            this.r = r;
            this.c = c;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];

        for (int i = 0; i < R; i++) {
            String line = br.readLine();

            for (int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'L') {
                    bfs(i, j);
                }
            }
        }

        System.out.println(max_cnt);
    }

    static void bfs(int r, int c) {
        boolean[][] visited = new boolean[R][C];
        Queue<Node> q = new ArrayDeque<>();

        visited[r][c] = true;
        q.offer(new Node(r, c, 0));

        while (!q.isEmpty()) {
            Node cur = q.poll();

            max_cnt = Math.max(max_cnt, cur.dist);

            for (int d = 0; d < 4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];

                if (nr < 0 || nr >= R || nc < 0 || nc >= C) {
                    continue;
                }

                if (visited[nr][nc]) {
                    continue;
                }

                if (map[nr][nc] != 'L') {
                    continue;
                }

                visited[nr][nc] = true;
                q.offer(new Node(nr, nc, cur.dist + 1));
            }
        }
    }
}