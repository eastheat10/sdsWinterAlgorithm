package day2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A1806 {
    static int n, s, length = Integer.MAX_VALUE;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        input();
        process();
        System.out.println(length);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());

        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void process() {
        int sum = 0;
        int r = 0;
        int l = 0;
        while (r < n && l < n) {
            if (sum >= s) {
                length = Math.min(length, (r - l));
                sum -= arr[l++];
            } else if (r == n) {
                break;
            } else {
                if (++r >= n) break;
                sum += arr[r];
            }
        }
        length = length < Integer.MAX_VALUE ? length : 0;
    }
}
