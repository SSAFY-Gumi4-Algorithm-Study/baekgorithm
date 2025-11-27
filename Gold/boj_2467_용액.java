

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_2467_용액 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0; // 왼쪽 포인터 (가장 작은 값)
        int right = N - 1; // 오른쪽 포인터 (가장 큰 값)
        int res = Integer.MAX_VALUE; // 현재까지의 최소 합 절대값
        int min_al = 0, min_san = 0; // 최소가 되는 조합 저장

        while (left < right) {
            int sum = arr[left] + arr[right]; // 두 용액의 합

            // 더 0에 가까운 조합이면 갱신
            if (Math.abs(sum) < res) {
                res = Math.abs(sum);
                min_al = arr[left];
                min_san = arr[right];
            }

            // 합이 양수면 더 작은 값을 만들어야 하므로 right 감소
            if (sum > 0) {
                right--;
            }
            // 합이 음수면 더 큰 값을 만들어야 하므로 left 증가
            else if (sum < 0) {
                left++;
            }
            // 합이 0이면 가장 좋은 경우 → 바로 종료
            else {
                break;
            }
        }
        System.out.print(min_al + " " + min_san); // 결과 출력
    }
}
