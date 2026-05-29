import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class jol_1124_색종이_고 {
    


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        int [][] T = new int[101][101];
        
        for(int k=0; k<N; k++){
            st = new StringTokenizer(br.readLine());
            int x= Integer.parseInt(st.nextToken());
            int y= Integer.parseInt(st.nextToken());
            
            for(int i=x; i<x+10; i++){
                for(int j=y; j<y+10; j++){
                    T[i][j] =1;
                }
            }

        }
        int [] height = new int[101];
        int answer =0;

        for(int i=0; i<100; i++){
            for(int j=0; j<100; j++){
                if(T[i][j]==1){
                    height[j]++;
                }else{
                    height[j]=0;
                }
            }
            answer = Math.max(answer, getMaxRect(height));
        }
        System.out.println(answer);
        
    }

    static int getMaxRect(int [] height){
        Deque<Integer> stack = new ArrayDeque<>();
        int maxArea=0;

        for(int i=0; i<=100; i++){
            int cur_h = (i==100)?0 : height[i];

            while (!stack.isEmpty() && height[stack.peek()] > cur_h) {
                int h = height[stack.pop()];

                int w;
                if(stack.isEmpty()){
                    w = i;
                }else{
                    w = i -stack.peek() -1;
                }
                
                maxArea = Math.max(maxArea, h*w);
            }
            stack.push(i);
        }
        return maxArea;
    }
}
