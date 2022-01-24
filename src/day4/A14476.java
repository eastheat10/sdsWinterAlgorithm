package day4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A14476 {

    static int n;
    static int[] arr, left, right;

    public static void main(String[] args) throws IOException {
        input();
        process();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        left = new int[n];
        right = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void process() {
        left[0] = arr[0];
        right[n - 1] = arr[n - 1];

        for (int i = 1; i < n; i++) {
            left[i] = gcd(left[i - 1], arr[i]);
        }

        for (int i = n - 2; i >= 0; i--) {
            right[i] = gcd(right[i + 1], arr[i]);
        }

        int result = 0;
        int gcd = 0;

        for (int i = 1; i < n - 1; i++) {
            int g = gcd(left[i - 1], right[i + 1]);

            if (arr[i] % g != 0 && g > gcd) {
                result = arr[i];
                gcd = g;
            }
        }

        if (result == 0 && gcd == 0) {
            System.out.println(-1);
        } else {
            System.out.println(gcd + " " + result);
        }
    }

    static int gcd(int a, int b) {
        while (b != 0) {
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
    }
}
