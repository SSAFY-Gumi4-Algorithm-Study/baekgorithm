import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class boj_1644_소수의연속합 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        // N < 2 예외 처리
        if (N < 2) {
            System.out.println(0);
            return;
        }

        // 에라토스테네스의 체로 소수 찾기
        boolean[] isPrime = new boolean[N + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        ArrayList<Integer> prime_List = new ArrayList<>();

        for (int i = 2; i <= N; i++) {
            if (isPrime[i]) {
                prime_List.add(i);
                // 소수의 배수 제거 (i*i부터 시작하여 효율성 확대)
                // 그보다 작은 배수들은 이미 더 작은 소수에 의해 한 번 이상 지워졌기 때문
                for (long j = (long) i * i; j <= N; j += i) {
                    isPrime[(int) j] = false;
                }
            }
        }
        // 투 포인터
        int left = 0;
        int right = 0;
        int cnt = 0;
        // 초기 값을 sum으로 설정
        long sum = prime_List.get(right);

        while (left < prime_List.size()) {
            if (sum == N) {
                cnt++;
                // N과 같으면 cnt증가후 구간 축소
                sum -= prime_List.get(left++);
            } else if (sum < N) {
                if (++right < prime_List.size()) {
                    // N보다 작으면 구간 점검하고 구간 축소.
                    sum += prime_List.get(right);
                } else {
                    break;
                }
            } else {// N보다 크면 구간 축소
                sum -= prime_List.get(left++);
            }

        }
        System.out.println(cnt);

    }
}
