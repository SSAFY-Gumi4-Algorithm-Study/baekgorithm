import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class boj_17140_이차원배열과연산 {

    static int R=3;
    static int C=3;
    static int [][]arr;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        arr = new int[100][100];
        for(int i=0; i<3; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<3; j++){
                arr[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        int t=0;
        while (true) {
            if(arr[r-1][c-1]==k){
                System.out.println(t);
                break;
            }
            if(t>100){
                System.out.println(-1);
                break;
            }

            if(R>=C){
                func_R();
            }
            else{
                func_C();
            }
            t++;
            
        }

    }

    static void func_R(){
        int max_c=0;
        for(int i=0; i<R; i++){
            Map<Integer,Integer> dict = new HashMap<>();
            for(int n : arr[i]){
                // dict.compute(n, (k,v)-> (v==null) ? 1 : v+1);
                if(n==0) continue;
                dict.merge(n, 1, Integer::sum);
            }
            List<Map.Entry<Integer,Integer>> list = new ArrayList<>(dict.entrySet());

            list.sort((a,b)->{
                if(a.getValue().equals( b.getValue())){
                    return a.getKey()-b.getKey();
                }else{
                    return b.getValue()-a.getValue();
                }
            });

            int idx =0;
            for(Map.Entry<Integer,Integer> m : list){
                if(idx==100) break;
                arr[i][idx++] = m.getKey();
                arr[i][idx++] = m.getValue();
            }

            if(idx>max_c){
                max_c=idx;
            }
        }
        C = max_c;
    }

    static void func_C() {
    int max_r = 0;
    
    for (int j = 0; j < C; j++) { // 열 기준 반복
        Map<Integer, Integer> dict = new HashMap<>();
        
        // 1️⃣ 열 단위로 값 수집
        for (int i = 0; i < R; i++) {
            int n = arr[i][j];
            if (n == 0) continue; // 0은 무시 (BOJ 17140 기준)
            dict.merge(n, 1, Integer::sum);
        }

        // 2️⃣ (숫자, 빈도) 리스트화
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(dict.entrySet());

        // 3️⃣ 정렬: value 내림차순, value 같으면 key 오름차순
        list.sort((a,b)->{
            if(a.getValue().equals( b.getValue())){
                return a.getKey()-b.getKey();
            }else{
                return b.getValue()-a.getValue();
            }
        });

        // 4️⃣ 정렬된 결과를 세로 방향으로 채우기
        int idx = 0;
        for (Map.Entry<Integer, Integer> m : list) {
            if (idx >= 100) break;
            arr[idx++][j] = m.getKey();
            arr[idx++][j] = m.getValue();
        }

        // 최대 행 길이(세로 길이) 갱신
        max_r = Math.max(max_r, idx);
    }

    // 5️⃣ 새로운 배열로 교체
    
    R = max_r; // 이제 행 개수(R)가 늘어남
}

}