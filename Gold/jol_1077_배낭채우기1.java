import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class jol_1077_배낭채우기1 {
    
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int[] weights = new int[N];
        int[] values = new int[N];
        long[] dp = new long[W+1];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            weights[i] = Integer.parseInt(st.nextToken());
            values[i] = Integer.parseInt(st.nextToken());

            
        }

        for(int i=1; i<=W; i++){
            for(int j=0; j<N; j++){
                if(i>=weights[j])
                    dp[i] = Math.max(dp[i], dp[i-weights[j]]+values[j]);
            }
        }
        System.out.println(dp[W]);

    }
}
