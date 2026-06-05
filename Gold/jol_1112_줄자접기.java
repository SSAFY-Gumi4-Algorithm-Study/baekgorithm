import java.io.*;
import java.util.*;

public class jol_1112_줄자접기 {

    static int len;
    static int[][] pos = new int[3][2];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        len = Integer.parseInt(br.readLine()) * 2;

        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());

            pos[i][0] = Integer.parseInt(st.nextToken()) * 2;
            pos[i][1] = Integer.parseInt(st.nextToken()) * 2;
        }

        for (int color = 0; color < 3; color++) {
            fold(color);
        }

        System.out.printf("%.1f\n", len / 2.0);
    }

    static void fold(int color) {
        int a = pos[color][0];
        int b = pos[color][1];

        if (a == b) return;

        int fold = (a + b) / 2;

        int leftLen = fold;
        int rightLen = len - fold;

        boolean leftIsLonger = leftLen > rightLen;

        len = Math.max(leftLen, rightLen);

        for (int i = color + 1; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                int x = pos[i][j];

                if (leftIsLonger) {
                    if (x > fold) {
                        pos[i][j] = 2 * fold - x;
                    }
                } else {
                    if (x <= fold) {
                        pos[i][j] = fold - x;
                    } else {
                        pos[i][j] = x - fold;
                    }
                }
            }
        }
    }
}