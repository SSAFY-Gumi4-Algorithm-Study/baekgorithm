import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class jol_1328_빌딩 {

    

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());
        Deque<Integer> stack = new ArrayDeque<>();
        int [] arr = new int[N+1];
        int [] res = new int[N+1];
        for(int i=1; i<=N; i++){
            int height = Integer.parseInt(br.readLine().trim());
            arr[i] = height;
        }
        stack.push(N);

        for(int i=N-1; i>=1; i--){
            
            while(!stack.isEmpty()&& arr[stack.peek()] <=arr[i]){
                stack.pop();
            }
            if(!stack.isEmpty()){

                res[i] = stack.peek();
            }else{
                res[i]=0;
            }
            stack.push(i);
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= N; i++) {
            sb.append(res[i]).append('\n');
        }

        System.out.print(sb);
    }
}