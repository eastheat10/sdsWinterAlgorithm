package day2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class A2805 {

    static int n, m, height = 0;
    static long[] arr;

    public static void main(String[] args) throws IOException {
        input();
        process();
        System.out.println(height);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new long[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
    }

    static void process() {
        int l = 0, r = Integer.MAX_VALUE;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (getSum(mid) < m) {
                r = mid - 1;
            } else {
                l = mid + 1;
                height = mid;
            }
        }
    }

    static long getSum(long height) {
        if (height > arr[n - 1]) {
            return 0;
        }
        long sum = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] < height) break;
            sum += (arr[i] - height);
        }
        return sum;
    }

}
