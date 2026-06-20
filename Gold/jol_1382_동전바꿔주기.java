import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class jol_1382_동전바꿔주기 {
    static class Coin implements Comparable<Coin>{
        int price, n;
        
        Coin(int price,int n){
            this.price = price;
            this.n = n;
        }        
        @Override
        public int compareTo(Coin o) {
            
            return this.price - o.price;
        }
    }

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        ArrayList<Coin> Coins = new ArrayList<>();
        for(int i=0; i<K; i++){
            st = new StringTokenizer(br.readLine());
            int price = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            Coins.add(new Coin(price, n));
        }
        Collections.sort(Coins);
        int dp[] = new int[T+1];
        dp[0]=1;
        for(Coin coin : Coins){
            int[] next = new int[T+1];
            for(int money=0; money<=T; money++){
                if(dp[money]==0) continue;

                for(int cnt=0; cnt<=coin.n; cnt++){
                    int nextMoney = money +coin.price*cnt;   
                    if(nextMoney>T) break;
                    next[nextMoney] +=dp[money];
                }
            }
            dp=next;
        }
        System.out.println(dp[T]);

    }
}
