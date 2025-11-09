import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static int[][] notebook;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        notebook = new int[N][M];
        
        // 각 스티커 처리
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            
            int[][] sticker = new int[R][C];
            for (int r = 0; r < R; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < C; c++) {
                    sticker[r][c] = Integer.parseInt(st.nextToken());
                }
            }
            
            // 4방향 회전 시도 (0도, 90도, 180도, 270도)
            for (int rotate = 0; rotate < 4; rotate++) {
                if (tryAttach(sticker)) {
                    break;
                }
                sticker = rotateSticker(sticker);
            }
        }
        
        // 노트북에 붙은 스티커 칸 수 계산
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (notebook[i][j] == 1) {
                    count++;
                }
            }
        }
        
        System.out.println(count);
    }
    
    // 스티커를 90도 회전
    static int[][] rotateSticker(int[][] sticker) {
        int R = sticker.length;
        int C = sticker[0].length;
        int[][] rotated = new int[C][R];
        
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                rotated[j][R - 1 - i] = sticker[i][j];
            }
        }
        
        return rotated;
    }
    
    // 스티커를 노트북에 붙일 수 있는지 확인하고 붙이기
    static boolean tryAttach(int[][] sticker) {
        int R = sticker.length;
        int C = sticker[0].length;
        
        // 노트북의 모든 위치에서 시도
        for (int i = 0; i <= N - R; i++) {
            for (int j = 0; j <= M - C; j++) {
                // (i, j) 위치에 스티커를 붙일 수 있는지 확인
                if (canAttach(sticker, i, j)) {
                    // 스티커 붙이기
                    attachSticker(sticker, i, j);
                    return true;
                }
            }
        }
        
        return false;
    }
    
    // 특정 위치에 스티커를 붙일 수 있는지 확인
    static boolean canAttach(int[][] sticker, int startR, int startC) {
        int R = sticker.length;
        int C = sticker[0].length;
        
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                // 스티커의 해당 부분이 1인 경우
                if (sticker[i][j] == 1) {
                    // 노트북의 해당 위치가 이미 1이면 붙일 수 없음
                    if (notebook[startR + i][startC + j] == 1) {
                        return false;
                    }
                }
            }
        }
        
        return true;
    }
    
    // 스티커를 노트북에 붙이기
    static void attachSticker(int[][] sticker, int startR, int startC) {
        int R = sticker.length;
        int C = sticker[0].length;
        
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (sticker[i][j] == 1) {
                    notebook[startR + i][startC + j] = 1;
                }
            }
        }
    }
}
