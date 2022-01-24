package day5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class A1837 {

    static String p;
    static int k;
    static boolean[] notPrime;
    static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        input();
        getPrime();
        for (Integer prime : list) {
            if (prime >= k) {
                break;
            }
            if (isBad(prime)) {
                System.out.println("BAD " + prime);
                return;
            }
        }
        System.out.println("GOOD");
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        p = st.nextToken();
        k = Integer.parseInt(st.nextToken());
        notPrime = new boolean[k + 1];
    }

    static void getPrime() {
        notPrime[0] = notPrime[1] = true;
        for (int i = 2; i <= k; i++) {
            if (notPrime[i]) continue;
            list.add(i);
            for (int j = i; j <= k; j += i) {
                notPrime[j] = true;
            }
        }
    }

    static boolean isBad(int x) {
        int r = 0;
        for (int i = 0; i < p.length(); i++) {
            r = (r * 10 + (p.charAt(i) - '0')) % x;
        }
        return r == 0;
    }
}
