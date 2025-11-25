import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class boj_1744_수묶기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        ArrayList<Integer> pos = new ArrayList<>(); // 2 이상 양수
        ArrayList<Integer> neg = new ArrayList<>(); // 음수
        int zero = 0; // 0 개수
        int one = 0; // 1 개수

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num > 1)
                pos.add(num); // 2 이상
            else if (num == 0)
                zero++; // 0
            else if (num == 1)
                one++; // 1
            else
                neg.add(num); // 음수
        }

        Collections.sort(pos, Collections.reverseOrder()); // 큰 양수끼리 묶기
        Collections.sort(neg); // 작은 음수끼리 묶기

        int res = 0;
        int i = 0;
        int pos_size = pos.size();

        // 양수는 큰 수끼리 곱
        while (i + 1 < pos_size) {
            res += pos.get(i) * pos.get(i + 1);
            i += 2;
        }
        if (i < pos_size)
            res += pos.get(i); // 남으면 더함

        i = 0;
        int neg_size = neg.size();

        // 음수는 작은 수끼리 곱(음수*음수=양수)
        while (i + 1 < neg_size) {
            res += neg.get(i) * neg.get(i + 1);
            i += 2;
        }
        // 음수 하나 남으면 0 없을 때만 더함
        if (i < neg_size && zero == 0)
            res += neg.get(i);

        res += one; // 1은 묶는 것보다 더하는 게 이득

        System.out.println(res);
    }
}
