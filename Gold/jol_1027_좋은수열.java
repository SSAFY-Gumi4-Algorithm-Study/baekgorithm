import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class jol_1027_좋은수열 {

    static int N;
    static StringBuilder sb = new StringBuilder();
    static boolean finished = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        dfs(0);
    }

    static void dfs(int depth) {
        if (finished) return;

        if (depth == N) {
            System.out.println(sb.toString());
            finished = true;
            return;
        }

        for (char c = '1'; c <= '3'; c++) {
            sb.append(c);

            if (isGood()) {
                dfs(depth + 1);
            }

            sb.deleteCharAt(sb.length() - 1);
        }
    }

    static boolean isGood() {
        int len = sb.length();

        for (int size = 1; size <= len / 2; size++) {
            boolean same = true;

            for (int i = 0; i < size; i++) {
                char a = sb.charAt(len - 1 - i);
                char b = sb.charAt(len - 1 - size - i);

                if (a != b) {
                    same = false;
                    break;
                }
            }

            if (same) {
                return false;
            }
        }

        return true;
    }
}