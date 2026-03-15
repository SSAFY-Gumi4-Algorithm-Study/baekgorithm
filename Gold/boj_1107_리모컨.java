import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1107_리모컨 {

    static boolean[] broken = new boolean[10];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int target = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        if (M > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                int num = Integer.parseInt(st.nextToken());
                broken[num] = true;
            }
        }

        int answer = Math.abs(target - 100); // +,-만 사용하는 경우

        for (int i = 0; i <= 1000000; i++) {
            int len = canPress(i);
            if (len > 0) {
                int pressCount = len + Math.abs(target - i);
                answer = Math.min(answer, pressCount);
            }
        }

        System.out.println(answer);
    }

    static int canPress(int num) {
        if (num == 0) {
            return broken[0] ? 0 : 1;
        }

        int len = 0;
        while (num > 0) {
            int digit = num % 10;
            if (broken[digit]) return 0;
            len++;
            num /= 10;
        }
        return len;
    }
}