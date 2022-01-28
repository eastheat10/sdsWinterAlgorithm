package day8;

import java.io.IOException;
import java.util.List;

public class A5719_거의_최단_경로 {

    static class Info implements Comparable<Info> {
        int node, distance;
        public Info(int node, int distance) {

        }

        @Override
        public int compareTo(Info info) {
            return Integer.compare(distance, info.distance);
        }
    }

    final static int INF = Integer.MAX_VALUE;
    static int n, m;

    static List<Info>[] map;
    static List<Integer>[] tracking;
    static boolean[][] isShortestPath;

    public static void main(String[] args) throws IOException {

    }

    static void input() throws IOException {

    }
}
