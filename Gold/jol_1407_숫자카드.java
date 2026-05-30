import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class jol_1407_숫자카드 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine().trim();
        int n = s.length();

        long[] dp = new long[n + 1];

        dp[0] = 1;

        for (int i = 1; i <= n; i++) {

            // 1자리 확인
            int one = s.charAt(i - 1) - '0';
            if (one >= 1 && one <= 9) {
                dp[i] += dp[i - 1];
            }

            // 2자리 확인
            if (i >= 2) {
                int two = Integer.parseInt(s.substring(i - 2, i));

                if (two >= 10 && two <= 34) {
                    dp[i] += dp[i - 2];
                }
            }
        }

        System.out.println(dp[n]);
    }
}