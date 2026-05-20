import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class jol_1809_탑 {
    

    public static void main(String[] args) throws IOException{
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int [] arr = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            arr[i]=Integer.parseInt(st.nextToken());
        }
        Deque<Integer> stack = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=N; i++){
            while (!stack.isEmpty()&&arr[stack.peek()]<=arr[i]) {
                stack.pop();
            }
            if(stack.isEmpty()){
                sb.append(0).append(" ");
            }else{
                sb.append(stack.peek()).append(" ");
            }
            stack.push(i);

        }
        System.out.println(sb);
    }
}
