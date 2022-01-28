package day8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A11660_구간합구하기5 {

    static int n, m;
    static int[][] arr, dp;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n + 1][n + 1];
        dp = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        getTable();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            sb.append(getResult(x1, y1, x2, y2)).append('\n');
        }

        System.out.println(sb);
    }

    static void getTable() {
        dp[1][1] = arr[1][1];
        for (int i = 2; i <= n; i++) {
            dp[1][i] = dp[1][i - 1] + arr[1][i];
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = dp[i][j - 1] + (dp[i - 1][j] - dp[i - 1][j - 1]) + arr[i][j];
            }
        }
    }

    static int getResult(int x1, int y1, int x2, int y2) {
        return dp[x2][y2] - (dp[x2][y1 - 1] + dp[x1 - 1][y2]) + dp[x1 - 1][y1 - 1];
    }

}
