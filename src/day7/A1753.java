package day7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge> {
    int to, cost;

    public Edge(int to, int cost) {
        this.to = to;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge e) {
        return this.cost - e.cost;
    }
}

public class A1753 {

    static int v, e, k;
    static List<Edge>[] list;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        input();
        process();
        print();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        k = Integer.parseInt(br.readLine());

        dist = new int[v + 1];
        list = new List[v + 1];
        for (int i = 0; i <= v; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            list[u].add(new Edge(v, w));
        }
    }

    static void process() {
        for (int i = 0; i <= v; i++)
            dist[i] = Integer.MAX_VALUE;

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(k, 0));
        dist[k] = 0;

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();

            for (Edge e : list[edge.to]) {
                if (dist[edge.to] + e.cost >= dist[e.to]) continue;
                dist[e.to] = dist[edge.to] + e.cost;
                pq.offer(new Edge(e.to, dist[e.to]));
            }
        }
    }

    static void print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= v; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                sb.append("INF");
            } else {
                sb.append(dist[i]);
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }

}