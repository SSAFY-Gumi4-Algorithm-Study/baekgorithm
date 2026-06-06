import java.io.*;
import java.util.*;

public class jol_1019_소형기관차 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] sum = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int people = Integer.parseInt(st.nextToken());
            sum[i] = sum[i - 1] + people;
        }

        int M = Integer.parseInt(br.readLine());

        int[][] dp = new int[4][N + 1];

        for (int train = 1; train <= 3; train++) {
            for (int i = train * M; i <= N; i++) {
                int current = sum[i] - sum[i - M];

                dp[train][i] = Math.max(
                    dp[train][i - 1],
                    dp[train - 1][i - M] + current
                );
            }
        }

        System.out.println(dp[3][N]);
    }
}