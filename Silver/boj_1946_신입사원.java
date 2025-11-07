import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_1946_신입사원 {
    

    static class Score implements Comparable<Score>{
        int a,b;
        Score(int a,int b){
            this.a =a;
            this.b =b;
        }
        @Override
        public int compareTo(Score o) {
            // TODO Auto-generated method stub
            return this.a-o.a;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int tc=0; tc<T; tc++){
            int N =Integer.parseInt(br.readLine());
            Score[] scores=new Score[N];
            StringTokenizer st ;
            PriorityQueue<Score> pq = new PriorityQueue<>();
            for(int i=0; i<N; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                pq.offer(new Score(a, b));
            }
            int cnt=0;
            Score cur = pq.poll();
            while (!pq.isEmpty()) {
                Score next = pq.poll();
                if(cur.b>next.b){
                    cnt++;
                    cur = next;
                }
                
            }
            System.out.println(cnt+1);
        }

        
    }
}
