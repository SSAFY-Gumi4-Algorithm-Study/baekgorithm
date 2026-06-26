import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class jol_1190_모두더하기 {
    

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Long> pq = new PriorityQueue<>();
        st = new StringTokenizer(br.readLine());
        while(N-->0){
            pq.add(Long.parseLong(st.nextToken()));
        }
        long sum=0;
        while (pq.size()>1) {
            long n1 = pq.poll();
            long n2 = pq.poll();
            long ret = n1+n2;
            sum+=ret;
            pq.add(ret);
        }
        System.out.println(sum);
    }
}
