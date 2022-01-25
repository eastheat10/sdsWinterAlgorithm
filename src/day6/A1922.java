package day6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Edge implements Comparable<Edge> {

    int start, end, cost;

    public Edge(int s, int e, int c) {
        this.start = s;
        this.end = e;
        this.cost = c;
    }

    @Override
    public int compareTo(Edge e) {
        return this.cost - e.cost;
    }
}

class Graph {
    List<Edge>[] edge;

    public Graph(int v) {
        edge = new LinkedList[v + 1];
        for (int i = 0; i <= v; i++) {
            edge[i] = new LinkedList<>();
        }
    }

    public void addEdge(int start, int end, int cost) {
        edge[start].add(new Edge(start, end, cost));
        edge[end].add(new Edge(end, start, cost));
    }
}

public class A1922 {

    static int n, m;
    static List<Edge> list = new ArrayList<>();
    static Graph graph;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        input();
        prim();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        visit = new boolean[n + 1];
        graph = new Graph(n + 1);
        StringTokenizer st;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.addEdge(a, b, c);
        }
    }

    static void prim() {
        int cost = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        Queue<Integer> q = new LinkedList<>();
        q.offer(1);

        while (!q.isEmpty()) {
            int start = q.poll();
            visit[start] = true;

            for (Edge edge : graph.edge[start]) {
                if (!visit[edge.end]) {
                    pq.offer(edge);
                }
            }

            while (!pq.isEmpty()) {
                Edge e = pq.poll();

                if (visit[e.end]) continue;

                q.offer(e.end);
                visit[e.end] = true;
                list.add(e);
                cost += e.cost;
                break;
            }
        }
        System.out.println(cost);
    }
}
