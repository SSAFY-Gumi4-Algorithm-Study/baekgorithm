import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class jol_1053_피보나치 {

    static final int MOD = 10000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in)
        );

        StringBuilder answer = new StringBuilder();

        while (true) {
            int n = Integer.parseInt(br.readLine().trim());

            if (n == -1) {
                break;
            }

            long[][] result = power(n);
            answer.append(result[0][1]).append('\n');
        }

        System.out.print(answer);
    }

    // 기본 행렬을 exponent번 거듭제곱
    static long[][] power(int exponent) {
        long[][] result = {
                {1, 0},
                {0, 1}
        };

        long[][] base = {
                {1, 1},
                {1, 0}
        };

        while (exponent > 0) {
            if (exponent % 2 == 1) {
                result = multiply(result, base);
            }

            base = multiply(base, base);
            exponent /= 2;
        }

        return result;
    }

    static long[][] multiply(long[][] a, long[][] b) {
        long[][] result = new long[2][2];

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }

                result[i][j] %= MOD;
            }
        }

        return result;
    }
}