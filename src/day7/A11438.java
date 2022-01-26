package day7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class A11438 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st = null;

    static int n, m, h;
    static List<Integer>[] tree;
    static int[] depth;
    static int[][] parents;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        getHeight();

        parents = new int[h + 1][n + 1];
        depth = new int[n + 1];
        tree = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            tree[x].add(y);
            tree[y].add(x);
        }

        bfs(1);
        makeSparseTable();

        m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            sb.append(lca(a, b)).append('\n');
        }
        System.out.println(sb);
    }

    static void getHeight() {
        h = 0;
        for (int i = 1; i < n; i *= 2) {
            h++;
        }
    }

    static void makeSparseTable() {
        for (int i = 1; i <= h; i++) {
            for (int j = 1; j <= n; j++) {
                parents[i][j] = parents[i - 1][parents[i - 1][j]];
            }
        }
    }

    static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        depth[start] = 1;
        q.offer(start);

        while (!q.isEmpty()) {
            int now = q.poll();
            for (Integer next : tree[now]) {
                if (depth[next] != 0) continue; // 탐색한 지점 건너뛰기
                depth[next] = depth[now] + 1;
                parents[0][next] = now;
                q.offer(next);
            }
        }

    }

    static int lca(int a, int b) {
        // a가 더 깊음을 가정
        if (depth[a] < depth[b]) return lca(b, a);

        // 높이 맞추기
        for (int i = h; i >= 0; i--) {
            if (((depth[a] - depth[b]) >= (1 << i)))
                a = parents[i][a];
        }

        // 같은지?
        if (a == b) return a;

        for (int i = h; i >= 0; i--) {
            if (parents[i][a] != parents[i][b]) {
                a = parents[i][a];
                b = parents[i][b];
            }
        }

        return parents[0][a];
    }

}
