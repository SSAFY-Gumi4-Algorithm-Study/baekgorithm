import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class jol_1141_불쾌한날 {
    static class Cow{
        int h,w;
        Cow(int h, int w){
            this.h=h;
            this.w=w;
        }
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Cow[] Cows = new Cow[N];
        for(int i=0; i<N ;i++){
            Cows[i] = new Cow(Integer.parseInt(br.readLine()), 0);
        }

        Stack<Cow> stack  = new Stack<>();
        long sum=0;
        for(int i=N-1; i>=0; i--){
            
            while(!stack.isEmpty() && stack.peek().h < Cows[i].h){
                Cows[i].w+=stack.peek().w+1;
                stack.pop();
            }
            sum+=Cows[i].w;
            stack.push(Cows[i]);
        }
        System.out.println(sum);
    }
}
