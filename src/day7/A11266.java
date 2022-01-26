package day7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class A11266 {

    static int v, e;
    static int order = 0, answer = 0;
    static List<Integer>[] graph;
    static int[] searchOrder;
    static boolean[] visit;
    static StringBuffer sb = new StringBuffer();

    public static void main(String[] args) throws IOException {
        input();
        process();
        System.out.println(answer);
        System.out.println(sb);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        searchOrder = new int[v + 1];
        visit = new boolean[v + 1];
        graph = new ArrayList[v + 1];
        for (int i = 1; i <= v; i++) {
            graph[i] = new ArrayList<>();
        }

        int a, b;
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            graph[b].add(a);
        }
    }

    static void process() {
        for (int i = 1; i <= v; i++) {
            if (searchOrder[i] == 0) {
                dfs(i, true);
            }
        }

        for (int i = 1; i <= v; i++) {
            if (visit[i]) {
                answer++;
                sb.append(i + " ");
            }
        }
    }

    static int dfs(int now, boolean isRoot) {
        order++;    // 방문 순서
        searchOrder[now] = order;
        int min = order;    // 지금 정점 이후 도달할 수 있는 모든 정점들의 탐색 순서 중 가장 작은 값

        int child = 0;  // 자식의 숫자 Root 일 경우 단절점 판단

        for (Integer next : graph[now]) {
            if (searchOrder[next] == 0) {
                child++;

                // 자식 정점 중 밤문 순서가 가장 빠른 값.
                // 이 때, 특정 자식 정점이 여러 개의 정점을 타고 올라가 1번 정점 까지 갈 수 있음을 주의
                int low = dfs(next, false); // 현재 정점의 다음에 방문할 모든 정점에 대해서 도달할 수 있는 최소의 order 순서
                // Root 가 아니고, 내 다음에 방문할 정점의 순서가 모두 나보다 클 경우에 지금 위치는 단절점이다.

                if (!isRoot && low >= searchOrder[now]) {
                    visit[now] = true;
                }
                min = Math.min(min, low);
            } else {
                // 자식 정점이 이미 방문한 경우
                min = Math.min(min, searchOrder[next]);
            }
        }

        if (isRoot && child >= 2) {
            visit[now] = true;
        }
        return min;
    }

}
