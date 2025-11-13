import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class boj_7662_이중우선순위큐 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수

        for (int tc = 0; tc < T; tc++) {

            // key: 숫자, value: 등장 횟수
            TreeMap<Integer, Integer> map = new TreeMap<>();

            int k = Integer.parseInt(br.readLine()); // 연산 개수

            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                String cal = st.nextToken(); // 연산 종류

                if (cal.equals("I")) { // 삽입
                    int num = Integer.parseInt(st.nextToken());
                    map.put(num, map.getOrDefault(num, 0) + 1);
                } else { // 삭제
                    if (map.isEmpty())
                        continue;

                    int type = Integer.parseInt(st.nextToken());
                    int num;

                    if (type == 1)
                        num = map.lastKey(); // 최댓값
                    else
                        num = map.firstKey(); // 최솟값

                    // put()은 기존 값을 반환함. 기존 값이 1이면 삭제해야 함.
                    if (map.put(num, map.get(num) - 1) == 1) {
                        map.remove(num);
                    }
                }
            }

            if (map.isEmpty()) {
                System.out.println("EMPTY");
            } else {
                System.out.println(map.lastKey() + " " + map.firstKey());
            }
        }
    }
}
