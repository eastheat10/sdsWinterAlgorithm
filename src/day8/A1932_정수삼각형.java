package day8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A1932_정수삼각형 {

    static int n;
    static int[][] triangle, dp;

    public static void main(String[] args) throws IOException {
        input();
        process();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        triangle = new int[n + 1][n + 1];
        dp = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int tmp = 1;
            while (st.hasMoreTokens()) {
                triangle[i][tmp++] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void process() {
        int max = Integer.MIN_VALUE;

        dp[1][1] = triangle[1][1];
        for (int i = 2; i <= n; i++) {
            dp[i][1] = dp[i - 1][1] + triangle[i][1];
            dp[i][i] = dp[i - 1][i - 1] + triangle[i][i];
        }

        for (int i = 3; i <= n; i++) {
            for (int j = 2; j <= i; j++) {
                dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + triangle[i][j];
            }
        }

        for (int i = 1; i <= n; i++)
            max= Math.max(max, dp[n][i]);

        System.out.println(max);
    }

}