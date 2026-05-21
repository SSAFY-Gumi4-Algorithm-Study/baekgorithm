import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class jol_1352_양팔저울 {
    

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        
        int sum =0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            int num = Integer.parseInt(st.nextToken());
            arr[i] = num;
            
            sum+=num;
            
        }
        
        boolean [] dp = new boolean[sum+1];
        dp[0]=true;
        for(int weight : arr){
            boolean [] next = dp.clone();
            for(int i=0; i<=sum; i++){
                if(!dp[i]) continue;
                
                if(i+weight<=sum){
                    next[i+weight]=true;
                }

                next[Math.abs(i-weight)]=true;
            }
            dp=next;
        }
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<M; i++){
            int num = Integer.parseInt(st.nextToken());
            if(num<=sum&&dp[num]==true){
                sb.append("Y").append(" "); 
            }else{
                sb.append("N").append(" ");
            }

        }
        System.out.println(sb);
    }
}
