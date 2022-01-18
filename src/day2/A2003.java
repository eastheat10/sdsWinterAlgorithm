package day2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A2003 {

    static int n, m, cnt = 0;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        input();
//        process();
        twoPointer();
        System.out.println(cnt);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void process() {
        int sum = 0;
        int r = 0;
        for (int l = 0; l < n; l++) {
            r = l;
            sum = 0;
            while (sum < m && r < n) {
                sum += arr[r++];
                if (sum == m) {
                    cnt++;
                    break;
                }
            }
        }
    }

    static void twoPointer() {
        int sum = arr[0];
        int l = 0, r = 0;
        while (r < n && l < n) {
            if (sum == m) {
                cnt++;
                sum -= arr[l++];
            } else if (sum < m) {
                if (++r >= n) break;
                sum += arr[r];
            } else {    // sum > m
                sum -= arr[l++];
            }
        }
    }

}
