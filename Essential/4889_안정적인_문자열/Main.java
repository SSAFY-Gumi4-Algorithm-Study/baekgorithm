import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int index = 1;
        while (true) {
            String line = br.readLine();
            if (line.charAt(0) == '-') break; // 종료 조건
            
            int res = 0;
            Stack<Character> stack = new Stack<>();

            // 문자열의 모든 문자 처리
            for (char c : line.toCharArray()) {
                
                if (c == '{') {
                    // 여는 괄호는 push
                    stack.push(c);
                } else {
                    // 닫는 괄호가 등장한 경우
                    if (stack.isEmpty()) {
                        // 앞에 매칭될 '{' 가 없으면 '}' → '{' 로 변경 (연산 +1)
                        res++;
                        stack.push('{');
                    } else {
                        // 정상적인 매칭이므로 pop
                        stack.pop();
                    }
                }
            }

            // 처리 후 스택에는 '{'만 남아 있음
            // "{{" → "{ }" 로 바꾸는 데 1회 필요 → size / 2
            res += stack.size() / 2;

            // 결과 출력
            System.out.printf("%d. %d\n", index++, res);
        }
    }
}
