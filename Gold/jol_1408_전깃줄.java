import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class jol_1408_전깃줄 {
    
    public static void main(String[] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine().trim());
        ArrayList<int[]> lines = new ArrayList<>();
        for(int i=0; i<N; i++){
            st= new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            lines.add(new int[]{start,end});

        }
        Collections.sort(lines,(o1,o2)->{
            return o1[0]-o2[0];
        });
        int []dp = new int[N];
        int max=0;
        for(int i=0; i<N; i++){
            dp[i] =1;
            for(int j=0; j<i; j++){
                if(lines.get(j)[1] < lines.get(i)[1])
                    dp[i] = Math.max(dp[i], dp[j]+1);
            }
            max = Math.max(max, dp[i]);
        }
        System.out.println(N-max);

    }
}
