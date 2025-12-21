import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_2166_다각형의면적 {

    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int arr[][] = new int[N+1][2];
        StringTokenizer st;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }
        arr[N][0] = arr[0][0];
        arr[N][1] = arr[0][1];
        long sum=0;

        for(int i=0; i<N; i++){
            sum += 1l*arr[i][0]*arr[(i+1)][1];
            sum -= 1l*arr[i][1]*arr[(i+1)][0];

        }

        sum=Math.abs(sum);
        System.out.printf("%.1f",1d*sum/2);
    }
}