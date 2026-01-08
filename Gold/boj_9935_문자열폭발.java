import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class boj_9935_문자열폭발 {

    public static void main(String[] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        String bomb = br.readLine();

        int line_len = line.length();
        int bomb_len = bomb.length();
        char last_bomb_char = bomb.charAt(bomb_len-1);
        
        Stack<Character> stack = new Stack<>();
        for(int i=0; i<line_len; i++){
            char c = line.charAt(i);
            stack.push(c);
            if(stack.size() >= bomb_len && c == last_bomb_char)
            {
                StringBuilder sb = new StringBuilder();
                for(int j=0; j<bomb_len; j++){
                    sb.append(stack.pop());
                    
                }
                String compare = sb.reverse().toString();
                if(!compare.equals(bomb)){
                    for(int j=0; j<bomb_len; j++){
                        stack.push(compare.charAt(j));
                        
                    }
                }
            }
        }
        if(stack.isEmpty()){
            System.out.println("FRULA");
        }else{
            StringBuilder sb = new StringBuilder();
            while (!stack.isEmpty()) {
                sb.append(stack.pop());
            }
            System.out.println(sb.reverse().toString());
        }
    }
}