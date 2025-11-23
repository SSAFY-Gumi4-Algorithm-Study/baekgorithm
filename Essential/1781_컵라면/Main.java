import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    // 라면 문제에서 한 개의 작업(deadline, reward)을 표현
    static class Ramen implements Comparable<Ramen>{
        int deadline, cup;

        Ramen(int deadline, int cup){
            this.deadline = deadline;
            this.cup = cup;
        }

        // 정렬 기준: 1) 마감 시간 오름차순  2) 같은 마감일이면 보상 큰 순
        @Override
        public int compareTo(Ramen o) {
            if (this.deadline == o.deadline) {
                return o.cup - this.cup;
            }
            return this.deadline - o.deadline;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 모든 라면를 deadline 기준으로 정렬하기 위한 PQ
        PriorityQueue<Ramen> pq = new PriorityQueue<>();

        // 입력받고 PQ에 넣음
        for(int i = 1; i <= N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int deadline = Integer.parseInt(st.nextToken());
            int cup = Integer.parseInt(st.nextToken());
            pq.add(new Ramen(deadline, cup));
        }

        // 선택된 보상들을 저장하는 PQ (최소힙)
        // → 마감일 초과 시 가장 보상 낮은 것을 제거하기 위함
        PriorityQueue<Integer> result = new PriorityQueue<>();

        // 정렬된 라면들을 순서대로 처리
        while (!pq.isEmpty()) {
            Ramen cur = pq.poll();
            result.offer(cur.cup);  // 일단 현재 라면 선택

            // 현재 시점에서 선택된 라면 개수가 마감일을 초과하면
            // → 최소 보상 라면을 제거하여 최적 유지
            if (result.size() > cur.deadline) {
                result.poll();
            }
        }

        // 남은 라면들의 보상 합
        long sum = 0;
        while (!result.isEmpty()) {
            sum += result.poll();
        }

        System.out.println(sum);
    }
}
