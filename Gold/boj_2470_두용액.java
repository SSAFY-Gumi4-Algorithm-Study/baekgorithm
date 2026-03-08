import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_2470_두용액 {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine()); //2이상 100,000이하

        st = new StringTokenizer(br.readLine());
        int [] arr = new int[N];
        for(int i=0; i<N; i++){
            arr[i]=Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        int res[] = new int[2];
        int left=0;
        int right=N-1;
        int min = Integer.MAX_VALUE;
        while (left<right) {
            int sum = arr[left]+arr[right];

            if(min > Math.abs(sum)){
                min = Math.abs(sum);
                res[0]=arr[left];
                res[1]=arr[right];

                if(sum==0) break;
            }

            if(sum>0) right--;
            else left++;

        }
        System.out.println(res[0]+" "+res[1]);

    }
}
