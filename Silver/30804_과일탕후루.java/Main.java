import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int [] nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }
        int kindCnt=0; //꼬지에 꽂힌 과일의 종류
        int[] fruit = new int[10]; //1~9까지의 과일
        int left=0;
        int max_len=0;
        for(int right=0; right<N; right++){//투포인터
            //right의 과일이 꽂힌 개수가 0이라면 과일의 종류 cnt를 +1
            if(fruit[nums[right]]==0) kindCnt++; 
            fruit[nums[right]]++; //해당과일이 몇 개 꽂혀있는지 카운트 한다.
            while (kindCnt>2) {//꼬치의 꽃힌 과일의 종류가 2이상이라면
                fruit[nums[left]]--; //왼쪽에 꽂힌 과일을 하나씩 제거하고
                //왼쪽의 꽃힌 과일의 개수가 0 이되면 과일의 종류 cnt를 -1
                if(fruit[nums[left]]==0) kindCnt--; 
                left++;//하나를 제거했으니 다음 과일로
            }
            //왼쪽에서 오른쪽 까지의 길이의 최대
            max_len=Integer.max(max_len, right-left+1);
        }
        System.out.println(max_len);
    }
}