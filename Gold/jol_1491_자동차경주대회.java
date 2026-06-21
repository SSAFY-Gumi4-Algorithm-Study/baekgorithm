
import java.io.*;
import java.util.*;

public class jol_1491_자동차경주대회 {
    static final long INF = Long.MAX_VALUE / 4;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long maxDist = Long.parseLong(br.readLine());
        int n = Integer.parseInt(br.readLine());

        long[] pos = new long[n + 2];
        int[] time = new int[n + 2];
        long[] dp = new long[n + 2];
        int[] parent = new int[n + 2];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n + 1; i++) {
            pos[i] = pos[i - 1] + Long.parseLong(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            time[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.fill(dp, INF);
        dp[0] = 0;

        for (int i = 1; i <= n + 1; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (pos[i] - pos[j] > maxDist) {
                    break;
                }

                if (dp[i] > dp[j] + time[j]) {
                    dp[i] = dp[j] + time[j];
                    parent[i] = j;
                }
            }
        }

        ArrayList<Integer> path = new ArrayList<>();

        int cur = parent[n + 1];
        while (cur != 0) {
            path.add(cur);
            cur = parent[cur];
        }

        Collections.reverse(path);

        System.out.println(dp[n + 1]);

        if (path.size() == 0) {
            return;
        }

        System.out.println(path.size());

        for (int x : path) {
            System.out.print(x + " ");
        }
    }
}